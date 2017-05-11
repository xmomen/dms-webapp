package com.xmomen.module.wx.module.cart.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmomen.framework.mybatis.dao.MybatisDao;
import com.xmomen.module.product.model.ProductModel;
import com.xmomen.module.product.model.ProductQuery;
import com.xmomen.module.product.service.ProductService;
import com.xmomen.module.wx.module.cart.entity.TbCartItem;
import com.xmomen.module.wx.module.cart.mapper.CartMapper;
import com.xmomen.module.wx.module.cart.model.CartItemModel;
import com.xmomen.module.wx.module.cart.model.CartItemQuery;
import com.xmomen.module.wx.module.cart.model.CartMetadata;
import com.xmomen.module.wx.module.cart.model.CartModel;
import com.xmomen.module.wx.util.Constant;

@Service
public class CartService {

	private ConcurrentHashMap<String, CartModel> cartCache = new ConcurrentHashMap<String, CartModel>();
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private MybatisDao mybatisDao;
	
	public List<CartItemModel> getCartItemsByUserToken(String userToken) {
		CartItemQuery cartItemQuery = new CartItemQuery();
		cartItemQuery.setUserToken(userToken);
		return mybatisDao.getSqlSessionTemplate().selectList(CartMapper.CART_MAPPER_NAMESPACE + "getCartItemList", cartItemQuery);
	}
		
	public List<ProductModel> getProductsInCart(ProductQuery productQuery) {
		List<CartItemModel> items = this.getCartItems(productQuery.getMemberCode());
		Map<String, Integer> itemNumberMap = new HashMap<String, Integer>();
		for(CartItemModel cartItem: items) {
			itemNumberMap.put(String.valueOf(cartItem.getItemId()), cartItem.getItemQty());
		}
		ArrayList<Integer> productIds = new ArrayList<Integer>();
		for(CartItemModel item: items) {
			productIds.add(item.getItemId());
		}
		productQuery.setProductIds(productIds);
		List<ProductModel> products = productService.getProducts(productIds);
		
		for(ProductModel product: products) {
			String itemId = String.valueOf(product.getId());
			product.setItemQty(itemNumberMap.get(itemId));
		}
		return products;
	}
	
	public void removeItems(String userToken, List<Integer> itemIds) {
		CartModel cartModel = cartCache.get(userToken);
		if(CollectionUtils.isEmpty(itemIds)) return;
		if(cartModel != null && !CollectionUtils.isEmpty(cartModel.getItems())) {
			List<CartMetadata> cartItems = cartModel.getItems();
			boolean changed = false;
			for(CartMetadata item: cartItems) {
				if(itemIds.contains(item.getItemId())) {
					item.setItemQty(0);
					changed = true;
				}
			}
			if(changed) cartModel.setStatus(Constant.DIRTY);
			this.syncToDB(userToken);
		}
	}

	public List<CartItemModel> getCartItems(String userToken, boolean alwaysSync) {
		CartModel cartModel = cartCache.get(userToken);
		ArrayList<CartItemModel> cartItems = new ArrayList<CartItemModel>();
		boolean needCreate = false;
		
		// 从数据库中那购物车信息,如果内存中没有则合并到内存中，否则以内存中为主(数目)
		List<CartItemModel> persistentCartItems = null;
		if(alwaysSync) {
			persistentCartItems = this.getCartItemsByUserToken(userToken);
		} else {
			if(cartModel == null || CollectionUtils.isEmpty(cartModel.getItems())) {
				persistentCartItems = this.getCartItemsByUserToken(userToken);
			}
		}
		CopyOnWriteArrayList<CartMetadata> cartItemMetas = null;
		Map<String, CartMetadata> memoryCartMap = new HashMap<String, CartMetadata>();
		if(cartModel != null && cartModel.getItems() != null) {
			for(CartMetadata cartMetadata: cartModel.getItems()) {
				memoryCartMap.put(String.valueOf(cartMetadata.getItemId()), cartMetadata);
			}
		}
		if(cartModel != null) {
			cartItemMetas = cartModel.getItems();
		}
		if(!CollectionUtils.isEmpty(persistentCartItems)) {
			//同步数据到内存中
			//TODO 当数据量很大的时候也要同步进内存吗？恶意大量数据可能会造成内存问题
			for(CartItemModel cartItem: persistentCartItems) {
				String itemId = String.valueOf(cartItem.getItemId());
				if(!memoryCartMap.containsKey(itemId)) {
					CartMetadata pCartItem = this.newCartMetadata(userToken, cartItem.getItemId(), cartItem.getItemQty());
					if(cartItemMetas == null) {
						cartItemMetas = new CopyOnWriteArrayList<CartMetadata>();
						if(cartModel == null) {
							needCreate = true;
						} else {
							cartModel.setItems(cartItemMetas);
						}
					}
					cartItemMetas.add(pCartItem);
				}
			}
		}
		if(needCreate) {
			CartModel newCartModel = new CartModel();
			newCartModel.setStatus(Constant.CLEAN);
			newCartModel.setSyncTime(new Timestamp(new Date().getTime()));
			newCartModel.setUserToken(userToken);
			newCartModel.setItems(cartItemMetas);
			cartCache.put(userToken, newCartModel);
		}
		if(cartItemMetas != null) {
			for(CartMetadata metaData: cartItemMetas) {
				if(!Constant.DELETE.equalsIgnoreCase(metaData.getStatus())) {
					CartItemModel cartItem = new CartItemModel();
					cartItem.setItemId(metaData.getItemId());
					cartItem.setItemQty(metaData.getItemQty());
					cartItems.add(cartItem);
				}
			}
		}
		return cartItems;
	}

	public List<CartItemModel> getCartItems(String userToken) {
		return getCartItems(userToken, false);
	}
	public void modify(CartModel newCartModel) throws Exception {
		String userToken = newCartModel.getUserToken();
		if(StringUtils.isEmpty(userToken)) throw new Exception("userToken不能为空!");
		CartModel sourceCart = cartCache.get(userToken);
		if(CollectionUtils.isEmpty(newCartModel.getItems())) {
			// 如果购物车被清空
			if(sourceCart != null) {
				if(this.compareAndUpdate(sourceCart, newCartModel)) {
					sourceCart.setStatus(Constant.DIRTY);
				};
			} 
			return;
		}
		if(sourceCart == null) {
			sourceCart = new CartModel();
			sourceCart.setUserToken(newCartModel.getUserToken());
			sourceCart.setItems(new CopyOnWriteArrayList<CartMetadata>());
			cartCache.put(userToken, sourceCart);
		}
		if(this.compareAndUpdate(sourceCart, newCartModel)) {
			sourceCart.setStatus(Constant.DIRTY);
		}
	}
	
	public void change(String userToken, Integer itemId, Integer number) {
		CartModel sourceCart = cartCache.get(userToken);
		if(number == null) {
			Integer newNumber = 1;
			if(sourceCart != null && !CollectionUtils.isEmpty(sourceCart.getItems())) {
				List<CartMetadata> cartItems = sourceCart.getItems();
				for(CartMetadata cartItem: cartItems) {
					if(cartItem.getItemId().equals(itemId)) {
						newNumber += cartItem.getItemQty();
						break;
					}
				}
			}
			this.change(userToken, itemId, newNumber);
		} else if(number >= 0) {
			if(sourceCart == null) {
				CartModel cartModel = new CartModel();
				cartModel.setUserToken(userToken);
				CopyOnWriteArrayList<CartMetadata> items = new CopyOnWriteArrayList<CartMetadata>();
				cartModel.setItems(items);
				CartMetadata metadata = this.newCartMetadata(userToken, itemId, number);
				items.add(metadata);
				cartModel.setStatus(Constant.DIRTY);
				cartCache.put(userToken, cartModel);
			} else {
				CopyOnWriteArrayList<CartMetadata> items = sourceCart.getItems();
				if(items == null) {
					items = new CopyOnWriteArrayList<CartMetadata>();
					sourceCart.setItems(items);
				}
				boolean newAdd = true;
				boolean updated = false;
				for(CartMetadata item: items) {
					if(item.getItemId().equals(itemId)) {
						newAdd = false;
						if(number >= 0) {
							updated = item.setItemQty(number);
						}
					}
				}
				if(newAdd) {
					CartMetadata metadata = this.newCartMetadata(userToken, itemId, number);
					if(metadata != null) {
						updated = items.add(metadata);
					}
				}
				if(updated) {
					sourceCart.setStatus(Constant.DIRTY);
				}
			}
		}
	}
	
	/**
	 * @param cartModel 缓存中的原始购物车信息
	 * @param newCartModel 需要更新的购物车信息
	 * @return
	 */
	private Boolean compareAndUpdate(CartModel cartModel, CartModel newCartModel) {
		if(cartModel == null || newCartModel == null) return Boolean.FALSE;
		if(cartModel.getItems() == null) {
			cartModel.setItems(new CopyOnWriteArrayList<CartMetadata>());
		}
		CopyOnWriteArrayList<CartMetadata> sourceItems = cartModel.getItems();
		CopyOnWriteArrayList<CartMetadata> newItems = newCartModel.getItems() == null ? new CopyOnWriteArrayList<CartMetadata>() : newCartModel.getItems();
		Map<String, Integer> sourceItemMap = new HashMap<String,Integer>();
		Map<String, CartMetadata> sourceItemModelMap = new HashMap<String,CartMetadata>();
		Boolean updated = Boolean.FALSE;
		for(CartMetadata item: sourceItems) {
			String itemId = String.valueOf(item.getItemId());
			sourceItemMap.put(itemId, item.getItemQty());
			sourceItemModelMap.put(itemId, item);
		}
		Set<String> itemIds = new HashSet<String>();
		for(CartMetadata item: newItems) {
			itemIds.add(String.valueOf(item.getItemId()));
			String itemId = String.valueOf(item.getItemId());
			CartMetadata sourceItem = sourceItemModelMap.get(itemId);
			if(sourceItem == null) {
				sourceItem = this.newCartMetadata(newCartModel.getUserToken(), item.getItemId(), item.getItemQty());
				if(sourceItem != null) {
					updated = sourceItems.add(sourceItem);
				}
			} else {
				updated = sourceItem.setItemQty(item.getItemQty());
			}
		}
		// 再检查哪些物品被删除了
		for(CartMetadata sourceItem : sourceItems) {
			if(!itemIds.contains(String.valueOf(sourceItem.getItemId()))) {
				updated = sourceItem.setItemQty(0);
			}
		}
		return updated;
	}
	
	public CartMetadata newCartMetadata(String userToken, Integer itemId, Integer number) {
		if(number == null || number <= 0) return null;
		CartMetadata metadata = new CartMetadata();
		metadata.setUserToken(userToken);
		metadata.setItemId(itemId);
		metadata.setItemQty(number);
		return metadata;
	}

	
	/**
	 * 同步当前用户的购物车信息到购物车
	 * @param userToken
	 */
	public void syncToDB(String userToken) {
		if(StringUtils.isEmpty(userToken)) return;
		CartModel cartModel = cartCache.get(userToken);
		if(cartModel != null && Constant.DIRTY.equalsIgnoreCase(cartModel.getStatus())) {
			List<CartMetadata> cartMetadatas = cartModel.getItems();
			List<CartMetadata> beRemovedList = new ArrayList<CartMetadata>();
			List<CartMetadata> beUpdatedList = new ArrayList<CartMetadata>();
			for(CartMetadata cartMetadata: cartMetadatas) {
				if(Constant.DELETE.equalsIgnoreCase(cartMetadata.getStatus())) {
					beRemovedList.add(cartMetadata);
				} else if(Constant.MODIFY.equalsIgnoreCase(cartMetadata.getStatus())) {
					beUpdatedList.add(cartMetadata);
				}
			}
			// 删除标记为删除的物品,然后再从内存中删除
			if(beRemovedList.size() > 0) {
				List<Integer> itemIds = new ArrayList<Integer>();
				for(CartMetadata cartItem: beRemovedList){
					itemIds.add(cartItem.getItemId());
				}
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("userToken", userToken);
				params.put("itemIds", itemIds);
				mybatisDao.getSqlSessionTemplate().delete(CartMapper.CART_MAPPER_NAMESPACE + "removeCartItems", params);
				cartMetadatas.removeAll(beRemovedList);
			}
			
			// 同步更新字段的DB，然后将状态设置为clean
			for(CartMetadata updatedCartItem: beUpdatedList) {
				// UPDATE to DB(saveOrUpdate)
				CartItemQuery cartItemQuery = new CartItemQuery();
				cartItemQuery.setUserToken(userToken);
				cartItemQuery.setItemId(updatedCartItem.getItemId());
				List<CartItemModel> persistentCartItems =  mybatisDao.getSqlSessionTemplate().selectList(CartMapper.CART_MAPPER_NAMESPACE + "getCartItemList", cartItemQuery);
				TbCartItem tbCartItem = new TbCartItem();
				tbCartItem.setItemId(updatedCartItem.getItemId());
				tbCartItem.setUserToken(updatedCartItem.getUserToken());
				tbCartItem.setItemNumber(updatedCartItem.getItemQty());
				if(!CollectionUtils.isEmpty(persistentCartItems)) {
					tbCartItem.setId(persistentCartItems.get(0).getId());
				}
				mybatisDao.getSqlSessionTemplate().insert(CartMapper.CART_MAPPER_NAMESPACE + "saveOrUpdateItem", tbCartItem);
				updatedCartItem.setStatus(Constant.CLEAN);
				updatedCartItem.setUpdateTime(null);
			}
			cartModel.setStatus(Constant.CLEAN);
			cartModel.setSyncTime(new Timestamp(new Date().getTime()));
		}
	}
	
	/**
	 * 同步内存中的数据到数据库
	 */
	public void batchSyncToDB() {
		//TODO 
	}
	
	public void copyCartInfo(String oldMemberId, String newMemberId) {
		if(StringUtils.isEmpty(oldMemberId) || StringUtils.isEmpty(newMemberId)) {
			throw new IllegalArgumentException("memberId不能为空. oldMemberId:" + oldMemberId + ", newMemberId:" + newMemberId);
		}
		CartModel cartInfo = cartCache.get(oldMemberId);
		if(cartInfo != null) {
			cartInfo.setUserToken(newMemberId);
			cartCache.put(newMemberId, cartInfo);
			cartCache.remove(oldMemberId);
		}
		updateCartOwner(oldMemberId, newMemberId);
	}
	
	public void updateCartOwner(String oldMemberId, String newMemberId) {
		Map<String, String> info = new HashMap<String, String>();
		info.put("oldMemberId", oldMemberId);
		info.put("newMemberId", newMemberId);
		mybatisDao.getSqlSessionTemplate().update(CartMapper.CART_MAPPER_NAMESPACE + "copyCartItems", info);
	}
}

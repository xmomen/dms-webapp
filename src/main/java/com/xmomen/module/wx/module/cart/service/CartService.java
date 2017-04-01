package com.xmomen.module.wx.module.cart.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmomen.module.product.model.ProductModel;
import com.xmomen.module.product.model.ProductQuery;
import com.xmomen.module.product.service.ProductService;
import com.xmomen.module.wx.module.cart.model.CartItemModel;
import com.xmomen.module.wx.module.cart.model.CartMetadata;
import com.xmomen.module.wx.module.cart.model.CartModel;
import com.xmomen.module.wx.util.Constant;

@Service
public class CartService {

	private ConcurrentHashMap<String, CartModel> mapCache = new ConcurrentHashMap<String, CartModel>();
	
	@Autowired
	private ProductService productService;
	
	public List<ProductModel> getProductsInCart(ProductQuery productQuery) {
		List<CartItemModel> items = this.getCartItems(productQuery.getMemberCode());
		Map<String, Integer> itemNumberMap = new HashMap<String, Integer>();
		for(CartItemModel cartItem: items) {
			itemNumberMap.put(String.valueOf(cartItem.getItemId()), cartItem.getItemNumber());
		}
		List<Integer> productIds = new ArrayList<Integer>();
		for(CartItemModel item: items) {
			productIds.add(item.getItemId());
		}
		productQuery.setProductIds(productIds);
		List<ProductModel> products = productService.getProductsInCart(productQuery);
		
		for(ProductModel product: products) {
			String itemId = String.valueOf(product.getId());
			product.setItemNumber(itemNumberMap.get(itemId));
		}
		return products;
	}

	public List<CartItemModel> getCartItems(String userToken) {
		CartModel cartModel = mapCache.get(userToken);
		List<CartItemModel> cartItems = new ArrayList<CartItemModel>();
		if(cartModel != null) {
			List<CartMetadata> cartItemMetas = cartModel.getItems();
			//TODO 从数据库中那购物车信息,如果内存中没有并且没有标注为DELETE,则合并到内存中
			if(cartItemMetas != null) {
				for(CartMetadata metaData: cartItemMetas) {
					if(!Constant.DELETE.equalsIgnoreCase(metaData.getStatus())) {
						CartItemModel cartItem = new CartItemModel();
						cartItem.setItemId(metaData.getItemId());
						cartItem.setItemNumber(metaData.getItemNumber());
						cartItems.add(cartItem);
					}
				}
			}
		}
		return cartItems;
	}

	public void modify(CartModel newCartModel) {
		String userToken = newCartModel.getUserToken();
		CartModel sourceCart = mapCache.get(userToken);
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
			sourceCart.setItems(new ArrayList<CartMetadata>());
			mapCache.put(userToken, sourceCart);
		}
		if(this.compareAndUpdate(sourceCart, newCartModel)) {
			sourceCart.setStatus(Constant.DIRTY);
		}
	}
	
	public void change(String userToken, Integer itemId, Integer number) {
		CartModel sourceCart = mapCache.get(userToken);
		if(number >= 0) {
			if(sourceCart == null) {
				CartModel cartModel = new CartModel();
				List<CartMetadata> items = new ArrayList<CartMetadata>();
				cartModel.setItems(items);
				CartMetadata metadata = this.newCartMetadata(itemId, number);
				items.add(metadata);
				cartModel.setStatus(Constant.DIRTY);
				mapCache.put(userToken, cartModel);
			} else {
				List<CartMetadata> items = sourceCart.getItems();
				if(CollectionUtils.isEmpty(items)) {
					items = new ArrayList<CartMetadata>();
					sourceCart.setItems(items);
				}
				boolean newAdd = true;
				boolean updated = false;
				for(CartMetadata item: items) {
					if(item.getItemId().equals(itemId)) {
						newAdd = false;
						if(number >= 0) {
							updated = item.setItemNumber(number);
						}
					}
				}
				if(newAdd) {
					CartMetadata metadata = this.newCartMetadata(itemId, number);
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
		if(cartModel == null || cartModel == null) return Boolean.FALSE;
		List<CartMetadata> sourceItems = cartModel.getItems();
		List<CartMetadata> newItems = newCartModel.getItems() == null ? new ArrayList<CartMetadata>() : newCartModel.getItems();
		Map<String, Integer> sourceItemMap = new HashMap<String,Integer>();
		Map<String, CartMetadata> sourceItemModelMap = new HashMap<String,CartMetadata>();
		Boolean updated = Boolean.FALSE;
		for(CartMetadata item: sourceItems) {
			String itemId = String.valueOf(item.getItemId());
			sourceItemMap.put(itemId, item.getItemNumber());
			sourceItemModelMap.put(itemId, item);
		}
		Set<String> itemIds = new HashSet<String>();
		for(CartMetadata item: newItems) {
			itemIds.add(String.valueOf(item.getItemId()));
			String itemId = String.valueOf(item.getItemId());
			CartMetadata sourceItem = sourceItemModelMap.get(itemId);
			if(sourceItem == null) {
				sourceItem = this.newCartMetadata(item.getItemId(), item.getItemNumber());
				if(sourceItem != null) {
					updated = sourceItems.add(sourceItem);
				}
			} else {
				updated = sourceItem.setItemNumber(item.getItemNumber());
			}
		}
		// 再检查哪些物品被删除了
		for(CartMetadata sourceItem : sourceItems) {
			if(!itemIds.contains(String.valueOf(sourceItem.getItemId()))) {
				updated = sourceItem.setItemNumber(0);
			}
		}
		return updated;
	}
	
	public CartMetadata newCartMetadata(Integer itemId, Integer number) {
		if(number <= 0) return null;
		CartMetadata metadata = new CartMetadata();
		metadata.setItemId(itemId);
		metadata.setItemNumber(number);
		return metadata;
	}
}

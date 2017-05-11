package com.xmomen.module.product.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.xmomen.module.resource.service.ResourceUtilsService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.xmomen.framework.mybatis.dao.MybatisDao;
import com.xmomen.framework.mybatis.page.Page;
import com.xmomen.module.product.mapper.ProductMapper;
import com.xmomen.module.product.model.ProductModel;
import com.xmomen.module.product.model.ProductQuery;
import com.xmomen.module.product.service.ProductService;
import com.xmomen.module.resource.service.ResourceService;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    MybatisDao mybatisDao;

    @SuppressWarnings("unchecked")
    @Override
    public Page<ProductModel> getProductList(ProductQuery productQuery, Integer limit, Integer offset) {
        Page<ProductModel> pageModel = (Page<ProductModel>) mybatisDao.selectPage(ProductMapper.ProductMapperNameSpace + "getProductList", productQuery, limit, offset);
        List<ProductModel> products = pageModel.getResult();
        if (products != null) {
            for (ProductModel product : products) {
            	product.setBasePrice(null);//隐藏商品原价
                if (StringUtils.isEmpty(product.getPicUrl())) {
                    product.setPicUrl(ResourceUtilsService.getDefaultPicPath());
                }
                else {
                    product.setPicUrl(ResourceUtilsService.getWholeHttpPath(product.getPicUrl()));
                }
            }
        }
        return pageModel;
    }

    @Override
    public ProductModel getDetailById(Integer id) {
        List<ProductModel> products = mybatisDao.getSqlSessionTemplate().selectList(ProductMapper.ProductMapperNameSpace + "getProductDetail", id);
        List<String> picUrls = new ArrayList<String>();
        String defaultPicUrl = null;
        if (products != null && !products.isEmpty()) {
            for (ProductModel product : products) {
                if (!StringUtils.isEmpty(product.getPicUrl())) {
                    if (product.getIsDefaultPath()) {
                        defaultPicUrl = product.getPicUrl();
                    }
                    //else {
                        picUrls.add(ResourceUtilsService.getWholeHttpPath(product.getPicUrl()));
                    //}
                }
            }
            ProductModel detail = products.get(0);
            if(picUrls.isEmpty()) {
            	picUrls.add(ResourceUtilsService.getDefaultPicPath());
            }
            detail.setPicUrls(picUrls);
            detail.setBasePrice(null);//隐藏商品原价
            if (defaultPicUrl == null) {
                if (StringUtils.isEmpty(detail.getPicUrl())) {
                    detail.setPicUrl(ResourceUtilsService.getDefaultPicPath());
                }
                else {
                    detail.setPicUrl(ResourceUtilsService.getWholeHttpPath(detail.getPicUrl()));
                }
            }
            else {
                detail.setPicUrl(ResourceUtilsService.getWholeHttpPath(defaultPicUrl));
            }
            return detail;
        }
        return null;
    }

    @Override
    public List<ProductModel> getProducts(List<Integer> itemIds) {
        if (CollectionUtils.isEmpty(itemIds)) {
            return new ArrayList<ProductModel>();
        }
        ProductQuery productQuery = new ProductQuery();
        productQuery.setProductIds(itemIds);
        List<ProductModel> products = mybatisDao.getSqlSessionTemplate().selectList(ProductMapper.ProductMapperNameSpace + "getProductsByIds", productQuery);
        if (products != null && !products.isEmpty()) {
            for (ProductModel product : products) {
            	product.setBasePrice(null);//隐藏商品原价
                if (StringUtils.isEmpty(product.getPicUrl())) {
                	product.setPicUrl(ResourceUtilsService.getDefaultPicPath());
                }
                else {
                	product.setPicUrl(ResourceUtilsService.getWholeHttpPath(product.getPicUrl()));
                }
            }
        }
        return products;
    }
}

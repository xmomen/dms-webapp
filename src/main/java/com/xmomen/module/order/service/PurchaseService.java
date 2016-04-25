package com.xmomen.module.order.service;

import com.xmomen.framework.mybatis.dao.MybatisDao;
import com.xmomen.framework.mybatis.page.Page;
import com.xmomen.module.order.entity.TbPurchase;
import com.xmomen.module.order.mapper.OrderMapper;
import com.xmomen.module.order.model.CreatePurchase;
import com.xmomen.module.order.model.PurchaseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jeng on 16/4/5.
 */
@Service
public class PurchaseService {

    @Autowired
    MybatisDao mybatisDao;

    /**
     * 查询采购单
     * @param keyword
     * @param limit
     * @param offset
     * @return
     */
    public Page<PurchaseModel> getPurchaseList(String keyword, Integer limit, Integer offset){
        Map param = new HashMap();
        param.put("keyword", keyword);
        return (Page<PurchaseModel>) mybatisDao.selectPage(OrderMapper.ORDER_MAPPER_NAMESPACE + "getPurchaseList", param, limit, offset);
    }

    /**
     * 创建采购单
     * @param createPurchase
     * @return
     */
    public TbPurchase createPurchase(CreatePurchase createPurchase){
        return null;
    }

    /**
     * 删除采购单
     * @param id
     */
    public void deletePurchase(Integer id){
        mybatisDao.deleteByPrimaryKey(TbPurchase.class, id);
    }

}

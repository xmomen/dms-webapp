package com.xmomen.module.order.service;

import com.xmomen.framework.mybatis.dao.MybatisDao;
import com.xmomen.framework.mybatis.page.Page;
import com.xmomen.framework.utils.DateUtils;
import com.xmomen.framework.utils.StringUtilsExt;
import com.xmomen.module.order.entity.TbOrder;
import com.xmomen.module.order.model.CreateOrder;
import com.xmomen.module.order.model.OrderModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Created by Jeng on 16/4/5.
 */
@Service
public class OrderService {

    @Autowired
    MybatisDao mybatisDao;

    public Page<OrderModel> getOrderList(String keyword, Integer limit, Integer offset){
        return null;
    }

    public TbOrder createOrder(CreateOrder createOrder){
        TbOrder tbOrder = new TbOrder();
        tbOrder.setConsigneeName(createOrder.getConsigneeName());
        tbOrder.setConsigneeAddress(createOrder.getConsigneeAddress());
        tbOrder.setConsigneePhone(createOrder.getConsigneePhone());
        tbOrder.setCreateTime(mybatisDao.getSysdate());
        tbOrder.setOrderNo(DateUtils.getDateTimeString());
        tbOrder.setOrderSource(createOrder.getOrderSource());
        return null;
    }

    public void deleteOrder(Integer id){

    }

}

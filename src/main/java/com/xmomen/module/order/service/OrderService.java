package com.xmomen.module.order.service;

import com.xmomen.framework.mybatis.page.Page;
import com.xmomen.module.order.entity.TbOrder;
import com.xmomen.module.order.model.OrderModel;
import org.springframework.stereotype.Service;

/**
 * Created by Jeng on 16/4/5.
 */
@Service
public class OrderService {

    public Page<OrderModel> getOrderList(String keyword, Integer limit, Integer offset){
        return null;
    }

    public TbOrder createOrder(TbOrder tbOrder){
        return null;
    }

    public void deleteOrder(Integer id){

    }

}

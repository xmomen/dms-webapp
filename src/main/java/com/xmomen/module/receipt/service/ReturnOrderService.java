package com.xmomen.module.receipt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmomen.framework.mybatis.dao.MybatisDao;
import com.xmomen.framework.mybatis.page.Page;
import com.xmomen.module.base.service.ItemService;
import com.xmomen.module.order.model.OrderQuery;
import com.xmomen.module.receipt.mapper.ReturnOrderMapper;
import com.xmomen.module.receipt.model.ReturnOrderModel;
import com.xmomen.module.receipt.model.ReturnOrderQuery;

/**
 * Created by Jeng on 16/4/5.
 */
@Service
public class ReturnOrderService {

    @Autowired
    MybatisDao mybatisDao;

    @Autowired
    ItemService itemService;

    /**
     * 查询订单
     *
     * @param orderQuery
     * @param limit
     * @param offset
     * @return
     */
    public Page<ReturnOrderModel> getReturnOrderList(ReturnOrderQuery orderQuery, Integer limit, Integer offset) {
        return (Page<ReturnOrderModel>) mybatisDao.selectPage(ReturnOrderMapper.RETURN_ORDER_MAPPER_NAMESPACE + "getReturnOrderList", orderQuery, limit, offset);
    }

    /**
     * 查询订单
     *
     * @param orderQuery
     * @return
     */
    public List<ReturnOrderModel> getReturnOrderList(ReturnOrderQuery orderQuery) {
        return mybatisDao.getSqlSessionTemplate().selectList(ReturnOrderMapper.RETURN_ORDER_MAPPER_NAMESPACE + "getReturnOrderList", orderQuery);
    }
}

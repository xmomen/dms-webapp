package com.xmomen.module.receipt.service;

import java.util.Date;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xmomen.framework.mybatis.dao.MybatisDao;
import com.xmomen.framework.mybatis.page.Page;
import com.xmomen.module.base.constant.AppConstants;
import com.xmomen.module.base.service.ItemService;
import com.xmomen.module.order.entity.TbReturnOrder;
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
    
    @Transactional
    public void auditReturnOrder(int id,int statusCd){
    	TbReturnOrder returnOrder = this.mybatisDao.selectByPrimaryKey(TbReturnOrder.class, id);
    	returnOrder.setAuditStatus(statusCd);
    	returnOrder.setReturnTime(new Date());
    	Integer userId = (Integer) SecurityUtils.getSubject().getSession().getAttribute(AppConstants.SESSION_USER_ID_KEY);
    	returnOrder.setAuditUserId(userId);
    	this.mybatisDao.save(returnOrder);
    }
}

package com.xmomen.module.receipt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.xmomen.framework.mybatis.dao.MybatisDao;
import com.xmomen.framework.mybatis.page.Page;
import com.xmomen.framework.utils.StringUtilsExt;
import com.xmomen.module.logger.Log;
import com.xmomen.module.order.entity.TbOrderItem;
import com.xmomen.module.order.entity.TbReturnOrderItem;
import com.xmomen.module.order.model.OrderModel;
import com.xmomen.module.order.model.OrderQuery;
import com.xmomen.module.receipt.model.ReturnOrderModel;
import com.xmomen.module.receipt.model.ReturnOrderQuery;
import com.xmomen.module.receipt.service.ReturnOrderService;

/**
 * Created by Jeng on 2016/3/30.
 */
@RestController
public class ReturnOrderController {

    @Autowired
    ReturnOrderService returnOrderService;

    @Autowired
    MybatisDao mybatisDao;

    /**
     * 退货订单列表
     * @param limit
     * @param offset
     * @param keyword
     * @return
     */
    @RequestMapping(value = "/returnOrder", method = RequestMethod.GET)
    @Log(actionName = "查询退货订单列表")
    public Page<ReturnOrderModel> getUserList(@RequestParam(value = "limit") Integer limit,
                                  @RequestParam(value = "offset") Integer offset,
                                  @RequestParam(value = "orderStatus", required = false) Integer orderStatus,
                                  @RequestParam(value = "keyword", required = false) String keyword,
                                  @RequestParam(value = "returnTimeStart",required = false) String returnTimeStart,
                                  @RequestParam(value = "returnTimeEnd",required = false) String returnTimeEnd
                                 ){
        ReturnOrderQuery orderQuery = new ReturnOrderQuery();
        orderQuery.setKeyword(keyword);
        if(StringUtilsExt.isNotBlank(returnTimeStart)){
        	orderQuery.setReturnTimeStart(returnTimeStart);
        }
        
        if(StringUtilsExt.isNotBlank(returnTimeEnd)){
        	orderQuery.setReturnTimeEnd(returnTimeEnd);
        }
        //客服经理过滤 如果有客服组权限则不过滤
//        if(SecurityUtils.getSubject().hasRole(AppConstants.CUSTOMER_MANAGER_PERMISSION_CODE) && !SecurityUtils.getSubject().hasRole(AppConstants.CUSTOMER_PERMISSION_CODE)){
//            Integer userId = (Integer) SecurityUtils.getSubject().getSession().getAttribute(AppConstants.SESSION_USER_ID_KEY);
//            orderQuery.setCreateUserId(userId);
//        }
        return returnOrderService.getReturnOrderList(orderQuery, limit, offset);
    }
    /**
     * 查询退货订单商品列表
     * @param limit
     * @param offset
     * @param orderNo
     * @return
     */
    @RequestMapping(value = "/returnOrder/{id}/item", method = RequestMethod.GET)
    @Log(actionName = "查询退货订单商品列表")
    public Page<TbReturnOrderItem> getUserList(@RequestParam(value = "limit") Integer limit,
                                        @RequestParam(value = "offset") Integer offset,
                                        @PathVariable(value = "id") Integer id){
        TbReturnOrderItem returnOrderItem = new TbReturnOrderItem();
        returnOrderItem.setReturnOrderId(id);
        return mybatisDao.selectPageByModel(returnOrderItem, limit, offset);
    }

    /**
     * 审核退货订单
     * @param orderNo
     * @param statusCd
     * @return
     */
    @RequestMapping(value = "/returnOrder/{id}/audit", method = RequestMethod.POST)
    @Log(actionName = "审核退货订单")
    public void auditReturnOrder(@PathVariable(value = "id") Integer id,
    		@RequestParam(value="statusCd") Integer statusCd) {
       this.returnOrderService.auditReturnOrder(id,statusCd);
    }
    
    /**
     * 退货收货扫描操作
     * @param boxNo 箱号
     */
    @RequestMapping(value ="/returnOrder/returnOrderShouhuo",method = RequestMethod.PUT)
    @Log(actionName = "退货收货扫描操作")
    public ReturnOrderModel returnOrderShouhuo(@RequestParam(value = "boxNo")String boxNo){
    	return this.returnOrderService.returnOrderShouhuo(boxNo);
    }
    
    /**
     * 退货收货操作
     * @param boxNo 箱号
     */
    @RequestMapping(value ="/returnOrder/shouhuo",method = RequestMethod.PUT)
    @Log(actionName = "退货收货操作")
    public void orderNo(@RequestParam(value = "orderNo")String orderNo){
    	this.returnOrderService.shouhuo(orderNo);
    }
    
}

package com.xmomen.module.order.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xmomen.framework.mybatis.dao.MybatisDao;
import com.xmomen.framework.mybatis.page.Page;
import com.xmomen.framework.utils.StringUtilsExt;
import com.xmomen.framework.web.exceptions.ArgumentValidException;
import com.xmomen.module.base.constant.AppConstants;
import com.xmomen.module.logger.Log;
import com.xmomen.module.order.entity.TbOrder;
import com.xmomen.module.order.entity.TbOrderItem;
import com.xmomen.module.order.model.CreateOrder;
import com.xmomen.module.order.model.OrderModel;
import com.xmomen.module.order.model.OrderQuery;
import com.xmomen.module.order.model.UpdateOrder;
import com.xmomen.module.order.service.OrderService;
import com.xmomen.module.wx.model.AjaxResult;

/**
 * Created by Jeng on 2016/3/30.
 */
@RestController
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    MybatisDao mybatisDao;

    /**
     * 订单列表
     * @param limit
     * @param offset
     * @param keyword
     * @return
     */
    @RequestMapping(value = "/order", method = RequestMethod.GET)
    @Log(actionName = "查询订单列表")
    public Page<OrderModel> getUserList(@RequestParam(value = "limit") Integer limit,
                                  @RequestParam(value = "offset") Integer offset,
                                  @RequestParam(value = "orderStatus", required = false) Integer orderStatus,
                                  @RequestParam(value = "keyword", required = false) String keyword,
                                  @RequestParam(value = "orderCreateTimeStart",required = false) String orderCreateTimeStart,
                                  @RequestParam(value = "orderCreateTimeEnd",required = false) String orderCreateTimeEnd,
                                  @RequestParam(value = "couponNumber",required = false) String couponNumber,
                                  @RequestParam(value = "managerId", required = false) Integer managerId,
                                  @RequestParam(value = "companyId", required = false) Integer companyId,
                                  @RequestParam(value = "consigneeName", required = false) String consigneeName,
                                  @RequestParam(value = "hasNoShowCancel", required = false) Boolean hasNoShowCancel,
                                  @RequestParam(value = "hasShowDespatch", required = false) Boolean hasShowDespatch,
                                  @RequestParam(value = "appointmentTimeStart", required = false) String appointmentTimeStart,
                                  @RequestParam(value = "appointmentTimeEnd", required = false) String appointmentTimeEnd,
                                  @RequestParam(value = "despatchExpressId",required = false) Integer despatchExpressId,
                                  @RequestParam(value = "isTwoSend",required = false) Integer isTwoSend){
        OrderQuery orderQuery = new OrderQuery();
        orderQuery.setKeyword(keyword);
        orderQuery.setOrderStatus(orderStatus);
        orderQuery.setManagerId(managerId);
        orderQuery.setHasNoShowCancel(hasNoShowCancel == null ? false : hasNoShowCancel);
        orderQuery.setConsigneeName(consigneeName);
        orderQuery.setDespatchExpressId(despatchExpressId);
        orderQuery.setCompanyId(companyId);
        orderQuery.setCouponNumber(couponNumber);
        orderQuery.setIsTwoSend(isTwoSend);
        if(null != hasShowDespatch){
        	//0显示未分配快递商 1显示已分配快递商
        	 orderQuery.setShowDespatch(hasShowDespatch ? 1 : 0);
        }
        if(StringUtilsExt.isNotBlank(orderCreateTimeStart)){
       	    orderQuery.setOrderCreateTimeStart(orderCreateTimeStart.substring(0, 10));
        }
        if(StringUtilsExt.isNotBlank(orderCreateTimeEnd)){
        	orderQuery.setOrderCreateTimeEnd(orderCreateTimeEnd.substring(0, 10));
        }

        if(StringUtilsExt.isNotBlank(appointmentTimeStart)){
        	orderQuery.setAppointmentTimeStart(appointmentTimeStart.substring(0, 10));
        }
        
        if(StringUtilsExt.isNotBlank(appointmentTimeEnd)){
        	orderQuery.setAppointmentTimeEnd(appointmentTimeEnd.substring(0, 10));
        }
        //客服经理过滤 如果有客服组权限则不过滤
        if(SecurityUtils.getSubject().hasRole(AppConstants.CUSTOMER_MANAGER_PERMISSION_CODE) && !SecurityUtils.getSubject().hasRole(AppConstants.CUSTOMER_PERMISSION_CODE)){
            Integer userId = (Integer) SecurityUtils.getSubject().getSession().getAttribute(AppConstants.SESSION_USER_ID_KEY);
            orderQuery.setCreateUserId(userId);
        }
        return orderService.getOrderList(orderQuery, limit, offset);
    }

    /**
     * 订单明细
     * @return
     */
    @RequestMapping(value = "/order/{id}", method = RequestMethod.GET)
    @Log(actionName = "查询订单列表")
    public OrderModel getOrderDetail(@PathVariable(value = "id") Integer id){
        OrderQuery orderQuery = new OrderQuery();
        orderQuery.setId(id);
        List<OrderModel> orderModelList = orderService.getOrderList(orderQuery);
        if(orderModelList != null && !orderModelList.isEmpty() && orderModelList.size() == 1){
            return orderModelList.get(0);
        }
        return null;
    }

    /**
     * 新增订单
     * @param createOrder
     * @param bindingResult
     * @return
     */
    @RequestMapping(value = "/order", method = RequestMethod.POST)
    @Log(actionName = "新增订单")
    public TbOrder createOrder(@RequestBody @Valid CreateOrder createOrder, BindingResult bindingResult) throws ArgumentValidException {
        if(bindingResult != null && bindingResult.hasErrors()){
            throw new ArgumentValidException(bindingResult);
        }
        Integer userId = (Integer) SecurityUtils.getSubject().getSession().getAttribute("user_id");
        createOrder.setCreateUserId(userId);
        return orderService.createOrder(createOrder);
    }

    /**
     * 批量新增订单
     * @param createOrder
     * @param bindingResult
     * @return
     */
    @RequestMapping(value = "/order/batch", method = RequestMethod.POST)
    @Log(actionName = "批量新增订单")
    public void batchCreateOrder(@RequestBody @Valid CreateOrder createOrder, BindingResult bindingResult) throws ArgumentValidException {
        if(bindingResult != null && bindingResult.hasErrors()){
            throw new ArgumentValidException(bindingResult);
        }
        Integer userId = (Integer) SecurityUtils.getSubject().getSession().getAttribute("user_id");
        createOrder.setCreateUserId(userId);
        orderService.batchCreateOrder(createOrder);
    }

    /**
     * 更新订单
     * @param updateOrder
     * @param bindingResult
     * @return
     */
    @RequestMapping(value = "/order/{id}", method = RequestMethod.PUT)
    @Log(actionName = "更新订单")
    public TbOrder updateOrder(
            @PathVariable(value = "id") Integer id,
            @RequestBody @Valid UpdateOrder updateOrder, BindingResult bindingResult) throws ArgumentValidException {
        if(bindingResult != null && bindingResult.hasErrors()){
            throw new ArgumentValidException(bindingResult);
        }
        Integer userId = (Integer) SecurityUtils.getSubject().getSession().getAttribute("user_id");
        updateOrder.setCreateUserId(userId);
        return orderService.updateOrder(updateOrder);
    }

    /**
     * 订单商品列表
     * @param limit
     * @param offset
     * @param orderNo
     * @return
     */
    @RequestMapping(value = "/order/{id}/item", method = RequestMethod.GET)
    @Log(actionName = "查询订单商品列表")
    public Page<TbOrderItem> getUserList(@RequestParam(value = "limit") Integer limit,
                                        @RequestParam(value = "offset") Integer offset,
                                         @RequestParam(value = "orderNo", required = false) String orderNo){
        TbOrderItem tbOrderItem = new TbOrderItem();
        tbOrderItem.setOrderNo(orderNo);
        return mybatisDao.selectPageByModel(tbOrderItem, limit, offset);
    }

    /**
     *  取消订单
     * @param id
     */
    @RequestMapping(value = "/order/{id}", method = RequestMethod.DELETE)
    @Log(actionName = "取消订单")
    public void cancelOrder(@PathVariable(value = "id") Integer id){
        orderService.cancelOrder(id);
    }

    /**
     * 更新订单装箱数
     * @param createOrder
     * @param bindingResult
     * @return
     */
    @RequestMapping(value = "/order/updateTotalBox", method = RequestMethod.POST)
    @Log(actionName = "更新订单装箱数")
    public void updateTotalBox(@RequestParam(value = "orderNo") String orderNo,
    		@RequestParam(value="totalBox") int totalBox) {
        orderService.updateTotalBox(orderNo,totalBox);
    }
    
    /**
     *  二次配送审核
     * @param id
     */
    @RequestMapping(value = "/order/{id}/twoSend", method = RequestMethod.POST)
    @Log(actionName = "二次配送审核")
    public AjaxResult twoSendOrder(@PathVariable(value = "id") Integer id,@RequestParam(value="auditStatusCd") Integer auditStatusCd){
        orderService.twoSendOrder(id,auditStatusCd);
        AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.setResult(1);
        ajaxResult.setMessage("审核成功。");
        return ajaxResult;
    }
}

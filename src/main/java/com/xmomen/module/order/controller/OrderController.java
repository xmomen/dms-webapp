package com.xmomen.module.order.controller;

import com.xmomen.framework.mybatis.dao.MybatisDao;
import com.xmomen.framework.mybatis.page.Page;
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

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

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
                                  @RequestParam(value = "keyword", required = false) String keyword){
        OrderQuery orderQuery = new OrderQuery();
        orderQuery.setKeyword(keyword);
        orderQuery.setOrderStatus(orderStatus);
        if(SecurityUtils.getSubject().hasRole(AppConstants.CUSTOMER_MANAGER_PERMISSION_CODE)){
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
     * @param keyword
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

}

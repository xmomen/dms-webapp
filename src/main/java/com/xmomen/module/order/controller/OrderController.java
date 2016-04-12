package com.xmomen.module.order.controller;

import com.xmomen.framework.mybatis.dao.MybatisDao;
import com.xmomen.framework.mybatis.page.Page;
import com.xmomen.framework.web.exceptions.ArgumentValidException;
import com.xmomen.module.logger.Log;
import com.xmomen.module.order.entity.TbOrder;
import com.xmomen.module.order.model.CreateOrder;
import com.xmomen.module.order.model.OrderModel;
import com.xmomen.module.order.service.OrderService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
                                  @RequestParam(value = "keyword", required = false) String keyword){
        return orderService.getOrderList(keyword, limit, offset);
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
     *  删除订单
     * @param id
     */
    @RequestMapping(value = "/order/{id}", method = RequestMethod.DELETE)
    @Log(actionName = "删除订单")
    public void deleteOrder(@PathVariable(value = "id") Integer id){
        orderService.deleteOrder(id);
    }

}

package com.xmomen.module.order.controller;

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
import com.xmomen.framework.web.exceptions.ArgumentValidException;
import com.xmomen.module.base.constant.AppConstants;
import com.xmomen.module.logger.Log;
import com.xmomen.module.order.entity.TbPacking;
import com.xmomen.module.order.entity.TbPackingRecord;
import com.xmomen.module.order.model.CreatePacking;
import com.xmomen.module.order.model.CreatePackingRecord;
import com.xmomen.module.order.model.OrderModel;
import com.xmomen.module.order.model.OrderQuery;
import com.xmomen.module.order.model.PackingModel;
import com.xmomen.module.order.model.PackingOrderModel;
import com.xmomen.module.order.model.PackingOrderQuery;
import com.xmomen.module.order.model.PackingQuery;
import com.xmomen.module.order.model.PackingRecordModel;
import com.xmomen.module.order.model.PackingRecordQuery;
import com.xmomen.module.order.model.PackingTask;
import com.xmomen.module.order.model.PackingTaskCount;
import com.xmomen.module.order.service.OrderService;
import com.xmomen.module.order.service.PackingService;

/**
 * Created by Jeng on 2016/3/30.
 */
@RestController
public class PackingController {

    @Autowired
    PackingService packingService;

    @Autowired
    OrderService orderService;

    @Autowired
    MybatisDao mybatisDao;

    /**
     * 装箱记录列表
     * @param limit
     * @param offset
     * @param keyword
     * @return
     */
    @RequestMapping(value = "/packing", method = RequestMethod.GET)
    @Log(actionName = "查询装箱记录列表")
    public Page<PackingModel> getUserList(@RequestParam(value = "limit") Integer limit,
                                  @RequestParam(value = "offset") Integer offset,
                                  @RequestParam(value = "keyword", required = false) String keyword,
                                  @RequestParam(value = "orderNo", required = false) String orderNo,
                                  @RequestParam(value = "packingNo", required = false) String packingNo){
        PackingQuery packingQuery = new PackingQuery();
        packingQuery.setKeyword(keyword);
        packingQuery.setPackingNo(packingNo);
        packingQuery.setOrderNo(orderNo);
        return packingService.getPackingList(packingQuery, limit, offset);
    }

    /**
     * 新增装箱记录
     * @param createPacking
     * @param bindingResult
     * @return
     */
    @RequestMapping(value = "/packing", method = RequestMethod.POST)
    @Log(actionName = "新增装箱记录")
    public TbPacking createPacking(@RequestBody @Valid CreatePacking createPacking, BindingResult bindingResult) throws ArgumentValidException {
        if(bindingResult != null && bindingResult.hasErrors()){
            throw new ArgumentValidException(bindingResult);
        }
        return packingService.create(createPacking);
    }

    /**
     * 装箱记录列表
     * @param limit
     * @param offset
     * @param keyword
     * @return
     */
    @RequestMapping(value = "/packing/task", method = RequestMethod.GET)
    @Log(actionName = "查询装箱任务统计列表")
    public Page<PackingTaskCount> getPackingTaskList(@RequestParam(value = "limit") Integer limit,
                                          @RequestParam(value = "offset") Integer offset,
                                          @RequestParam(value = "keyword", required = false) String keyword,
                                          @RequestParam(value = "orderNo", required = false) String orderNo,
                                          @RequestParam(value = "packingNo", required = false) String packingNo){
        return packingService.getPackingTaskCountList(null, limit, offset);
    }

    /**
     * 分配装箱任务
     * @param packingTask
     * @param bindingResult
     * @return
     */
    @RequestMapping(value = "/packing/task/bind", method = RequestMethod.PUT)
    @Log(actionName = "分配装箱任务")
    public void createPacking(@RequestBody @Valid PackingTask packingTask, BindingResult bindingResult) throws ArgumentValidException {
        if(bindingResult != null && bindingResult.hasErrors()){
            throw new ArgumentValidException(bindingResult);
        }
        packingService.dispatchPackingTask(packingTask);
    }

    /**
     * 分配装箱任务
     * @param orderNoList
     * @throws ArgumentValidException
     */
    @RequestMapping(value = "/packing/task/unbind", method = RequestMethod.PUT)
    @Log(actionName = "分配装箱任务")
    public void createPacking(@RequestParam(value = "orderNos", required = true)String[] orderNoList) throws ArgumentValidException {
        if(orderNoList != null && orderNoList.length <= 0){
            return;
        }
        packingService.cancelPackingTask(orderNoList);
    }

    @RequestMapping(value = "/packing/order", method = RequestMethod.GET)
    @Log(actionName = "装箱订单列表")
    public Page<OrderModel> queryPackingOrder(@RequestParam(value = "limit") Integer limit,
                              @RequestParam(value = "orderNo", required = false) String orderNo,
                              @RequestParam(value = "offset") Integer offset,
                              @RequestParam(value = "packingTaskStatus", required = false) Integer packingTaskStatus,
                              @RequestParam(value = "keyword", required = false) String keyword) {
        OrderQuery orderQuery = new OrderQuery();
        orderQuery.setKeyword(keyword);
        orderQuery.setOrderNo(orderNo);
        //orderQuery.setOrderStatus(7);//待装箱
        orderQuery.setPackingTaskStatus(packingTaskStatus);
        if(SecurityUtils.getSubject().hasRole(AppConstants.PACKING_PERMISSION_CODE)){
            Integer userId = (Integer) SecurityUtils.getSubject().getSession().getAttribute(AppConstants.SESSION_USER_ID_KEY);
            orderQuery.setPackingTaskUserId(userId);
        }
        return orderService.getOrderList(orderQuery, limit, offset);
    }

    /**
     *  删除装箱记录
     * @param id
     */
    @RequestMapping(value = "/packing/{id}", method = RequestMethod.DELETE)
    @Log(actionName = "删除装箱记录")
    public void deletePacking(@PathVariable(value = "id") Integer id){
        packingService.delete(id);
    }

    /**
     * 商品装箱汇总
     * @param packingId
     * @param orderId
     * @param limit
     * @param offset
     * @return
     */
    @RequestMapping(value = "/packing/{id}/order")
    public Page<PackingOrderModel> queryPackingOrder(@PathVariable(value = "id") Integer packingId,
                                                        @RequestParam(value = "orderId") Integer orderId,
                                                        @RequestParam(value = "keyword", required = false) String keyword,
                                                        @RequestParam(value = "limit") Integer limit,
                                                        @RequestParam(value = "offset") Integer offset){
        PackingOrderQuery packingOrderQuery = new PackingOrderQuery();
        packingOrderQuery.setKeyword(keyword);
        packingOrderQuery.setOrderId(orderId);
        return packingService.queryPackingOrder(packingOrderQuery, limit, offset);
    }

    @RequestMapping(value = "/packing/{id}/record", method = RequestMethod.GET)
    public Page<PackingRecordModel> queryPackingRecord(@PathVariable(value = "id") Integer packingId,
                                                       @RequestParam(value = "orderItemId") Integer orderItemId,
                                                       @RequestParam(value = "keyword", required = false) String keyword,
                                                       @RequestParam(value = "limit") Integer limit,
                                                       @RequestParam(value = "offset") Integer offset){
        PackingRecordQuery packingRecordQuery = new PackingRecordQuery();
        packingRecordQuery.setKeyword(keyword);
        packingRecordQuery.setOrderItemId(orderItemId);
        //packingRecordQuery.setId(packingId);
        return packingService.queryPackingRecord(packingRecordQuery, limit, offset);
    }

    @RequestMapping(value = "/packing/record", method = RequestMethod.POST)
    @Log(actionName = "新增装箱记录")
    public TbPackingRecord createPackingRecord(@RequestBody @Valid CreatePackingRecord createPackingRecord, BindingResult bindingResult) throws ArgumentValidException {
        if(bindingResult != null && bindingResult.hasErrors()){
            throw new ArgumentValidException(bindingResult);
        }
        return packingService.createRecord(createPackingRecord);
    }

    @RequestMapping(value = "/packing/{id}/record/{recordId}", method = RequestMethod.DELETE)
    @Log(actionName = "删除装箱记录")
    public void createPackingRecord(@PathVariable(value = "id") Integer id,
                                               @PathVariable(value = "recordId") Integer recordId){
        packingService.deleteRecord(recordId);
    }


}

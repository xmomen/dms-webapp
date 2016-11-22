package com.xmomen.module.order.controller;

import com.xmomen.framework.mybatis.dao.MybatisDao;
import com.xmomen.framework.mybatis.page.Page;
import com.xmomen.framework.utils.StringUtilsExt;
import com.xmomen.framework.web.exceptions.ArgumentValidException;
import com.xmomen.module.base.constant.AppConstants;
import com.xmomen.module.logger.Log;
import com.xmomen.module.order.entity.TbPacking;
import com.xmomen.module.order.model.*;
import com.xmomen.module.order.service.OrderService;
import com.xmomen.module.order.service.PackingService;

import org.apache.commons.lang3.StringUtils;
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
    @Log(actionName = "取消分配装箱任务")
    public void unbind(@RequestParam(value = "orderNos", required = true)String[] orderNoList) throws ArgumentValidException {
        if(orderNoList != null && orderNoList.length <= 0){
            return;
        }
        packingService.cancelPackingTask(orderNoList);
    }

    @RequestMapping(value = "/packing/order", method = RequestMethod.GET)
    @Log(actionName = "装箱订单列表")
    public Page<OrderModel> queryPackingOrder(@RequestParam(value = "limit") Integer limit,
                              @RequestParam(value = "orderNo", required = false) String orderNo,
                              @RequestParam(value = "isHasPackingTaskUserId", required = false) boolean isHasPackingTaskUserId,
                              @RequestParam(value = "packingTaskCreateTimeStart", required = false) String packingTaskCreateTimeStart,
                              @RequestParam(value = "packingTaskCreateTimeEnd", required = false) String packingTaskCreateTimeEnd,
                              @RequestParam(value = "appointmentTimeStart", required = false) String appointmentTimeStart,
                              @RequestParam(value = "appointmentTimeEnd", required = false) String appointmentTimeEnd,
                              @RequestParam(value = "offset") Integer offset,
                              @RequestParam(value = "packingTaskStatus", required = false) Integer packingTaskStatus,
                              @RequestParam(value = "keyword", required = false) String keyword,
                              @RequestParam(value = "managerId", required = false) Integer managerId,
                              @RequestParam(value = "consigneeName", required = false) String consigneeName,
                              @RequestParam(value = "isShowPackingAssigns", required = false) Boolean isShowPackingAssigns) {
        OrderQuery orderQuery = new OrderQuery();
        orderQuery.setKeyword(keyword);
        orderQuery.setOrderNo(orderNo);
        orderQuery.setHasPackingTaskUserId(isHasPackingTaskUserId);
        orderQuery.setHasNoShowCancel(true);
        if(StringUtilsExt.isNotBlank(packingTaskCreateTimeStart)){
        	orderQuery.setPackingTaskCreateTimeStart(packingTaskCreateTimeStart);
        }
        if(StringUtilsExt.isNotBlank(packingTaskCreateTimeEnd)){
        	orderQuery.setPackingTaskCreateTimeEnd(packingTaskCreateTimeEnd);
        }
        
        if(StringUtilsExt.isNotBlank(appointmentTimeStart)){
        	orderQuery.setAppointmentTimeStart(appointmentTimeStart);
        }
        
        if(StringUtilsExt.isNotBlank(appointmentTimeEnd)){
        	orderQuery.setAppointmentTimeEnd(appointmentTimeEnd);
        }
        
        if(isShowPackingAssigns != null){
        	//0显示未分配装箱工 1显示已分配装箱工
        	 orderQuery.setShowPackingAssigns(isShowPackingAssigns ? 1 : 0);
        }
        orderQuery.setManagerId(managerId);
        orderQuery.setConsigneeName(consigneeName);
        //orderQuery.setOrderStatus(7);//待装箱
        orderQuery.setPackingTaskStatus(packingTaskStatus);
        
        if(SecurityUtils.getSubject().hasRole(AppConstants.PACKING_PERMISSION_CODE)){
            Integer userId = (Integer) SecurityUtils.getSubject().getSession().getAttribute(AppConstants.SESSION_USER_ID_KEY);
            orderQuery.setPackingTaskUserId(userId);
        }
        
        Page<OrderModel> orderModePage = orderService.getPackageTaskList(orderQuery, limit, offset);
        return orderModePage;
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
                                                     @RequestParam(value = "orderNos", required = false) String[] orderNos,
                                                     @RequestParam(value = "limit") Integer limit,
                                                     @RequestParam(value = "offset") Integer offset){
        PackingOrderQuery packingOrderQuery = new PackingOrderQuery();
        packingOrderQuery.setKeyword(keyword);
        packingOrderQuery.setOrderId(orderId);
        packingOrderQuery.setOrderNos(orderNos);
        return packingService.queryPackingOrder(packingOrderQuery, limit, offset);
    }
    
    /**
     * 订单打印
     * @param orderId 订单ID
     * @return
     */
    @RequestMapping(value = "/packing/printOrder")
    public OrderModel printOrder(@RequestParam(value = "orderId") Integer orderId){
    	OrderModel orderModel = null;
    	OrderQuery orderQuery = new OrderQuery();
    	orderQuery.setId(orderId);
    	List<OrderModel> orderModels = orderService.getOrderList(orderQuery);
    	//打印传递箱数
        if(orderModels.size() == 1){
        	orderModel = orderModels.get(0);
        	//已完成
        	if(orderModel.getPackingTaskStatus() == 2){
    			  PackingQuery packingQuery = new PackingQuery();
        		  packingQuery.setOrderNo(orderModel.getOrderNo());
        		  List<PackingModel> packingModels = packingService.getPackingList(packingQuery);
                orderModel.setPackingModels(packingModels);
        	}
        }
        //
        if(StringUtils.isNotEmpty(orderModel.getBatchNo())){
        	int batchNum = orderService.getCountBatch(orderModel.getBatchNo());
        	orderModel.setBatchNum(batchNum);
        }else{
        	orderModel.setBatchNum(0);
        }
        
        return orderModel;
    }
    
    
    @RequestMapping(value = "/packing/order/item")
    public Page<PackingOrderItemModel> queryPackingOrderItem(@RequestParam(value = "orderNos", required = false) String[] orderNos,
                                                             @RequestParam(value = "limit") Integer limit,
                                                             @RequestParam(value = "offset") Integer offset){
        PackingOrderQuery packingOrderQuery = new PackingOrderQuery();
        packingOrderQuery.setOrderNos(orderNos);
        return packingService.queryPackingOrderItem(packingOrderQuery, limit, offset);
    }

    /**
     * 查询装箱记录
     * 
     * @param packingId
     * @param orderItemId
     * @param keyword
     * @param limit
     * @param offset
     * @return
     */
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

    /**
     * 装箱扫码
     * 
     * @param createPackingRecord
     * @param bindingResult
     * @return
     * @throws ArgumentValidException
     */
    @RequestMapping(value = "/packing/record", method = RequestMethod.POST)
    @Log(actionName = "新增装箱记录")
    public ScanModel createPackingRecord(@RequestBody @Valid CreatePackingRecord createPackingRecord, BindingResult bindingResult) throws ArgumentValidException {
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

    @RequestMapping(value = "/packing/auto", method = RequestMethod.POST)
    @Log(actionName = "订单自动装箱")
    public List<ScanModel> autoPacking(@RequestParam(value = "packingId") Integer packingId,
                            @RequestParam(value = "orderNo") String orderNo){
        return packingService.autoPacking(orderNo, packingId);
    }
}

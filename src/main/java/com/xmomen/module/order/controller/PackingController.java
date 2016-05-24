package com.xmomen.module.order.controller;

import com.xmomen.framework.mybatis.dao.MybatisDao;
import com.xmomen.framework.mybatis.page.Page;
import com.xmomen.framework.web.exceptions.ArgumentValidException;
import com.xmomen.module.logger.Log;
import com.xmomen.module.order.entity.TbPacking;
import com.xmomen.module.order.entity.TbPackingRecord;
import com.xmomen.module.order.model.*;
import com.xmomen.module.order.service.PackingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by Jeng on 2016/3/30.
 */
@RestController
public class PackingController {

    @Autowired
    PackingService packingService;

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

    @RequestMapping(value = "/packing/{id}/record", method = RequestMethod.POST)
    @Log(actionName = "新增装箱记录")
    public TbPackingRecord createPackingRecord(@PathVariable(value = "id") Integer id,
                                         @RequestBody @Valid CreatePackingRecord createPackingRecord, BindingResult bindingResult) throws ArgumentValidException {
        if(bindingResult != null && bindingResult.hasErrors()){
            throw new ArgumentValidException(bindingResult);
        }
        createPackingRecord.setPackingId(id);
        return packingService.createRecord(createPackingRecord);
    }

    @RequestMapping(value = "/packing/{id}/record/{recordId}", method = RequestMethod.DELETE)
    @Log(actionName = "删除装箱记录")
    public void createPackingRecord(@PathVariable(value = "id") Integer id,
                                               @PathVariable(value = "recordId") Integer recordId){
        packingService.deleteRecord(recordId);
    }


}

package com.xmomen.module.base.controller;

import java.util.HashMap;
import java.util.Map;

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
import com.xmomen.module.base.entity.CdExpress;
import com.xmomen.module.base.mapper.ExpressMapper;
import com.xmomen.module.base.model.ExpressTask;
import com.xmomen.module.base.service.ExpressService;
import com.xmomen.module.logger.Log;
import com.xmomen.module.order.model.OrderModel;
import com.xmomen.module.order.model.OrderQuery;

@RestController
public class ExpressController {
	@Autowired
	ExpressService expressService;
	@Autowired
	ExpressMapper expressMapper;
	@Autowired
    MybatisDao mybatisDao;
	
    /**
     * 查询快递公司信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/express", method = RequestMethod.GET)
    @Log(actionName = "查询快递公司信息")
    public Page<CdExpress> getExpressList(@RequestParam(value = "limit") Integer limit,
            @RequestParam(value = "offset") Integer offset,
            @RequestParam(value = "id", required = false) Integer id,
            @RequestParam(value = "keyword", required = false) String keyword){
    	 Map map = new HashMap<String,Object>();
         map.put("id", id);
         map.put("keyword", keyword);
        return (Page<CdExpress>) mybatisDao.selectPage(ExpressMapper.ExpressMapperNameSpace + "getExpressList", map, limit, offset);
    }
    
    @RequestMapping(value = "/express", method = RequestMethod.POST)
    @Log(actionName = "新增快递、公司")
    public void createExpress(@RequestBody @Valid CdExpress createExpress, BindingResult bindingResult) throws ArgumentValidException {
        if(bindingResult != null && bindingResult.hasErrors()){
            throw new ArgumentValidException(bindingResult);
        }
        expressService.createExpress(createExpress);
    }
    
    
    /**
     * 快递商查询已分配未提货订单
     * 
     * @param limit
     * @param offset
     * @param keyword
     * @return
     */
    @RequestMapping(value = "/express/noScanOrder", method = RequestMethod.GET)
    @Log(actionName = "快递商查询已分配未提货订单")
    public Page<OrderModel> noScanOrder(@RequestParam(value = "limit") Integer limit,
                                  @RequestParam(value = "offset") Integer offset,
                                  @RequestParam(value = "keyword", required = false) String keyword,
                              		@RequestParam(value = "startTime", required = false) String startTime,
                              		@RequestParam(value = "endTime", required = false) String endTime){
    	OrderQuery orderQuery = new OrderQuery();
		if (StringUtilsExt.isNotBlank(startTime)
				&& !"undefined".equals(startTime)) {
			orderQuery.setOrderCreateTimeStart(startTime.substring(0, 10));
		}
		if (StringUtilsExt.isNotBlank(endTime) && !"undefined".equals(endTime)) {
			orderQuery.setOrderCreateTimeEnd(endTime.substring(0, 10));
		}
		// 运输部
		if (SecurityUtils.getSubject().hasRole(
				AppConstants.YUN_SHU_PERMISSION_CODE)) {
			String despatchExpressCode = (String) SecurityUtils.getSubject()
					.getPrincipal();
			orderQuery.setDespatchExpressCode(despatchExpressCode);
		}
        return expressService.getOrderNoDespatchReportList(orderQuery, limit, offset);
    }
    
    
    /**
     * 订单列表
     * @param limit
     * @param offset
     * @param keyword
     * @return
     */
    @RequestMapping(value = "/express/order", method = RequestMethod.GET)
    @Log(actionName = "查询订单列表")
    public Page<OrderModel> getUserList(@RequestParam(value = "limit") Integer limit,
                                  @RequestParam(value = "offset") Integer offset,
                                  @RequestParam(value = "orderStatus", required = false) Integer orderStatus,
                                  @RequestParam(value = "keyword", required = false) String keyword,
                                  @RequestParam(value = "orderCreateTimeStart",required = false) String orderCreateTimeStart,
                                  @RequestParam(value = "orderCreateTimeEnd",required = false) String orderCreateTimeEnd,
                                  @RequestParam(value = "managerId", required = false) Integer managerId,
                                  @RequestParam(value = "consigneeName", required = false) String consigneeName,
                                  @RequestParam(value = "hasNoShowCancel", required = false) Boolean hasNoShowCancel){
        OrderQuery orderQuery = new OrderQuery();
        orderQuery.setKeyword(keyword);
        orderQuery.setOrderStatus(orderStatus);
        orderQuery.setManagerId(managerId);
        orderQuery.setHasNoShowCancel(hasNoShowCancel == null?false:hasNoShowCancel);
        orderQuery.setConsigneeName(consigneeName);
        if(StringUtilsExt.isNotBlank(orderCreateTimeStart)){
       	 orderQuery.setOrderCreateTimeStart(orderCreateTimeStart.substring(0, 10));
        }
        if(StringUtilsExt.isNotBlank(orderCreateTimeEnd)){
        	orderQuery.setOrderCreateTimeEnd(orderCreateTimeEnd.substring(0, 10));
        }
        //运输部
        if(SecurityUtils.getSubject().hasRole(AppConstants.YUN_SHU_PERMISSION_CODE)){
        	String despatchExpressCode = (String) SecurityUtils.getSubject().getPrincipal();
            orderQuery.setDespatchExpressCode(despatchExpressCode);
        }
        return expressService.getTakeDeliveryList(orderQuery, limit, offset);
    }
    
    /**
     *  修改
     * @param id
     */
    @RequestMapping(value = "/express/{id}", method = RequestMethod.PUT)
    @Log(actionName = "修改快递信息")
    public void updateMember(@PathVariable(value = "id") Integer id,
                                @RequestBody @Valid CdExpress updateExpress, BindingResult bindingResult) throws ArgumentValidException {
        if(bindingResult != null && bindingResult.hasErrors()){
            throw new ArgumentValidException(bindingResult);
        }
        expressService.updateExpress(id, updateExpress);
    }
    
    /**
     *  删除
     * @param id
     */
    @RequestMapping(value = "/express/{id}", method = RequestMethod.DELETE)
    @Log(actionName = "删除快递信息")
    public void deleteMember(@PathVariable(value = "id") Integer id){
    	expressService.delete(id);
    }
    
    
    /**
     * 分配快递商
     * @param packingTask
     * @param bindingResult
     * @return
     */
    @RequestMapping(value = "/express/order/bind", method = RequestMethod.PUT)
    @Log(actionName = "分配快递商")
    public void createPacking(@RequestBody @Valid ExpressTask expressTask, BindingResult bindingResult) throws ArgumentValidException {
        if(bindingResult != null && bindingResult.hasErrors()){
            throw new ArgumentValidException(bindingResult);
        }
        expressService.dispatchExpress(expressTask);
    }

    /**
     * 解绑快递商
     * @param orderNoList
     * @throws ArgumentValidException
     */
    @RequestMapping(value = "/express/order/unbind", method = RequestMethod.PUT)
    @Log(actionName = "解绑快递商")
    public void createPacking(@RequestParam(value = "orderNos", required = true)String[] orderNoList) throws ArgumentValidException {
        if(orderNoList != null && orderNoList.length <= 0){
            return;
        }
        expressService.cancelExpress(orderNoList);
    }
    
    @RequestMapping(value ="/express/order/takeDelivery",method = RequestMethod.PUT)
    @Log(actionName = "快递商提货")
    /**
     * 
     * @param boxNo 箱号
     */
    public void takeDelivery(@RequestParam(value = "boxNo",required = true)String boxNo){
    	expressService.takeDelivery(boxNo);
    }
    
    @RequestMapping(value ="/express/order/untakeDelivery",method = RequestMethod.PUT)
    @Log(actionName = "快递商取消提货")
    public void untakeDelivery(@RequestParam(value = "orderNo",required = true)String orderNo){
    	expressService.unTakeDelivery(orderNo);
    }
}

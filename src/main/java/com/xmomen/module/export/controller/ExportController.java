package com.xmomen.module.export.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.shiro.SecurityUtils;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xmomen.framework.utils.StringUtilsExt;
import com.xmomen.module.base.constant.AppConstants;
import com.xmomen.module.base.service.ExpressService;
import com.xmomen.module.export.service.ExportService;
import com.xmomen.module.export.util.PrintUtils;
import com.xmomen.module.order.model.OrderModel;
import com.xmomen.module.order.model.OrderQuery;
import com.xmomen.module.order.model.PurchaseModel;
import com.xmomen.module.order.service.PurchaseService;
import com.xmomen.module.report.model.OrderDeliveryReport;
import com.xmomen.module.report.model.OrderReport;

@Controller
public class ExportController {

	@Autowired
	ExportService exportService;
	@Autowired
	ExpressService expressService;

	@Autowired
	PurchaseService purchaseService;

	/**
	 * 导出未采购的采购物料
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/export/exportPurchaseExcel")
	public String exportPurchaseExcel(HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap) throws IOException {
		Map param = new HashMap();
		param.put("purchaseStatus", 0);
		List<PurchaseModel> purchaseModels = purchaseService
				.getPurchaseList(param);
		modelMap.put(NormalExcelConstants.FILE_NAME, "未采购商品信息");
		modelMap.put(NormalExcelConstants.PARAMS, new ExportParams());
		modelMap.put(NormalExcelConstants.CLASS, PurchaseModel.class);
		modelMap.put(NormalExcelConstants.DATA_LIST, purchaseModels);
		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}

	/**
	 * 快递商导出订单信息
	 * 
	 * @param request
	 * @param response
	 * @param startTime
	 * @param endTime
	 * @throws IOException
	 */
	@RequestMapping(value = "/export/exportTakeDeliveryExcel")
	public String exportTakeDeliveryExcel(
			HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "startTime", required = false) String startTime,
			@RequestParam(value = "endTime", required = false) String endTime,
			@RequestParam(value = "type") int type, ModelMap modelMap)
			throws IOException {
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
		if (type == 1) {
			List<OrderDeliveryReport> takeDeliverys = expressService
					.getTakeDeliveryReportList(orderQuery);
			modelMap.put(NormalExcelConstants.FILE_NAME, "已提货订单信息");
			modelMap.put(NormalExcelConstants.DATA_LIST, takeDeliverys);
		} else {
			List<OrderDeliveryReport> takeDeliverys = expressService
					.getOrderNoDespatchReportList(orderQuery);
			modelMap.put(NormalExcelConstants.FILE_NAME, "已分配未提货订单信息");
			modelMap.put(NormalExcelConstants.DATA_LIST, takeDeliverys);
		}

		modelMap.put(NormalExcelConstants.PARAMS, new ExportParams());
		modelMap.put(NormalExcelConstants.CLASS, OrderDeliveryReport.class);

		return NormalExcelConstants.JEECG_EXCEL_VIEW;
	}
}

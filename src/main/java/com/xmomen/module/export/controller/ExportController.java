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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xmomen.framework.utils.StringUtilsExt;
import com.xmomen.module.base.constant.AppConstants;
import com.xmomen.module.export.service.ExportService;
import com.xmomen.module.export.util.PrintUtils;
import com.xmomen.module.order.model.OrderQuery;


@RestController
public class ExportController {
	
	@Autowired
	ExportService exportService;
	
	/**
	 * 导出未采购的采购物料
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/export/exportPurchaseExcel")
	public void exportPurchaseExcel(HttpServletRequest request,HttpServletResponse response) throws IOException {
		String templatePath = request.getServletContext().getRealPath("") + "/WEB-INF/excelFile/purchaseExcel.xlsx";
		XSSFWorkbook workbook = exportService.exportPurchaseExcel(templatePath);
		PrintUtils.labelResponseOutput(response, workbook, new String(System.currentTimeMillis() + ".xlsx"));
	}
	/**
	 * 快递商导出订单信息
	 * @param request
	 * @param response
	 * @param startTime
	 * @param endTime
	 * @throws IOException
	 */
	@RequestMapping(value="/export/exportTakeDeliveryExcel")
	public void exportTakeDeliveryExcel(HttpServletRequest request,HttpServletResponse response,
            @RequestParam(value = "startTime",required = false) String startTime,
            @RequestParam(value = "endTime",required = false) String endTime) throws IOException {
		 OrderQuery orderQuery = new OrderQuery();
		 if(StringUtilsExt.isNotBlank(startTime) && !"undefined".equals(startTime)){
	       	 orderQuery.setOrderCreateTimeStart(startTime.substring(0, 10));
	        }
        if(StringUtilsExt.isNotBlank(endTime) && !"undefined".equals(endTime)){
        	orderQuery.setOrderCreateTimeEnd(endTime.substring(0, 10));
        }
	      //运输部
        if(SecurityUtils.getSubject().hasRole(AppConstants.YUN_SHU_PERMISSION_CODE)){
        	String despatchExpressCode = (String) SecurityUtils.getSubject().getPrincipal();
            orderQuery.setDespatchExpressCode(despatchExpressCode);
        }
		String templatePath = request.getServletContext().getRealPath("") + "/WEB-INF/excelFile/takeDeliveryExcel.xlsx";
		XSSFWorkbook workbook = exportService.exportTakeDeliveryExcel(templatePath,orderQuery);
		PrintUtils.labelResponseOutput(response, workbook, new String(System.currentTimeMillis() + ".xlsx"));
	}
}

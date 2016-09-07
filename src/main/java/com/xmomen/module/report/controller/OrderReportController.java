package com.xmomen.module.report.controller;

import com.xmomen.framework.utils.StringUtilsExt;
import com.xmomen.module.order.model.OrderQuery;
import com.xmomen.module.order.service.OrderService;
import com.xmomen.module.report.model.OrderReport;
import com.xmomen.module.report.model.UploadFileVo;
import org.apache.poi.ss.usermodel.Workbook;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.TemplateExportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.vo.TemplateExcelConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tanxinzheng on 16/9/3.
 */
@Controller
public class OrderReportController {

    @Autowired
    OrderService orderService;


    /**
     * 订单导出
     * @param modelMap
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="/order/report", method = RequestMethod.GET)
    public String exportOrder(
            @RequestParam(value = "orderCreateTimeStart",required = false) String orderCreateTimeStart,
            @RequestParam(value = "orderCreateTimeEnd",required = false) String orderCreateTimeEnd,
            @RequestParam(value = "managerId", required = false) Integer managerId,
            @RequestParam(value = "companyId", required = false) Integer companyId,
            ModelMap modelMap) {
        OrderQuery orderQuery = new OrderQuery();
        orderQuery.setCompanyId(companyId);
        orderQuery.setManagerId(managerId);
        if(StringUtilsExt.isNotBlank(orderCreateTimeStart)){
            orderQuery.setOrderCreateTimeStart(orderCreateTimeStart.substring(0, 10));
        }
        if(StringUtilsExt.isNotBlank(orderCreateTimeEnd)){
            orderQuery.setOrderCreateTimeEnd(orderCreateTimeEnd.substring(0, 10));
        }
        List<OrderReport> list = orderService.getOrderReportList(orderQuery);
        modelMap.put(NormalExcelConstants.FILE_NAME, "订单导出信息");
        modelMap.put(NormalExcelConstants.PARAMS, new ExportParams());
        modelMap.put(NormalExcelConstants.CLASS, OrderReport.class);
        modelMap.put(NormalExcelConstants.DATA_LIST, list);
        return NormalExcelConstants.JEECG_EXCEL_VIEW;
    }

}

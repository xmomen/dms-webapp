package com.xmomen.module.report.controller;

import com.xmomen.framework.utils.StringUtilsExt;
import com.xmomen.module.report.model.OrderReport;
import com.xmomen.module.report.model.UploadFileVo;
import org.apache.poi.ss.usermodel.Workbook;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.TemplateExportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.vo.TemplateExcelConstants;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
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


    @RequestMapping(value="/order/report")
    public String downDesaptchImportTemplate(ModelMap modelMap, HttpServletRequest request,HttpServletResponse response) {
        List<OrderReport> list = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            OrderReport orderReport = new OrderReport();
            orderReport.setAmount(new BigDecimal(324));
            orderReport.setOrderNo(StringUtilsExt.getUUID(32));
            orderReport.setConsigneeName("谭新政");
            list.add(orderReport);
        }
        modelMap.put(NormalExcelConstants.FILE_NAME, "订单导出信息");
        modelMap.put(NormalExcelConstants.PARAMS, new ExportParams());
        modelMap.put(NormalExcelConstants.CLASS, OrderReport.class);
        modelMap.put(NormalExcelConstants.DATA_LIST, list);
        return NormalExcelConstants.JEECG_EXCEL_VIEW;
    }

}

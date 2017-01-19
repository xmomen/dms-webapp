package com.xmomen.module.report.controller;

import com.xmomen.framework.utils.StringUtilsExt;
import com.xmomen.module.base.constant.AppConstants;
import com.xmomen.module.order.model.OrderQuery;
import com.xmomen.module.order.service.OrderService;
import com.xmomen.module.report.model.ExpressReport;
import com.xmomen.module.report.model.FinanceReport;
import com.xmomen.module.report.model.OrderReport;
import com.xmomen.module.report.model.ReportQuery;
import com.xmomen.module.report.service.ReportOrderService;
import org.apache.shiro.SecurityUtils;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.vo.NormalExcelConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by tanxinzheng on 16/9/3.
 */
@Controller
public class OrderReportController {

    @Autowired
    ReportOrderService reportOrderService;


    /**
     * 订单导出
     *
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/report/order", method = RequestMethod.GET)
    public String exportOrder(
            @RequestParam(value = "beginTime", required = false) String beginTime,
            @RequestParam(value = "endTime", required = false) String endTime,
            @RequestParam(value = "companyId", required = false) Integer companyId,
            @RequestParam(value = "managerId", required = false) Integer managerId,
            ModelMap modelMap) {
        ReportQuery reportQuery = new ReportQuery();
        if (StringUtilsExt.isNotBlank(beginTime)) {
            reportQuery.setBeginTime(beginTime);
        }
        if (StringUtilsExt.isNotBlank(endTime)) {
            reportQuery.setEndTime(endTime);
        }
        reportQuery.setCompanyId(companyId);
        reportQuery.setManagerId(managerId);
        //客服经理过滤 如果有客服组权限则不过滤
        if (SecurityUtils.getSubject().hasRole(AppConstants.CUSTOMER_MANAGER_PERMISSION_CODE) && !SecurityUtils.getSubject().hasRole(AppConstants.CUSTOMER_PERMISSION_CODE)) {
            Integer userId = (Integer) SecurityUtils.getSubject().getSession().getAttribute(AppConstants.SESSION_USER_ID_KEY);
            reportQuery.setManagerId(userId);
        }
        List<OrderReport> list = reportOrderService.getOrderReportList(reportQuery);
        String[] beginTimes = beginTime.split("-");
        String[] endTimes = endTime.split("-");
        modelMap.put(NormalExcelConstants.FILE_NAME, beginTimes[0] + "年" + beginTimes[1] + "月" + beginTimes[2] + "日-" + endTimes[0] + "年" + endTimes[1] + "月" + endTimes[2] + "日订单报表");
        modelMap.put(NormalExcelConstants.PARAMS, new ExportParams());
        modelMap.put(NormalExcelConstants.CLASS, OrderReport.class);
        modelMap.put(NormalExcelConstants.DATA_LIST, list);
        return NormalExcelConstants.JEECG_EXCEL_VIEW;
    }

    /**
     * 物流导出
     *
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/report/express", method = RequestMethod.GET)
    public String exportExpress(
            @RequestParam(value = "beginTime", required = false) String beginTime,
            @RequestParam(value = "endTime", required = false) String endTime,
            @RequestParam(value = "companyId", required = false) Integer companyId,
            @RequestParam(value = "managerId", required = false) Integer managerId,
            ModelMap modelMap) {
        ReportQuery reportQuery = new ReportQuery();
        if (StringUtilsExt.isNotBlank(beginTime)) {
            reportQuery.setBeginTime(beginTime);
        }
        if (StringUtilsExt.isNotBlank(endTime)) {
            reportQuery.setEndTime(endTime);
        }
        reportQuery.setCompanyId(companyId);
        reportQuery.setManagerId(managerId);
        //客服经理过滤 如果有客服组权限则不过滤
        if (SecurityUtils.getSubject().hasRole(AppConstants.CUSTOMER_MANAGER_PERMISSION_CODE) && !SecurityUtils.getSubject().hasRole(AppConstants.CUSTOMER_PERMISSION_CODE)) {
            Integer userId = (Integer) SecurityUtils.getSubject().getSession().getAttribute(AppConstants.SESSION_USER_ID_KEY);
            reportQuery.setManagerId(userId);
        }
        List<ExpressReport> list = reportOrderService.getExpressReportList(reportQuery);
        String[] beginTimes = beginTime.split("-");
        String[] endTimes = endTime.split("-");
        modelMap.put(NormalExcelConstants.FILE_NAME, beginTimes[0] + "年" + beginTimes[1] + "月" + beginTimes[2] + "日-" + endTimes[0] + "年" + endTimes[1] + "月" + endTimes[2] + "日物流报表");
        modelMap.put(NormalExcelConstants.PARAMS, new ExportParams());
        modelMap.put(NormalExcelConstants.CLASS, ExpressReport.class);
        modelMap.put(NormalExcelConstants.DATA_LIST, list);
        return NormalExcelConstants.JEECG_EXCEL_VIEW;
    }

    /**
     * 财务导出
     *
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/report/finance", method = RequestMethod.GET)
    public String exportFinance(
            @RequestParam(value = "beginTime", required = false) String beginTime,
            @RequestParam(value = "endTime", required = false) String endTime,
            @RequestParam(value = "companyId", required = false) Integer companyId,
            @RequestParam(value = "managerId", required = false) Integer managerId,
            ModelMap modelMap) {
        ReportQuery reportQuery = new ReportQuery();
        if (StringUtilsExt.isNotBlank(beginTime)) {
            reportQuery.setBeginTime(beginTime);
        }
        if (StringUtilsExt.isNotBlank(endTime)) {
            reportQuery.setEndTime(endTime);
        }
        reportQuery.setCompanyId(companyId);
        reportQuery.setManagerId(managerId);
        //客服经理过滤 如果有客服组权限则不过滤
        if (SecurityUtils.getSubject().hasRole(AppConstants.CUSTOMER_MANAGER_PERMISSION_CODE) && !SecurityUtils.getSubject().hasRole(AppConstants.CUSTOMER_PERMISSION_CODE)) {
            Integer userId = (Integer) SecurityUtils.getSubject().getSession().getAttribute(AppConstants.SESSION_USER_ID_KEY);
            reportQuery.setManagerId(userId);
        }
        List<FinanceReport> list = reportOrderService.getFinanceReportList(reportQuery);
//        String[] beginTimes = beginTime.split("-");
//        String[] endTimes = endTime.split("-");
//        modelMap.put(NormalExcelConstants.FILE_NAME, beginTimes[0] + "年" + beginTimes[1] + "月" + beginTimes[2] + "日-" + endTimes[0] + "年" + endTimes[1] + "月" + endTimes[2] + "日财务报表");
        modelMap.put(NormalExcelConstants.FILE_NAME, "财务报表");
        modelMap.put(NormalExcelConstants.PARAMS, new ExportParams());
        modelMap.put(NormalExcelConstants.CLASS, FinanceReport.class);
        modelMap.put(NormalExcelConstants.DATA_LIST, list);
        return NormalExcelConstants.JEECG_EXCEL_VIEW;
    }
}

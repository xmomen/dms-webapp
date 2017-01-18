package com.xmomen.module.report.service;

import com.xmomen.framework.mybatis.dao.MybatisDao;
import com.xmomen.framework.mybatis.page.Page;
import com.xmomen.framework.utils.AssertExt;
import com.xmomen.framework.utils.StringUtilsExt;
import com.xmomen.module.base.constant.AppConstants;
import com.xmomen.module.base.service.ItemService;
import com.xmomen.module.order.entity.*;
import com.xmomen.module.order.mapper.OrderMapper;
import com.xmomen.module.receipt.mapper.ReturnOrderMapper;
import com.xmomen.module.receipt.model.ReturnOrderModel;
import com.xmomen.module.receipt.model.ReturnOrderQuery;
import com.xmomen.module.report.mapper.ReportOrderMapper;
import com.xmomen.module.report.model.ExpressReport;
import com.xmomen.module.report.model.FinanceReport;
import com.xmomen.module.report.model.OrderReport;
import com.xmomen.module.report.model.ReportQuery;
import com.xmomen.module.wx.model.AjaxResult;
import org.apache.shiro.SecurityUtils;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Jeng on 16/4/5.
 */
@Service
public class ReportOrderService {

    @Autowired
    MybatisDao mybatisDao;

    /**
     * 查询订单报表
     *
     * @param reportQuery
     * @return
     */
    public List<OrderReport> getOrderReportList(ReportQuery reportQuery) {
        List<OrderReport> orderReportList = mybatisDao.getSqlSessionTemplate().selectList(ReportOrderMapper.REPORT_ORDER_MAPPER_NAMESPACE + "getReportOrderList", reportQuery);
        for (OrderReport orderReport : orderReportList) {
            if ("1".equals(orderReport.getPaymentMode())) {
                orderReport.setManagerAmount(orderReport.getPayAmount());
            }
            else if ("4".equals(orderReport.getPaymentMode())) {
                orderReport.setExpressAmount(orderReport.getPayAmount());
            }
            else if ("5".equals(orderReport.getPaymentMode())) {
                orderReport.setCouponAmount(orderReport.getPayAmount());
            }
            else if ("7".equals(orderReport.getPaymentMode())) {
                orderReport.setQuanAmount(orderReport.getPayAmount());
            }
            else if ("2".equals(orderReport.getPaymentMode())) {
                orderReport.setPosAmount(orderReport.getPayAmount());
            }
            else if ("3".equals(orderReport.getPaymentMode())) {
                orderReport.setZhuanZhanAmount(orderReport.getPayAmount());
            }
            else if ("6".equals(orderReport.getPaymentMode())) {
                orderReport.setOtherAmount(orderReport.getPayAmount());
            }
            else if ("8".equals(orderReport.getPaymentMode())) {
                orderReport.setHuiKuiAmount(orderReport.getPayAmount());
            }
            //其他付款方式
            if ("1".equals(orderReport.getOtherPaymentMode())) {
                orderReport.setManagerAmount(orderReport.getOtherPayAmount());
            }
            else if ("4".equals(orderReport.getOtherPaymentMode())) {
                orderReport.setExpressAmount(orderReport.getOtherPayAmount());
            }
            else if ("5".equals(orderReport.getOtherPaymentMode())) {
                orderReport.setCouponAmount(orderReport.getOtherPayAmount());
            }
            else if ("7".equals(orderReport.getOtherPaymentMode())) {
                orderReport.setQuanAmount(orderReport.getOtherPayAmount());
            }
            else if ("2".equals(orderReport.getOtherPaymentMode())) {
                orderReport.setPosAmount(orderReport.getOtherPayAmount());
            }
            else if ("3".equals(orderReport.getOtherPaymentMode())) {
                orderReport.setZhuanZhanAmount(orderReport.getOtherPayAmount());
            }
            else if ("6".equals(orderReport.getOtherPaymentMode())) {
                orderReport.setOtherAmount(orderReport.getOtherPayAmount());
            }
            else if ("8".equals(orderReport.getOtherPaymentMode())) {
                orderReport.setHuiKuiAmount(orderReport.getOtherPayAmount());
            }
        }

        return orderReportList;
    }

    /**
     * 查询物流报表
     *
     * @param reportQuery
     * @return
     */
    public List<ExpressReport> getExpressReportList(ReportQuery reportQuery) {
        List<ExpressReport> expressReportList = mybatisDao.getSqlSessionTemplate().selectList(ReportOrderMapper.REPORT_ORDER_MAPPER_NAMESPACE + "getReportExpressList", reportQuery);
        for (ExpressReport expressReport : expressReportList) {
            if (expressReport.getIsReject() == 0) {
                expressReport.setIsNormal(1);
                expressReport.setIsNoNormal(0);
            }
            else {
                expressReport.setIsNormal(0);
                expressReport.setIsNoNormal(1);
            }
        }
        return expressReportList;
    }

    /**
     * 查询财务报表
     *
     * @param reportQuery
     * @return
     */
    public List<FinanceReport> getFinanceReportList(ReportQuery reportQuery) {
        List<FinanceReport> financeReportList = mybatisDao.getSqlSessionTemplate().selectList(ReportOrderMapper.REPORT_ORDER_MAPPER_NAMESPACE + "getReportFinanceList", reportQuery);
        return financeReportList;
    }

}

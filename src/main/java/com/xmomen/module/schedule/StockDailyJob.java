package com.xmomen.module.schedule;


import com.xmomen.framework.support.SpringContextUtil;
import com.xmomen.module.order.model.CreatePurchase;
import com.xmomen.module.order.service.PurchaseService;
import com.xmomen.module.stockdaily.service.StockDailyService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by Jeng on 2016/2/23.
 */
@Component(value = "stockDailyJob")
public class StockDailyJob implements Job {

    StockDailyService stockDailyService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        if (stockDailyService == null) {
            stockDailyService = (StockDailyService) SpringContextUtil.getApplicationContext().getBean(StockDailyService.class);
            stockDailyService.createStockDaily();
        }
    }
}

package com.xmomen.module.schedule;


import com.xmomen.framework.support.SpringContextUtil;
import com.xmomen.module.order.model.CreatePurchase;
import com.xmomen.module.order.service.PurchaseService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Jeng on 2016/2/23.
 */
@Component(value = "itemPurchaseJob")
public class ItemPurchaseJob implements Job {

    PurchaseService purchaseService;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        if(purchaseService == null){
            purchaseService = (PurchaseService) SpringContextUtil.getApplicationContext().getBean(PurchaseService.class);
            CreatePurchase createPurchase = new CreatePurchase();
            createPurchase.setOrderDate(new Date());
            purchaseService.createPurchase(createPurchase);
        }
    }
}

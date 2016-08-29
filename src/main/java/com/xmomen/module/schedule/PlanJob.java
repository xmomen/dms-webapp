package com.xmomen.module.schedule;


import com.xmomen.framework.support.SpringContextUtil;
import com.xmomen.module.order.model.CreatePurchase;
import com.xmomen.module.order.service.PurchaseService;
import com.xmomen.module.plan.service.TablePlanSercvice;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 */
@Component(value = "planJob")
public class PlanJob implements Job {

	TablePlanSercvice tablePlanSercvice;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        if(tablePlanSercvice == null){
        	tablePlanSercvice = (TablePlanSercvice) SpringContextUtil.getApplicationContext().getBean(TablePlanSercvice.class);
            tablePlanSercvice.createTablePlanOrder();
        }
    }
}

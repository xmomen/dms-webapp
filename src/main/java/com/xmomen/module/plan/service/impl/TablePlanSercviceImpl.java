package com.xmomen.module.plan.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xmomen.framework.mybatis.dao.MybatisDao;
import com.xmomen.module.base.entity.CdPlan;
import com.xmomen.module.base.entity.CdPlanItem;
import com.xmomen.module.order.model.CreateOrder;
import com.xmomen.module.order.model.CreateOrder.OrderItem;
import com.xmomen.module.order.service.OrderService;
import com.xmomen.module.plan.entity.TbTablePlan;
import com.xmomen.module.plan.entity.TbTablePlanExample;
import com.xmomen.module.plan.mapper.TablePlanMapper;
import com.xmomen.module.plan.model.CreateTablePlan;
import com.xmomen.module.plan.model.TablePlanModel;
import com.xmomen.module.plan.model.UpdateTablePlan;
import com.xmomen.module.plan.service.TablePlanSercvice;

@Service
public class TablePlanSercviceImpl implements TablePlanSercvice {
	@Autowired
	MybatisDao mybatisDao;
	
	@Autowired
	OrderService orderService;

	@Override
	@Transactional
	public void createTablePlan(CreateTablePlan createTablePlan) {
		for(TbTablePlan tablePlan :createTablePlan.getTablePlans()){
			tablePlan.setAuditStatus(1);
			tablePlan.setCdMemberId(createTablePlan.getCdMemberId());
			tablePlan.setConsigneeAddress(createTablePlan.getConsigneeAddress());
			tablePlan.setConsigneeName(createTablePlan.getConsigneeName());
			tablePlan.setConsigneePhone(createTablePlan.getConsigneePhone());
			tablePlan.setCouponNumber(createTablePlan.getCouponNumber());
			tablePlan.setSendValue(0);
			CdPlan plan = mybatisDao.selectByPrimaryKey(CdPlan.class,tablePlan.getCdPlanId());
			tablePlan.setTotalSendValue(plan.getDeliverCount());
			mybatisDao.saveByModel(tablePlan);
		}
	}
	
	@Transactional
	public void updateTablePlan(Integer id,UpdateTablePlan updateTablePlan) {
		TbTablePlan tablePlan = new TbTablePlan();
		tablePlan.setId(id);
		tablePlan.setCdPlanId(updateTablePlan.getCdPlanId());
		tablePlan.setCdMemberId(updateTablePlan.getCdMemberId());
		tablePlan.setConsigneeAddress(updateTablePlan.getConsigneeAddress());
		tablePlan.setConsigneeName(updateTablePlan.getConsigneeName());
		tablePlan.setConsigneePhone(updateTablePlan.getConsigneePhone());
		tablePlan.setCouponNumber(updateTablePlan.getCouponNumber());
		CdPlan plan = mybatisDao.selectByPrimaryKey(CdPlan.class,updateTablePlan.getCdPlanId());
		tablePlan.setTotalSendValue(plan.getDeliverCount());
		mybatisDao.saveByModel(tablePlan);
	}
	
	@Transactional
    public void delete(Integer id){
        mybatisDao.deleteByPrimaryKey(TbTablePlan.class, id);
    }

	/**
	 * 生成餐桌计划订单
	 */
	@Override
	@Transactional
	public void createTablePlanOrder() {
		//获取今天星期几
		Date currentDate = new Date();
		Calendar cal = Calendar.getInstance();
        cal.setTime(currentDate);
        //查找后一天的送货计划
        int weekday = cal.get(Calendar.DAY_OF_WEEK);
        SimpleDateFormat dateFm = new SimpleDateFormat("YYYY-MM-dd");
		//查询出所有未暂停的餐桌计划
		 Map map = new HashMap<String,Object>();
	     map.put("weekday", weekday+"");		
	     map.put("beginTime", dateFm.format(new Date()));
	     List<TablePlanModel> tablePlanList = mybatisDao.getSqlSessionTemplate().selectList(TablePlanMapper.TablePlanMapperNameSpace + "getNormalTablePlanList", map);
		if(tablePlanList.size() == 0){
			return ;
		}
	     //拼装订单
		Map<String,List<TablePlanModel>> tablePlanMap = new HashMap<String,List<TablePlanModel>>();
		for(TablePlanModel tablePlanModel:tablePlanList){
			//先判断是否是要配送的订单
			//上次配送时间
			Date lastSendDate = tablePlanModel.getLastSendDate();
			if(lastSendDate != null){
				//获取送货频次
				Integer deliveryType = tablePlanModel.getDeliveryType();
				//获取间隔天数
				Integer day = deliveryType * 7;
				Date newDate = addDate(lastSendDate,day);
				 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
				 //上次配送时间+间隔时间 正好不等于当前时间
				 if (!dateFormat.format(currentDate).equals(dateFormat.format(newDate))) {
					continue; 
				 }
			}
			//有效订单
			if(tablePlanMap.containsKey(tablePlanModel.getConsigneePhone())){
				tablePlanMap.get(tablePlanModel.getConsigneePhone()).add(tablePlanModel);
			}else{
				List<TablePlanModel> tablePlanModels = new ArrayList<TablePlanModel>();
				tablePlanModels.add(tablePlanModel);
				tablePlanMap.put(tablePlanModel.getConsigneePhone(), tablePlanModels);
			}
			//更新订单的最后次送货时间 和次数
			TbTablePlanExample tbTablePlanExample = new TbTablePlanExample();
			tbTablePlanExample.createCriteria()
                    .andIdEqualTo(tablePlanModel.getId());
                 
            TbTablePlan tbTablePlan = new TbTablePlan();
            tbTablePlan.setLastSendDate(currentDate);
            tbTablePlan.setSendValue(tablePlanModel.getSendValue()+1);
            mybatisDao.updateOneByExampleSelective(tbTablePlan, tbTablePlanExample);
		}
		//下单
		if(tablePlanMap.size() > 0){
			createOrderFormTablePlan(tablePlanMap);
		}
	}
	
	//餐桌计划下单
	private void createOrderFormTablePlan(Map<String,List<TablePlanModel>> tablePlanMap){
		//一个一个手机号下单
		for(String key :tablePlanMap.keySet()){
			List<TablePlanModel> tablePlanModels = tablePlanMap.get(key);
			TablePlanModel tablePlanModelHead =tablePlanModels.get(0);
			CreateOrder createOrder = new CreateOrder();
			createOrder.setConsigneeName(tablePlanModelHead.getConsigneeName());
			createOrder.setConsigneeAddress(tablePlanModelHead.getConsigneeAddress());
			createOrder.setConsigneePhone(tablePlanModelHead.getConsigneePhone());
	        createOrder.setOrderSource(4);
	        //其他付款方式
	        createOrder.setPaymentMode(6);
	        createOrder.setOrderType(3);
	        createOrder.setCreateUserId(tablePlanModelHead.getManagerId());
	        createOrder.setManagerId(tablePlanModelHead.getManagerId());
	        createOrder.setCompanyId(tablePlanModelHead.getCompanyId());
	        createOrder.setAppointmentTime(addDate(mybatisDao.getSysdate(),1));
	        List<OrderItem> orderItemList = new ArrayList<OrderItem>();
	        //查找商品
			for(TablePlanModel tablePlanModel:tablePlanModels){
				List<CdPlanItem> planItems  = new ArrayList<CdPlanItem>();
				//随机送产品
				if(tablePlanModel.getIsRandom() == 1){
					
				}
				else{
					CdPlanItem cdPlanItem = new CdPlanItem();
					cdPlanItem.setCdPlanId(tablePlanModel.getCdPlanId());
					planItems = mybatisDao.selectByModel(cdPlanItem);
				}
				
				for(CdPlanItem planItem : planItems){
					OrderItem orderItem = new OrderItem();
					orderItem.setOrderItemId(planItem.getCdItemId());
					orderItem.setItemQty(new BigDecimal(planItem.getCountValue()));
					orderItemList.add(orderItem);
				}
			}
			
			createOrder.setOrderItemList(orderItemList);
			//下单
			orderService.createOrder(createOrder);
		}
	}
	
	public static Date addDate(Date d,long day) {
		  long time = d.getTime(); 
		  day = day*24*60*60*1000; 
		  time += day; 
		  return new Date(time);
	}}

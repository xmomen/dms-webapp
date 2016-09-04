package com.xmomen.module.export.service;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xmomen.module.base.service.ExpressService;
import com.xmomen.module.order.model.OrderModel;
import com.xmomen.module.order.model.OrderQuery;
import com.xmomen.module.order.model.PurchaseModel;
import com.xmomen.module.order.service.PurchaseService;


@Service
public class ExportService {
	@Autowired
	PurchaseService purchaseService;
	@Autowired
	ExpressService expressService;
	/**
	 * 采购单导出
	 * @param templatePath
	 * @return
	 */
	public XSSFWorkbook exportPurchaseExcel(String templatePath){
		XSSFWorkbook workbook = new XSSFWorkbook();
        ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();     
		try {
			workbook = new XSSFWorkbook(new FileInputStream(templatePath));
			XSSFSheet sheet = workbook.getSheet("Sheet1");
			 Map param = new HashMap();
	         param.put("purchaseStatus", 0);
			List<PurchaseModel> purchaseModels  = purchaseService.getPurchaseList(param);
			for(int i=0,size=purchaseModels.size();i<size;i++){
				PurchaseModel purchaseModel =purchaseModels.get(i);
				XSSFRow row = sheet.createRow(i+1);
				XSSFCell purchaseNoCell = row.createCell(0);
				purchaseNoCell.setCellValue(purchaseModel.getPurchaseCode());
				XSSFCell itemNameCell = row.createCell(1);;
				itemNameCell.setCellValue(purchaseModel.getItemName());
				XSSFCell totalItemQtyCell = row.createCell(2);;
				totalItemQtyCell.setCellValue(purchaseModel.getTotalItemQty()+"");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if(byteArrayOut!=null)
					byteArrayOut.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	
		return workbook;
	}
	/**
	 * 快速商导出订单
	 * @param templatePath
	 * @param query
	 * @return
	 */
	public XSSFWorkbook exportTakeDeliveryExcel(String templatePath,OrderQuery query){
		XSSFWorkbook workbook = new XSSFWorkbook();
        ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();     
		try {
			workbook = new XSSFWorkbook(new FileInputStream(templatePath));
			XSSFSheet sheet = workbook.getSheet("Sheet1");
			List<OrderModel> takeDeliverys  =  expressService.getTakeDeliveryList(query);
			for(int i=0,size=takeDeliverys.size();i<size;i++){
				OrderModel orderModel =takeDeliverys.get(i);
				XSSFRow row = sheet.createRow(i+1);
				XSSFCell orderNoCell = row.createCell(0);
				orderNoCell.setCellValue(orderModel.getOrderNo());
				XSSFCell expressNameCell = row.createCell(1);
				expressNameCell.setCellValue(orderModel.getExpressName());
				XSSFCell consigneeNameCell = row.createCell(2);
				consigneeNameCell.setCellValue(orderModel.getConsigneeName());
				XSSFCell consigneePhoneCell = row.createCell(3);
				consigneePhoneCell.setCellValue(orderModel.getConsigneePhone());
				XSSFCell consigneeAddressCell = row.createCell(4);
				consigneeAddressCell.setCellValue(orderModel.getConsigneeAddress());
				XSSFCell appointmentTimeCell = row.createCell(5);
				SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd");
				appointmentTimeCell.setCellValue(sFormat.format(orderModel.getAppointmentTime()));
				XSSFCell remarkCell = row.createCell(6);
				remarkCell.setCellValue(orderModel.getRemark());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if(byteArrayOut!=null)
					byteArrayOut.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	
		return workbook;
	}
}

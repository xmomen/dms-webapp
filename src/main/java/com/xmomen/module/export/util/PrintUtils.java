package com.xmomen.module.export.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.xmomen.module.export.model.UploadFileVo;


public class PrintUtils {
	
	public static void excelResponseOutput(HttpServletResponse response, HSSFWorkbook workbook, String outPutFileName) {
		try{
			response.reset();
			response.addHeader("Content-Disposition", "attachment;filename=" + outPutFileName);    
			response.setContentType("application/msexcel-print-Surface;charset=UTF-8");
			OutputStream out = response.getOutputStream();
			workbook.write(out);
			out.flush();
			workbook = null;
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void excelResponseOutput(HttpServletResponse response, XSSFWorkbook workbook, String outPutFileName) {
		try{
			response.reset();
			response.addHeader("Content-Disposition", "attachment;filename=" + outPutFileName);    
			response.setContentType("application/msexcel-print-Surface;charset=UTF-8");
			OutputStream out = response.getOutputStream();
			workbook.write(out);
			out.flush();
			workbook = null;
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void labelResponseOutput(HttpServletResponse response, HSSFWorkbook workbook, String outPutFileName) {
		try{
			response.reset();
			response.addHeader("Content-Disposition", "attachment;filename=" + outPutFileName);    
			response.setContentType("application/msexcel-print-Consignment;charset=UTF-8");
			OutputStream out = response.getOutputStream();
			workbook.write(out);
			out.flush();
			workbook = null;
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void labelResponseOutput(HttpServletResponse response, XSSFWorkbook workbook, String outPutFileName) {
		try{
			response.reset();
			response.addHeader("Content-Disposition", "attachment;filename=" + outPutFileName);    
			response.setContentType("application/msexcel-print-Consignment;charset=UTF-8");
			OutputStream out = response.getOutputStream();
			workbook.write(out);
			out.flush();
			workbook = null;
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 文件下载
	 * 
	 * @param request
	 * @throws Exception
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static HttpServletResponse viewOrDownloadFile(UploadFileVo uploadFile) {
		uploadFile.getResponse().setContentType("UTF-8");
		uploadFile.getResponse().setCharacterEncoding("UTF-8");
		InputStream bis = null;
		BufferedOutputStream bos = null;
		HttpServletResponse response = uploadFile.getResponse();
		HttpServletRequest request = uploadFile.getRequest();
		String ctxPath = request.getSession().getServletContext().getRealPath("/");
		String downLoadPath = "";
		long fileLength = 0;
		if (uploadFile.getRealPath() != null&&uploadFile.getContent() == null) {
			downLoadPath = ctxPath + uploadFile.getRealPath();
			fileLength = new File(downLoadPath).length();
			try {
				bis = new BufferedInputStream(new FileInputStream(downLoadPath));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} else {
			if (uploadFile.getContent() != null)
				bis = new ByteArrayInputStream(uploadFile.getContent());
			fileLength = uploadFile.getContent().length;
		}
		try {
			if (!uploadFile.isView() && uploadFile.getExtend() != null) {
				if (uploadFile.getExtend().equals("text")) {
					response.setContentType("text/plain;");
				} else if (uploadFile.getExtend().equals("doc")) {
					response.setContentType("application/msword;");
				} else if (uploadFile.getExtend().equals("xls")|| uploadFile.getExtend().equals("xlsx")) {
					response.setContentType("application/ms-excel;");
				} else if (uploadFile.getExtend().equals("pdf")) {
					response.setContentType("application/pdf;");
				} else if (uploadFile.getExtend().equals("jpg") || uploadFile.getExtend().equals("jpeg")) {
					response.setContentType("image/jpeg;");
				} else {
					response.setContentType("application/x-msdownload;");
				}
				response.setHeader("Content-disposition", "attachment; filename=" + new String((uploadFile.getTitleField() + "." + uploadFile.getExtend()).getBytes("GBK"), "ISO8859-1"));
				response.setHeader("Content-Length", String.valueOf(fileLength));
			}
			bos = new BufferedOutputStream(response.getOutputStream());
			byte[] buff = new byte[2048];
			int bytesRead;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bis != null) {
					bis.close();
				}
				if (bos != null) {
					bos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return response;
	}
}

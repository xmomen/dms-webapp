package com.xmomen.module.resource.service;

import java.io.File;

import org.apache.commons.lang3.StringUtils;

import com.xmomen.module.resource.api.DfsPath;
import com.xmomen.module.resource.api.DfsSdk;
import com.xmomen.module.resource.api.DfsService;

public class ResourceService {

	public static String getWholeHttpPath(String resourcePath) {
		if(StringUtils.isEmpty(resourcePath)) {
			return "";
		}
		DfsService dfsService = DfsSdk.getDfsInstance();
		// M00 是FDFS文件路径的一个标识
		if(resourcePath.indexOf("M00/") > -1) {
			return dfsService.getHttpPath(resourcePath);
		} else {
			return dfsService.getDmsHttpPath(resourcePath);
		}
	}
	
	public static String uploadFile(File file) {
    	DfsService dfsServcie = DfsSdk.getDfsInstance();
    	DfsPath path = dfsServcie.putObject(file, null, null);
    	if(path == null) return null;
    	return path.getHttpPath();
    }
	
	public static String getDefaultPicPath() {
		DfsService dfsServcie = DfsSdk.getDfsInstance();
		return dfsServcie.getDefaultPath("PICTURE");
	}
}

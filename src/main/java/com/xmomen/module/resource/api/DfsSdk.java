package com.xmomen.module.resource.api;

import java.io.File;

public class DfsSdk {

    public static DfsService getDfsInstance(String dfsType, String bucketName) {
        if ("FastDFS".equalsIgnoreCase(dfsType)) {
            DfsService dfsService = FastDfsService.getInstance(bucketName);
            return dfsService;
        }
        return null;
    }
    
    public static DfsService getDfsInstance() {
    	return getDfsInstance("FastDFS", "group1");
    }
}

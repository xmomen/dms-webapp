package com.xmomen.module.wx.model.requestModel.event;


import java.io.Serializable;

/**
 * 自定义菜单二维码扫描请求封装类
 */
public class RequestMessageEventScanCode extends RequestMessageEventBase implements Serializable {
    //扫描信息
    private ScanCodeInfo scanCodeInfo;

    public ScanCodeInfo getScanCodeInfo() {
        return scanCodeInfo;
    }

    public void setScanCodeInfo(ScanCodeInfo scanCodeInfo) {
        this.scanCodeInfo = scanCodeInfo;
    }

    public class ScanCodeInfo {
        //扫描类型，一般是qrcode
        private String scanType;

        //扫描结果，即二维码对应的字符串信息
        private String scanResult;

        public String getScanType() {
            return scanType;
        }

        public void setScanType(String scanType) {
            this.scanType = scanType;
        }

        public String getScanResult() {
            return scanResult;
        }

        public void setScanResult(String scanResult) {
            this.scanResult = scanResult;
        }
    }
}

package com.xmomen.module.report.model;

import lombok.Data;

import java.io.Serializable;

/**
 */
public @Data
class ReportQuery implements Serializable {
    private String beginTime;
    private String endTime;
    private Integer managerId;
    private Integer companyId;
}

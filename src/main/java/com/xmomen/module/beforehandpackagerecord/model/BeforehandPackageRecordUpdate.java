package com.xmomen.module.beforehandpackagerecord.model;

import lombok.Data;
import com.xmomen.module.beforehandpackagerecord.entity.BeforehandPackageRecord;
import org.springframework.beans.BeanUtils;

    import java.lang.String;
    import java.lang.Integer;
    import java.util.Date;
import java.io.Serializable;

/**
 * @author  tanxinzheng
 * @date    2017-5-18 23:36:38
 * @version 1.0.0
 */
public @Data class BeforehandPackageRecordUpdate implements Serializable {

    /**  */
    private String id;
    /** 包装商品 */
    private Integer cdItemId;
    /** 包装商品数 */
    private Integer packageNum;
    /** 创建时间 */
    private Date insertDate;
    /** 创建人 */
    private Integer insertUserId;
    /** 更新时间 */
    private Date updateDate;
    /** 更新人 */
    private Integer updateUserId;


    public BeforehandPackageRecord getEntity(){
        BeforehandPackageRecord beforehandPackageRecord = new BeforehandPackageRecord();
        BeanUtils.copyProperties(this, beforehandPackageRecord);
        return beforehandPackageRecord;
    }
}

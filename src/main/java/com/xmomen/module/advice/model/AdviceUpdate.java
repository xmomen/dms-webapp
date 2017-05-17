package com.xmomen.module.advice.model;

import lombok.Data;
import com.xmomen.module.advice.entity.Advice;
import org.springframework.beans.BeanUtils;

    import java.lang.String;
    import java.lang.Integer;
    import java.util.Date;
import java.io.Serializable;

/**
 * @author  tanxinzheng
 * @date    2017-5-14 20:05:05
 * @version 1.0.0
 */
public @Data class AdviceUpdate implements Serializable {

    /** 主键 */
    private String id;
    /** 标题 */
    private String title;
    /** 创建时间 */
    private Date insertDate;
    /** 创建人 */
    private Integer insertUserId;
    /** 更新时间 */
    private Date updateDate;
    /** 更新人 */
    private Integer updateUserId;
    /** 内容 */
    private String content;


    public Advice getEntity(){
        Advice advice = new Advice();
        BeanUtils.copyProperties(this, advice);
        return advice;
    }
}

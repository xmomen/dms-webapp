package com.xmomen.module.resource.model;

import lombok.Data;
import com.xmomen.module.resource.entity.Resource;
import org.springframework.beans.BeanUtils;

    import java.lang.Integer;
    import java.lang.String;
import java.io.Serializable;

/**
 * @author  tanxinzheng
 * @date    2017-4-10 23:26:20
 * @version 1.0.0
 */
public @Data class ResourceUpdate implements Serializable {

    /**  */
    private String id;
    /**  */
    private String entityType;
    /**  */
    private String entityId;
    /**  */
    private String path;
    /**  */
    private String resourceType;
    /**  */
    private Integer isDefault;


    public Resource getEntity(){
        Resource resource = new Resource();
        BeanUtils.copyProperties(this, resource);
        return resource;
    }
}

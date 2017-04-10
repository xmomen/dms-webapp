package com.xmomen.module.resource.service;

import com.xmomen.framework.mybatis.page.Page;
import com.xmomen.module.resource.model.ResourceQuery;
import com.xmomen.module.resource.model.ResourceModel;
import com.xmomen.module.resource.entity.Resource;
import org.apache.ibatis.exceptions.TooManyResultsException;

import java.io.Serializable;
import java.util.List;

/**
 * @author  tanxinzheng
 * @date    2017-4-10 23:26:20
 * @version 1.0.0
 */
public interface ResourceService {

    /**
     * 新增资源目录
     * @param  resourceModel   新增资源目录对象参数
     * @return  ResourceModel    资源目录领域对象
     */
    public ResourceModel createResource(ResourceModel resourceModel);

    /**
     * 新增资源目录实体对象
     * @param   resource 新增资源目录实体对象参数
     * @return  Resource 资源目录实体对象
     */
    public Resource createResource(Resource resource);

    /**
    * 批量新增资源目录
    * @param ResourceModel     新增资源目录对象集合参数
    * @return List<ResourceModel>    资源目录领域对象集合
    */
    List<ResourceModel> createResources(List<ResourceModel> resourceModels);

    /**
     * 更新资源目录
     * @param resourceModel    更新资源目录对象参数
     */
    public void updateResource(ResourceModel resourceModel);

    /**
     * 更新资源目录实体对象
     * @param   resource 新增资源目录实体对象参数
     * @return  Resource 资源目录实体对象
     */
    public void updateResource(Resource resource);

    /**
     * 批量删除资源目录
     * @param ids   主键数组
     */
    public void deleteResource(String[] ids);

    /**
     * 删除资源目录
     * @param id   主键
     */
    public void deleteResource(String id);

    /**
     * 查询资源目录领域分页对象（带参数条件）
     * @param resourceQuery 查询参数
     * @param limit     每页最大数
     * @param offset    页码
     * @return Page<ResourceModel>   资源目录参数对象
     */
    public Page<ResourceModel> getResourceModelPage(int limit, int offset, ResourceQuery resourceQuery);

    /**
     * 查询资源目录领域分页对象（无参数条件）
     * @param limit 每页最大数
     * @param offset 页码
     * @return Page<ResourceModel> 资源目录领域对象
     */
    public Page<ResourceModel> getResourceModelPage(int limit, int offset);

    /**
     * 查询资源目录领域集合对象（带参数条件）
     * @param resourceQuery 查询参数对象
     * @return List<ResourceModel> 资源目录领域集合对象
     */
    public List<ResourceModel> getResourceModelList(ResourceQuery resourceQuery);

    /**
     * 查询资源目录领域集合对象（无参数条件）
     * @return List<ResourceModel> 资源目录领域集合对象
     */
    public List<ResourceModel> getResourceModelList();

    /**
     * 查询资源目录实体对象
     * @param id 主键
     * @return Resource 资源目录实体对象
     */
    public Resource getOneResource(String id);

    /**
     * 根据主键查询单个对象
     * @param id 主键
     * @return ResourceModel 资源目录领域对象
     */
    public ResourceModel getOneResourceModel(String id);

    /**
     * 根据查询参数查询单个对象（此方法只用于提供精确查询单个对象，若结果数超过1，则会报错）
     * @param resourceQuery 资源目录查询参数对象
     * @return ResourceModel 资源目录领域对象
     */
    public ResourceModel getOneResourceModel(ResourceQuery resourceQuery) throws TooManyResultsException;
}

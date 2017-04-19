package com.xmomen.module.resource.service.impl;

import com.xmomen.module.resource.api.DfsPath;
import com.xmomen.module.resource.api.DfsSdk;
import com.xmomen.module.resource.api.DfsService;
import com.xmomen.module.resource.entity.Resource;
import com.xmomen.module.resource.entity.ResourceExample;
import com.xmomen.module.resource.mapper.ResourceMapperExt;
import com.xmomen.module.resource.model.ResourceCreate;
import com.xmomen.module.resource.model.ResourceQuery;
import com.xmomen.module.resource.model.ResourceUpdate;
import com.xmomen.module.resource.model.ResourceModel;
import com.xmomen.module.resource.service.ResourceService;
import com.xmomen.framework.mybatis.dao.MybatisDao;
import com.xmomen.framework.mybatis.page.Page;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.exceptions.TooManyResultsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author tanxinzheng
 * @version 1.0.0
 * @date 2017-4-10 23:26:20
 */
@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    MybatisDao mybatisDao;

    /**
     * 新增资源目录
     *
     * @param resourceModel 新增资源目录对象参数
     * @return ResourceModel    资源目录领域对象
     */
    @Override
    @Transactional
    public ResourceModel createResource(ResourceModel resourceModel) {
        Resource resource = createResource(resourceModel.getEntity());
        if (resource != null) {
            return getOneResourceModel(resource.getId());
        }
        return null;
    }

    /**
     * 新增资源目录实体对象
     *
     * @param resource 新增资源目录实体对象参数
     * @return Resource 资源目录实体对象
     */
    @Override
    @Transactional
    public Resource createResource(Resource resource) {
        return mybatisDao.insertByModel(resource);
    }

    /**
     * 批量新增资源目录
     *
     * @param resourceModels 新增资源目录对象集合参数
     * @return List<ResourceModel>    资源目录领域对象集合
     */
    @Override
    @Transactional
    public List<ResourceModel> createResources(List<ResourceModel> resourceModels) {
        List<ResourceModel> resourceModelList = null;
        for (ResourceModel resourceModel : resourceModels) {
            resourceModel = createResource(resourceModel);
            if (resourceModel != null) {
                if (resourceModelList == null) {
                    resourceModelList = new ArrayList<>();
                }
                resourceModelList.add(resourceModel);
            }
        }
        return resourceModelList;
    }

    /**
     * 更新资源目录
     *
     * @param resourceModel 更新资源目录对象参数
     */
    @Override
    @Transactional
    public void updateResource(ResourceModel resourceModel) {
        mybatisDao.update(resourceModel.getEntity());
    }

    /**
     * 更新资源目录实体对象
     *
     * @param resource 新增资源目录实体对象参数
     * @return Resource 资源目录实体对象
     */
    @Override
    @Transactional
    public void updateResource(Resource resource) {
        mybatisDao.update(resource);
    }

    /**
     * 删除资源目录
     *
     * @param ids 主键数组
     */
    @Override
    @Transactional
    public void deleteResource(String[] ids) {
        ResourceExample resourceExample = new ResourceExample();
        resourceExample.createCriteria().andIdIn(Arrays.<String>asList((String[]) ids));
        mybatisDao.deleteByExample(resourceExample);
    }

    /**
     * 删除资源目录
     *
     * @param id 主键
     */
    @Override
    @Transactional
    public void deleteResource(String id) {
        mybatisDao.deleteByPrimaryKey(Resource.class, id);
    }

    /**
     * 查询资源目录领域分页对象（带参数条件）
     *
     * @param limit         每页最大数
     * @param offset        页码
     * @param resourceQuery 查询参数
     * @return Page<ResourceModel>   资源目录参数对象
     */
    @Override
    public Page<ResourceModel> getResourceModelPage(int limit, int offset, ResourceQuery resourceQuery) {
        return (Page<ResourceModel>) mybatisDao.selectPage(ResourceMapperExt.ResourceMapperNameSpace + "getResourceModel", resourceQuery, limit, offset);
    }

    /**
     * 查询资源目录领域分页对象（无参数条件）
     *
     * @param limit  每页最大数
     * @param offset 页码
     * @return Page<ResourceModel> 资源目录领域对象
     */
    @Override
    public Page<ResourceModel> getResourceModelPage(int limit, int offset) {
        return (Page<ResourceModel>) mybatisDao.selectPage(ResourceMapperExt.ResourceMapperNameSpace + "getResourceModel", null, limit, offset);
    }

    /**
     * 查询资源目录领域集合对象（带参数条件）
     *
     * @param resourceQuery 查询参数对象
     * @return List<ResourceModel> 资源目录领域集合对象
     */
    @Override
    public List<ResourceModel> getResourceModelList(ResourceQuery resourceQuery) {
        return mybatisDao.getSqlSessionTemplate().selectList(ResourceMapperExt.ResourceMapperNameSpace + "getResourceModel", resourceQuery);
    }

    /**
     * 查询资源目录领域集合对象（无参数条件）
     *
     * @return List<ResourceModel> 资源目录领域集合对象
     */
    @Override
    public List<ResourceModel> getResourceModelList() {
        return mybatisDao.getSqlSessionTemplate().selectList(ResourceMapperExt.ResourceMapperNameSpace + "getResourceModel");
    }

    /**
     * 查询资源目录实体对象
     *
     * @param id 主键
     * @return Resource 资源目录实体对象
     */
    @Override
    public Resource getOneResource(String id) {
        return mybatisDao.selectByPrimaryKey(Resource.class, id);
    }

    /**
     * 根据主键查询单个对象
     *
     * @param id 主键
     * @return ResourceModel 资源目录领域对象
     */
    @Override
    public ResourceModel getOneResourceModel(String id) {
        ResourceQuery resourceQuery = new ResourceQuery();
        resourceQuery.setId(id);
        return mybatisDao.getSqlSessionTemplate().selectOne(ResourceMapperExt.ResourceMapperNameSpace + "getResourceModel", resourceQuery);
    }

    /**
     * 根据查询参数查询单个对象（此方法只用于提供精确查询单个对象，若结果数超过1，则会报错）
     *
     * @param resourceQuery 资源目录查询参数对象
     * @return ResourceModel 资源目录领域对象
     */
    @Override
    public ResourceModel getOneResourceModel(ResourceQuery resourceQuery) throws TooManyResultsException {
        return mybatisDao.getSqlSessionTemplate().selectOne(ResourceMapperExt.ResourceMapperNameSpace + "getResourceModel", resourceQuery);
    }
}

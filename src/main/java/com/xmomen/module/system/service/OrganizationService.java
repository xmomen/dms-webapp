package com.xmomen.module.system.service;

import com.alibaba.fastjson.JSONObject;
import com.xmomen.framework.mybatis.dao.MybatisDao;
import com.xmomen.module.system.entity.SysOrganization;
import com.xmomen.module.system.entity.SysOrganizationExample;
import com.xmomen.module.system.entity.SysUserOrganization;
import com.xmomen.module.system.entity.SysUserOrganizationExample;
import com.xmomen.module.system.mapper.OrganizationMapper;
import com.xmomen.module.system.model.OrganizationModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jeng on 16/3/25.
 */
@Service
public class OrganizationService {

    @Autowired
    MybatisDao mybatisDao;

    @Autowired
    OrganizationMapper organizationMapper;

    /**
     * 查询树形组织结构
     * @param id
     * @return
     */
    public List<OrganizationModel> getOrganizationTree(Integer id){
        SysOrganizationExample sysOrganizationExample = new SysOrganizationExample();
        List<OrganizationModel> result = new ArrayList<OrganizationModel>();
        if(id == null){
            sysOrganizationExample.createCriteria().andParentIdIsNull();
            List<SysOrganization> sysOrganizationList = mybatisDao.selectByExample(sysOrganizationExample);
            for (SysOrganization sysOrganization : sysOrganizationList) {
                List<OrganizationModel> organizationModels = organizationMapper.getOrganizationTree(sysOrganization.getId());
                result.add(getTree(organizationModels, id));
            }
        }
        return result;
    }

    private OrganizationModel getTree(List<OrganizationModel> list, Integer id){
        OrganizationModel root = new OrganizationModel();
        for (OrganizationModel organization : list) {
            if(organization.getParentId() == id){
                root = organization;
            }else{
                getTreeNode(organization, root);
            }
        }
        return root;
    }

    /**
     * 获取子节点
     * @param child
     * @param parent
     */
    private void getTreeNode(OrganizationModel child, OrganizationModel parent){
        if(child.getParentId() != null && child.getParentId().equals(parent.getId())){
            if(parent.getNodes() == null){
                List<OrganizationModel> childes = new ArrayList<OrganizationModel>();
                childes.add(child);
                parent.setNodes(childes);
            }else{
                parent.getNodes().add(child);
            }
        }else{
            if(parent.getNodes() != null && parent.getNodes().size() > 0){
                for (int i = 0; i < parent.getNodes().size(); i++) {
                    OrganizationModel childTree = parent.getNodes().get(i);
                    getTreeNode(child, childTree);
                }
            }
        }
    }

    /**
     * 创建组织结构
     * @param sysOrganization
     * @return
     */
    @Transactional
    public SysOrganization createOrganization(SysOrganization sysOrganization){
        return mybatisDao.saveByModel(sysOrganization);
    }

    /**
     * 绑定组织机构用户
     * @param organizationId
     * @param userId
     */
    @Transactional
    public void bindOrganizationUser(Integer organizationId, Integer userId){
        SysUserOrganization sysUserOrganization = new SysUserOrganization();
        sysUserOrganization.setOrganizationId(organizationId);
        sysUserOrganization.setUserId(userId);
        mybatisDao.insert(sysUserOrganization);
    }

    /**
     * 批量绑定组织机构用户
     * @param organizationId
     * @param userIds
     */
    @Transactional
    public void bindOrganizationUser(Integer organizationId, Integer... userIds){
        for (Integer userId : userIds) {
            bindOrganizationUser(organizationId, userId);
        }
    }

    /**
     * 解除组织机构用户关系
     * @param organizationId
     * @param userId
     */
    @Transactional
    public void unBindOrganizationUser(Integer organizationId, Integer userId){
        SysUserOrganizationExample sysUserOrganizationExample = new SysUserOrganizationExample();
        sysUserOrganizationExample.createCriteria()
                .andOrganizationIdEqualTo(organizationId)
                .andUserIdEqualTo(userId);
        mybatisDao.deleteByExample(sysUserOrganizationExample);
    }

    /**
     * 批量解除组织机构用户
     * @param organizationId
     * @param userIds
     */
    @Transactional
    public void unBindOrganizationUser(Integer organizationId, Integer... userIds){
        for (Integer userId : userIds) {
            unBindOrganizationUser(organizationId, userId);
        }
    }

    /**
     * 更新组织机构
     * @param sysOrganization
     */
    @Transactional
    public void updateOrganization(SysOrganization sysOrganization){
        mybatisDao.save(sysOrganization);
    }

    /**
     * 删除机构组织
     * @param id
     */
    @Transactional
    public void delete(Integer id){
        mybatisDao.deleteByPrimaryKey(SysOrganization.class, id);
    }
}

package com.xmomen.module.system.service;

import com.alibaba.fastjson.JSONObject;
import com.xmomen.framework.mybatis.dao.MybatisDao;
import com.xmomen.module.system.entity.SysOrganization;
import com.xmomen.module.system.entity.SysOrganizationExample;
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

    public List<OrganizationModel> getOrganizationTree(Integer id){
        SysOrganizationExample sysOrganizationExample = new SysOrganizationExample();
        List<OrganizationModel> result = new ArrayList<OrganizationModel>();
        if(id == null){
            sysOrganizationExample.createCriteria().andParentIdIsNull();
            List<SysOrganization> sysOrganizationList = mybatisDao.selectByExample(sysOrganizationExample);
            for (SysOrganization sysOrganization : sysOrganizationList) {
                List<SysOrganization> list = organizationMapper.getOrganizationTree(sysOrganization.getId());
                List<OrganizationModel> organizationModels = new ArrayList<OrganizationModel>();
                for (SysOrganization organization : list) {
                    OrganizationModel organizationModel = new OrganizationModel();
                    organizationModel.setParentNodeId(organization.getParentId());
                    organizationModel.setName(organization.getName());
                    organizationModel.setId(organization.getId());
                    organizationModel.setDesc(organization.getDescription());
                    organizationModels.add(organizationModel);
                }
                result.add(getTree(organizationModels, id));
            }
        }
        return result;
    }

    private OrganizationModel getTree(List<OrganizationModel> list, Integer id){
        OrganizationModel root = new OrganizationModel();
        for (OrganizationModel organization : list) {
            if(organization.getParentNodeId() == id){
                root = organization;
            }else{
                getTreeNode(organization, root);
            }
        }
        return root;
    }

    private void getTreeNode(OrganizationModel child, OrganizationModel parent){
        if(child.getParentNodeId() != null && child.getParentNodeId().equals(parent.getId())){
            if(parent.getNodes() == null){
                List<OrganizationModel> childs = new ArrayList<OrganizationModel>();
                childs.add(child);
                parent.setNodes(childs);
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

    @Transactional
    public SysOrganization createOrganization(SysOrganization sysOrganization){
        return mybatisDao.saveByModel(sysOrganization);
    }

    @Transactional
    public void delete(Integer id){
        mybatisDao.deleteByPrimaryKey(SysOrganization.class, id);
    }
}

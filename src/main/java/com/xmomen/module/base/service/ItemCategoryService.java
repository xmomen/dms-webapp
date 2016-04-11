package com.xmomen.module.base.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xmomen.framework.mybatis.dao.MybatisDao;
import com.xmomen.module.base.entity.CdCategory;
import com.xmomen.module.base.entity.CdCategoryExample;
import com.xmomen.module.base.mapper.ItemCategoryMapper;
import com.xmomen.module.base.model.ItemCategoryModel;

/**
 * Created by Jeng on 16/3/25.
 */
@Service
public class ItemCategoryService {

    @Autowired
    MybatisDao mybatisDao;

    @Autowired
    ItemCategoryMapper itemCategoryMapper;

    public List<ItemCategoryModel> getItemCategoryTree(Integer id){
        CdCategoryExample itemCategoryExample = new CdCategoryExample();
        List<ItemCategoryModel> result = new ArrayList<ItemCategoryModel>();
        if(id == null){
        	itemCategoryExample.createCriteria().andParentIdIsNull();
            List<CdCategory> itemCategoryList = mybatisDao.selectByExample(itemCategoryExample);
            for (CdCategory itemCategory : itemCategoryList) {
                List<ItemCategoryModel> itemCategoryModels = itemCategoryMapper.getItemCategoryTree(itemCategory.getId());
                result.add(getTree(itemCategoryModels, id));
            }
        }
        return result;
    }

    private ItemCategoryModel getTree(List<ItemCategoryModel> list, Integer id){
        ItemCategoryModel root = new ItemCategoryModel();
        for (ItemCategoryModel itemCategory : list) {
            if(itemCategory.getParentId() == id){
                root = itemCategory;
            }else{
                getTreeNode(itemCategory, root);
            }
        }
        return root;
    }

    private void getTreeNode(ItemCategoryModel child, ItemCategoryModel parent){
        if(child.getParentId() != null && child.getParentId().equals(parent.getId())){
            if(parent.getNodes() == null){
                List<ItemCategoryModel> childes = new ArrayList<ItemCategoryModel>();
                childes.add(child);
                parent.setNodes(childes);
            }else{
                parent.getNodes().add(child);
            }
        }else{
            if(parent.getNodes() != null && parent.getNodes().size() > 0){
                for (int i = 0; i < parent.getNodes().size(); i++) {
                    ItemCategoryModel childTree = parent.getNodes().get(i);
                    getTreeNode(child, childTree);
                }
            }
        }
    }

    @Transactional
    public CdCategory createItemCategory(CdCategory cdCategory){
        return mybatisDao.saveByModel(cdCategory);
    }

    @Transactional
    public void updateItemCategory(CdCategory cdCategory){
        mybatisDao.save(cdCategory);
    }

    @Transactional
    public void delete(Integer id){
        mybatisDao.deleteByPrimaryKey(CdCategory.class, id);
    }
}

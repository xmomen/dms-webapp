package com.xmomen.module.system.service;

import com.xmomen.framework.mybatis.dao.MybatisDao;
import com.xmomen.framework.mybatis.page.Page;
import com.xmomen.framework.utils.StringUtilsExt;
import com.xmomen.module.system.entity.SysDictionary;
import com.xmomen.module.system.entity.SysDictionaryExample;
import com.xmomen.module.system.entity.SysDictionaryParameter;
import com.xmomen.module.system.entity.SysDictionaryParameterExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Jeng on 16/4/2.
 */
@Service
public class DictionaryService {

    @Autowired
    MybatisDao mybatisDao;

    @Transactional
    public SysDictionaryParameter createDictionary(SysDictionaryParameter sysDictionaryParameter){
        return mybatisDao.insertByModel(sysDictionaryParameter);
    }

    public Page<SysDictionary> getDictionaryList(Integer groupId, Integer limit, Integer offset){
        SysDictionaryParameterExample sysDictionaryExample = new SysDictionaryParameterExample();
        sysDictionaryExample.createCriteria().andSysDictionaryIdEqualTo(groupId);
        return mybatisDao.selectPageByExample(sysDictionaryExample, limit, offset);
    }

    public SysDictionaryParameter getDictionary(Integer id){
        return mybatisDao.selectByPrimaryKey(SysDictionaryParameter.class, id);
    }

    public void updateDictionary(SysDictionaryParameter sysDictionary){
        mybatisDao.update(sysDictionary);
    }

    public void deleteDictionary(Integer id){
        mybatisDao.deleteByPrimaryKey(SysDictionaryParameter.class, id);
    }


}

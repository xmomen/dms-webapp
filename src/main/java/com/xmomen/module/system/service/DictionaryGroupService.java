package com.xmomen.module.system.service;

import com.xmomen.framework.mybatis.dao.MybatisDao;
import com.xmomen.framework.mybatis.page.Page;
import com.xmomen.framework.utils.StringUtilsExt;
import com.xmomen.module.system.entity.SysDictionary;
import com.xmomen.module.system.entity.SysDictionaryExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Jeng on 16/4/2.
 */
@Service
public class DictionaryGroupService {

    @Autowired
    MybatisDao mybatisDao;

    @Transactional
    public SysDictionary createDictionary(SysDictionary sysDictionary){
        return mybatisDao.insertByModel(sysDictionary);
    }

    public Page<SysDictionary> getDictionaryList(String keyword, Integer limit, Integer offset){
        String keywordP ="%" + StringUtilsExt.trimToEmpty(keyword) + "%";
        SysDictionaryExample sysDictionaryExample = new SysDictionaryExample();
        sysDictionaryExample.createCriteria()
                .andDictionaryCodeLike(keywordP);
        sysDictionaryExample.or().andDictionaryDescLike(keywordP);
        return mybatisDao.selectPageByExample(sysDictionaryExample, limit, offset);
    }

    public SysDictionary getDictionary(Integer id){
        return mybatisDao.selectByPrimaryKey(SysDictionary.class, id);
    }

    public void updateDictionary(SysDictionary sysDictionary){
        mybatisDao.update(sysDictionary);
    }

    public void deleteDictionary(Integer id){
        mybatisDao.deleteByPrimaryKey(SysDictionary.class, id);
    }


}

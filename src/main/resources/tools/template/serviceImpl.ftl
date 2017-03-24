package ${targetPackage};

import ${modulePackage}.entity.${domainObjectClassName};
import ${modulePackage}.entity.${domainObjectClassName}Example;
import ${modulePackage}.mapper.${domainObjectClassName}MapperExt;
import ${modulePackage}.model.${domainObjectClassName}Create;
import ${modulePackage}.model.${domainObjectClassName}Query;
import ${modulePackage}.model.${domainObjectClassName}Update;
import ${modulePackage}.model.${domainObjectClassName}Model;
import ${modulePackage}.service.${domainObjectClassName}Service;
import com.xmomen.framework.mybatis.dao.MybatisDao;
import com.xmomen.framework.mybatis.page.Page;
import org.apache.ibatis.exceptions.TooManyResultsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

<#include "header.ftl">
@Service
public class ${domainObjectClassName}ServiceImpl implements ${domainObjectClassName}Service {

    @Autowired
    MybatisDao mybatisDao;

    /**
     * 新增${tableComment}
     *
     * @param ${domainObjectName}Model 新增${tableComment}对象参数
     * @return ${domainObjectClassName}Model    ${tableComment}领域对象
     */
    @Override
    @Transactional
    public ${domainObjectClassName}Model create${domainObjectClassName}(${domainObjectClassName}Model ${domainObjectName}Model) {
        ${domainObjectClassName} ${domainObjectName} = create${domainObjectClassName}(${domainObjectName}Model.getEntity());
        if(${domainObjectName} != null){
            return getOne${domainObjectClassName}Model(${domainObjectName}.getId());
        }
        return null;
    }

    /**
     * 新增${tableComment}实体对象
     *
     * @param ${domainObjectName} 新增${tableComment}实体对象参数
     * @return ${domainObjectClassName} ${tableComment}实体对象
     */
    @Override
    @Transactional
    public ${domainObjectClassName} create${domainObjectClassName}(${domainObjectClassName} ${domainObjectName}) {
        return mybatisDao.insertByModel(${domainObjectName});
    }

    /**
    * 批量新增${tableComment}
    *
    * @param ${domainObjectName}Models 新增${tableComment}对象集合参数
    * @return List<${domainObjectClassName}Model>    ${tableComment}领域对象集合
    */
    @Override
    @Transactional
    public List<${domainObjectClassName}Model> create${domainObjectClassName}s(List<${domainObjectClassName}Model> ${domainObjectName}Models) {
        List<${domainObjectClassName}Model> ${domainObjectName}ModelList = null;
        for (${domainObjectClassName}Model ${domainObjectName}Model : ${domainObjectName}Models) {
            ${domainObjectName}Model = create${domainObjectClassName}(${domainObjectName}Model);
            if(${domainObjectName}Model != null){
                if(${domainObjectName}ModelList == null){
                    ${domainObjectName}ModelList = new ArrayList<>();
                }
                ${domainObjectName}ModelList.add(${domainObjectName}Model);
            }
        }
        return ${domainObjectName}ModelList;
    }

    /**
     * 更新${tableComment}
     *
     * @param ${domainObjectName}Model 更新${tableComment}对象参数
     */
    @Override
    @Transactional
    public void update${domainObjectClassName}(${domainObjectClassName}Model ${domainObjectName}Model) {
        mybatisDao.update(${domainObjectName}Model.getEntity());
    }

    /**
     * 更新${tableComment}实体对象
     *
     * @param ${domainObjectName} 新增${tableComment}实体对象参数
     * @return ${domainObjectClassName} ${tableComment}实体对象
     */
    @Override
    @Transactional
    public void update${domainObjectClassName}(${domainObjectClassName} ${domainObjectName}) {
        mybatisDao.update(${domainObjectName});
    }

    /**
     * 删除${tableComment}
     *
     * @param ids 主键数组
     */
    @Override
    @Transactional
    public void delete${domainObjectClassName}(String[] ids) {
        ${domainObjectClassName}Example ${domainObjectName}Example = new ${domainObjectClassName}Example();
        ${domainObjectName}Example.createCriteria().andIdIn(Arrays.<String>asList((String[]) ids));
        mybatisDao.deleteByExample(${domainObjectName}Example);
    }

    /**
    * 删除${tableComment}
    *
    * @param id 主键
    */
    @Override
    @Transactional
    public void delete${domainObjectClassName}(String id) {
        mybatisDao.deleteByPrimaryKey(${domainObjectClassName}.class, id);
    }

    /**
     * 查询${tableComment}领域分页对象（带参数条件）
     *
     * @param limit     每页最大数
     * @param offset    页码
     * @param ${domainObjectName}Query 查询参数
     * @return Page<${domainObjectClassName}Model>   ${tableComment}参数对象
     */
    @Override
    public Page<${domainObjectClassName}Model> get${domainObjectClassName}ModelPage(int limit, int offset, ${domainObjectClassName}Query ${domainObjectName}Query) {
        return (Page<${domainObjectClassName}Model>) mybatisDao.selectPage(${domainObjectClassName}MapperExt.${domainObjectClassName}MapperNameSpace + "get${domainObjectClassName}Model", ${domainObjectName}Query, limit, offset);
    }

    /**
     * 查询${tableComment}领域分页对象（无参数条件）
     *
     * @param limit  每页最大数
     * @param offset 页码
     * @return Page<${domainObjectClassName}Model> ${tableComment}领域对象
     */
    @Override
    public Page<${domainObjectClassName}Model> get${domainObjectClassName}ModelPage(int limit, int offset) {
        return (Page<${domainObjectClassName}Model>) mybatisDao.selectPage(${domainObjectClassName}MapperExt.${domainObjectClassName}MapperNameSpace + "get${domainObjectClassName}Model", null, limit, offset);
    }

    /**
     * 查询${tableComment}领域集合对象（带参数条件）
     *
     * @param ${domainObjectName}Query 查询参数对象
     * @return List<${domainObjectClassName}Model> ${tableComment}领域集合对象
     */
    @Override
    public List<${domainObjectClassName}Model> get${domainObjectClassName}ModelList(${domainObjectClassName}Query ${domainObjectName}Query) {
        return mybatisDao.getSqlSessionTemplate().selectList(${domainObjectClassName}MapperExt.${domainObjectClassName}MapperNameSpace + "get${domainObjectClassName}Model", ${domainObjectName}Query);
    }

    /**
     * 查询${tableComment}领域集合对象（无参数条件）
     *
     * @return List<${domainObjectClassName}Model> ${tableComment}领域集合对象
     */
    @Override
    public List<${domainObjectClassName}Model> get${domainObjectClassName}ModelList() {
        return mybatisDao.getSqlSessionTemplate().selectList(${domainObjectClassName}MapperExt.${domainObjectClassName}MapperNameSpace + "get${domainObjectClassName}Model");
    }

    /**
     * 查询${tableComment}实体对象
     *
     * @param id 主键
     * @return ${domainObjectClassName} ${tableComment}实体对象
     */
    @Override
    public ${domainObjectClassName} getOne${domainObjectClassName}(String id) {
        return mybatisDao.selectByPrimaryKey(${domainObjectClassName}.class, id);
    }

    /**
     * 根据主键查询单个对象
     *
     * @param id 主键
     * @return ${domainObjectClassName}Model ${tableComment}领域对象
     */
    @Override
    public ${domainObjectClassName}Model getOne${domainObjectClassName}Model(String id) {
        ${domainObjectClassName}Query ${domainObjectName}Query = new ${domainObjectClassName}Query();
        ${domainObjectName}Query.setId(id);
        return mybatisDao.getSqlSessionTemplate().selectOne(${domainObjectClassName}MapperExt.${domainObjectClassName}MapperNameSpace + "get${domainObjectClassName}Model", ${domainObjectName}Query);
    }

    /**
     * 根据查询参数查询单个对象（此方法只用于提供精确查询单个对象，若结果数超过1，则会报错）
     *
     * @param ${domainObjectName}Query ${tableComment}查询参数对象
     * @return ${domainObjectClassName}Model ${tableComment}领域对象
     */
    @Override
    public ${domainObjectClassName}Model getOne${domainObjectClassName}Model(${domainObjectClassName}Query ${domainObjectName}Query) throws TooManyResultsException {
        return mybatisDao.getSqlSessionTemplate().selectOne(${domainObjectClassName}MapperExt.${domainObjectClassName}MapperNameSpace + "get${domainObjectClassName}Model", ${domainObjectName}Query);
    }
}

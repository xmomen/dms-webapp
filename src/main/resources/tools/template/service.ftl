package ${targetPackage};

import com.xmomen.framework.mybatis.page.Page;
import ${modulePackage}.model.${domainObjectClassName}Query;
import ${modulePackage}.model.${domainObjectClassName}Model;
import ${modulePackage}.entity.${domainObjectClassName};
import org.apache.ibatis.exceptions.TooManyResultsException;

import java.io.Serializable;
import java.util.List;

<#include "header.ftl">
public interface ${domainObjectClassName}Service {

    /**
     * 新增${tableComment}
     * @param  ${domainObjectName}Model   新增${tableComment}对象参数
     * @return  ${domainObjectClassName}Model    ${tableComment}领域对象
     */
    public ${domainObjectClassName}Model create${domainObjectClassName}(${domainObjectClassName}Model ${domainObjectName}Model);

    /**
     * 新增${tableComment}实体对象
     * @param   ${domainObjectName} 新增${tableComment}实体对象参数
     * @return  ${domainObjectClassName} ${tableComment}实体对象
     */
    public ${domainObjectClassName} create${domainObjectClassName}(${domainObjectClassName} ${domainObjectName});

    /**
    * 批量新增${tableComment}
    * @param ${domainObjectClassName}Model     新增${tableComment}对象集合参数
    * @return List<${domainObjectClassName}Model>    ${tableComment}领域对象集合
    */
    List<${domainObjectClassName}Model> create${domainObjectClassName}s(List<${domainObjectClassName}Model> ${domainObjectName}Models);

    /**
     * 更新${tableComment}
     * @param ${domainObjectName}Model    更新${tableComment}对象参数
     */
    public void update${domainObjectClassName}(${domainObjectClassName}Model ${domainObjectName}Model);

    /**
     * 更新${tableComment}实体对象
     * @param   ${domainObjectName} 新增${tableComment}实体对象参数
     * @return  ${domainObjectClassName} ${tableComment}实体对象
     */
    public void update${domainObjectClassName}(${domainObjectClassName} ${domainObjectName});

    /**
     * 批量删除${tableComment}
     * @param ids   主键数组
     */
    public void delete${domainObjectClassName}(String[] ids);

    /**
     * 删除${tableComment}
     * @param id   主键
     */
    public void delete${domainObjectClassName}(String id);

    /**
     * 查询${tableComment}领域分页对象（带参数条件）
     * @param ${domainObjectName}Query 查询参数
     * @param limit     每页最大数
     * @param offset    页码
     * @return Page<${domainObjectClassName}Model>   ${tableComment}参数对象
     */
    public Page<${domainObjectClassName}Model> get${domainObjectClassName}ModelPage(int limit, int offset, ${domainObjectClassName}Query ${domainObjectName}Query);

    /**
     * 查询${tableComment}领域分页对象（无参数条件）
     * @param limit 每页最大数
     * @param offset 页码
     * @return Page<${domainObjectClassName}Model> ${tableComment}领域对象
     */
    public Page<${domainObjectClassName}Model> get${domainObjectClassName}ModelPage(int limit, int offset);

    /**
     * 查询${tableComment}领域集合对象（带参数条件）
     * @param ${domainObjectName}Query 查询参数对象
     * @return List<${domainObjectClassName}Model> ${tableComment}领域集合对象
     */
    public List<${domainObjectClassName}Model> get${domainObjectClassName}ModelList(${domainObjectClassName}Query ${domainObjectName}Query);

    /**
     * 查询${tableComment}领域集合对象（无参数条件）
     * @return List<${domainObjectClassName}Model> ${tableComment}领域集合对象
     */
    public List<${domainObjectClassName}Model> get${domainObjectClassName}ModelList();

    /**
     * 查询${tableComment}实体对象
     * @param id 主键
     * @return ${domainObjectClassName} ${tableComment}实体对象
     */
    public ${domainObjectClassName} getOne${domainObjectClassName}(String id);

    /**
     * 根据主键查询单个对象
     * @param id 主键
     * @return ${domainObjectClassName}Model ${tableComment}领域对象
     */
    public ${domainObjectClassName}Model getOne${domainObjectClassName}Model(String id);

    /**
     * 根据查询参数查询单个对象（此方法只用于提供精确查询单个对象，若结果数超过1，则会报错）
     * @param ${domainObjectName}Query ${tableComment}查询参数对象
     * @return ${domainObjectClassName}Model ${tableComment}领域对象
     */
    public ${domainObjectClassName}Model getOne${domainObjectClassName}Model(${domainObjectClassName}Query ${domainObjectName}Query) throws TooManyResultsException;
}

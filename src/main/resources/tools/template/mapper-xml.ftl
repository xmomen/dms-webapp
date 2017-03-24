<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="${targetPackage}.${domainObjectClassName}MapperExt" >

    <!--    查询消息    -->
    <select id="get${domainObjectClassName}Model"
            resultType="${modulePackage}.model.${domainObjectClassName}Model"
            parameterType="${modulePackage}.model.${domainObjectClassName}Query">
        SELECT * FROM ${tableName} t
        <where>
        <#if keywordColumns?exists>
            <if test="keyword">
        <#list keywordColumns as keywordColumn>
            <#if (keywordColumns?size=1) >
                AND (t.${keywordColumn.actualColumnName} LIKE CONCAT('%', ${r"#{keyword}"}, '%'))
            </#if>
            <#if (keywordColumns?size>1) >
                <#if keywordColumn_index = 0>
                    AND (t.${keywordColumn.actualColumnName} LIKE CONCAT('%', ${r"#{keyword}"}, '%')
                </#if>
                <#if keywordColumn_index != 0 && keywordColumn_has_next>
                    OR t.${keywordColumn.actualColumnName} LIKE CONCAT('%', ${r"#{keyword}"}, '%')
                </#if>
                <#if !keywordColumn_has_next>
                    OR t.${keywordColumn.actualColumnName} LIKE CONCAT('%', ${r"#{keyword}"}, '%'))
                </#if>
            </#if>
        </#list>
            </if>
        </#if>
            <if test="id">
                AND t.ID = ${r"#{id}"}
            </if>
            <if test="ids">
                AND t.ID IN
                <foreach collection="ids" item="item" separator="," open="(" close=")">
                ${r"#{item}"}
                </foreach>
            </if>
            <if test="excludeIds">
                AND t.ID NOT IN
                <foreach collection="excludeIds" item="item" separator="," open="(" close=")">
                    ${r"#{item}"}
                </foreach>
            </if>
          </where>
          ORDER BY t.id
    </select>

</mapper>
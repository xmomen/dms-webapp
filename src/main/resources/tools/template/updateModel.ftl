package ${targetPackage};

import lombok.Data;
import ${modulePackage}.entity.${domainObjectClassName};
import org.springframework.beans.BeanUtils;

<#if importClassList?exists>
    <#list importClassList?keys as mykey>
    import ${mykey};
    </#list>
</#if>
import java.io.Serializable;

<#include "header.ftl">
public @Data class ${domainObjectClassName}Update implements Serializable {

<#if fieldList?exists>
    <#list fieldList as field>
    /** ${field['fieldComment']} */
    private ${field['fieldType']} ${field['fieldName']};
    </#list>
</#if>


    public ${domainObjectClassName} getEntity(){
        ${domainObjectClassName} ${domainObjectName} = new ${domainObjectClassName}();
        BeanUtils.copyProperties(this, ${domainObjectName});
        return ${domainObjectName};
    }
}

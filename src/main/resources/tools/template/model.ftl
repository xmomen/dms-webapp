package ${targetPackage};

import com.fasterxml.jackson.annotation.JsonIgnore;
import ${modulePackage}.entity.${domainObjectClassName};
import lombok.Data;
import org.hibernate.validator.constraints.*;
import javax.validation.constraints.*;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelTarget;
import org.springframework.beans.BeanUtils;

<#if importClassList?exists>
    <#list importClassList?keys as mykey>
import ${mykey};
    </#list>
</#if>
import java.io.Serializable;

<#include "header.ftl">
@ExcelTarget(value = "${domainObjectClassName}Model")
public @Data class ${domainObjectClassName}Model implements Serializable {

<#if fieldList?exists>
    <#list fieldList as field>
    /** ${field['fieldComment']} */
    <#if !field.primaryKey && !field.hide>
    @Excel(name = "${field['fieldComment']}")
    </#if>
    <#if !field.nullable>
    @NotBlank(message = "${field['fieldComment']}为必填项")
    </#if>
    <#if field['fieldType'] = 'String'>
    @Length(max = ${field.maxLength}, message = "${field['fieldComment']}字符长度限制[0,${field.maxLength}]")
    </#if>
    <#if field['fieldType'] = 'Integer'>
    @Range(max = 999999999, min = -999999999, message = "${field['fieldComment']}数值范围[999999999,-999999999]")
    </#if>
    <#if field['fieldType'] = 'Long'>
    @Range(max = ${field['max']}l, min = ${field['min']}l, message = "${field['fieldComment']}数值范围[${field['min']}l,${field['max']}l]")
    </#if>
    <#if field['fieldType'] = 'BigDecimal'>
    @DecimalMax(value = "${field['max']}", message = "${field['fieldComment']}数值范围[${field['min']},${field['max']}]")
    @DecimalMin(value = "${field['min']}", message = "${field['fieldComment']}数值范围[${field['min']},${field['max']}]")
    </#if>
    private ${field['fieldType']} ${field['fieldName']};
    </#list>
</#if>

    /**
    * Get ${domainObjectClassName} Entity Object
    * @return
    */
    @JsonIgnore
    public ${domainObjectClassName} getEntity(){
        ${domainObjectClassName} ${domainObjectName} = new ${domainObjectClassName}();
        BeanUtils.copyProperties(this, ${domainObjectName});
        return ${domainObjectName};
    }


}

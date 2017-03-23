<div class="hbox hbox-auto-xs hbox-auto-sm" ng-init="
    app.settings.asideFolded = false;
    app.settings.asideDock = false;
  " >
    <!-- main -->
    <div class="col">
        <!-- main header -->
        <div class="bg-light lter b-b wrapper-sm">
            <div class="row">
                <div class="col-sm-6 col-xs-12">
                    <h1 class="m-n font-thin h3 text-black">${tableComment}</h1>
                </div>
            </div>
        </div>
        <!-- / main header -->
        <div class="wrapper-sm">
            <div class="panel panel-default">
                <div class="panel-heading">
                ${tableComment}
                </div>
                <div class="row wrapper">
                    <div class="col-sm-12">
                        <div class="input-group">
                            <input type="text" ng-model="queryParam.keyword"
                                   data-ng-keyup="get${domainObjectClassName}List()"
                                   class="input-sm form-control" placeholder="请输入关键字进行模糊查询">
                                <span class="input-group-btn">
                                    <button class="btn btn-sm btn-default"
                                            btn-loading="pageSetting.queryBtnLoading"
                                            data-ng-click="get${domainObjectClassName}List()" type="button">
                                        <i class="icon icon-magnifier"></i>&nbsp;&nbsp;查询
                                    </button>
                                    <button class="btn btn-sm btn-default"
                                            data-ng-click="reset()" type="button">
                                        <i class="icon icon-action-redo"></i>&nbsp;&nbsp;重置
                                    </button>
                                    <button class="btn btn-sm btn-default"
                                            data-ng-click="add()" type="button">
                                        <i class="icon icon-plus"></i>&nbsp;&nbsp;新增
                                    </button>
                                </span>
                        </div>
                    </div>
                </div>
                <div class="table">
                    <table class="table table-striped b-t b-light">
                        <thead>
                        <tr>
                            <th>
                                <label class="i-checks m-b-none">
                                    <input type="checkbox"
                                           name="checkAll"
                                           data-ng-click="checkAll()"
                                           ng-model="pageSetting.checkAll"><i></i>
                                </label>
                            </th>
                        <#if fieldList?exists>
                            <#list fieldList as field>
                                <#if !field.primaryKey && !field.hide>
                            <th>${field['fieldComment']}</th>
                                </#if>
                            </#list>
                        </#if>
                            <th class="action">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr data-ng-repeat="item in ${domainObjectName}List"
                            ng-if="(${domainObjectName}List && ${domainObjectName}List.length > 0) && !pageSetting.queryBtnLoading"
                            data-ng-mouseenter="item.showAction=true"
                            data-ng-mouseleave="item.showAction=false">
                            <td>
                                <label class="i-checks m-b-none">
                                    <input type="checkbox"
                                           ng-model="item.checked"
                                           data-ng-click="changeItemChecked()"
                                           data-ng-checked="item.checked"
                                           data-ng-true-value="true"
                                           data-ng-false-value="false"><i></i>
                                </label>
                            </td>
                    <#if fieldList?exists>
                        <#list fieldList as field>
                            <#if !field.primaryKey && !field.hide>
                            <td>
                            <#if field['fieldType'] = 'Boolean'>
                                <label class="i-switch bg-primary m-r">
                                    <input type="checkbox" name="${field['fieldName']}"
                                           data-ng-true-value="true"
                                           data-ng-false-value="false"
                                           disabled
                                           ng-model="item.${field['fieldName']}">
                                    <i></i>
                                </label>
                            <#elseif field['fieldType'] = 'Date'>
                                <a ng-bind="item.${field['fieldName']} | date:'yyyy-HH-mm hh:MM:ss'"></a>
                            <#else>
                                <a ng-bind="item.${field['fieldName']}"></a>
                            </#if>
                            </td>
                            </#if>
                        </#list>
                    </#if>
                            <td class="action">
                                <div ng-show="item.showAction" class="dropdown dropdown-right-action" dropdown="">
                                    <i class="icon icon-settings" dropdown-toggle="" aria-haspopup="true" aria-expanded="false"></i>
                                    <ul class="dropdown-menu">
                                        <li><a href="javascript:void(0)" data-ng-click="view($index)"><i class="icon icon-info"></i>&nbsp;&nbsp;查看</a></li>
                                        <li><a href="javascript:void(0)" data-ng-click="update($index)"><i class="fa fa-edit"></i>&nbsp;&nbsp;修改</a></li>
                                        <li class="divider"></li>
                                        <li><a href="javascript:void(0)" data-ng-click="delete($index)"><i class="fa fa-trash-o"></i>&nbsp;&nbsp;删除</a></li>
                                    </ul>
                                </div>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <div ug-empty-data-msg ng-if="(!${domainObjectName}List || ${domainObjectName}List.length == 0) && !pageSetting.queryBtnLoading"></div>
                <div ug-loading-msg ng-if="pageSetting.queryBtnLoading"></div>
                <footer class="panel-footer" ng-if="${domainObjectName}List && ${domainObjectName}List.length > 0 && !pageSetting.queryBtnLoading">
                    <div class="row">
                        <div class="col-sm-6">
                            <label class="i-checks m-b-none">
                                <input type="checkbox"
                                       name="checkAll"
                                       data-ng-click="checkAll()"
                                       ng-model="pageSetting.checkAll"
                                       data-ng-true-value="true"
                                       data-ng-false-value="false"><i></i>
                            </label>
                            <div class="btn-group open" dropdown="">
                                <button type="button" class="btn btn-sm btn-default dropdown-toggle" dropdown-toggle>
                                    批量操作&nbsp;&nbsp;
                                    <span class="caret"></span>
                                    <span class="sr-only">Split button!</span>
                                </button>
                                <ul class="dropdown-menu" role="menu">
                                    <li><a href="javascript:void(0)" data-ng-click="batchExport($index)"><i class="fa fa-download">&nbsp;&nbsp;批量导出</i></a></li>
                                    <li class="divider"></li>
                                    <li><a href="javascript:void(0)" data-ng-click="batchDelete($index)"><i class="fa fa-trash">&nbsp;&nbsp;批量删除</i></a></li>
                                </ul>
                            </div>
                        </div>
                        <div class="col-sm-6 text-right text-center-xs">
                            <ug-pagination page-info="pageInfoSetting" load-parameter="queryParam" load-data="get${domainObjectClassName}List"></ug-pagination>
                        </div>
                    </div>
                </footer>
            </div>
        </div>
    </div>
    <!-- / main -->
</div>
<script type="text/ng-template" id="${domainObjectName}_detail.html">
    <div class="modal-header">
        <h3 class="modal-title">${tableComment}</h3>
    </div>
    <div class="modal-body">
        <div class="widget-body">

            <form class="form-horizontal" ug-validate="${domainObjectName}DetailForm" name="${domainObjectName}DetailFormName">
                <fieldset>
                <#if fieldList?exists>
                    <#list fieldList as field>
                        <#if !field.primaryKey && !field.hide>
                    <div class="form-group">
                        <label class="col-md-3 control-label">
                            ${field['fieldComment']}
                            <#if !field.nullable>
                            <span class="vaild">*</span>
                            </#if>
                        </label>
                        <#if field['fieldType'] = 'Boolean'>
                        <div class="col-md-9 control-div">
                        <#else>
                        <div class="col-md-9">
                        </#if>
                            <#if field['fieldType'] = 'Boolean'>
                            <label class="i-switch bg-primary m-r">
                                <input type="checkbox" name="${field['fieldName']}"
                                       data-ng-true-value="true"
                                       data-ng-false-value="false"
                                       ng-disabled="pageSetting.formDisabled"
                                       ng-model="${domainObjectName}.${field['fieldName']}">
                                <i></i>
                            </label>
                            <#elseif field['fieldType'] = 'Date'>
                            <div class="input-group">
                                <input class="form-control" type="text" name="${field['fieldName']}" placeholder="请输入${field['fieldComment']}"
                                       ng-disabled="pageSetting.formDisabled"
                                    <#if !field.nullable>
                                       data-msg-required="${field['fieldComment']}必填"
                                       required="true"
                                    </#if>
                                       ug-datetimepicker
                                       ng-model="${domainObjectName}.${field['fieldName']}">
                                    <span class="input-group-btn">
                                        <button type="button" class="btn btn-default"><i class="glyphicon glyphicon-calendar"></i></button>
                                    </span>
                            </div>
                            <#else>
                            <input class="form-control" type="text" name="${field['fieldName']}" placeholder="请输入${field['fieldComment']}"
                                   ng-disabled="pageSetting.formDisabled"
                                <#if !field.nullable>
                                   data-msg-required="${field['fieldComment']}必填"
                                   required="true"
                                </#if>
                                   data-rule-maxlength="${field.maxLength}"
                                   data-msg-maxlength="${field['fieldComment']}字符长度限制[0,${field.maxLength}]"
                                   ng-model="${domainObjectName}.${field['fieldName']}">
                            </#if>
                        </div>
                    </div>
                        </#if>
                    </#list>
                </#if>

                    <div class="form-group" ng-if="errors">
                        <label class="col-md-2 control-label"></label>
                        <div class="col-md-10 error">
                            {{errors.message}}
                        </div>
                    </div>
                </fieldset>
            </form>
        </div>
    </div>
    <div class="modal-footer">
        <button class="btn btn-primary" type="submit"
                btn-loading="pageSetting.saveBtnLoading"
                ng-disabled="pageSetting.formDisabled"
                ng-if="!pageSetting.formDisabled" data-ng-click="save${domainObjectClassName}()">
            <i class="fa fa-save"></i>
            保存
        </button>
        <button class="btn btn-default" ng-click="cancel()">
            <i class="icon icon-close"></i>
            关闭</button>
    </div>
</script>
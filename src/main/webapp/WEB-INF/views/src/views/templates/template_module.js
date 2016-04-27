/**
 * Created by Administrator on 2016/1/15.
 */
define([
    "views/templates/member_add_modal"
],function (member_add_modal) {
    angular.module('DMS.tpls', [
        "permission"
    ]).factory("$modalMemberAdd",["$modal",function($modal){
        return {
            open: function(params){
                return $modal.open({
                    templateUrl: 'views/templates/member_add_modal.html',
                    controller: member_add_modal,
                    resolve: {
                        currentMember : function(){
                            return params == undefined ? undefined : params.currentMember
                        }
                    },
                    size : 'lg'
                })
            }
        };
    }]);
});
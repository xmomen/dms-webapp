/**
 * Created by Jeng on 2015/12/17.
 */
/*jshint globalstrict:true*/
/*global angular:false*/
(function(){
    // Create global xmg obj and its namespaces
    // build processes may have already created an xmg obj
    window.ug = window.ug || {};
    window.ug.version = '1.0.0';
(function(angular){
    'use strict';

var $ugPagination = function() {
    return {
        restrict: 'E',
        replace: true ,
        transclude : true, //嵌入
        scope:{
            pageInfo:"="
        },
        templateUrl : 'js/ug-ui/template/pagination-tpl.html',//模板url
        controller : ['$scope',function($scope){
            $scope.pageConfig = {
                showSkip:true,
                showTotal:true,
                total:0,
                showPageNum:true,
                pageSize:10,
                pageNum:1,
                styleCss:1
            };
            $scope.pageInfo = $scope.pageInfo||{};
            $scope.pageConfig = angular.extend($scope.pageConfig,$scope.pageInfo.pageConfig);
            $scope.$watch('pageInfo',function(newVal,oldVal){
                if(newVal && newVal !== oldVal){
                    $scope.load();
                }
            });
            $scope.maxPageSize = $scope.pageConfig.pageSize + "";
            $scope.changeMaxPageSize = function(){
                $scope.pageInfo.pageSize = parseInt($scope.maxPageSize);
                $scope.pageInfo.loadData();
            };
            $scope.load = function(){
                $scope.curPage = $scope.pageInfo.pageNum;//当前页
                $scope.pageSize = $scope.pageInfo.pageSize;//每页总条数
                $scope.total = $scope.pageInfo.total;//总条数
                if($scope.pageInfo.pages){
                    $scope.pages = $scope.pageInfo.pages
                }else{
                    $scope.pages = 1
                }
                $scope.pageList = [];
                for(var i=1;i<=$scope.pages;i++){
                    var page = {
                        isDisabled:false,
                        num:i,
                        isOmit:false,
                        text:"",
                        isShow:true
                    };
                    //如果页码等于当前页禁用点击
                    if(page.num == $scope.curPage){
                        page.isDisabled = true;
                    }
                    //总页数小于7，显示所有分页
                    if($scope.pages < 7){
                        $scope.pageList.push(page);
                    }else{
                        //小于3
                        if(i == 1){
                            $scope.pageList.push(page);
                            continue;
                        }
                        //大于最后2页
                        if(i == $scope.pages){
                            $scope.pageList.push(page);
                            continue;
                        }
                        if($scope.curPage >= 1 && $scope.curPage <= $scope.pages){
                            if(($scope.curPage-1)==i || ($scope.curPage+1)==i || i==$scope.curPage){
                                if(($scope.curPage-1)==i &&  i!=$scope.curPage){
                                    var page2 = angular.copy(page);
                                    page2.isOmit = true;
                                    page2.text = "...";
                                    page2.num=null;
                                    if(i!=2){
                                        $scope.pageList.push(page2);
                                    }
                                    $scope.pageList.push(page);
                                }else if(($scope.curPage+1)==i && i!=$scope.curPage){
                                    $scope.pageList.push(page);
                                    var page2 = angular.copy(page);
                                    page2.isOmit = true;
                                    page2.text = "...";
                                    page2.num=null;
                                    if(i!=($scope.pages-1)){
                                        $scope.pageList.push(page2);
                                    }
                                }
                                if(i==$scope.curPage){
                                    $scope.pageList.push(page);
                                }
                            }
                        }
                    }
                }
            };
            $scope.skipPage = function(num){
                num = parseInt(num);
                if(num<=$scope.pages && num>=1){
                    $scope.pageInfo.pageNum = num;
                }else if(num > $scope.pages){
                    $scope.pageInfo.pageNum = angular.copy($scope.pages);
                    $scope.inPageNo = angular.copy($scope.pages);
                }else if(num<1){
                    $scope.pageInfo.pageNum = 1;
                    $scope.inPageNo = 1;
                }
                $scope.pageInfo.loadData();
            };
            $scope.load();
        }]
    };
}
    /**
     * 分页插件
     */
angular.module('ug.pagination', ["ng"])
    .directive('ugPagination', $ugPagination);
}(angular));
})();
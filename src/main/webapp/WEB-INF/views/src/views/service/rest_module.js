/**
 * Created by Jeng on 2016/1/28.
 */
define(function () {
    var ngREST = angular.module("AMS.REST",["ngResource"]);
    ngREST.factory("UserAPI", ["$resource", function($resource){
        return $resource("/user/:userId", { userId:"@id" }, {
            query:{ isArray:false},
            lock:{
                method:"PUT",
                url:"/user/:userId/locked" ,
                params:{
                    userId:"@userId",
                    locked:"@locked"
                }
            },
            resetPassword: {
                url:"/account/resetPassword" ,
                method:"POST",
                params:{
                    current_password: "@current_password",
                    password:"@password"
                }
            }
        });
    }]);
    ngREST.factory("MessageAPI", ["$resource", function($resource){
        return $resource("/message/:id",{id:'@id'},{
            query:{ isArray:false},
            update:{ method:"PUT", params:{id:"@id"}},
            log:{
                isArray: true,
                method:"GET",
                url:"/message/:id/log" ,
                params:{id:"@id"}
            },
            retry:{
                url: "/message/retry",
                method:"POST" ,
                params: {messageId: "@messageId"}
            }
        });
    }]);
    ngREST.factory("UserGroupAPI", ["$resource", function($resource){
        return $resource("/group/:id",{id:'@id'},{
            query:{ isArray:false},
            save:{method:"PUT"}
        });
    }]);
    ngREST.factory("UserGroupRelationAPI", ["$resource", function($resource){
        return $resource("/group/:id/user",{id:'@id'},{
            query:{ isArray:false},
            save:{
                method:"PUT",
                params: {
                    chose: "@chose",
                    userId: "@userId"
                }
            }
        });
    }]);
    ngREST.factory("ScheduleAPI", ["$resource", function($resource){
        return $resource("/schedule/:id",{id:'@id'},{
            query:{ isArray:false},
            update:{method:"PUT", params:{id:"@id"}},
            getTemplates:{
                url : "/schedule/template",
                method:"GET" ,
                isArray: true
            }
        });
    }]);
});
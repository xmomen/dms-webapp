/**
 * Created by Jeng on 2016/1/28.
 */
define(function () {
    return angular.module("${domainObjectClassName}.REST",[
        "ngResource"
    ]).factory("${domainObjectClassName}API", ["Resource", function(Resource){
        var resource = Resource("${restMapping}/:id", { id:"@id" });
        resource.export = function(data, success, error){
            if(!data.url){
                data.url = "${restMapping}/export";
            }
            resource.$export(data, success, error);
        };
        return resource;
    }]);
});

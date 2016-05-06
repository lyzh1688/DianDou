/**
 * Created by 胡志洁 on 2016/5/5.
 */
angular.module('diandou.services')
  .factory('VideoService',['$http','$q','UrlServer',function($http,$q,UrlServer){
    return {
      getVideoList: function(params){
        var defered = $q.defer();
        var serverUrl = UrlServer.getServer();
        $http.post( serverUrl + '/video/getVideoList',params)
          .success(function(data,status,headers,config){
            defered.resolve(data);
          })
          .error(function(data,status,headers,config){
            defered.reject(data);
          })

        return defered.promise;
      }
    }
  }])

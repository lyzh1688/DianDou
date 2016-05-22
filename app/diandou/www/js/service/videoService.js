/**
 * Created by 胡志洁 on 2016/5/5.
 */
angular.module('diandou.services')
  .factory('VideoService',['$http','$q','REMOTE_SERVER',function($http,$q,REMOTE_SERVER){
    return {
      getVideoInfoById: function(params){
        var defered = $q.defer();
        var serverUrl = REMOTE_SERVER.remoteDiandouSrv;
        $http.post( serverUrl + '/video/getVideoInfoById',params)
          .success(function(data,status,headers,config){
            defered.resolve(data);
          })
          .error(function(data,status,headers,config){
            defered.reject(data);
          })

        return defered.promise;
      },
      getAdvertVideoList: function(params){
        var defered = $q.defer();
        var serverUrl = REMOTE_SERVER.remoteDiandouSrv;
        $http.post( serverUrl + '/video/getAdvertVideoList',params)
          .success(function(data,status,headers,config){
            defered.resolve(data);
          })
          .error(function(data,status,headers,config){
            defered.reject(data);
          })

        return defered.promise;
    },

      getVideoList: function(params){
        var defered = $q.defer();
        var serverUrl = REMOTE_SERVER.remoteDiandouSrv;
        $http.post( serverUrl + '/video/getVideoList',params)
          .success(function(data,status,headers,config){
            defered.resolve(data);
          })
          .error(function(data,status,headers,config){
            defered.reject(data);
          })

        return defered.promise;
      },
      getTagList: function(params){
        var defered = $q.defer();
        var serverUrl = REMOTE_SERVER.remoteDiandouSrv;
        $http.post( serverUrl + '/tag/getTagList',params)
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

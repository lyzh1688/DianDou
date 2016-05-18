/**
 * Created by 胡志洁 on 2016/5/5.
 */
angular.module('diandou.services')
  .factory('TagService',['$http','$q','REMOTE_SERVER',function($http,$q,REMOTE_SERVER){
    return {

      getTagModelListByType: function(params){
        var defered = $q.defer();
        var serverUrl = REMOTE_SERVER.remoteDiandouSrv;
        $http.post( serverUrl + '/tag/getTagModelListByType',params)
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
      },

      searchTags: function(params){
        var defered = $q.defer();
        var serverUrl = REMOTE_SERVER.remoteDiandouSrv;
        $http.post( serverUrl + '/tag/searchTagList',params)
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

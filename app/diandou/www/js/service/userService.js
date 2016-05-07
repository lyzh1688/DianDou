/**
 * Created by 胡志洁 on 2016/5/7.
 */
/**
 * Created by 胡志洁 on 2016/5/5.
 */
angular.module('diandou.services')
  .factory('UserService',['$http','$q','UrlServer',function($http,$q,UrlServer){
    return {
      getUserList: function(params){
        var defered = $q.defer();
        var serverUrl = UrlServer.getServer();
        $http.post( serverUrl + '/user/getUserList',params)
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

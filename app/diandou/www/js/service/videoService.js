/**
 * Created by 胡志洁 on 2016/5/5.
 */
angular.module('diandou.services',[])
  .factory('VideoService',['$http','$q',function($http,$q){
    return {
      getVideoList: function(params){
        var defered = $q.defer();
        $http.post('http://localhost:8080/diandou/video/getVideoList',params)
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

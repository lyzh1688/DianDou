/**
 * Created by 胡志洁 on 2016/5/22.
 */

angular.module('diandou.services')
  .factory('CommentService',['$http','$q','REMOTE_SERVER',function($http,$q,REMOTE_SERVER){
    return {
      makeComment: function (params) {
        var defered = $q.defer();
        var serverUrl = REMOTE_SERVER.remoteDiandouSrv;
        $http.post( serverUrl + '/comment/makeComment',params)
          .success(function(data,status,headers,config){
            defered.resolve(data);
          })
          .error(function(data,status,headers,config){
            defered.reject(data);
          })

        return defered.promise;
      },
      getAllComments: function(params){
        var defered = $q.defer();
        var serverUrl = REMOTE_SERVER.remoteDiandouSrv;
        $http.post( serverUrl + '/comment/getCommentsByVideo',params)
          .success(function(data,status,headers,config){
            defered.resolve(data);
          })
          .error(function(data,status,headers,config){
            defered.reject(data);
          })

        return defered.promise;
      },

    }
  }])

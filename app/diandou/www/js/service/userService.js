/**
 * Created by 胡志洁 on 2016/5/7.
 */
/**
 * Created by 胡志洁 on 2016/5/5.
 */
angular.module('diandou.services')
  .factory('UserService',['$http','$q','$parse','REMOTE_SERVER',function($http,$q,$parse,REMOTE_SERVER){

    user = {};
    return {
      setUser: function(_user){
        user = _user;
      },
      setUserProperty: function(val,arrt){
        var prop = $parse(arrt);
        prop = val;
      },

      getUser: function(){
        return user;
      },


      searchUserListByName :function(params){
        var defered = $q.defer();
        var serverUrl = REMOTE_SERVER.remoteDiandouSrv;
        $http.post( serverUrl + '/user/searchUserListByName',params)
          .success(function(data,status,headers,config){
            defered.resolve(data);
          })
          .error(function(data,status,headers,config){
            defered.reject(data);
          })

        return defered.promise;
      },

      getUserList: function(params){
        var defered = $q.defer();
        var serverUrl = REMOTE_SERVER.remoteDiandouSrv;
        $http.post( serverUrl + '/user/getUserList',params)
          .success(function(data,status,headers,config){
            defered.resolve(data);
          })
          .error(function(data,status,headers,config){
            defered.reject(data);
          })

        return defered.promise;
      },

      login: function (params) {
        var defered = $q.defer();
        //var serverUrl = UrlServer.getServer();
        var serverUrl = REMOTE_SERVER.remoteDiandouSrv;
        $http.post( serverUrl + '/authority/loginAuthority',params)
          .success(function(data,status,headers,config){
            defered.resolve(data);
          })
          .error(function(data,status,headers,config){
            defered.reject(data);
          })

        return defered.promise;
      },

      register: function(params) {
        var defered = $q.defer();
        var serverUrl = REMOTE_SERVER.remoteDiandouSrv;
        $http.post( serverUrl + '/user/userRegister',params)
          .success(function(data,status,headers,config){
            defered.resolve(data);
          })
          .error(function(data,status,headers,config){
            defered.reject(data);
          })

        return defered.promise;
      },

      follow: function(params){
        var defered = $q.defer();
        var serverUrl = REMOTE_SERVER.remoteDiandouSrv;
        $http.post( serverUrl + '/friendship/follow',params)
          .success(function(data,status,headers,config){
            defered.resolve(data);
          })
          .error(function(data,status,headers,config){
            defered.reject(data);
          })

        return defered.promise;
      },

      getFriendsByUserId: function(params){
        var defered = $q.defer();
        var serverUrl = REMOTE_SERVER.remoteDiandouSrv;
        $http.post( serverUrl + '/friendship/getFriendsByUserId',params)
          .success(function(data,status,headers,config){
            defered.resolve(data);
          })
          .error(function(data,status,headers,config){
            defered.reject(data);
          })

        return defered.promise;
      },

      getUserInfoById:function(params){
        var defered = $q.defer();
        var serverUrl = REMOTE_SERVER.remoteDiandouSrv;
        $http.post( serverUrl + '/user/getUserInfoById',params)
          .success(function(data,status,headers,config){
            defered.resolve(data);
          })
          .error(function(data,status,headers,config){
            defered.reject(data);
          })

        return defered.promise;
      },

      updateUserSex:function(params){
        var defered = $q.defer();
        var serverUrl = REMOTE_SERVER.remoteDiandouSrv;
        $http.post( serverUrl + '/user/updateUserSex',params)
          .success(function(data,status,headers,config){
            defered.resolve(data);
          })
          .error(function(data,status,headers,config){
            defered.reject(data);
          })

        return defered.promise;
      },

      updateUserName:function(params){
        var defered = $q.defer();
        var serverUrl = REMOTE_SERVER.remoteDiandouSrv;
        $http.post( serverUrl + '/user/updateUserName',params)
          .success(function(data,status,headers,config){
            defered.resolve(data);
          })
          .error(function(data,status,headers,config){
            defered.reject(data);
          })

        return defered.promise;
      },

      updateUserBrief:function(params){
        var defered = $q.defer();
        var serverUrl = REMOTE_SERVER.remoteDiandouSrv;
        $http.post( serverUrl + '/user/updateUserBrief',params)
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

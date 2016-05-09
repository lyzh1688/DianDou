/**
 * Created by 胡志洁 on 2016/5/9.
 */

angular.module('diandou.services')
  .factory('AuthService',[function(){

    var LOCAL_TOKEN_KEY = 'DIANDOU_TOKEN';
    var noNeedAuthorityList = ['login','regisiter'];
    var isAuthenticated = false;
    var authToken;
    return {
        getNoNeedAuthorityList: function(){
            return noNeedAuthorityList;
        },

        loadUserCredentials: function(){
            var token = window.localStorage.getItem(LOCAL_TOKEN_KEY);
            //if (token) {
            //  useCredentials(token);
            //}
            return token;
        },
        storeUserCredentials: function(token) {
            window.localStorage.setItem(LOCAL_TOKEN_KEY, token);
            //useCredentials(token);
        },
        useCredentials: function(token) {
            isAuthenticated = true;
            authToken = token;
            // Set the token as header for your requests!
            $http.defaults.headers.common['X-Auth-Token'] = token;
        },

    }
  }])
.factory('AuthInjector', ['$q','AuthService','REMOTE_SERVER', function($q,AuthService,REMOTE_SERVER) {
    var authInjector = {
      request: function(config) {
        if(config.url.indexOf(REMOTE_SERVER.remoteDiandouSrv) >= 0){

            var getNoNeedAuthorityList = AuthService.getNoNeedAuthorityList()
            for(var i = 0;i <  getNoNeedAuthorityList.length; ++i){
                if(config.url.indexOf(getNoNeedAuthorityList[i]) >= 0){
                  return config;
                }
            }
            config.headers['X-Auth-Token'] = AuthService.loadUserCredentials();

        }
        return config;
      },

      response: function(response) {
        var sessionToken = response.data['sessionToken'];
        var authStatus = response.data['sessionToken'];
        // 强制用户登录
        if (!sessionToken || authStatus == 'fail'  ) {
          //$state.go('login')
          //return $q.reject(response);
        }
        return response;
      }

    };
    return authInjector;
}]);
/*
.service('AuthService', function($q, $http) {
    var isAuthenticated = false;
    var authToken;

    function loadUserCredentials() {
      var token = window.localStorage.getItem(LOCAL_TOKEN_KEY);
      if (token) {
        useCredentials(token);
      }
    }

    function storeUserCredentials(token) {
      window.localStorage.setItem(LOCAL_TOKEN_KEY, token);
      useCredentials(token);
    }

    function useCredentials(token) {
      username = token.split('.')[0];
      isAuthenticated = true;
      authToken = token;

      // Set the token as header for your requests!
      $http.defaults.headers.common['X-Auth-Token'] = token;
    }

    function destroyUserCredentials() {
        authToken = undefined;
        username = '';
        isAuthenticated = false;
        $http.defaults.headers.common['X-Auth-Token'] = authToken;
        window.localStorage.removeItem(LOCAL_TOKEN_KEY);
    }

    var login = function(name, pw) {
      return $q(function(resolve, reject) {
        if ((name == 'admin' && pw == '1') || (name == 'user' && pw == '1')) {
          // Make a request and receive your auth token from your server
          storeUserCredentials(name + '.yourServerToken');
          resolve('Login success.');
        } else {
          reject('Login Failed.');
        }
      });
    };

    var logout = function() {
      destroyUserCredentials();
    };

    var isAuthorized = function(authorizedRoles) {
      if (!angular.isArray(authorizedRoles)) {
        authorizedRoles = [authorizedRoles];
      }
      return (isAuthenticated && authorizedRoles.indexOf(role) !== -1);
    };

    loadUserCredentials();

    return {
      login: login,
      logout: logout,
      isAuthorized: isAuthorized,
    };
  })
*/

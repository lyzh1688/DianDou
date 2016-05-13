/**
 * Created by 胡志洁 on 2016/5/6.
 */
angular.module('diandou.controllers')
  .controller('LoginCtrl', ['$scope','$state','$ionicPopup','UserService','AuthService','AUTH_STATUS',function($scope,$state,$ionicPopup,UserService,AuthService,AUTH_STATUS) {
    $scope.loginData = {};

    $scope.doLogin = function(){

      var params = $scope.loginData;
        UserService.login(params).then(
          function(result){
            if(result.authStatus == AUTH_STATUS.login_pass){
                AuthService.storeUserCredentials(result.sessionToken);
                AuthService.setUserId(result.userId);
              $state.go('app.main');
            }
            else{
                var alertPopup = $ionicPopup.alert({
                  title: '登陆失败',
                  template: '用户名或密码错误!'
                });
            }
          },
          function(err){
              var alertPopup = $ionicPopup.alert({
                title: '登陆失败',
                template: '请检查网络情况'
              });
          }
        );
    }
}])

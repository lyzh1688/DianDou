/**
 * Created by 胡志洁 on 2016/5/10.
 */
angular.module('diandou.controllers')
  .controller('RegisterCtrl', ['$scope','$state','$ionicPopup','UserService','AuthService','AUTH_STATUS',function($scope,$state,$ionicPopup,UserService,AuthService,AUTH_STATUS) {

    $scope.registerData = {};

    $scope.doRegister = function(){

      var params = $scope.registerData;
      UserService.register(params).then(
        function(result){
          if(result.authStatus == AUTH_STATUS.login_pass){
            AuthService.storeUserCredentials(result.sessionToken);
            AuthService.setUserId(result.userId);
            $state.go('app.main');
          }
          else{
            var failedReason = "请检查网络情况!";
            if(result.authStatus == AUTH_STATUS.reg_failed_mobile_exists){
              failedReason = '该手机已经注册';
            }
            else if(result.authStatus == AUTH_STATUS.reg_failed_username_exists){
              failedReason = '该用户名已被使用';
            }
            else{
              failedReason = "请检查网络情况!";
            }
            var alertPopup = $ionicPopup.alert({
              title: '注册失败',
              template:failedReason
            });
          }
        },
        function(err){
          var alertPopup = $ionicPopup.alert({
            title: '注册失败',
            template: '请检查网络情况'
          });
        }
      );
    }
  }])

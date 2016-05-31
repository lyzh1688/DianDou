/**
 * Created by 胡志洁 on 2016/5/31.
 */

angular.module('diandou.controllers')
  .controller('NameSettingCtrl', function($scope,$window,$stateParams,$ionicPopup,UserService,AuthService) {

    $scope.user = {};

    $scope.onInit = function(){
      $scope.user = UserService.getUser();
    }

    $scope.onHistoryGoBack = function(){
      $window.history.back();
    }

    $scope.onConfirm = function(){
      var params = {userName:$scope.user.userName, userId: AuthService.getUserId()}
      UserService.updateUserName(params).then(function(result){
        UserService.setUserProperty($scope.user.userName,'user.userName');
        $window.history.back();

      },function(err){
        var alertPopup = $ionicPopup.alert({
          title: '网络连接失败',
          template: '请检查网络情况'
        });
      });
    }

  })

  .controller('BriefSettingCtrl', function($scope,$window,$stateParams,$ionicPopup,UserService,AuthService) {

    $scope.user = {};

    $scope.onInit = function(){
      $scope.user = UserService.getUser();
    }

    $scope.onHistoryGoBack = function(){
      $window.history.back();
    }

    $scope.onConfirm = function(){
      var params = {brief:$scope.user.brief, userId: AuthService.getUserId()}
      UserService.updateUserBrief(params).then(function(result){
        UserService.setUserProperty($scope.user.brief,'user.brief');
        $window.history.back();

      },function(err){
        var alertPopup = $ionicPopup.alert({
          title: '网络连接失败',
          template: '请检查网络情况'
        });
      });
    }

  })

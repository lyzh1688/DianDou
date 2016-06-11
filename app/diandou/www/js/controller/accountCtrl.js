/**
 * Created by 胡志洁 on 2016/5/30.
 */

angular.module('diandou.controllers')
  .controller('AccountCtrl', function($scope,$window,$ionicPopup,UserService,AuthService) {

    $scope.user = {};
    var sexPopup = null;

    $scope.onInit = function(){
      params = {userId:AuthService.getUserId()};
      UserService.getUserInfoById(params).then(function(result){
        UserService.setUser(result)
        $scope.user = UserService.getUser();
      });
    }

    $scope.onHistoryGoBack = function(){
      $window.history.back();
      //$state.go('app.main.myself');
    }

    $scope.onTap = function () {
      var params = {sex: $scope.user.sex, userId: AuthService.getUserId()};
      UserService.updateUserSex(params);
      if (sexPopup) {
        sexPopup.close();
      }
      //console.log($scope.user.sex);
    }

    $scope.onSelectSex = function () {
      sexPopup = $ionicPopup.show({
        templateUrl: 'templates/myself/popup/sexpop.html',
        scope: $scope,
        title: '请选择您的性别',
      });
    }
  })

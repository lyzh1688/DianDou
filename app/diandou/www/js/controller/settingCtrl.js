/**
 * Created by 胡志洁 on 2016/5/31.
 */


var tagArrayContains = function(tagArray,tag) {
  var i = tagArray.length;
  while (i--) {
    if (tagArray[i].tagId == tag.tagId) {
      return true;
    }
  }
  return false;
}

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

  .controller('TagSettingCtrl', function($scope,$window,$stateParams,$ionicPopup,UserService,AuthService) {

    $scope.useTags = [];

    $scope.onInit = function(){
      var params = {userId:AuthService.getUserId()};
      $scope.useTags = [];
      UserService.getAllTag().then(function (result) {
          $scope.useTags = result;
          $scope.useTags.forEach(function (e) {
            e.isChecked = false;
          });
          UserService.getUserTagsByUserId(params).then(function (result) {
            $scope.useTags.forEach(function (e) {
              if(tagArrayContains(result,e)){
                e.isChecked = true;
              }
            })
          })

        },
        function(err){
          var alertPopup = $ionicPopup.alert({
            title: '网络连接失败',
            template: '请检查网络情况'
          });
        });
    }

    $scope.onHistoryGoBack = function(){
      $window.history.back();
    }

    $scope.onConfirm = function(){

      //alert($scope.useTags);
      var tagsStr = $scope.useTags.filter(function (item) {
        return item.isChecked;
      }).map(function (item,index) {

          return item.tagId;
      }).join();

      //var tagsStr = $scope.useTags
      var params = {userTags:tagsStr, userId: AuthService.getUserId()}
      UserService.updateUserTags(params).then(function (result) {
        $window.history.back();
      },
        function (err) {
            var alertPopup = $ionicPopup.alert({
              title: '网络连接失败',
              template: '请检查网络情况'
            });
        })
      //var params = {brief:$scope.user.brief, userId: AuthService.getUserId()}
      //UserService.updateUserBrief(params).then(function(result){
      //  UserService.setUserProperty($scope.user.brief,'user.brief');
      //  $window.history.back();
      //
      //},function(err){
      //  var alertPopup = $ionicPopup.alert({
      //    title: '网络连接失败',
      //    template: '请检查网络情况'
      //  });
      //});

    }

  })

/**
 * Created by 胡志洁 on 2016/5/31.
 */
angular.module('diandou.controllers')
  .controller('VideoSearchCtrl', function($scope,$state,$ionicHistory,$stateParams,$ionicPopup,TagService,AuthService) {

    $scope.searchObj = {searchVal:''};
    $scope.tagList = [];
    $scope.onInit = function(){
      var params = {tagType:0};
      TagService.getTagList(params).then(function(result){
        $scope.tagList = result;
      })
      //$scope.user = UserService.getUser();
    }

    $scope.onHistoryGoBack = function(){
      $ionicHistory.goBack();
    }

    $scope.onConfirm = function(){
      var searchVal = $scope.searchObj.searchVal;
    }

    $scope.onSearchVideos = function () {
      $state.go('app.main.tagvideolist',{tagId:'-1',tagName:$scope.searchObj.searchVal})
    }
  })


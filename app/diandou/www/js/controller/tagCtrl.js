/**
 * Created by 胡志洁 on 2016/5/12.
 */
angular.module('diandou.controllers')
  .controller('TagCtrl', ['$scope','$ionicPopup','TagService',function($scope,$ionicPopup,TagService) {

    $scope.tags = [];
    $scope.searchVal = {};

    var lastSearchTagName = null;

    $scope.onInit = function(){
      var params = {tagType:'0'}

      TagService.getTagList(params)
        .then(function(result){
          $scope.tags = result;
        },
        function(err){
          var alertPopup = $ionicPopup.alert({
            title: '请求失败',
            template: '请检查网络'
          });
        })
    }

    $scope.onSearch = function(){

      if(lastSearchTagName == $scope.searchVal.tagName){
        return ;
      }

      if($scope.searchVal.tagName == ""){
        var params = {tagType:'0'}
        TagService.getTagList(params)
          .then(function(result){
            $scope.tags = result;
            lastSearchTagName = $scope.searchVal.tagName;
          },
          function(err){
            var alertPopup = $ionicPopup.alert({
              title: '请求失败',
              template: '请检查网络'
            });
          });
      }
      else{
        var params = {tagName:$scope.searchVal.tagName};
        TagService.searchTags(params)
          .then(function(result){
            $scope.tags = result;
            lastSearchTagName = $scope.searchVal.tagName;

          },
          function(err){
            var alertPopup = $ionicPopup.alert({
              title: '请求失败',
              template: '请检查网络'
            });
          })
      }

    }
  }])

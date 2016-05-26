angular.module('diandou.controllers')
  .controller('TagVideoListCtrl', ['$scope','$stateParams','$timeout','$ionicHistory','VideoService',
    function($scope,$stateParams,$timeout,$ionicHistory,VideoService) {

      $scope.videos = [];
      $scope.loadMore = true;
      $scope.PageIndex = 0;
      $scope.PageSize = 18;
      $scope.searchObj = {searchVal:''};
      var tagId = $stateParams.tagId;
      var tagName = $stateParams.tagName;
      var searchTpye = 'Normal';

      $scope.onSearchVideos = function(){
        $scope.PageIndex = 0;
        $scope.PageSize = 6;
        $scope.videos = [];
        if('' != $scope.searchObj.searchVal){
          searchTpye = 'Search';
        }
        else{
          searchTpye = 'Normal';
        }
        $scope.onLoadMore();
      }

      $scope.onHistoryGoBack = function(){
        $ionicHistory.goBack();
      }

      //滚动条响应事件
      $scope.onLoadMore = function(){

        //var timer = null;

        var params = {};

        if(searchTpye == 'Normal'){
          params = {tagId:tagId,pageIdx:$scope.PageIndex,pageSize:$scope.PageSize};
        }
        else{
          params = {videoName:$scope.searchObj.searchVal,pageIdx:$scope.PageIndex,pageSize:$scope.PageSize};
        }

        VideoService.getVideoList(params)
          .then(function(result){
            if(!result ||  result.length == 0 || result.length < $scope.PageSize){
              $scope.loadMore = false;
            }

            $scope.videos = $scope.videos.concat(result);
            $scope.$broadcast('scroll.infiniteScrollComplete');
          },
          function(){
            //$timeout.cancel(timer);
            $scope.loadMore = false;
          })
        $scope.PageIndex++;
      };

      $scope.$on("$destroy", function () {
        //clearTimeout(timer.$$timeoutId);
        //$timeout.cancel(timer);
        //清除配置,不然scroll会重复请求
      });
    }])

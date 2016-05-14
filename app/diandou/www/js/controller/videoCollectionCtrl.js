/**
 * Created by 胡志洁 on 2016/5/14.
 */
angular.module('diandou.controllers')
  .controller('VideoCollectionCtrl', ['$scope','$stateParams','$timeout','VideoService',function($scope,$stateParams,$timeout,VideoService) {

    $scope.videos = [];
    $scope.loadMore = true;
    $scope.PageIndex = 0;
    $scope.PageSize = 6;

    //滚动条响应事件
    $scope.onLoadMore = function(){

      //var timer = null;
      var ownerId = $stateParams.ownerId;

      var params = {};
      params = {ownerId:ownerId,pageIdx:$scope.PageIndex,pageSize:$scope.PageSize}

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

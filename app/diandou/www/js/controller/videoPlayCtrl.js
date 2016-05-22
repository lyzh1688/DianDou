/**
 * Created by 胡志洁 on 2016/5/22.
 */
angular.module('diandou.controllers')
  .controller('VideoPlayCtrl', ['$scope','$stateParams','$sce','$ionicPopup','CommentService','VideoService',
                function($scope,$stateParams,$sce,$ionicPopup,CommentService,VideoService) {

    $scope.comments = [];
    var videoId = $stateParams.videoId;
    $scope.loadMore = true;
    $scope.PageIndex = 0;
    $scope.PageSize = 6;
    $scope.videoLink = "";

    $scope.onInit = function(){
      var params = {videoId:videoId};
      VideoService.getVideoInfoById(params)
        .then(function(result){
          if(result){
            $scope.videoLink = $sce.trustAsResourceUrl(result.videoLink);
          }
        })
    }
    //滚动条响应事件
    $scope.onLoadMore = function(){

      var params = {videoId:videoId ,pageIdx:$scope.PageIndex,pageSize:$scope.PageSize}

      CommentService.getAllComments(params)
        .then(function(result){
          if(!result ||  result.length == 0 || result.length < $scope.PageSize){
            $scope.loadMore = false;
          }

          $scope.comments = $scope.comments.concat(result);

          $scope.$broadcast('scroll.infiniteScrollComplete');
        },
        function(){
          //$timeout.cancel(timer);
          $scope.loadMore = false;
        })
      $scope.PageIndex++;
    };
  }])

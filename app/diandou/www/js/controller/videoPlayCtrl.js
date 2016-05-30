/**
 * Created by 胡志洁 on 2016/5/22.
 */
angular.module('diandou.controllers')
  .controller('VideoPlayCtrl', ['$scope','$window','$stateParams','$sce','$ionicPopup','$cordovaKeyboard',
                'CommentService','VideoService','AuthService','$ionicPopup',
                function($scope,$window,$stateParams,$sce,$ionicPopup,$cordovaKeyboard,
                         CommentService,VideoService,AuthService,$ionicPopup) {

    $scope.comments = [];
    var videoId = $stateParams.videoId;
    $scope.loadMore = true;
    $scope.PageIndex = 0;
    $scope.PageSize = 6;
    $scope.videoLink = "";
    $scope.videoInfo = {};
    $scope.cmt = {comment:''};
    $scope.onHistoryGoBack = function(){
      $window.history.back();
    }

    $scope.$on("$cordovaKeyboard:hide", function (event, msg) {
      $scope.footerShow = false;
    });

    $scope.onInit = function(){
      $scope.footerShow = false;
      var params = {videoId:videoId};
      VideoService.getVideoInfoById(params)
        .then(function(result){
          if(result){
            $scope.videoInfo = result;
            $scope.videoLink = $sce.trustAsResourceUrl(result.videoLink);
          }
        })
    }

    $scope.onComment = function(){
      $cordovaKeyboard.show();
      $scope.footerShow = true;
    }

    $scope.onMakeComment = function () {

      var params = {videoId:videoId,
                    comment:$scope.cmt.comment,
                    userId:AuthService.getUserId()};

      CommentService.makeComment(params).then(function (result) {
        $scope.comments = $scope.comments.concat(result);
      },
      function(){
        var alertPopup = $ionicPopup.alert({
          title: '网络忙',
          template: '请稍后评论!'
        });
      })

      $cordovaKeyboard.hide();
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

angular.module('diandou.controllers')
  .controller('VideoCtrl', ['$scope','$stateParams','$timeout','VideoService',function($scope,$stateParams,$timeout,VideoService) {
    /*
     var newVideos = [{video_pic:'http://localhost:8080/diandou/image/5.jpg',owner_id:'555555',video_name:'test5'},
     {video_pic:'http://localhost:8080/diandou/image/6.jpg',owner_id:'666666',video_name:'test6'},
     {video_pic:'http://localhost:8080/diandou/image/7.jpg',owner_id:'777777',video_name:'test7'},
     {video_pic:'http://localhost:8080/diandou/image/8.jpg',owner_id:'888888',video_name:'test8'}];
     $scope.videos = $scope.videos.concat(newVideos);
     */
    $scope.videos = [];
    $scope.loadMore = true;
    $scope.PageIndex = 0;
    $scope.PageSize = 6;

    //滚动条响应事件
    $scope.onLoadMore = function(){

      //var timer = null;
      var paramType = $stateParams.paramType;
      var paramVal = $stateParams.paramVal;

      var params = {};
      if(paramType == 'byTag'){
        params = {tagId:paramVal,pageIdx:$scope.PageIndex,pageSize:$scope.PageSize}
      }
      if(paramType == 'byOwner'){
        params = {ownerId:paramVal,pageIdx:$scope.PageIndex,pageSize:$scope.PageSize}
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

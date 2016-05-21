angular.module('diandou.controllers')
  .controller('OwnerVideoListCtrl', ['$scope','$state','$stateParams','$timeout','$ionicHistory','VideoService',
                                function($scope,$state,$stateParams,$timeout,$ionicHistory,VideoService) {
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
    $scope.PageSize = 18;

    var ownerId = $stateParams.ownerId;
    var ownerName = $stateParams.ownerName;


    $scope.onHistoryGoBack = function(){
      var backView = $ionicHistory.backView()
      if(backView){
        $ionicHistory.goBack();
      }
      else{
        //because of the ownervideolist is belong to lectrure tab,
        //so ionicHistory has no history when jump to ownervideolist from follow page
        //which makes we have to use this way to go back to last page
        $ionicHistory.clearHistory();
        $state.go('app.main.follow',{adType:5});
      }

    }

    //滚动条响应事件
    $scope.onLoadMore = function(){

      //var timer = null;


      var params = {};
      params = {ownerId:ownerId,pageIdx:$scope.PageIndex,pageSize:$scope.PageSize};


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

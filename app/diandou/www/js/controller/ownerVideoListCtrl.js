angular.module('diandou.controllers')
  .controller('OwnerVideoListCtrl', ['$scope','$state','$stateParams','$timeout','$ionicHistory',
                                          'VideoService','UserService','AuthService',
                                function($scope,$state,$stateParams,$timeout,$ionicHistory,VideoService,UserService,AuthService) {

    $scope.videos = [];
    $scope.loadMore = true;
    $scope.PageIndex = 0;
    $scope.PageSize = 18;
    $scope.userModel = {};
    $scope.searchObj = {searchVal:''};
    //$scope.showVideoList = false;

    var ownerId = $stateParams.ownerId;
    var ownerName = $stateParams.ownerName;
    $scope.onInit = function(){
      var followerId =  AuthService.getUserId();
      var params = {userId:ownerId,followerId:followerId}
      UserService.getUserModelById(params).then(function (result) {
        $scope.userModel = result;
      });
    }

    $scope.onSearchOwnerVideos = function () {
      //$state.go('app.main.ownervideosearch', {ownerId:ownerId,ownerName:ownerName});
      //$scope.isShow = false;
      $scope.videos = [];
      $scope.PageIndex = 0;
      $scope.PageSize = 18;
      $scope.onLoadMore();
    }
    //$scope.onShowAll = function () {
    //
    //  $scope.showVideoList = !$scope.showVideoList;
    //}

    $scope.onHistoryGoBack = function(){
      var backView = $ionicHistory.backView()
      if(backView){
        $ionicHistory.goBack();
      }
      else{
        //because of the ownervideolist is belong to lectrure tab,
        //so ionicHistory has no history when jump to ownervideolist from follow page
        //which makes we have to use the hard code way to go back to last page
        $ionicHistory.clearHistory();
        $state.go('app.main.follow',{adType:5});
      }

    }

    //滚动条响应事件
    $scope.onLoadMore = function(){

      var params = {};
      params = {ownerId:ownerId,videoName:$scope.searchObj.searchVal,pageIdx:$scope.PageIndex,pageSize:$scope.PageSize};


      VideoService.getVideoList(params)
        .then(function(result){
            if(!result ||  result.length == 0 || result.length < $scope.PageSize){
                $scope.loadMore = false;
            }
            //$scope.isShow = true;
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

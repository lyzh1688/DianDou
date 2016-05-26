/**
 * Created by 胡志洁 on 2016/5/13.
 */
angular.module('diandou.controllers')
  .controller('FriendsCtrl', ['$scope','$interval','$stateParams','$ionicPopup','$ionicSlideBoxDelegate','UserService','AuthService','VideoService',
                                function($scope,$interval,$stateParams,$ionicPopup,$ionicSlideBoxDelegate,UserService,AuthService,VideoService) {

    $scope.friends = [];
    $scope.loadMore = true;
    $scope.PageIndex = 0;
    $scope.PageSize = 6;
    $scope.isShowFlag = false;
    $scope.AdvertVideos = [];
    $scope.searchObj = {searchVal:''};
    var searchTpye = 'Normal';

    $scope.onSearchLecturer = function(){
      $scope.PageIndex = 0;
      $scope.PageSize = 6;
      $scope.friends = [];
      if('' != $scope.searchObj.searchVal){
        searchTpye = 'Search';
      }
      else{
        searchTpye = 'Normal';
      }
      $scope.onLoadMore();
    }

    $scope.onInit = function(){
      var adType = $stateParams.adType;
      params = {tagId:adType}
      VideoService.getAdvertVideoList(params)
        .then(function(result){
          $scope.AdvertVideos = $scope.AdvertVideos.concat(result);
          $scope.isShowFlag = true;
        })

    }

    //var refresh = $interval(function(){
    //  console.log('执行$timeout回调');
    //  return 'angular'
    //},3000);
    //refresh.then(function(data){
    //  console.log(data)
    //},function(data){
    //  console.log(data)
    //});

    //滚动条响应事件
    $scope.onLoadMore = function(){
      if( 'Normal' == searchTpye){
          var selfId =  AuthService.getUserId();

          var params = {selfId:selfId ,pageIdx:$scope.PageIndex,pageSize:$scope.PageSize}

          UserService.getFriendsByUserId(params)
            .then(function(result){
              if(!result ||  result.length == 0 || result.length < $scope.PageSize){
                $scope.loadMore = false;
              }

              $scope.friends = $scope.friends.concat(result);
              $scope.$broadcast('scroll.infiniteScrollComplete');
            },
            function(){
              //$timeout.cancel(timer);
              $scope.loadMore = false;
            })
      }
      if('Search' == searchTpye){
        var userName = $scope.searchObj.searchVal;
        var followerId =  AuthService.getUserId();
        var params = {userName:userName ,followerId:followerId,pageIdx:$scope.PageIndex,pageSize:$scope.PageSize}
        UserService.searchUserListByName(params)
          .then(function(result){
            if(!result ||  result.length == 0 || result.length < $scope.PageSize){
              $scope.loadMore = false;
            }

            $scope.friends = $scope.friends.concat(result);
            $scope.$broadcast('scroll.infiniteScrollComplete');
          },
          function(){
            //$timeout.cancel(timer);
            $scope.loadMore = false;
          })
      }
      $scope.PageIndex++;
    };
  }])

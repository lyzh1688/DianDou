/**
 * Created by 胡志洁 on 2016/5/13.
 */
angular.module('diandou.controllers')
  .controller('FriendsCtrl', ['$scope','$stateParams','$ionicPopup','UserService','AuthService',function($scope,$stateParams,$ionicPopup,UserService,AuthService) {

    $scope.friends = [];
    $scope.loadMore = true;
    $scope.PageIndex = 0;
    $scope.PageSize = 6;



    //滚动条响应事件
    $scope.onLoadMore = function(){

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
      $scope.PageIndex++;
    };
  }])

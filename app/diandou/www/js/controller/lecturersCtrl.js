/**
 * Created by 胡志洁 on 2016/5/7.
 */
angular.module('diandou.controllers')
  .controller('LecturersCtrl', ['$scope','$stateParams','$ionicPopup','UserService','AuthService',function($scope,$stateParams,$ionicPopup,UserService,AuthService) {

    $scope.lecturers = [];
    $scope.loadMore = true;
    $scope.PageIndex = 0;
    $scope.PageSize = 6;



    //滚动条响应事件
    $scope.onLoadMore = function(){


      var roleId = $stateParams.roleId;
      var followerId =  AuthService.getUserId();


      var params = {roleId:roleId ,followerId:followerId,pageIdx:$scope.PageIndex,pageSize:$scope.PageSize}

      UserService.getUserList(params)
        .then(function(result){
          if(!result ||  result.length == 0 || result.length < $scope.PageSize){
            $scope.loadMore = false;
          }

          $scope.lecturers = $scope.lecturers.concat(result);
          $scope.$broadcast('scroll.infiniteScrollComplete');
        },
        function(){
          //$timeout.cancel(timer);
          $scope.loadMore = false;
        })
      $scope.PageIndex++;
    };

    $scope.onFollow = function(index){
      var isFollow = $scope.lecturers[index].isFollowed;
      var bIsFollow = typeof isFollow == "String" ? eval(isFollow):isFollow;
      $scope.lecturers[index].isFollowed = !bIsFollow;

      var params = {followStatus:$scope.lecturers[index].isFollowed,
                    selfId:AuthService.getUserId(),
                    targetId: $scope.lecturers[index].user.userId};
      UserService.follow(params)
        .then(function(result){
          if(result == "false"){
            var alertPopup = $ionicPopup.alert({
              title: '关注失败',
              template: '请检查网络情况'
            });
          }
        },
      function(err){
        var alertPopup = $ionicPopup.alert({
          title: '关注失败',
          template: '请检查网络情况'
        });
      })

    }

    $scope.$on("$destroy", function () {
      //clearTimeout(timer.$$timeoutId);
      //$timeout.cancel(timer);
      //清除配置,不然scroll会重复请求
    });
  }])

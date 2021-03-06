/**
 * Created by 胡志洁 on 2016/5/7.
 */
angular.module('diandou.controllers')
  .controller('LecturersCtrl', ['$scope','$ionicPopup','UserService','AuthService','CONFIG',
                function($scope,$ionicPopup,UserService,AuthService,CONFIG) {

    $scope.lecturers = [];
    $scope.loadMore = true;
    $scope.PageIndex = 0;
    $scope.PageSize = 6;
    $scope.searchObj = {searchVal:''};
    var searchTpye = 'Normal';

    $scope.onSearchLecturer = function(){
      $scope.PageIndex = 0;
      $scope.PageSize = 6;
      $scope.lecturers = [];
      if('' != $scope.searchObj.searchVal){
        searchTpye = 'Search';
      }
      else{
        searchTpye = 'Normal';
      }
      $scope.onLoadMore();
    }

    //滚动条响应事件
    $scope.onLoadMore = function(){

      if( 'Normal' == searchTpye){
        var roleId = CONFIG.role_id;
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

            $scope.lecturers = $scope.lecturers.concat(result);
            $scope.$broadcast('scroll.infiniteScrollComplete');
          },
          function(){
            //$timeout.cancel(timer);
            $scope.loadMore = false;
          })
      }

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
          else{
            if(bIsFollow == true){
              $scope.lecturers[index].friendCount -= 1;
            }
            else{
              $scope.lecturers[index].friendCount += 1;
            }

            $scope.$emit("Lecturer_Follow_Status_Change_Emit",{});

          }
        },
      function(err){
        var alertPopup = $ionicPopup.alert({
          title: '关注失败',
          template: '请检查网络情况'
        });
      })

    }

    $scope.$on("Friend_Follow_Status_Change_Broadcast",function (event, msg) {
      $scope.PageIndex = 0;
      $scope.PageSize = 6;
      $scope.lecturers = [];
      $scope.onLoadMore();
    });

    $scope.$on("$destroy", function () {
      //clearTimeout(timer.$$timeoutId);
      //$timeout.cancel(timer);
      //清除配置,不然scroll会重复请求
    });
  }])

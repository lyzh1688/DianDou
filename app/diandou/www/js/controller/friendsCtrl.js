/**
 * Created by 胡志洁 on 2016/5/13.
 */
angular.module('diandou.controllers')
  .controller('FriendsCtrl', ['$scope','$interval','$ionicPopup','$ionicSlideBoxDelegate','UserService','AuthService','VideoService','CONFIG',
                                function($scope,$interval,$ionicPopup,$ionicSlideBoxDelegate,UserService,AuthService,VideoService,CONFIG) {

    $scope.friends = [];
    $scope.loadMore = true;
    $scope.PageIndex = 0;
    $scope.PageSize = 6;
    $scope.isShowFlag = false;
    $scope.AdvertVideos = [];
    $scope.searchObj = {searchVal:''};
    var searchTpye = 'Normal';

    $scope.onFollow = function(index){
      var isFollow = $scope.friends[index].isFollowed;
      var bIsFollow = typeof isFollow == "String" ? eval(isFollow):isFollow;
      $scope.friends[index].isFollowed = !bIsFollow;

      var params = {followStatus:$scope.friends[index].isFollowed,
                    selfId:AuthService.getUserId(),
                    targetId: $scope.friends[index].user.userId};
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
            $scope.friends[index].friendCount -= 1;
          }
          else{
            $scope.friends[index].friendCount += 1;
          }

          $scope.$emit("Friend_Follow_Status_Change_Emit",{});

        }
      },
      function(err){
        var alertPopup = $ionicPopup.alert({
          title: '关注失败',
          template: '请检查网络情况'
        });
      })

    }
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
      var adType = CONFIG.adv_type;
      params = {tagId:adType}
      VideoService.getAdvertVideoList(params)
        .then(function(result){
          $scope.AdvertVideos = $scope.AdvertVideos.concat(result);
          $scope.isShowFlag = true;
        })

    }

     $scope.$on("Lecturer_Follow_Status_Change_Broadcast",function (event, msg) {
       $scope.PageIndex = 0;
       $scope.PageSize = 6;
       $scope.friends = [];
       $scope.onLoadMore();
     });

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

/**
 * Created by 胡志洁 on 2016/5/7.
 */
angular.module('diandou.controllers')
  .controller('LecturersCtrl', ['$scope','$stateParams','UserService',function($scope,$stateParams,UserService) {

    $scope.users = [];
    $scope.loadMore = true;
    $scope.PageIndex = 0;
    $scope.PageSize = 6;

    //滚动条响应事件
    $scope.onLoadMore = function(){

      //var timer = null;
      var roleId = $stateParams.roleId;
      var params = {roleId:roleId,pageIdx:$scope.PageIndex,pageSize:$scope.PageSize}

      UserService.getUserList(params)
        .then(function(result){
          if(!result ||  result.length == 0 || result.length < $scope.PageSize){
            $scope.loadMore = false;
          }

          $scope.users = $scope.users.concat(result);
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

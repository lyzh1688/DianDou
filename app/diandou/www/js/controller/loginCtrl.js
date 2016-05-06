/**
 * Created by 胡志洁 on 2016/5/6.
 */
angular.module('diandou.controllers')
  .controller('loginCtrl', ['$scope','$state',function($scope,$state) {
    $scope.doLogin = function(){
      $state.go('app.main');
    }
}])

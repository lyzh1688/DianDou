/**
 * Created by 胡志洁 on 2016/5/6.
 */
angular.module('diandou.controllers')
  .controller('LoginCtrl', ['$scope','$state','UserService','AuthService',function($scope,$state,UserService,AuthService) {
    $scope.doLogin = function(){

      var mobile = "18016224617"
      var params = {mobile:mobile}
      UserService.login(params).then(function(result){
          AuthService.storeUserCredentials(result.sessionToken);
          $state.go('app.main');
      });
    }
}])

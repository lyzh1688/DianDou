angular.module('diandou.controllers', [])


.controller('RootCtrl', function($scope,$window) {
    $scope.$on("Lecturer_Follow_Status_Change_Emit", function (event, msg) {
      $scope.$broadcast("Lecturer_Follow_Status_Change_Broadcast", msg);
    });

    $scope.$on("Friend_Follow_Status_Change_Emit", function (event, msg) {
      $scope.$broadcast("Friend_Follow_Status_Change_Broadcast", msg);
    });
})

  .controller('MainCtrl', function($scope,$window) {
    $scope.hideTabs = false;

    //$window.addEventListener('native.keyboardshow', function(){
    //  $scope.hideTabs = true;
    //
    //});

    $scope.$on('Before_Search_Input',function(event, msg){
      $scope.hideTabs = true;
    });

    $scope.$on('After_Search_Input',function(event, msg){
      $scope.hideTabs = false;
    });

    $window.addEventListener('native.keyboardhide', function(){
      $scope.hideTabs = false;
//      $rootScope.footerShow = false;
    });

  })

.controller('MenuCtrl', function($scope, $ionicModal, $timeout) {

  // With the new view caching in Ionic, Controllers are only called
  // when they are recreated or on app start, instead of every page change.
  // To listen for when this page is active (for example, to refresh data),
  // listen for the $ionicView.enter event:
  //$scope.$on('$ionicView.enter', function(e) {
  //});

  // Form data for the login modal

  $scope.loginData = {};

  // Create the login modal that we will use later
  $ionicModal.fromTemplateUrl('templates/login.html', {
    scope: $scope
  }).then(function(modal) {
    $scope.loginModal = modal;
  });

  // Triggered in the login modal to close it
  $scope.closeLogin = function() {
    $scope.loginModal.hide();
  };

  // Open the login modal
  $scope.login = function() {
    $scope.loginModal.show();
  };


  // Perform the login action when the user submits the login form
  $scope.doLogin = function() {
    console.log('Doing login', $scope.loginData);

    // Simulate a login delay. Remove this and replace with your login
    // code if using a login system
    $timeout(function() {
      $scope.closeLogin();
    }, 1000);
  };

    $ionicModal.fromTemplateUrl('templates/user/setting.html', {
      scope: $scope,
      animation: 'slide-in-up'
    }).then(function(modal) {
      $scope.settingModal = modal;

    });

    $scope.setting = function(){
      $scope.settingModal.show();
    }

    $scope.confirmSetting = function(){
      $scope.settingModal.hide()
    }
})



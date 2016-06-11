/**
 * Created by 胡志洁 on 2016/5/31.
 */


var tagArrayContains = function(tagArray,tag) {
  var i = tagArray.length;
  while (i--) {
    if (tagArray[i].tagId == tag.tagId) {
      return true;
    }
  }
  return false;
}
angular.module('diandou.controllers')
  .controller('HeadSettingCtrl', function($scope, $ionicActionSheet, $timeout, $state, $ionicLoading,
                                          $cordovaImagePicker, Camera,$cordovaFileTransfer,AuthService,UserService,REMOTE_SERVER) {


    $scope.user = {};
    $scope.imageURI = '';
    var orgImageURI = '';
    $scope.onInit = function(){
      $scope.user = UserService.getUser();
    }

    $scope.onHistoryGoBack = function(){
      $scope.user.headPortrait = orgImageURI;
      //$window.history.back();
      $state.go('account');
    }
    // 图片选择项
    $scope.showImageUploadChoices = function (prop) {
      var hideSheet = $ionicActionSheet.show({
        buttons: [
          //{text: '拍照上传'},
          {text: '从相册中选'}
        ],
        titleText: '图片上传',
        cancelText: '取 消',
        cancel: function () {
          // add cancel code..
          return false;
        },
        buttonClicked: function (index) {
          // 相册文件选择上传
          if (index == 0) {
            $scope.readalbum(prop);
          }
          //else if (index == 0) {
          //  // 拍照上传
          //  $scope.taskPicture(prop);
          //}
          return true;
        }
      });

    };
    $scope.onConfirm = function () {
      var fileURL = $scope.imageURI;
      if('undefined' != fileURL && undefined != fileURL && '' != fileURL)
        $ionicLoading.show({
          template: '上传中...'
        });
      var userId = AuthService.getUserId()
      var serverUri = encodeURI(REMOTE_SERVER.remoteDiandouSrv + '/file/uploadHeadPortrait?userId='+ userId);
      $cordovaFileTransfer.upload(serverUri,fileURL)
        .then(function(result) {
          orgImageURI = fileURL;
          $ionicLoading.hide();
        }, function(err) {
          alert(angular.toJson(err));
        }, function (progress) {
          // constant progress updates
        });
    };

    // 读用户相册
    $scope.readalbum = function (prop) {
      if (!window.imagePicker) {
        alert('目前您的环境不支持相册上传。')
        return;
      }

      var options = {
        maximumImagesCount: 1,
        width: 800,
        height: 800,
        quality: 80
      };

      $cordovaImagePicker.getPictures(options).then(function (results) {

        var fileURL = results[0];

        $scope.imageURI = fileURL;
        if('undefined' != fileURL && undefined != fileURL && '' != fileURL){
          orgImageURI = $scope.user.headPortrait;
          $scope.user.headPortrait = fileURL;
        }
        //if('undefined' != fileURL && undefined != fileURL)
        //$ionicLoading.show({
        //  template: '上传中...'
        //});
        //var userId = AuthService.getUserId()
        //var serverUri = encodeURI(REMOTE_SERVER.remoteDiandouSrv + '/file/uploadHeadPortrait?userId='+ userId);
        //$cordovaFileTransfer.upload(serverUri,fileURL)
        //  .then(function(result) {
        //    $ionicLoading.hide();
        //  }, function(err) {
        //    alert(angular.toJson(err));
        //  }, function (progress) {
        //    // constant progress updates
        //  });

      }, function (error) {
        alert(error);
      });
    };

    // 拍照
    $scope.taskPicture = function (prop) {
      if (!navigator.camera) {
        alert('请在真机环境中使用拍照上传。')
        return;
      }

      var options = {
        quality: 75,
        targetWidth: 800,
        targetHeight: 800,
        saveToPhotoAlbum: false
      };
      Camera.getPicture(options).then(function (imageURI) {
        //$scope.uploadimage(imageURI);
        var fileURL = imageURI;
        alert(fileURL);
        $ionicLoading.show({
          template: '上传中...'
        });
        var userId = AuthService.getUserId()
        var serverUri = encodeURI(REMOTE_SERVER.remoteDiandouSrv + '/file/uploadHeadPortrait?userId='+ userId);
        $cordovaFileTransfer.upload(serverUri,fileURL)
          .then(function(result) {
            $ionicLoading.hide();
          }, function(err) {
            alert(angular.toJson(err));
          }, function (progress) {
            // constant progress updates
          });


      }, function (err) {
        alert("照相机：" + err);
      });

    }

  })

  .controller('NameSettingCtrl', function($scope,$window,$stateParams,$ionicPopup,UserService,AuthService) {

    $scope.user = {};

    $scope.onInit = function(){
      $scope.user = UserService.getUser();
    }

    $scope.onHistoryGoBack = function(){
      $window.history.back();
    }

    $scope.onConfirm = function(){
      var params = {userName:$scope.user.userName, userId: AuthService.getUserId()}
      UserService.updateUserName(params).then(function(result){
        UserService.setUserProperty($scope.user.userName,'user.userName');
        $window.history.back();

      },function(err){
        var alertPopup = $ionicPopup.alert({
          title: '网络连接失败',
          template: '请检查网络情况'
        });
      });
    }

  })

  .controller('BriefSettingCtrl', function($scope,$window,$stateParams,$ionicPopup,UserService,AuthService) {

    $scope.user = {};

    $scope.onInit = function(){
      $scope.user = UserService.getUser();
    }

    $scope.onHistoryGoBack = function(){
      $window.history.back();
    }

    $scope.onConfirm = function(){
      var params = {brief:$scope.user.brief, userId: AuthService.getUserId()}
      UserService.updateUserBrief(params).then(function(result){
        UserService.setUserProperty($scope.user.brief,'user.brief');
        $window.history.back();

      },function(err){
        var alertPopup = $ionicPopup.alert({
          title: '网络连接失败',
          template: '请检查网络情况'
        });
      });
    }

  })

  .controller('TagSettingCtrl', function($scope,$window,$stateParams,$ionicPopup,UserService,AuthService) {

    $scope.useTags = [];

    $scope.onInit = function(){
      var params = {userId:AuthService.getUserId()};
      $scope.useTags = [];
      UserService.getAllTag().then(function (result) {
          $scope.useTags = result;
          $scope.useTags.forEach(function (e) {
            e.isChecked = false;
          });
          UserService.getUserTagsByUserId(params).then(function (result) {
            $scope.useTags.forEach(function (e) {
              if(tagArrayContains(result,e)){
                e.isChecked = true;
              }
            })
          })

        },
        function(err){
          var alertPopup = $ionicPopup.alert({
            title: '网络连接失败',
            template: '请检查网络情况'
          });
        });
    }

    $scope.onHistoryGoBack = function(){
      $window.history.back();
    }

    $scope.onConfirm = function(){

      //alert($scope.useTags);
      var tagsStr = $scope.useTags.filter(function (item) {
        return item.isChecked;
      }).map(function (item,index) {

          return item.tagId;
      }).join();

      //var tagsStr = $scope.useTags
      var params = {userTags:tagsStr, userId: AuthService.getUserId()}
      UserService.updateUserTags(params).then(function (result) {
        $window.history.back();
      },
        function (err) {
            var alertPopup = $ionicPopup.alert({
              title: '网络连接失败',
              template: '请检查网络情况'
            });
        })
      //var params = {brief:$scope.user.brief, userId: AuthService.getUserId()}
      //UserService.updateUserBrief(params).then(function(result){
      //  UserService.setUserProperty($scope.user.brief,'user.brief');
      //  $window.history.back();
      //
      //},function(err){
      //  var alertPopup = $ionicPopup.alert({
      //    title: '网络连接失败',
      //    template: '请检查网络情况'
      //  });
      //});

    }

  })

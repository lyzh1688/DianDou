/**
 * Created by 胡志洁 on 2016/5/12.
 */
angular.module('diandou.controllers')
  .controller('TagCtrl', ['$scope','VideoService',function($scope,VideoService) {
    /*
     var newVideos = [{video_pic:'http://localhost:8080/diandou/image/5.jpg',owner_id:'555555',video_name:'test5'},
     {video_pic:'http://localhost:8080/diandou/image/6.jpg',owner_id:'666666',video_name:'test6'},
     {video_pic:'http://localhost:8080/diandou/image/7.jpg',owner_id:'777777',video_name:'test7'},
     {video_pic:'http://localhost:8080/diandou/image/8.jpg',owner_id:'888888',video_name:'test8'}];
     $scope.videos = $scope.videos.concat(newVideos);
     */
    $scope.tags = [];

    $scope.onInit = function(){
      var params = {tagType:'0'}

      VideoService.getTagList(params)
        .then(function(result){
          $scope.tags = result;
        })
    }

  }])

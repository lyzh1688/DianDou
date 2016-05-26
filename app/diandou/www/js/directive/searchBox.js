/**
 * Created by 胡志洁 on 2016/5/25.
 */
angular.module('diandou.searchBox',[])
.directive('searchBox',[function(){
  return {
    restrict: 'AE',
    //controller: 'yearController',
    scope: {
      searchVal: '=',
      placeholderVal:'@',
      onClickCallback: '&'
    },

    templateUrl:'js/directive/searchBox.html',

    /*
    link: function($scope, $element, $attrs){
      $scope.search = function()
      {
        var onClickCallbackfunc = $parse($scope.onClickCallback);
        onClickCallbackfunc();
      }
    }
    */
}
}]);

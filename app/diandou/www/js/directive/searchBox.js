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


    link: function($scope, $element, $attrs){
      $scope.onBeforeInput = function()
      {
        $scope.$emit("Before_Search_Input",{});

      }

      $scope.onBlur = function(){
        $scope.$emit("After_Search_Input",{});
      }
    }

}
}]);

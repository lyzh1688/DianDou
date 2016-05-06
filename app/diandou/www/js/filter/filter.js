/**
 * Created by 胡志洁 on 2016/5/6.
 */
angular.module('diandou.filter',[])
.filter('joinTagFilter',function(){

      function joinTagName(tagList){
          var resStr = '';
          if(tagList && tagList.constructor == Array){
              for(var i = 0 ;i < tagList.length;++i){
                  resStr = resStr + tagList[i].tagName + ',';
              }
          }

          return resStr;
      }

      return function(tagList){
          return joinTagName(tagList);
      }
  })

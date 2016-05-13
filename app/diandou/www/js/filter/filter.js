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

.filter('followFilter',function(){

    return function (isFollow){
      var bIsFollow = typeof isFollow == "String" ? eval(isFollow):isFollow;
      if(bIsFollow == false ){
        return "关注";
        }
        else{
        return "取消"
      }
    }

  })

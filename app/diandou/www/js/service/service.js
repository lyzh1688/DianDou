/**
 * Created by 胡志洁 on 2016/5/6.
 */
angular.module('diandou.services',[])
  .factory('UrlServer',['$http','$q',function($http,$q){
    return {
      getServer: function(){
        return 'http://localhost:8080/diandou';
      }
    }

  }])

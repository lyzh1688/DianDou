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

  },

  ])
  .factory('Camera', function($q) {
    return {
      getPicture: function(options) {
        var q = $q.defer();
        navigator.camera.getPicture(function(result) {
          // Do any magic you need
          q.resolve(result);
        }, function(err) {
          q.reject(err);
        }, options);

        return q.promise;
      }
    }
  })

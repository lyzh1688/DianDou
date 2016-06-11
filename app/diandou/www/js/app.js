// Ionic Starter App

// angular.module is a global place for creating, registering and retrieving Angular modules
// 'starter' is the name of this angular module example (also set in a <body> attribute in index.html)
// the 2nd parameter is an array of 'requires'
// 'starter.controllers' is found in controllers.js


angular.module('diandou', ['ionic',
                              'ngCordova',
                              'ngCordova.plugins.keyboard',
                              'diandou.controllers',
                              'diandou.services',
                              'diandou.filter',
                              'diandou.searchBox'])

.run(function($ionicPlatform) {
  $ionicPlatform.ready(function() {
    // Hide the accessory bar by default (remove this to show the accessory bar above the keyboard
    // for form inputs)
    if (window.cordova && window.cordova.plugins.Keyboard) {
      cordova.plugins.Keyboard.hideKeyboardAccessoryBar(true);
      cordova.plugins.Keyboard.disableScroll(true);

    }
    if (window.StatusBar) {
      // org.apache.cordova.statusbar required
      StatusBar.styleDefault();
    }

    //ionic.Platform.isFullScreen = true;
  });
})

.config(function($stateProvider, $urlRouterProvider,$ionicConfigProvider,$httpProvider) {
    $ionicConfigProvider.platform.ios.tabs.style('standard');
    $ionicConfigProvider.platform.ios.tabs.position('bottom');
    $ionicConfigProvider.platform.android.tabs.style('standard');
    $ionicConfigProvider.platform.android.tabs.position('standard');

    $ionicConfigProvider.platform.ios.navBar.alignTitle('center');
    $ionicConfigProvider.platform.android.navBar.alignTitle('left');

    $ionicConfigProvider.platform.ios.backButton.previousTitleText('').icon('ion-ios-arrow-thin-left');
    $ionicConfigProvider.platform.android.backButton.previousTitleText('').icon('ion-android-arrow-back');

    $ionicConfigProvider.platform.ios.views.transition('ios');
    $ionicConfigProvider.platform.android.views.transition('android');
    $ionicConfigProvider.scrolling.jsScrolling(true);
// Use x-www-form-urlencoded Content-Type
    $httpProvider.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded;charset=utf-8';
    $httpProvider.interceptors.push('AuthInjector');
    /**
     * The workhorse; converts an object to x-www-form-urlencoded serialization.
     * @param {Object} obj
     * @return {String}
     */
    var param = function(obj) {
      var query = '', name, value, fullSubName, subName, subValue, innerObj, i;

      for(name in obj) {
        value = obj[name];

        if(value instanceof Array) {
          for(i=0; i<value.length; ++i) {
            subValue = value[i];
            fullSubName = name + '[' + i + ']';
            innerObj = {};
            innerObj[fullSubName] = subValue;
            query += param(innerObj) + '&';
          }
        }
        else if(value instanceof Object) {
          for(subName in value) {
            subValue = value[subName];
            fullSubName = name + '[' + subName + ']';
            innerObj = {};
            innerObj[fullSubName] = subValue;
            query += param(innerObj) + '&';
          }
        }
        else if(value !== undefined && value !== null)
          query += encodeURIComponent(name) + '=' + encodeURIComponent(value) + '&';
      }

      return query.length ? query.substr(0, query.length - 1) : query;
    };

    // Override $http service's default transformRequest
    $httpProvider.defaults.transformRequest = [function(data) {
      return angular.isObject(data) && String(data) !== '[object File]' ? param(data) : data;
    }];
    $stateProvider
     .state('login', {
          url: '/login',
          templateUrl: 'templates/user/login.html',
          controller:"LoginCtrl"
     })
     .state('register', {
        url: '/register',
        templateUrl: 'templates/user/register.html',
        controller:"RegisterCtrl"
      })
     .state('play', {
        url: '/play/:videoId',
        templateUrl: 'templates/common/videoplay.html',
        controller:"VideoPlayCtrl"
      })
      .state('account', {
        url: '/account',
        templateUrl: 'templates/myself/account.html',
        controller:"AccountCtrl"
      })
      .state('namesetting', {
        url: '/namesetting',
        templateUrl: 'templates/myself/namesetting.html',
        controller:"NameSettingCtrl"
      })
      .state('briefsetting', {
        url: '/briefsetting',
        templateUrl: 'templates/myself/briefsetting.html',
        controller:"BriefSettingCtrl"
      })
      .state('tagsetting', {
        url: '/tagsetting',
        templateUrl: 'templates/myself/tagsetting.html',
        controller:"TagSettingCtrl"
      })
      .state('changepswd', {
        url: '/changepswd',
        templateUrl: 'templates/myself/changepswd.html',
        //controller:"AccountCtrl"
      })
      .state('headsetting', {
        url: '/headsetting',
        templateUrl: 'templates/myself/headsetting.html',
        controller:"HeadSettingCtrl"
      })
     .state('app', {
        url: '/app',
        abstract: true,
        templateUrl: 'templates/menu.html',
        controller: 'MenuCtrl'
      })
    .state('app.main', {
        url: '/main',
        views: {
          'menuContent': {
            templateUrl: 'templates/main.html',
            controller:"MainCtrl"
          }
        }
      })
    .state('app.main.videotag', {
      url: "/videotag",
      views: {
        'videotag-tab': {
          templateUrl: "templates/video/videotag.html",
          controller:"TagCtrl"
        }
      }
    })

    .state('app.main.tagvideolist', {
        url: "/tagvideolist/:tagId/:tagName",
        views: {
          'videotag-tab': {
            templateUrl: "templates/video/tagvideolist.html",
            controller:"TagVideoListCtrl"
          }
        }
      })

      .state('app.main.videosearch', {
        url: '/videosearch',
        views: {
          'videotag-tab': {
            templateUrl: 'templates/video/videosearch.html',
            controller:"VideoSearchCtrl"
          }
        }
      })

    .state('app.main.lecturer', {
        url: "/lecturer",
        views: {
          'lecturer-tab': {
            templateUrl: "templates/lecturer/lecturer.html",
            controller:"LecturersCtrl"

          }
        }
      })
      .state('app.main.ownervideolist', {
        url: "/ownervideolist/:ownerId/:ownerName",
        views: {
          'lecturer-tab': {
            templateUrl: "templates/video/ownervideolist.html",
            controller:"OwnerVideoListCtrl"
          }
        }
      })

      .state('app.main.follow', {
        url: "/follow/",
        views: {
          'follow-tab': {
            templateUrl: "templates/follow/follow.html",
            controller:"FriendsCtrl"
          }
        }
      })
      .state('app.main.myself', {
        url: "/myself",
        views: {
          'activity-tab': {
            templateUrl: "templates/myself/myself.html"
          }
        }
      })
    ;
  // if none of the above states are matched, use this as the fallback
  $urlRouterProvider.otherwise('/login');
});

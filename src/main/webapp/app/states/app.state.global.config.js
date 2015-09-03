'use strict';

angular.module('angularSeedApp').config([ '$stateProvider', '$urlRouterProvider', function($stateProvider, $urlRouterProvider) {

    $urlRouterProvider.otherwise('/');

    $stateProvider.state('site', {
	'abstract' : true,
	views : {
	    'navbar@' : {
		templateUrl : 'app/views/navbar/navbar.html',
		controller : 'NavbarController'
	    }
	},
	resolve : {
	    authorize : [ 'AuthorizationService', function(AuthorizationService) {
		return AuthorizationService.authorize();
	    } ],
	    translatePartialLoader : [ '$translate', '$translatePartialLoader', function($translate, $translatePartialLoader) {
		$translatePartialLoader.addPart('global');
	    } ]
	}
    })

    .state('site.welcome', {
	url : '/',
	data : {
	    roles : []
	},
	views : {
	    'content@' : {
		templateUrl : 'app/views/welcome.html',
	    }
	},
	resolve : {
	    translatePartialLoader : [ '$translate', '$translatePartialLoader', function($translate, $translatePartialLoader) {
		$translatePartialLoader.addPart('welcome');
		return $translate.refresh();
	    } ]
	}
    })
    
    .state('site.home', {
	'abstract' : true,
	views : {
	    'content@' : {
		templateUrl : 'app/views/home/home.html',
		controller : 'HomeController as homeController'
	    }
	},
	resolve : {
	    translatePartialLoader : [ '$translate', '$translatePartialLoader', function($translate, $translatePartialLoader) {
		$translatePartialLoader.addPart('home');
		return $translate.refresh();
	    } ]
	}
    })

    .state('site.error', {
	url : '/error',
	data : {
	    roles : [],
	    pageTitle : 'error.title'
	},
	views : {
	    'content@' : {
		templateUrl : 'app/views/error/error.html'
	    }
	},
	resolve : {
	    translatePartialLoader : [ '$translate', '$translatePartialLoader', function($translate, $translatePartialLoader) {
		$translatePartialLoader.addPart('error');
		return $translate.refresh();
	    } ]
	}
    }).state('site.accessdenied', {
	url : '/accessdenied',
	data : {
	    roles : []
	},
	views : {
	    'content@' : {
		templateUrl : 'app/views/error/accessdenied.html'
	    }
	},
	resolve : {
	    translatePartialLoader : [ '$translate', '$translatePartialLoader', function($translate, $translatePartialLoader) {
		$translatePartialLoader.addPart('error');
		return $translate.refresh();
	    } ]
	}
    });

} ]);

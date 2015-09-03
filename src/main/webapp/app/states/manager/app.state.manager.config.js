'use strict';

angular.module('angularSeedApp').config([ '$stateProvider', function($stateProvider) {

    $stateProvider.state('site.home.manager', {
	url : '/',
	data : {
	    roles : [ 'ROLE_MANAGER' ]
	},
	views : {
	    'detail@site.home' : {
		templateUrl : 'app/views/home/manager/home.manager.html',
		controller : 'ManagerHomeController as mangerHomeController'
	    }
	},
	resolve : {
	    translatePartialLoader : [ '$translate', '$translatePartialLoader', function($translate, $translatePartialLoader) {
		$translatePartialLoader.addPart('home');
		return $translate.refresh();
	    } ]
	}
    });

} ]);
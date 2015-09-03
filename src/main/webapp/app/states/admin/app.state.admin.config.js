'use strict';

angular.module('angularSeedApp').config([ '$stateProvider', function($stateProvider) {

    $stateProvider.state('site.home.admin', {
	url : '/',
	data : {
	    roles : [ 'ROLE_ADMIN' ],
	},
	views : {
	    'detail@site.home' : {
		templateUrl : 'app/views/home/admin/home.admin.html',
		controller : 'AdminHomeController as adminHomeController'
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
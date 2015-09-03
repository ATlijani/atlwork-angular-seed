'use strict';

angular.module('angularSeedApp').config([ '$stateProvider', function($stateProvider) {

    $stateProvider.state('site.home.user', {
	url : '/',
	data : {
	    roles : [ 'ROLE_USER' ],
	},
	views : {
	    'detail@site.home' : {
		templateUrl : 'app/views/home/user/home.user.html',
		controller : 'UserHomeController as userHomeController'
	    }
	},
	resolve : {
	    translatePartialLoader : [ '$translate', '$translatePartialLoader', function($translate, $translatePartialLoader) {
		$translatePartialLoader.addPart('home');
		return $translate.refresh();
	    } ]
	}
    }).state('site.home.user.mission', {
	'abstract' : true,
	data : {
	    roles : [ 'ROLE_USER' ],
	},
	resolve : {
	    translatePartialLoader : [ '$translate', '$translatePartialLoader', function($translate, $translatePartialLoader) {
		$translatePartialLoader.addPart('mission');
		return $translate.refresh();
	    } ]
	}
    }).state('site.home.user.mission.add', {
	url : 'mission/add',
	views : {
	    'detail@site.home' : {
		templateUrl : 'app/views/home/user/mission.add.html',
		controller : 'UserMissionCreateController as userMissionCreateController'
	    }
	},
	resolve : {
	    translatePartialLoader : [ '$translate', '$translatePartialLoader', function($translate, $translatePartialLoader) {
		$translatePartialLoader.addPart('home');
		return $translate.refresh();
	    } ]
	}
    }).state('site.home.user.mission.edit', {
	url : 'mission/edit',
	views : {
	    'detail@site.home' : {
		templateUrl : 'app/views/home/user/mission.edit.html',
		controller : 'UserMissionEditController as userMissionEditController'
	    }
	},
	params : {
	    mission : null
	},
	resolve : {
	    translatePartialLoader : [ '$translate', '$translatePartialLoader', function($translate, $translatePartialLoader) {
		$translatePartialLoader.addPart('home');
		return $translate.refresh();
	    } ]
	}
    });

} ]);
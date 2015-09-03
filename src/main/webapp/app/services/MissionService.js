'use strict';

angular.module('angularSeedApp').factory('MissionService', [ '$resource', function($resource) {

    return $resource('api/:loginId/missions/:idMission', {
	loginId : '@loginId',
	idMission : '@idMission'
    }, {
	update : {
	    method : 'PUT'
	}
    });
} ]);
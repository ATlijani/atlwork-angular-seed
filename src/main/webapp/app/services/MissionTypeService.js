'use strict';

angular.module('angularSeedApp').factory('MissionTypeService', [ '$resource', function($resource) {

    return $resource('api/mission-types/:idMissionType', {
	idMissionType : '@idMissionType'
    }, {
	update : {
	    method : 'PUT'
	}
    });
} ]);
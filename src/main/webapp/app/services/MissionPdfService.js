'use strict';

angular.module('angularSeedApp').factory('MissionPdfService', [ '$resource', function($resource) {

    return $resource('api/mission-report/:idMission', {
	idMission : '@idMission'
    });
} ]);
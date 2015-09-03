'use strict';

angular.module('angularSeedApp').factory('ProjectService', [ '$resource', function($resource) {

    return $resource('api/projects/:idProject', {
	idProject : '@idProject'
    }, {
	update : {
	    method : 'PUT'
	}
    });
} ]);
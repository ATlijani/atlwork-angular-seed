'use strict';

angular.module('angularSeedApp').factory('FunctionService', [ '$resource', function($resource) {

    return $resource('api/functions/:idFunction', {
	idFunction : '@idFunction'
    }, {
	update : {
	    method : 'PUT'
	}
    });
} ]);
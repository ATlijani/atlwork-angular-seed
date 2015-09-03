'use strict';

angular.module('angularSeedApp').factory('FunctionTypeService', [ '$resource', function($resource) {

    return $resource('api/function-types/:idFunctionType', {
	idFunctionType : '@idFunctionType'
    }, {
	update : {
	    method : 'PUT'
	}
    });
} ]);
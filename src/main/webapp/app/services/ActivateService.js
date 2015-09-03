'use strict';

angular.module('angularSeedApp').factory('ActivateService', [ '$resource', function($resource) {

    var activateResource = $resource('api/account/activate', {}, {
	'get' : {
	    method : 'GET',
	    params : {},
	    isArray : false
	}
    });

    return {
	activateAccount : function(key, callback) {
	    var cb = callback || angular.noop;

	    return activateResource.get(key, function(response) {
		return cb(response);
	    }, function(err) {
		return cb(err);
	    }).$promise;
	}
    };
} ]);

'use strict';

angular.module('angularSeedApp').factory('RegisterService', [ '$resource', function($resource) {

    return {
	createAccount : function(account, callback) {

	    var cb = callback || angular.noop;

	    return $resource('api/account/register', {}, {}).save(account, function() {
		return cb(account);
	    }, function(err) {
		return cb(err);
	    }.bind(this)).$promise;
	}
    };
} ]);

'use strict';

angular.module('angularSeedApp').factory('PasswordService', [ '$resource', function($resource) {

    return {
	
	changePassword : function(currentPassword,newPassword, callback) {
	    var cb = callback || angular.noop;

	    return $resource('api/account/change_password', {}, {}).save({'currentPassword':currentPassword,'newPassword':newPassword}, function() {
		return cb();
	    }, function(error) {
		return cb(error);
	    }).$promise;
	},

	resetPasswordInit : function(mail, callback) {
	    var cb = callback || angular.noop;

	    return $resource('api/account/reset_password/init', {}, {}).save(mail, function() {
		return cb();
	    }, function(error) {
		return cb(error);
	    }).$promise;
	},

	resetPasswordFinish : function(key, newPassword, callback) {
	    var cb = callback || angular.noop;

	    return $resource('api/account/reset_password/finish', {}, {}).save(key, newPassword, function() {
		return cb();
	    }, function(error) {
		return cb(error);
	    }).$promise;
	}
    };
} ]);

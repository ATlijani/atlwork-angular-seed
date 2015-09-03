'use strict';

angular.module('angularSeedApp').factory('LoginService', [ '$http', 'localStorageService', function($http, localStorageService) {

    return {
	login : function(credentials) {
	    var data = 'j_username=' + encodeURIComponent(credentials.username) + '&j_password=' + encodeURIComponent(credentials.password) + '&submit=Login';
	    return $http.post('api/authentication', data, {
		headers : {
		    'Content-Type' : 'application/x-www-form-urlencoded'
		}
	    }).success(function(response) {
		return response;
	    });
	},

	logout : function() {
	    // logout from the server
	    $http.post('api/logout').success(function(response) {
		localStorageService.clearAll();
		// to get a new csrf token call the api
		$http.get('api/account/useraccount');
		return response;
	    });
	},

	getToken : function() {
	    return localStorageService.get('token');
	},

	hasValidToken : function() {
	    return !!this.getToken();
	}
    };
} ]);

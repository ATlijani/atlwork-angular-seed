'use strict';

angular.module('angularSeedApp').factory('AuthExpiredInterceptor', [ '$rootScope', '$q', '$injector', function($rootScope, $q, $injector) {
    return {
	responseError : function(response) {
	    // If we have an unauthorized request we redirect to the login page
	    // Don't do this check on the account API to avoid infinite loop
	    if (response.status === 401 && response.data.path !== undefined && response.data.path.indexOf('/api/account/useraccount') === -1) {
		var AuthorizationService = $injector.get('AuthorizationService');
		var $state = $injector.get('$state');
		var to = $rootScope.toState;
		var params = $rootScope.toStateParams;
		AuthorizationService.logout();
		$rootScope.returnToState = to;
		$rootScope.returnToStateParams = params;
		$state.go('site.login');
	    }
	    return $q.reject(response);
	}
    };
} ]);
'use strict';

angular.module('angularSeedApp').controller(
	'NavbarController',
	[ '$scope', '$state', 'AuthorizationService', 'PrincipalService',
		function($scope, $state, AuthorizationService, PrincipalService) {

		    $scope.isAuthenticated = function() {
			return PrincipalService.isIdentityResolved() ? PrincipalService.isInAnyRole([ 'ROLE_ADMIN', 'ROLE_USER', 'ROLE_MANAGER' ]) : false;
		    };

		    $scope.$state = $state;

		    $scope.logout = function() {
			AuthorizationService.logout();
			$state.go('site.welcome');
		    };
		} ]);

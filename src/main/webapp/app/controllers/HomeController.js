'use strict';

angular.module('angularSeedApp').controller(
		'HomeController',
		[ '$scope', '$state', 'PrincipalService',
				function($scope, $state, PrincipalService) {

					var vm = this;
					$scope.$state = $state;

					PrincipalService.identity().then(function(account) {
						vm.account = account;
					});

				} ]);

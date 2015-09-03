'use strict';

angular.module(
	'angularSeedApp',
	[ 'LocalStorageModule', 'tmh.dynamicLocale', 'pascalprecht.translate', 'ngResource', 'ui.router', 'ngCookies', 'ui.bootstrap', 'ngCacheBuster',
		'infinite-scroll', 'jcs-autoValidate', 'ui.select', 'ngSanitize', 'angular-loading-bar' ]).run(
	[ 'bootstrap3ElementModifier', '$rootScope', '$state', '$window', '$translate', 'AuthorizationService', 'PrincipalService', 'APP_ENV', 'APP_VERSION',
		function(bootstrap3ElementModifier, $rootScope, $state, $window, $translate, AuthorizationService, PrincipalService, APP_ENV, APP_VERSION) {

		    $rootScope.APP_ENV = APP_ENV;
		    $rootScope.APP_VERSION = APP_VERSION;

		    $rootScope.$on('$stateChangeStart', function(event, toState, toStateParams) {

			$rootScope.toState = toState;
			$rootScope.toStateParams = toStateParams;

			if (PrincipalService.isIdentityResolved()) {
			    AuthorizationService.authorize();
			}

		    });

		    $rootScope.$on('$stateChangeSuccess', function(event, toState, toParams, fromState, fromParams) {

			$rootScope.previousStateName = fromState.name;
			$rootScope.previousStateParams = fromParams;

			var titleKey = 'global.title';

			if (toState.data.pageTitle) {
			    titleKey = toState.data.pageTitle;
			}

			$translate(titleKey).then(function(title) {
			    $window.document.title = title;
			});

		    });

		    bootstrap3ElementModifier.enableValidationStateIcons(true);
		} ]);

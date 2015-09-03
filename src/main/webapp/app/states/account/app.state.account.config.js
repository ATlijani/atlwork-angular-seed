'use strict';

angular.module('angularSeedApp').config([ '$stateProvider', function($stateProvider) {

    $stateProvider.state('site.account', {
	'abstract' : true,
	url : '/account'
    }).state('site.account.activate', {
	url : '/activate?key',
	data : {
	    roles : [],
	    pageTitle : 'activate.title'
	},
	views : {
	    'content@' : {
		templateUrl : 'app/views/account/account.activate.html',
		controller : 'ActivationController as activationController'
	    }
	},
	resolve : {
	    translatePartialLoader : [ '$translate', '$translatePartialLoader', function($translate, $translatePartialLoader) {
		$translatePartialLoader.addPart('activate');
		return $translate.refresh();
	    } ]
	}
    }).state('site.account.login', {
	url : '/login',
	data : {
	    roles : [],
	    pageTitle : 'login.title'
	},
	views : {
	    'content@' : {
		templateUrl : 'app/views/account/account.login.html',
		controller : 'LoginController as loginController'
	    }
	},
	resolve : {
	    translatePartialLoader : [ '$translate', '$translatePartialLoader', function($translate, $translatePartialLoader) {
		$translatePartialLoader.addPart('login');
		return $translate.refresh();
	    } ]
	}
    }).state('site.account.settings', {
	url : '/settings',
	data : {
	    roles : [ 'ROLE_USER', 'ROLE_MANAGER' ],
	    pageTitle : 'global.menu.account.settings'
	},
	views : {
	    'content@' : {
		templateUrl : 'app/views/account/account.settings.html',
		controller : 'SettingsController as settingsController'
	    }
	},
	resolve : {
	    translatePartialLoader : [ '$translate', '$translatePartialLoader', function($translate, $translatePartialLoader) {
		$translatePartialLoader.addPart('settings');
		return $translate.refresh();
	    } ]
	}
    }).state('site.account.password', {
	url : '/password',
	data : {
	    roles : [ 'ROLE_USER', 'ROLE_MANAGER' ],
	    pageTitle : 'global.menu.account.password'
	},
	views : {
	    'content@' : {
		templateUrl : 'app/views/account/account.password.html',
		controller : 'PasswordController as passwordController'
	    }
	},
	resolve : {
	    translatePartialLoader : [ '$translate', '$translatePartialLoader', function($translate, $translatePartialLoader) {
		$translatePartialLoader.addPart('password');
		return $translate.refresh();
	    } ]
	}
    }).state('site.account.register', {
	url : '/register',
	data : {
	    roles : [],
	    pageTitle : 'register.title'
	},
	views : {
	    'content@' : {
		templateUrl : 'app/views/account/account.register.html',
		controller : 'RegisterController as registerController'
	    }
	},
	resolve : {
	    translatePartialLoader : [ '$translate', '$translatePartialLoader', function($translate, $translatePartialLoader) {
		$translatePartialLoader.addPart('register');
		return $translate.refresh();
	    } ]
	}
    }).state('site.account.requestReset', {
	url : '/reset/request',
	data : {
	    roles : []
	},
	views : {
	    'content@' : {
		templateUrl : 'app/views/account/account.reset.request.html',
		controller : 'RequestResetController as requestResetController'
	    }
	},
	resolve : {
	    translatePartialLoader : [ '$translate', '$translatePartialLoader', function($translate, $translatePartialLoader) {
		$translatePartialLoader.addPart('reset');
		return $translate.refresh();
	    } ]
	}
    }).state('site.account.finishReset', {
	url : '/reset/finish?key',
	data : {
	    roles : []
	},
	views : {
	    'content@' : {
		templateUrl : 'app/views/account/account.reset.finish.html',
		controller : 'ResetFinishController as resetFinishController'
	    }
	},
	resolve : {
	    translatePartialLoader : [ '$translate', '$translatePartialLoader', function($translate, $translatePartialLoader) {
		$translatePartialLoader.addPart('reset');
		return $translate.refresh();
	    } ]
	}
    });

} ]);
'use strict';

angular.module('angularSeedApp').controller('LoginController', ['$state', '$timeout', 'AuthorizationService', function ($state, $timeout, AuthorizationService) {
	
	var vm = this;
	
        $timeout(function (){angular.element('[ng-model="loginController.username"]').focus();});
        
        vm.login = function (event) {
            
            event.preventDefault();
            
            AuthorizationService.login({
                username: vm.username,
                password: vm.password
            }).then(function () {
        	vm.SUCCESS_STATUS='OK';
        	$state.go('site.welcome');
            }).catch(function () {
        	vm.ERROR_STATUS = 'KO';
            });
        };
    }]);

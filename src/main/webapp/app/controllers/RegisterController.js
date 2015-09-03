'use strict';

angular.module('angularSeedApp').controller('RegisterController', ['$timeout', 'RegisterService', function ($timeout, RegisterService) {
	
	var vm = this;
	
	vm.ERROR_STATUS = null;
        vm.SUCCESS_STATUS = null;
        vm.DO_NOT_MATCH_STATUS = null;
        vm.ALREADY_USED_LOGIN_STATUS = null;
        
        vm.account = {};
        $timeout(function (){angular.element('[ng-model="registerController.account.login"]').focus();});

        vm.register = function () {
            if (vm.account.password !== vm.confirmPassword) {
        	vm.DO_NOT_MATCH_STATUS = 'KO';
            } else {

        	RegisterService.createAccount(vm.account).then(function () {
        	    vm.ERROR_STATUS = null;
                    vm.SUCCESS_STATUS = 'OK';
                    vm.DO_NOT_MATCH_STATUS = null;
                    vm.ALREADY_USED_LOGIN_STATUS = null;
                    vm.ALREADY_USED_EMAIL_STATUS = null;
                }).catch(function (error) {
                    vm.SUCCESS_STATUS = null;
                    if (error.status === 400 && error.data.message === 'ALREADY_USED_LOGIN_STATUS') {
                	vm.ALREADY_USED_LOGIN_STATUS = 'KO';
                    } else if (error.status === 400 && error.data.message === 'ALREADY_USED_EMAIL_STATUS') {
                	vm.ALREADY_USED_EMAIL_STATUS = 'KO';
                    } else {
                	vm.ERROR_STATUS = 'KO';
                    }
                });
            }
        };
    }]);

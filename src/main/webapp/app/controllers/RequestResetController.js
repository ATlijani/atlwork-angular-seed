'use strict';

angular.module('angularSeedApp').controller('RequestResetController', ['$timeout', 'PasswordService', function ($timeout, PasswordService) {
	
	var vm = this;

        vm.SUCCESS_STATUS = null;
        vm.ERROR_STATUS = null;
        vm.EMAIL_NOT_REGISTERED_STATUS = null;
        vm.USER_NOT_ACTIVATED_STATUS=null;
        
        vm.resetAccount = {};
        $timeout(function (){angular.element('[ng-model="requestResetController.resetAccount.email"]').focus();});

        vm.requestReset = function () {

            vm.ERROR_STATUS = null;
            vm.EMAIL_NOT_REGISTERED_STATUS = null;
            vm.USER_NOT_ACTIVATED_STATUS=null;
            
            PasswordService.resetPasswordInit(vm.resetAccount.email).then(function () {
        	vm.SUCCESS_STATUS = 'OK';
            }).catch(function (error) {
        	vm.SUCCESS_STATUS = null;
                if (error.status === 400 && error.data.message === 'EMAIL_NOT_REGISTERED_STATUS') {
                    vm.EMAIL_NOT_REGISTERED_STATUS = 'KO';
                }else if (error.status === 400 && error.data.message === 'USER_NOT_ACTIVATED_STATUS') {
                    vm.USER_NOT_ACTIVATED_STATUS = 'KO';
                } else {
                    vm.ERROR_STATUS = 'KO';
                }
            });
        };

    }]);

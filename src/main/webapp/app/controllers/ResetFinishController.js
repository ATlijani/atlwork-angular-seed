'use strict';

angular.module('angularSeedApp').controller('ResetFinishController', ['$stateParams', '$timeout', 'PasswordService', function ($stateParams, $timeout, PasswordService) {

	var vm = this;
	
	vm.KEY_MISSING_STATUS = $stateParams.key === undefined;

	vm.resetAccount = {};
        $timeout(function (){angular.element('[ng-model="resetFinishController.resetAccount.password"]').focus();});

        vm.finishReset = function() {
            
            vm.DO_NOT_MATCH_STATUS = null;
            vm.INCORRECT_PASSWORD_STATUS=null;
            
            if (vm.resetAccount.password !== vm.confirmPassword) {
        	vm.DO_NOT_MATCH_STATUS = 'KO';
            } else {
        	PasswordService.resetPasswordFinish({key: $stateParams.key, newPassword: vm.resetAccount.password}).then(function () {
        	    vm.SUCCESS_STATUS = 'OK';
                }).catch(function (error) {
                    
                    
                    vm.SUCCESS_STATUS = null;
                    
                    if (error.status === 400 && error.data.message === 'INCORRECT_PASSWORD_STATUS') {
                	vm.INCORRECT_PASSWORD_STATUS = 'KO';
                    }else{
                	
                    vm.ERROR_STATUS = 'KO';
                    }

                });
            }

        };
    }]);

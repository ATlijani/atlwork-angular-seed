'use strict';

angular.module('angularSeedApp').controller('PasswordController', ['PasswordService', 'PrincipalService', function (PasswordService, PrincipalService) {
	
	var vm = this;
	
	PrincipalService.identity().then(function(account) {
            vm.account = account;
        });

	
	vm.changePassword = function () {

	    vm.ERROR_STATUS = null;
	    vm.SUCCESS_STATUS = null;
	    vm.DO_NOT_MATCH_STATUS = null;
	    vm.INCORRECT_PASSWORD_STATUS=null;
	    vm.DO_NOT_MATCH_CURRENT_STATUS=null;
	    vm.BAD_PASSWORD_STATUS=null;
	    
	    if (vm.password !== vm.confirmPassword) {
        	vm.DO_NOT_MATCH_STATUS = 'KO';
            } else {
     	    	    PasswordService.changePassword(vm.currentPassword,vm.password).then(function () {
                        vm.SUCCESS_STATUS = 'OK';
                    }).catch(function (error) {
                	console.log(error);
                        if (error.status === 400 && error.data.message === 'DO_NOT_MATCH_CURRENT_STATUS') {
                    		vm.DO_NOT_MATCH_CURRENT_STATUS = 'KO';
                        }else if (error.status === 400 && error.data.message === 'BAD_PASSWORD_STATUS') {
                    		vm.BAD_PASSWORD_STATUS = 'KO';
                        }else{
                    		vm.ERROR_STATUS = 'KO';
                        }
                    });
     	    	}
        	
        	
        	
            };
        
       
    }]);

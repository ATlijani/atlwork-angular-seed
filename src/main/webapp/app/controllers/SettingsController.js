'use strict';

angular.module('angularSeedApp').controller('SettingsController', ['PrincipalService', 'AuthorizationService', '$translate', function (PrincipalService, AuthorizationService) {
	
	var vm = this;
	
        vm.SUCCESS_STATUS = null;
        vm.ERROR_STATUS = null;
        PrincipalService.identity(true).then(function(account) {
            vm.account = account;
        });

        vm.save = function () {
            AuthorizationService.updateAccount(vm.account).then(function() {
        	vm.ERROR_STATUS = null;
        	vm.SUCCESS_STATUS = 'OK';
                PrincipalService.identity().then(function(account) {
                    vm.account = account;
                });
                        
            }).catch(function() {
        	vm.SUCCESS_STATUS = null;
        	vm.ERROR_STATUS = 'KO';
            });
        };
    }]);

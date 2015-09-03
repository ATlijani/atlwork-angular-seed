'use strict';

angular.module('angularSeedApp').controller('ActivationController', ['$stateParams','ActivateService',function ($stateParams, ActivateService) {
	
	var vm = this;
	
	ActivateService.activateAccount({key: $stateParams.key}).then(function () {
            vm.ERROR_STATUS = null;
            vm.SUCCESS_STATUS = 'OK';
        }).catch(function () {
            vm.SUCCESS_STATUS = null;
            vm.ERROR_STATUS = 'KO';
        });
    }]);


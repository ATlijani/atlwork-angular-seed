'use strict';

angular.module('angularSeedApp').controller('ManagerHomeController', function() {

    var vm = this;
    vm.searchParams={};
    vm.availableSearchParams = [ {
	key : 'client',
	name : 'Client',
	placeholder : 'Client...'
    }, {
	key : 'projet',
	name : 'Projet',
	placeholder : 'Projet...'
    } ];

});

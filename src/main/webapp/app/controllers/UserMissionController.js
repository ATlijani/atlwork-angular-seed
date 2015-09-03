'use strict';

angular.module('angularSeedApp').controller('UserMissionController', [ 'PrincipalService', 'MissionService', 'MissionPdfService', function(PrincipalService, MissionService, MissionPdfService) {

    var vm = this;
    vm.missions=[];

    PrincipalService.identity().then(function(account) {

	MissionService.query({}, {
	    loginId : account.login
	}, function(data) {
	    vm.missions = data;
	});
    });
    
    vm.deleteMission = function(mission) { 
    	PrincipalService.identity().then(function() {
		MissionService.delete({}, { idMission:mission.id },function(){
		    vm.missions.splice(vm.missions.indexOf(mission), 1);
		});
    	});
    };
    
    vm.getPdfMission = function(mission,event){
	event.stopPropagation();
	PrincipalService.identity().then(function() {
	    MissionPdfService.get({}, { idMission:mission.id });
	});
    };
} ]);

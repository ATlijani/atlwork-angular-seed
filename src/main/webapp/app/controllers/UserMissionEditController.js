'use strict';

angular.module('angularSeedApp').controller(
	'UserMissionEditController',
	[ '$state', '$timeout', '$stateParams', 'PrincipalService', 'ClientService', 'ProjectService', 'FunctionService', 'FunctionTypeService', 'MissionService', 'MissionTypeService',
		function($state, $timeout, $stateParams, PrincipalService, ClientService, ProjectService, FunctionService, FunctionTypeService, MissionService, MissionTypeService) {

		    var vm = this;

		    var mission = $stateParams.mission;
		    vm.mission = mission;
		    
		    vm.clients=[];
		    vm.projects=[];
		    vm.functions=[];
		    vm.functionTypes=[];
		    vm.missionTypes=[];

		    $timeout(function() {
			angular.element('[ng-model="userMissionEditController.mission.title"]').focus();
		    });
		    
		    ClientService.query({}, {}, function(response) {
			vm.clients = response;
		    });
		    ProjectService.query({}, {}, function(response) {
			vm.projects = response;
		    });
		    FunctionService.query({}, {}, function(response) {
			vm.functions = response;
		    });
		    FunctionTypeService.query({}, {}, function(response) {
			vm.functionTypes = response;
		    });
		    MissionTypeService.query({}, {}, function(response) {
			vm.missionTypes = response;
		    });

		    vm.updateMission = function(newMission) {

			PrincipalService.identity().then(function() {

			    var missionService = new MissionService(newMission);
			    missionService.$update({}, function() {
				//on success
				$state.go('site.welcome');
			    }, function(error) {
				// on failure
				console.log(error);
				if (error.status === 400 && error.data.message === 'UNIQUE_CONSTRAINT_VIOLATION_STATUS') {
				    vm.UNIQUE_CONSTRAINT_VIOLATION_STATUS = 'KO';
				}else{				    
				    vm.ERROR_STATUS = 'KO';
				}
			    });

			});
		    };
		} ]);

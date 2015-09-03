'use strict';

angular.module('angularSeedApp').controller(
	'UserMissionCreateController',
	[ '$location', '$timeout', 'PrincipalService', 'ClientService', 'ProjectService', 'FunctionService', 'FunctionTypeService', 'MissionService',
		'MissionTypeService', function($location, $timeout, PrincipalService, ClientService, ProjectService, FunctionService, FunctionTypeService, MissionService, MissionTypeService) {

		    var vm = this;
		    
		    vm.clients=[];
		    vm.projects=[];
		    vm.functions=[];
		    vm.functionTypes=[];
		    vm.missionTypes=[];

		    $timeout(function() {
			angular.element('[ng-model="userMissionCreateController.mission.title"]').focus();
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

		    vm.addMission = function(newMission) {
			PrincipalService.identity().then(function(account) {
			    vm.newMission = new MissionService(newMission);
			    vm.newMission.$save({
				loginId : account.login
			    }, function() {
				//on success
				$location.path('/');
				// state.go
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

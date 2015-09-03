'use strict';

angular.module('angularSeedApp').directive('hasRoleDirective', [ 'PrincipalService', function(PrincipalService) {
    
    return {
	restrict : 'A',
	link : function(scope, element, attrs) {
	    var setVisible = function() {
		element.removeClass('hidden');
	    }, setHidden = function() {
		element.addClass('hidden');
	    }, defineVisibility = function(reset) {
		var result;
		if (reset) {
		    setVisible();
		}

		result = PrincipalService.isInRole(role);
		if (result) {
		    setVisible();
		} else {
		    setHidden();
		}
	    }, role = attrs.hasRoleDirective.replace(/\s+/g, '');

	    if (role.length > 0) {
		defineVisibility(true);
	    }
	}
    };
} ]);
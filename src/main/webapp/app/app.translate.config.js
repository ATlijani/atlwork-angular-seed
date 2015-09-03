'use strict';

angular.module('angularSeedApp').config([ '$translateProvider', function($translateProvider) {
    $translateProvider.useLoader('$translatePartialLoader', {
	urlTemplate : 'app/views/properties/global.json'
    });

    $translateProvider.preferredLanguage('en');
    $translateProvider.useCookieStorage();
    $translateProvider.useSanitizeValueStrategy('escaped');

} ]);
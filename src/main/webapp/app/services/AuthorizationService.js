'use strict';

angular.module('angularSeedApp').factory('AuthorizationService', ['$rootScope', '$state', '$q', '$translate', 'PrincipalService', 'LoginService', 'AccountService', function($rootScope, $state, $q, $translate, PrincipalService, LoginService, AccountService) {
    
        return {
            login: function (credentials, callback) {
                var cb = callback || angular.noop;
                var deferred = $q.defer();

                LoginService.login(credentials).then(function (data) {
                    // retrieve the logged account information
                    PrincipalService.identity(true).then(function() {
                      
                        $translate.refresh();
                        deferred.resolve(data);
                    });
                    return cb();
                }).catch(function (err) {
                    this.logout();
                    deferred.reject(err);
                    return cb(err);
                }.bind(this));

                return deferred.promise;
            },

            logout: function () {
        	LoginService.logout();
        	PrincipalService.authenticate(null);
            },

            authorize: function(force) {
                return PrincipalService.identity(force)
                    .then(function() {
                	
                        if ($rootScope.toState.data.roles && $rootScope.toState.data.roles.length > 0 && !PrincipalService.isInAnyRole($rootScope.toState.data.roles)) {
                            
                            if (PrincipalService.isAuthenticated()) {
                                // user signed in but not authorized
                                $state.go('site.accessdenied');
                            }
                            else {
                                // user is not authenticated
                                $rootScope.returnToState = $rootScope.toState;
                                $rootScope.returnToStateParams = $rootScope.toStateParams;

                                $state.go('site.login');
                            }
                        }
                        else{
                            if($rootScope.toState.name==='site.welcome'){
                    		if(PrincipalService.isInRole('ROLE_ADMIN')){
                    		    $state.go('site.home.admin');
                    		}else if(PrincipalService.isInRole('ROLE_USER')){
                    		    $state.go('site.home.user');
                    		}else if(PrincipalService.isInRole('ROLE_MANAGER')){
                    		    $state.go('site.home.manager');
                    		}
                            }
                        }
                    });
            },
            
            updateAccount: function (account, callback) {
                var cb = callback || angular.noop;

                return AccountService.save(account,
                    function () {
                        return cb(account);
                    },
                    function (err) {
                        return cb(err);
                    }.bind(this)).$promise;
            }

            
         };
    }]);

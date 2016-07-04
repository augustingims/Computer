'use strict';

angular.module('devbridgeApp')
    .controller('NavbarController', function ($scope, $location, $state, Auth, Principal, $rootScope, $http) {
        $scope.isAuthenticated = Principal.isAuthenticated;
        $scope.$state = $state;

        $scope.logout = function () {
            Auth.logout();
            $state.go('home');
        };

        $rootScope.$on('devbridgeApp:paniershopUpdate', function(event, response) {
            $scope.nbreshop = response;
        });

        $rootScope.$on('devbridgeApp:vidershop', function(event, response) {
            $scope.nbreshop = 0;
        });

        $rootScope.$on('devbridgeApp:deleteshop', function(event, response) {
            $scope.nbreshop = $scope.nbreshop - 1;
        });

        $scope.nbreshops = function () {
            $http.get('api/nbreshop').success(function(response){
                $scope.nbreshop = response;
            }).error(function(reason){
                console.log(reason);
            });
        };

    });

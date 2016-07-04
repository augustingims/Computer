'use strict';

angular.module('devbridgeApp')
    .controller('OrdinateurDetailController', function ($scope, $rootScope, $stateParams, entity, Ordinateur) {
        $scope.ordinateur = entity;
        $scope.load = function (id) {
            Ordinateur.get({id: id}, function(result) {
                $scope.ordinateur = result;
            });
        };
        $rootScope.$on('devbridgeApp:ordinateurUpdate', function(event, result) {
            $scope.ordinateur = result;
        });
    });

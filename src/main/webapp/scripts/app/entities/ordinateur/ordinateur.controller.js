'use strict';

angular.module('devbridgeApp')
    .controller('OrdinateurController', function ($scope, Ordinateur) {
        $scope.ordinateurs = [];
        $scope.loadAll = function() {
            Ordinateur.query(function(result) {
               $scope.ordinateurs = result;
            });
        };
        $scope.loadAll();

        $scope.delete = function (id) {
            Ordinateur.get({id: id}, function(result) {
                $scope.ordinateur = result;
                $('#deleteOrdinateurConfirmation').modal('show');
            });
        };

        $scope.confirmDelete = function (id) {
            Ordinateur.delete({id: id},
                function () {
                    $scope.loadAll();
                    $('#deleteOrdinateurConfirmation').modal('hide');
                    $scope.clear();
                });
        };

        $scope.refresh = function () {
            $scope.loadAll();
            $scope.clear();
        };

        $scope.clear = function () {
            $scope.ordinateur = {name: null, type: null, cpu: null, ram: null, lecteur: null, graveur: null, bluetooth: null, wifi: null, cartegraphique: null, dd: null, tailleecran: null, webcam: null, id: null};
        };
    });

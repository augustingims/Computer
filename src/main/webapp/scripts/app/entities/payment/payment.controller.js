'use strict';

angular.module('devbridgeApp')
    .controller('paymentController', function ($scope,$http, Payment, entity, $stateParams, $rootScope) {

        $scope.payment = entity;

        $scope.load = function(id) {
            Payment.get({id : id}, function(result) {
                console.log(result);
                $scope.payment = result;
            });
        };
        $scope.load();

        var onSaveFinished = function (result) {
           console.log(result);
        };

        $rootScope.$on('devbridgeApp:prix', function(event, response) {
            $scope.montant = response;
        });

        $scope.pay = function () {

            if ($scope.payment.id != null) {
                Payment.update($scope.payment, onSaveFinished);
            } else {
                $scope.payment.montant = $scope.prix;
                Payment.save($scope.payment, onSaveFinished);
            }
            $scope.payment = {};
        };

    });

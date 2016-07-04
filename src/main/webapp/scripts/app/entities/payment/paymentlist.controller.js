'use strict';

angular.module('devbridgeApp')
    .controller('paymentlistController', function ($scope,$http) {
        $scope.payments = [];
        $scope.load = function () {
            $http.get('api/payment').success(function(response){
                $scope.payments = response;
            }).error(function(reason){
                console.log(reason);
            });
        };
        $scope.load();
    });

'use strict';

angular.module('devbridgeApp')
    .controller('paniershopController', function ($scope, $http, $state) {

        $scope.paniershops = [];

        $scope.loadAll = function () {
            $http.get('api/panieruser').success(function(response){
                $scope.paniershops = response;
            }).error(function(reason){
                console.log(reason);
            });
        };
        $scope.loadAll();

        $scope.payer = function(prix){
            console.log(prix);
            $state.go('payment');
            $scope.$emit('devbridgeApp:prix', prix);
        };
        $scope.total = function(){
            $scope.prixtotal = 0;
            for(var i=0;i< $scope.paniershops.length;i++){
               $scope.prixtotal= $scope.prixtotal + $scope.paniershops[i].prix;
                }
            return $scope.prixtotal;
        };

        $scope.delete = function (item) {
            $http.post('api/delete/'+item).success(function(response){
                $scope.$emit('devbridgeApp:deleteshop', response);
                console.log(response);
                $scope.loadAll();
            }).error(function(reason){
                console.log(reason);
            });
        };

        $scope.vider = function () {
            for(var i=0;i< $scope.paniershops.length;i++){
                $http.post('api/delete/' +$scope.paniershops[i].id).success(function(response){
                    $scope.$emit('devbridgeApp:vidershop', response);
                    console.log(response);
                }).error(function(reason){
                    console.log(reason);
                });
            }
            $scope.loadAll();
        };

    });

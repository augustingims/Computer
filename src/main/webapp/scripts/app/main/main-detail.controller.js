'use strict';

angular.module('devbridgeApp')
    .controller('MainDetailController', function ($scope, $rootScope, $stateParams, Commentaire, Ordinateur, Principal, $http, entity, $state) {

        $scope.isAuthenticated = Principal.isAuthenticated;

        $scope.ordinateur = {};

        $scope.commentaire = entity;

        $scope.comments = function(image){
            $http.get('api/nbrescom/'+image).success(function(response){
                console.log(response);
                $scope.nbrecomment = response;
            }).error(function(reason){
                console.log(reason);
            });

        };

        $scope.signin = function (){
            $state.go('login');
        };

        $scope.loadcom = function (image) {
            $http.get('api/comforimage/'+image).success(function(response){
                console.log(response);
                $scope.commentaires = response;
            }).error(function(reason){
                console.log(reason);
            });
        };

        $scope.load = function() {

            Ordinateur.get({id: $stateParams.id}, function(result) {
                $scope.ordinateur = result;
            });
            $scope.loadcom($stateParams.id);
            $scope.comments($stateParams.id);
        };
        $scope.load();

        var onSaveFinished = function (result) {
            $scope.$emit('devbridgeApp:commentaire', result);
            $scope.load();
        };

        $scope.loader = function(id) {
            Commentaire.get({id : id}, function(result) {
                console.log(result);
                $scope.commentaire = result;
            });
        };
        $scope.submit = function (image) {

            if ($scope.commentaire.id != null) {
                Commentaire.update($scope.commentaire, onSaveFinished);
            } else {
                $scope.commentaire.image = image ;
                Commentaire.save($scope.commentaire, onSaveFinished);
            }
            $scope.commentaire = {};
        };
        $scope.saveshop = function () {
            $http.post('api/paniershop/'+$scope.ordinateur.id+'/'+$scope.ordinateur.prix).success(function(response){
                console.log(response);
                $scope.$emit('devbridgeApp:paniershopUpdate', response);
            }).error(function(reason){
                console.log(reason);
            });
        };


    });



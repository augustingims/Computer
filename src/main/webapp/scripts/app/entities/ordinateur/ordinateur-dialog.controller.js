'use strict';

angular.module('devbridgeApp').controller('OrdinateurDialogController',
    ['$scope', '$stateParams', 'entity', 'Ordinateur', '$state',
        function($scope, $stateParams, entity, Ordinateur, $state) {

        $scope.ordinateur = entity;
        $scope.load = function(id) {
            Ordinateur.get({id : id}, function(result) {
                $scope.ordinateur = result;
            });
        };

        var onSaveFinished = function (result) {
            $scope.$emit('devbridgeApp:ordinateurUpdate', result);
            $scope.ordinateur = {};
            $state.go('ordinateur');
        };

        $scope.save = function () {
            if ($scope.ordinateur.id != null) {
                Ordinateur.update($scope.ordinateur, onSaveFinished);
            } else {
                Ordinateur.save($scope.ordinateur, onSaveFinished);
            }
        };
          $scope.clear = function(){
              $scope.ordinateur = {};
          }

            $scope.byteSize = function (base64String) {
                if (!angular.isString(base64String)) {
                    return '';
                }
                function endsWith(suffix, str) {
                    return str.indexOf(suffix, str.length - suffix.length) !== -1;
                }
                function paddingSize(base64String) {
                    if (endsWith('==', base64String)) {
                        return 2;
                    }
                    if (endsWith('=', base64String)) {
                        return 1;
                    }
                    return 0;
                }
                function size(base64String) {
                    return base64String.length / 4 * 3 - paddingSize(base64String);
                }
                function formatAsBytes(size) {
                    return size.toString().replace(/\B(?=(\d{3})+(?!\d))/g, " ") + " bytes";
                }

                return formatAsBytes(size(base64String));
            };

            $scope.setImage = function ($files, ordinateur) {
                if ($files[0]) {
                    var file = $files[0];
                    var fileReader = new FileReader();
                    fileReader.readAsDataURL(file);
                    fileReader.onload = function (e) {
                        var data = e.target.result;
                        var base64Data = data.substr(data.indexOf('base64,') + 'base64,'.length);
                        $scope.$apply(function() {
                            ordinateur.image = base64Data;
                        });
                    };
                }
            };
}]);

'use strict';

angular.module('devbridgeApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('paniershop', {
                parent: 'site',
                url: '/paniershop',
                data: {
                    authorities: [],
                    pageTitle: 'panier shopping'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/paniershop/paniershop.html',
                        controller: 'paniershopController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('paniershop');
                        return $translate.refresh();
                    }]
                }
            });

    });

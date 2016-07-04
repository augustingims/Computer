'use strict';

angular.module('devbridgeApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('ordinateur', {
                parent: 'entity',
                url: '/ordinateurs',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'devbridgeApp.ordinateur.home.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/ordinateur/ordinateurs.html',
                        controller: 'OrdinateurController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('ordinateur');
                        $translatePartialLoader.addPart('global');
                        return $translate.refresh();
                    }]
                }
            })
            .state('ordinateur.detail', {
                parent: 'entity',
                url: '/ordinateur/{id}',
                data: {
                    authorities: ['ROLE_USER'],
                    pageTitle: 'devbridgeApp.ordinateur.detail.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/ordinateur/ordinateur-detail.html',
                        controller: 'OrdinateurDetailController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('ordinateur');
                        return $translate.refresh();
                    }],
                    entity: ['$stateParams', 'Ordinateur', function($stateParams, Ordinateur) {
                        return Ordinateur.get({id : $stateParams.id});
                    }]
                }
            })
            .state('ordinateur.new', {
                parent: 'ordinateur',
                url: '/new',
                data: {
                    authorities: ['ROLE_USER'],
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/ordinateur/ordinateur-dialog.html',
                        controller: 'OrdinateurDialogController',
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('ordinateur');
                        return $translate.refresh();
                    }],
                    entity: ['$stateParams', 'Ordinateur', function($stateParams, Ordinateur) {
                        return {};
                    }]
                }
            })
            .state('ordinateur.edit', {
                parent: 'ordinateur',
                url: '/{id}/edit',
                data: {
                    authorities: ['ROLE_USER'],
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/ordinateur/ordinateur-dialog.html',
                        controller: 'OrdinateurDialogController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('ordinateur');
                        return $translate.refresh();
                    }],
                    entity: ['$stateParams','Ordinateur', function($stateParams,Ordinateur) {
                        return Ordinateur.get({id : $stateParams.id});
                    }]
            }});
    });

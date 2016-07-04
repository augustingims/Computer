'use strict';

angular.module('devbridgeApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('contact', {
                parent: 'site',
                url: '/contact',
                data: {
                    authorities: [],
                    pageTitle: 'Contact Me'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/contact/contact.html',
                        controller: 'contactController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('contact');
                        return $translate.refresh();
                    }]
                }
            });

    });

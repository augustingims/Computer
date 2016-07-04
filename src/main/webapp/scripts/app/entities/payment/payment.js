'use strict';

angular.module('devbridgeApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('payment', {
                parent: 'site',
                url: '/payment',
                data: {
                    authorities: [],
                    pageTitle: 'payment panier shopping'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/payment/payment.html',
                        controller: 'paymentController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('payment');
                        return $translate.refresh();
                    }],
                    entity: ['$stateParams', 'Payment', function($stateParams, Payment) {
                        return {};
                    }]
                }
            }).state('paymentlist', {
                parent: 'site',
                url: '/paymentlist',
                data: {
                    authorities: [],
                    pageTitle: 'payment effectif'
                },
                views: {
                    'content@': {
                        templateUrl: 'scripts/app/entities/payment/paymentlist.html',
                        controller: 'paymentlistController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('paymentlist');
                        return $translate.refresh();
                    }]
                }
            });


    });

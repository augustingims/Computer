'use strict';

angular.module('devbridgeApp')
    .factory('Register', function ($resource) {
        return $resource('api/register', {}, {
        });
    });



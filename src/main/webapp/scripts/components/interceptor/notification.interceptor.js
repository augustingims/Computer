 'use strict';

angular.module('devbridgeApp')
    .factory('notificationInterceptor', function ($q, AlertService) {
        return {
            response: function(response) {
                var alertKey = response.headers('X-devbridgeApp-alert');
                if (angular.isString(alertKey)) {
                    AlertService.success(alertKey, { param : response.headers('X-devbridgeApp-params')});
                }
                return response;
            },
        };
    });
'use strict';

angular.module('devbridgeApp')
    .controller('LogoutController', function (Auth) {
        Auth.logout();
    });

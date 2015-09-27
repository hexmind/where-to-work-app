'use strict';

angular.module('wheretoworkappApp')
    .controller('LogoutController', function (Auth) {
        Auth.logout();
    });

'use strict';

angular.module('wheretoworkappApp')
    .factory('Register', function ($resource) {
        return $resource('api/register', {}, {
        });
    });



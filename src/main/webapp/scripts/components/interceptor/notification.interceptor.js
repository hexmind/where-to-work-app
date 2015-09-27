 'use strict';

angular.module('wheretoworkappApp')
    .factory('notificationInterceptor', function ($q, AlertService) {
        return {
            response: function(response) {
                var alertKey = response.headers('X-wheretoworkappApp-alert');
                if (angular.isString(alertKey)) {
                    AlertService.success(alertKey, { param : response.headers('X-wheretoworkappApp-params')});
                }
                return response;
            },
        };
    });
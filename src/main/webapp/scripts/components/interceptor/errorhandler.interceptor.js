'use strict';

angular.module('wheretoworkappApp')
    .factory('errorHandlerInterceptor', function ($q, $rootScope) {
        return {
            'responseError': function (response) {
                if (!(response.status == 401 && response.data.path.indexOf("/api/account") == 0 )){
	                $rootScope.$emit('wheretoworkappApp.httpError', response);
	            }
                return $q.reject(response);
            }
        };
    });
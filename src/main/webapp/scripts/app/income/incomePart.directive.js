'use strict';

angular.module('wheretoworkappApp')
.directive('incomePart', function() {
  return {
    restrict: 'AE',
    scope: {
      title: '@',
      money: '=',
    },
    template: '<h4>{{title}}</h4><h3>{{money.value}} {{money.currency}}</h3>'
  }
});

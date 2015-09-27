'use strict';

angular.module('wheretoworkappApp')
    .service('IncomeService', function ($resource) {

       this.getCountries = function(){
            return $resource('/api/countries').query();
       }

        this.getIncome = function(countryCode, dailyRate){
            return $resource('/api/income').get({
                countryCode:countryCode,
                dailyRate:dailyRate
            });
        }
    });

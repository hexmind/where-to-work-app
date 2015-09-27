'use strict';

angular.module('wheretoworkappApp')
    .controller('MainController', function ($scope, Principal, IncomeService) {

        this.countries = IncomeService.getCountries();

        Principal.identity().then(function(account) {
            $scope.account = account;
            $scope.isAuthenticated = Principal.isAuthenticated;
        });

        this.calculate = function(){
            if(this.country && this.dailyRate){
                this.income =  IncomeService.getIncome(
                    this.country.code,
                    this.dailyRate);
            } else {
                this.income = undefined;
            }
        }
    });

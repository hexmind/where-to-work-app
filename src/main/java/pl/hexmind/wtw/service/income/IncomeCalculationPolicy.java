package pl.hexmind.wtw.service.income;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pl.hexmind.wtw.model.Country;
import pl.hexmind.wtw.model.CountryCost;
import pl.hexmind.wtw.model.Money;
import pl.hexmind.wtw.model.MonthlyIncome;

/**
 * @author ts
 */
@Component
public class IncomeCalculationPolicy {

    private final CountryCostService countryCostService;
    private final int workdaysInMonth;

    @Autowired
    public IncomeCalculationPolicy(CountryCostService countryCostService,
                                   @Value("${app.constants.workdaysInMonth}") int workdaysInMonth) {
        this.countryCostService = countryCostService;
        this.workdaysInMonth = workdaysInMonth;
    }

    public MonthlyIncome calculateMonthlyIncome(Money dailyRate, Country country) {
        Money gross = dailyRate.multiply(workdaysInMonth);
        CountryCost cost = countryCostService.findOneByCountry(country);

        Money taxValue = gross.multiply(cost.getIncomeTax());
        Money netValue = gross.substract(taxValue).substract(cost.getFixedCost());
        return new MonthlyIncome(netValue, taxValue, cost);
    }

}

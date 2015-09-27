package pl.hexmind.wtw.service.income;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.hexmind.wtw.model.Country;
import pl.hexmind.wtw.model.CountryCost;
import pl.hexmind.wtw.model.Money;
import pl.hexmind.wtw.model.MonthlyIncome;
import pl.hexmind.wtw.service.exchange.CurrencyConverter;

import java.math.BigDecimal;
import java.util.Currency;

/**
 * @author ts
 */
@Service
public class IncomeCalculatorService {

    private final CountryRepository countryRepository;
    private final IncomeCalculationPolicy policy;
    private final CurrencyConverter converter;
    private final String defaultCountry;
    private final Currency defaultCurrency;

    @Autowired
    public IncomeCalculatorService(CountryRepository countryRepository,
                                   IncomeCalculationPolicy policy,
                                   CurrencyConverter converter,
                                   @Value("${app.default.country}") String defaultCountry) {
        this.countryRepository = countryRepository;
        this.policy = policy;
        this.converter = converter;
        this.defaultCountry = defaultCountry;
        this.defaultCurrency = countryRepository.findOne(this.defaultCountry).getCurrency();
    }

    public MonthlyIncome calculateMonthlyIncome(String countryCode, BigDecimal dailyRate) {
        Country country = countryRepository.findOne(countryCode);
        Money money = new Money(dailyRate, country.getCurrency());
        MonthlyIncome income = policy.calculateMonthlyIncome(money, country);
        return convert(income);
    }

    public MonthlyIncome convert(MonthlyIncome income) {
        Money[] toConvert = {
            income.getNetValue(),
            income.getTaxValue(),
            income.getFixedCost()};
        Money[] converted = converter.convert(defaultCurrency, toConvert);

        Money netValue = converted[0];
        Money taxValue = converted[1];
        Money fixedCost = converted[2];
        CountryCost cost = new CountryCost(defaultCountry, income.getIncomeTax(), fixedCost);
        return new MonthlyIncome(netValue, taxValue, cost);
    }
}

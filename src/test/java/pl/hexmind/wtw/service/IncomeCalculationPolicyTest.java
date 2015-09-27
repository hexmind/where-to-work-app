package pl.hexmind.wtw.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import pl.hexmind.wtw.Application;
import pl.hexmind.wtw.model.Country;
import pl.hexmind.wtw.model.Money;
import pl.hexmind.wtw.model.MonthlyIncome;
import pl.hexmind.wtw.service.income.IncomeCalculationPolicy;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.Currency;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
@Transactional
public class IncomeCalculationPolicyTest {

    private static final Currency EURO = Currency.getInstance("EUR");
    private static final Country GERMANY = new Country("Germany", "DE", EURO);

    @Inject
    private IncomeCalculationPolicy incomeCalculationPolicy;

    @Value("${app.constants.workdaysInMonth}")
    private int workdays;

    @Test
    public void shouldCalculateMonthlyIncome() {
        int dailyValue = 1000;
        Money dailyRate = toEuro(dailyValue);

        MonthlyIncome income = incomeCalculationPolicy.calculateMonthlyIncome(dailyRate, GERMANY);
        int fixedCost = income.getFixedCost().getValue().intValue();

        assertThat(income.getNetValue()).isEqualTo(toEuro(workdays * dailyValue * (1.00 - 0.20) - fixedCost));
        assertThat(income.getTaxValue()).isEqualTo(toEuro(workdays * dailyValue * 0.20));
    }

    private Money toEuro(double d) {
        return new Money(BigDecimal.valueOf(d), EURO);
    }
}

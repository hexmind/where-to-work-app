package pl.hexmind.wtw.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import pl.hexmind.wtw.Application;
import pl.hexmind.wtw.model.Country;
import pl.hexmind.wtw.model.CountryCost;
import pl.hexmind.wtw.model.Money;
import pl.hexmind.wtw.service.income.CountryCostService;

import javax.inject.Inject;
import java.util.Currency;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
@Transactional
public class CountryCostServiceTest {

    private static final Currency EURO = Currency.getInstance("EUR");

    @Inject
    private CountryCostService countryCostService;

    @Test
    public void shouldFindCoutryCost() {
        Country germany = new Country("Germany", "DE", EURO);

        CountryCost cost = countryCostService.findOneByCountry(germany);

        assertThat(cost.getIncomeTax()).isEqualTo(0.20f);
        assertThat(cost.getFixedCost()).isEqualTo(new Money(800, EURO));
    }
}

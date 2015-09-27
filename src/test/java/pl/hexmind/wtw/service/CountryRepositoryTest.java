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
import pl.hexmind.wtw.service.income.CountryRepository;

import javax.inject.Inject;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
@Transactional
public class CountryRepositoryTest {

    @Inject
    private CountryRepository countryRepository;

    @Test
    public void shouldFindAllCountries() {
        List<Country> countries = countryRepository.findAll();

        assertThat(countries).hasSize(3);
    }
}

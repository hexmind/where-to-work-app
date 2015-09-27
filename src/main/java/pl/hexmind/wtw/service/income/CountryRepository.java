package pl.hexmind.wtw.service.income;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Repository;
import pl.hexmind.wtw.model.Country;

import java.util.Comparator;
import java.util.Currency;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author ts
 */
@Repository
@ConfigurationProperties(prefix = "country")
public class CountryRepository {

    private static final Comparator<Country> BY_NAME = (a, b) -> a.getName().compareTo(b.getName());
    private final Map<String, Country> countriesMap;
    private final List<Country> countries;

    @Autowired
    public CountryRepository(CountryProperties properties) {
        this.countriesMap = properties.getCurrency().entrySet().stream()
            .map(c -> {
                String code = c.getKey();
                String name = properties.getName().get(code);
                Currency currency = Currency.getInstance(c.getValue());
                return new Country(name, code, currency);
            })
            .collect(Collectors.toMap(
                Country::getCode,
                Function.identity()
            ));
        this.countries = countriesMap.values().stream()
            .sorted(BY_NAME)
            .collect(Collectors.toList());
    }

    public Country findOne(String countryCode) {
        return countriesMap.get(countryCode);
    }

    public List<Country> findAll() {
        return Lists.newArrayList(countries);
    }
}

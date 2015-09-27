package pl.hexmind.wtw.service.income;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.hexmind.wtw.model.Country;
import pl.hexmind.wtw.model.CountryCost;
import pl.hexmind.wtw.model.Money;

import java.util.Currency;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author ts
 */
@Service
public class CountryCostService {

    private final Map<String, CountryCost> costs;

    @Autowired
    public CountryCostService(CountryRepository countryRepository, CountryProperties countryProperties) {
        this.costs = countryProperties.getFixed().entrySet().stream()
            .map(f -> {
                String code = f.getKey();
                Float tax = countryProperties.getTax().get(code);
                Currency currency = countryRepository.findOne(code).getCurrency();
                Money fixed = new Money(f.getValue(), currency);
                return new CountryCost(code, tax, fixed);
            })
            .collect(Collectors.toMap(
                CountryCost::getCountryCode,
                Function.identity()
            ));
    }

    public CountryCost findOneByCountry(Country country) {
        return findOneByCountryCode(country.getCode());
    }

    public CountryCost findOneByCountryCode(String countryCode) {
        return costs.get(countryCode);
    }
}

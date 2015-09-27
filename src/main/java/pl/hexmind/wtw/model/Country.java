package pl.hexmind.wtw.model;

import java.util.Currency;
import java.util.Objects;

/**
 * @author ts
 */
public class Country {

    private final String name;
    private String code;
    private final Currency currency;

    public Country(String name, String code, Currency currency) {
        this.name = name;
        this.code = code;
        this.currency = currency;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public Currency getCurrency() {
        return currency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country = (Country) o;
        return Objects.equals(name, country.name) &&
            Objects.equals(code, country.code) &&
            Objects.equals(currency, country.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, code, currency);
    }
}

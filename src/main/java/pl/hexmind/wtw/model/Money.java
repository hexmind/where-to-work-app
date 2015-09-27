package pl.hexmind.wtw.model;

import com.google.common.base.Preconditions;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;
import java.util.Objects;

/**
 * @author ts
 */
public final class Money {

    private static final int VALUE_SCALE = 2;
    public static final Money EMPTY = new Money(0, Currency.getInstance("PLN"));
    private final BigDecimal value;
    private final Currency currency;

    public Money(int value, Currency currency) {
        this(BigDecimal.valueOf(value), currency);
    }

    public Money(BigDecimal value, Currency currency) {
        Preconditions.checkNotNull(value, "no value");
        Preconditions.checkNotNull(currency, "no currency");
        this.value = value.setScale(VALUE_SCALE, RoundingMode.HALF_UP);
        this.currency = currency;
    }

    public BigDecimal getValue() {
        return value;
    }

    public Currency getCurrency() {
        return currency;
    }

    public Money multiply(float multiplicand) {
        BigDecimal newValue = value.multiply(BigDecimal.valueOf(multiplicand));
        return update(newValue);
    }

    public Money add(Money augend) {
        return update(value.add(augend.getValue()));
    }

    public Money substract(Money subtrahend) {
        return update(value.subtract(subtrahend.getValue()));
    }

    private Money update(BigDecimal newValue) {
        return new Money(newValue, currency);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return Objects.equals(value, money.value) &&
            Objects.equals(currency, money.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, currency);
    }

    @Override
    public String toString() {
        return value + " " + currency.getCurrencyCode();
    }
}

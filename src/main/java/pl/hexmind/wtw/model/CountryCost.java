package pl.hexmind.wtw.model;

import com.google.common.base.Preconditions;

import java.util.Objects;

/**
 * @author ts
 */
public class CountryCost {

    private final float incomeTax;

    private final Money fixedCost;

    private String countryCode;

    public CountryCost(String countryCode, float incomeTax, Money fixedCost) {
        this.countryCode = countryCode;
        Preconditions.checkArgument(incomeTax < 1f, "expected < 100%");
        this.incomeTax = incomeTax;
        this.fixedCost = fixedCost;
    }

    public float getIncomeTax() {
        return incomeTax;
    }

    public Money getFixedCost() {
        return fixedCost;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CountryCost that = (CountryCost) o;
        return Objects.equals(incomeTax, that.incomeTax) &&
            Objects.equals(fixedCost, that.fixedCost) &&
            Objects.equals(countryCode, that.countryCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(incomeTax, fixedCost, countryCode);
    }
}

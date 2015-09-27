package pl.hexmind.wtw.model;

import java.util.Objects;

/**
 * @author ts
 */
public final class MonthlyIncome {

    public static final MonthlyIncome EMPTY = new MonthlyIncome(Money.EMPTY, Money.EMPTY, new CountryCost("", 0, Money.EMPTY));

    private final Money netValue;
    private final Money taxValue;
    private final CountryCost cost;

    public MonthlyIncome(Money netValue, Money taxValue, CountryCost cost) {
        this.netValue = netValue;
        this.taxValue = taxValue;
        this.cost = cost;
    }

    public Money getNetValue() {
        return netValue;
    }

    public Money getTaxValue() {
        return taxValue;
    }

    public float getIncomeTax() {
        return cost.getIncomeTax();
    }

    public Money getFixedCost() {
        return cost.getFixedCost();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MonthlyIncome that = (MonthlyIncome) o;
        return Objects.equals(netValue, that.netValue) &&
            Objects.equals(taxValue, that.taxValue) &&
            Objects.equals(cost, that.cost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(netValue, taxValue, cost);
    }
}

package pl.hexmind.wtw.service.income;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;

/**
 * @author ts
 */
@Component
@ConfigurationProperties(prefix = "country")
public class CountryProperties {

    private HashMap<String, String> name;

    private HashMap<String, String> currency;

    private HashMap<String, BigDecimal> fixed;

    private HashMap<String, Float> tax;

    public HashMap<String, String> getName() {
        return name;
    }

    public void setName(HashMap<String, String> name) {
        this.name = name;
    }

    public HashMap<String, String> getCurrency() {
        return currency;
    }

    public void setCurrency(HashMap<String, String> currency) {
        this.currency = currency;
    }

    public HashMap<String, BigDecimal> getFixed() {
        return fixed;
    }

    public void setFixed(HashMap<String, BigDecimal> fixed) {
        this.fixed = fixed;
    }

    public HashMap<String, Float> getTax() {
        return tax;
    }

    public void setTax(HashMap<String, Float> tax) {
        this.tax = tax;
    }
}

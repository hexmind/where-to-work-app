package pl.hexmind.wtw.service.exchange;

import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.hexmind.wtw.model.Money;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Currency;

/**
 * @author ts
 */
@Service
public class CurrencyConverter {

    private ExchangeRateService exchangeRateService;

    @Autowired
    public CurrencyConverter(ExchangeRateService exchangeRateService) {
        this.exchangeRateService = exchangeRateService;
    }

    public Money convert(Currency expectedCur, Money value) {
        Preconditions.checkNotNull(value);
        return convert(expectedCur, new Money[]{value})[0];
    }

    public Money[] convert(Currency expectedCur, Money... values) {
        Preconditions.checkArgument(values != null && values.length > 0, "no values");
        Money first = values[0];
        String curIn = first.getCurrency().getCurrencyCode();
        String curOut = expectedCur.getCurrencyCode();

        if (!first.getCurrency().equals(expectedCur)) {
            BigDecimal rate = exchangeRateService.getRate(curIn, curOut);
            return Arrays.stream(values)
                .map(i -> {
                    BigDecimal value = rate.multiply(i.getValue());
                    return new Money(value, expectedCur);
                })
                .toArray(Money[]::new);
        } else {
            return values;
        }
    }

}

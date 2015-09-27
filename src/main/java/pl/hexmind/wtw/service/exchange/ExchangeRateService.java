package pl.hexmind.wtw.service.exchange;

import java.math.BigDecimal;

/**
 * @author ts
 */
public interface ExchangeRateService {
    BigDecimal getRate(String curIn, String curOut);
}

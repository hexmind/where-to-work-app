package pl.hexmind.wtw.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import pl.hexmind.wtw.model.Money;
import pl.hexmind.wtw.service.exchange.CurrencyConverter;
import pl.hexmind.wtw.service.exchange.ExchangeRateService;

import java.math.BigDecimal;
import java.util.Currency;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CurrencyConverterTest {

    private static final Currency EURO = Currency.getInstance("EUR");
    private static final Currency PLN = Currency.getInstance("PLN");
    private CurrencyConverter currencyConverter;

    @Before
    public void setUp(){
        ExchangeRateService exchangeRateServiceMock = mock(ExchangeRateService.class);
        when(exchangeRateServiceMock.getRate(anyString(), anyString())).thenReturn(BigDecimal.TEN);

        currencyConverter = new CurrencyConverter(exchangeRateServiceMock);
    }

    @Test
    public void shouldConvertFromEuroToPln() {
        Money input = new Money(BigDecimal.TEN, EURO);

        Money output = currencyConverter.convert(PLN, input);

        assertThat(output.getCurrency()).isEqualTo(PLN);
        assertThat(output.getValue().intValue()).isEqualTo(100);
    }

}

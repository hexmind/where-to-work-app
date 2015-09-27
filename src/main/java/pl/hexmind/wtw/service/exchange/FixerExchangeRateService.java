package pl.hexmind.wtw.service.exchange;

import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import pl.hexmind.wtw.model.FixerResponse;

import java.math.BigDecimal;
import java.net.URI;

/**
 * @author ts
 */
@Service
public class FixerExchangeRateService implements ExchangeRateService {

    private static final String URL = "http://api.fixer.io/latest";
    private final RestTemplate restTemplate;

    public FixerExchangeRateService() {
        this.restTemplate = new RestTemplate();
    }

    @Cacheable("exchange")
    @Override
    public BigDecimal getRate(String curIn, String curOut) {
        URI uri = toUrl(curIn, curOut);
        FixerResponse response = restTemplate.getForObject(uri, FixerResponse.class);

        BigDecimal rate = response.getRates().get(curOut);
        Preconditions.checkState(curIn.equals(response.getBase()), "invalid in " + curIn);
        Preconditions.checkState(rate != null, "invalid out " + curOut);
        return rate;
    }

    private URI toUrl(String curIn, String curOut) {
        return UriComponentsBuilder.fromHttpUrl(URL)
            .queryParam("base", curIn)
            .queryParam("symbols", curOut)
            .build().encode().toUri();
    }

}

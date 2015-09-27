package pl.hexmind.wtw.web.rest;

import com.codahale.metrics.annotation.Timed;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.hexmind.wtw.model.Country;
import pl.hexmind.wtw.service.income.CountryRepository;

import javax.inject.Inject;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CountryResource {

    @Inject
    private CountryRepository countryRepository;

    @RequestMapping(value = "/countries",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public List<Country> getAll() {
        return countryRepository.findAll();
    }
}

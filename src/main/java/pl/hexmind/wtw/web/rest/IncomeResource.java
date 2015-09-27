package pl.hexmind.wtw.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.google.common.base.Strings;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.hexmind.wtw.model.MonthlyIncome;
import pl.hexmind.wtw.service.income.IncomeCalculatorService;

import javax.inject.Inject;
import java.math.BigDecimal;

@RestController
@RequestMapping("/api")
public class IncomeResource {

    @Inject
    private IncomeCalculatorService incomeCalculatorService;

    @RequestMapping(value = "/income",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE)
    @Timed
    public MonthlyIncome getIncome(@RequestParam String countryCode,
                                   @RequestParam BigDecimal dailyRate) {
        if (!Strings.isNullOrEmpty(countryCode) && dailyRate != null) {
            return incomeCalculatorService.calculateMonthlyIncome(countryCode, dailyRate);
        } else {
            return MonthlyIncome.EMPTY;
        }
    }
}

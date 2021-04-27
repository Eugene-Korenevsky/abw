package com.example.abw.utils.currency;

import com.example.abw.model.currency.Currency;
import org.springframework.stereotype.Component;

import java.util.EnumSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class CurrencyUtil {
    public String getCurrencyString(Currency currentCurrency) {
        return EnumSet.allOf(Currency.class).stream().filter(x -> !x.equals(currentCurrency))
                .map(Enum::toString).collect(Collectors.joining(","));
    }
}

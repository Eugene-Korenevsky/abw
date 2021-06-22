package com.example.abw.utils;

import com.example.abw.model.currency.Currency;
import org.springframework.stereotype.Component;

import java.util.EnumSet;
import java.util.stream.Collectors;

@Component
public class CurrencyUtil {
    public static String getCurrencyString(Currency currentCurrency) {
        return EnumSet.allOf(Currency.class).stream().filter(x -> !x.equals(currentCurrency))
                .map(Enum::toString).collect(Collectors.joining(","));
    }
}

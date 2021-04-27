package com.example.abw.utils.currency;

import com.example.abw.model.currency.Currency;
import org.springframework.stereotype.Component;

import java.util.EnumSet;
import java.util.stream.Stream;

@Component
public class CurrencyUtil {
    public String getCurrencyString(Currency currentCurrency) {
        Stream<Currency> stream = EnumSet.allOf(Currency.class).stream();
        String url = stream.filter(x -> !x.equals(currentCurrency)).map(x -> x + ",")
                .reduce("", (res, str) -> res + str);
        int last = url.lastIndexOf(",");
        return url.substring(0, last);
    }
}

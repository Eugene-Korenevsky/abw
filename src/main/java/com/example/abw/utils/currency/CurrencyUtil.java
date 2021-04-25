package com.example.abw.utils.currency;

import com.example.abw.model.currency.Currency;
import org.springframework.stereotype.Component;

import java.util.EnumSet;

@Component
public class CurrencyUtil {
    public String getCurrencyString() {
        StringBuilder stringBuilder = new StringBuilder();
        EnumSet<Currency> currencies = EnumSet.allOf(Currency.class);
        for (Currency cur : currencies) {
            stringBuilder.append((cur)).append(",");
        }
        int last = stringBuilder.lastIndexOf(",");
        return stringBuilder.substring(0, last);
    }
}

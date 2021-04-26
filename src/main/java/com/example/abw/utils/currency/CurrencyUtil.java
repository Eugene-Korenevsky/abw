package com.example.abw.utils.currency;

import com.example.abw.model.currency.Currency;
import org.springframework.stereotype.Component;

import java.util.EnumSet;

@Component
public class CurrencyUtil {
    public String getCurrencyString(Currency currentCurrency) {
        StringBuilder stringBuilder = new StringBuilder();
        EnumSet<Currency> currencies = EnumSet.allOf(Currency.class);
        for (Currency currency : currencies) {
            if (!currency.equals(currentCurrency)) stringBuilder.append((currency)).append(",");
        }
        int last = stringBuilder.lastIndexOf(",");
        return stringBuilder.substring(0, last);
    }
}

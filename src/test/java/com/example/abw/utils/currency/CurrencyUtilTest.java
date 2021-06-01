package com.example.abw.utils.currency;


import com.example.abw.model.currency.Currency;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class CurrencyUtilTest {
    @Test
    public void currencyUtilTest(){
        assertEquals("USD,EUR,RUB,UAH",CurrencyUtil.getCurrencyString(Currency.BYN));
    }
}

package com.example.abw.mongo_migration;

import com.example.abw.entities.currency.CurrencyEx;
import com.example.abw.repositories.currency.CurrencyExRepository;
import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@ChangeLog
public class DatabaseChangeLog {
    @ChangeSet(order = "001", id = "00.00.01", author = "Eugene")
    public void seedDatabase(CurrencyExRepository currencyExRepository) {
        List<CurrencyEx> currencyExes = new ArrayList<>();
        currencyExes.add(createCurrencyEx("BYN", BigDecimal.valueOf(34.56)));
        currencyExes.add(createCurrencyEx("JPN", BigDecimal.valueOf(13.45)));
        currencyExes.add(createCurrencyEx("USD", BigDecimal.valueOf(23.33)));
        currencyExRepository.insert(currencyExes);
    }

    private CurrencyEx createCurrencyEx(String currencyName, BigDecimal value) {
        CurrencyEx currencyEx = new CurrencyEx();
        currencyEx.setCurrency(currencyName);
        currencyEx.setValue(value);
        return currencyEx;
    }
}

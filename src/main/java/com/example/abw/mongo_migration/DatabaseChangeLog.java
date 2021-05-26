package com.example.abw.mongo_migration;

import com.example.abw.entities.currency.CurrencyEx;
import com.example.abw.repositories.currency.CurrencyExRepository;
import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;

import java.util.ArrayList;
import java.util.List;

@ChangeLog
public class DatabaseChangeLog {
    @ChangeSet(order = "001", id = "seedDatabase", author = "Sai")
    public void seedDatabase(CurrencyExRepository currencyExRepository) {
        List<CurrencyEx> currencyExes = new ArrayList<>();
        currencyExes.add(createCurrencyEx("BYN", 33));
        currencyExes.add(createCurrencyEx("JPN", 56));
        currencyExes.add(createCurrencyEx("USD", 54));
        currencyExRepository.insert(currencyExes);

    }

    private CurrencyEx createCurrencyEx(String currencyName, double value) {
        CurrencyEx currencyEx = new CurrencyEx();
        currencyEx.setCurrency(currencyName);
        currencyEx.setValue(value);
        return currencyEx;
    }
}

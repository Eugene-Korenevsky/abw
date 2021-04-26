package com.example.abw.repositories.currency;

import com.example.abw.entities.currency.CurrencyExchange;
import com.example.abw.model.currency.Currency;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyExchangeRepository extends CrudRepository<CurrencyExchange, Long> {
    public CurrencyExchange findByCurrencyMainAndCurrencyTo(Currency main, Currency to);

}

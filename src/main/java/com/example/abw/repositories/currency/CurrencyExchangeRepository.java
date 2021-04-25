package com.example.abw.repositories.currency;

import com.example.abw.entities.currency.CurrencyEntity;
import com.example.abw.entities.currency.CurrencyExchange;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyExchangeRepository extends CrudRepository<CurrencyExchange, Long> {
    public CurrencyExchange findByCurrencyEntityMainAndCurrencyEntityTo(CurrencyEntity currency,
                                                                        CurrencyEntity currencyTo);
}

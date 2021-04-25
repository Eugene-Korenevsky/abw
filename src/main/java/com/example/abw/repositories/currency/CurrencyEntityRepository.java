package com.example.abw.repositories.currency;

import com.example.abw.entities.currency.CurrencyEntity;
import com.example.abw.model.currency.Currency;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyEntityRepository extends CrudRepository<CurrencyEntity, Long> {
    public CurrencyEntity findByCurrency(Currency currency);
}

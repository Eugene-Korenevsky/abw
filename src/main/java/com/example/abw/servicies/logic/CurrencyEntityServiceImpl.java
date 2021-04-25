package com.example.abw.servicies.logic;

import com.example.abw.entities.currency.CurrencyEntity;
import com.example.abw.model.currency.Currency;
import com.example.abw.repositories.currency.CurrencyEntityRepository;
import com.example.abw.servicies.CurrencyEntityService;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.EnumSet;
import java.util.List;

@Service
public class CurrencyEntityServiceImpl extends GenericServiceImpl<CurrencyEntity> implements CurrencyEntityService {
    private final CurrencyEntityRepository currencyEntityRepository;

    public CurrencyEntityServiceImpl(CurrencyEntityRepository currencyEntityRepository) {
        super(currencyEntityRepository, CurrencyEntity.class);
        this.currencyEntityRepository = currencyEntityRepository;
    }

    @Override
    public void updateAppCurrencies() {
        EnumSet<Currency> currencies = EnumSet.allOf(Currency.class);
        for (Currency currency : currencies) {
            CurrencyEntity currencyEntity = currencyEntityRepository.findByCurrency(currency);
            if (currencyEntity == null) {
                CurrencyEntity newCurrencyEntity = new CurrencyEntity();
                newCurrencyEntity.setCurrency(currency);
                currencyEntityRepository.save(newCurrencyEntity);
            }
        }
        List<CurrencyEntity> currencyEntities = findAll();
        for (CurrencyEntity currencyEntity : currencyEntities) {
            try {
                Currency currency = currencyEntity.getCurrency();
            } catch (IllegalArgumentException e) {
                currencyEntityRepository.deleteById(currencyEntity.getId());
            }
        }
    }
}

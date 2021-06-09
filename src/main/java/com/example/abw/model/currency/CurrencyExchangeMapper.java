package com.example.abw.model.currency;

import com.example.abw.entities.currency.CurrencyExchange;
import org.mapstruct.Mapper;

@Mapper
public interface CurrencyExchangeMapper {
    public CurrencyExchange currencyExchangeDTOToCurrencyExchange(CurrencyExchangeDTO currencyExchangeDTO);

    public CurrencyExchangeDTO currencyExchangeToCurrencyExchangeDTO(CurrencyExchange currencyExchange);
}

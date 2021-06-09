package com.example.abw.model.currency;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CurrencyExchangeDTO {

    private Currency currencyMain;
    private Currency currencyTo;
    private BigDecimal value;
}

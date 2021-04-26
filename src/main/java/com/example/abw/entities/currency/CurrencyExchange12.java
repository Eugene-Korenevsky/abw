package com.example.abw.entities.currency;

import com.example.abw.model.currency.Currency;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Entity
@EqualsAndHashCode
@Table(name = "currency_exchange12")
public class CurrencyExchange12 {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Currency currencyMain;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Currency currencyTo;

    private BigDecimal value;
}

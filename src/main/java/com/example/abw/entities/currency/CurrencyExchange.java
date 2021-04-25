package com.example.abw.entities.currency;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@EqualsAndHashCode
@Table(name = "currency_exchange")
public class CurrencyExchange {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "first_currency_id", referencedColumnName = "id")
    private CurrencyEntity currencyEntityMain;

    @ManyToOne
    @JoinColumn(name = "second_currency_id", referencedColumnName = "id")
    private CurrencyEntity currencyEntityTo;

    private double value;
}

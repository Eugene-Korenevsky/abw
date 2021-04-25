package com.example.abw.entities.currency;

import com.example.abw.model.currency.Currency;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@Entity
@Table(name = "currency")
@EqualsAndHashCode(exclude = {"currencyExchanges"})
public class CurrencyEntity {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    private Currency currency;

    @ToString.Exclude
    @OneToMany(mappedBy = "currencyEntityMain", fetch = FetchType.EAGER)
    public Set<CurrencyExchange> currencyExchanges;


}

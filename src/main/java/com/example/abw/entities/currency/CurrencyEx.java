package com.example.abw.entities.currency;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Document(collection = "currency_ex")
public class CurrencyEx {
    @Id
    private String id;

    @NotNull
    @Field("currency")
    private String currency;

    @NotNull
    @Field("value")
    private BigDecimal value;
}

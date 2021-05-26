package com.example.abw.entities.currency;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Data
@Document(collection = "currency_ex1")
public class CurrencyEx {
    @Id
    private String id;

    @NotNull
    @Field("currency")
    private String currency;

    @NotNull
    @Field("value")
    private double value;
}

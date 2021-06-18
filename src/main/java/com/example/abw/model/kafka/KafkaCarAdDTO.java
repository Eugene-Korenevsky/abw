package com.example.abw.model.kafka;

import com.example.abw.model.currency.Currency;
import com.example.abw.model.user.UserDTO;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
public class KafkaCarAdDTO {
    private long id;
    private boolean isCorrect;
    private Timestamp publicationDate;
    private BigDecimal price;
    private Currency priceCurrency;
    private String carBrandName;
    private String carBrand;
    private UserDTO userDTO;
    private String errorMessage;
}

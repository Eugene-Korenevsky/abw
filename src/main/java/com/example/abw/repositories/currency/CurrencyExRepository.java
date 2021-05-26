package com.example.abw.repositories.currency;

import com.example.abw.entities.currency.CurrencyEx;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyExRepository extends MongoRepository<CurrencyEx,String> {
}

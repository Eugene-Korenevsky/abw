package com.example.abw.kafka;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:kafka.properties")
public class KafkaProducers {
    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void send(Object o, String topic) {
        ProducerRecord<String, Object> record = new ProducerRecord<>(topic, o);
        kafkaTemplate.send(record);
    }
}

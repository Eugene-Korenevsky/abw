package com.example.abw;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties
@PropertySource("classpath:global.properties")
public class AppProperties {
    private int pageSize;
    private String defaultFilter;
    private String defaultRole;
    private long activeTime;
    private String carAdvertisementMessage;
    private String advertisementText;
    private String carAdvertisementSubject;
    private String cryptoCompareUrl;
    private String cryptoCompareKey;
    private String cryptoCompareTo;
}

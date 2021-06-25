package com.ruihai.helper.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Value("${stock.base_url}")
    private String stockBaseUrl;

    @Value("${stock.token}")
    private String stockToken;

    @Bean
    public String getStockBaseUrl(){
        return stockBaseUrl;
    }

    @Bean
    public String getStockToken(){
        return stockToken;
    }



}

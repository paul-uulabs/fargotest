package com.fargotest.core.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import com.fargotest.core.errorhandling.FeignCustomErrorDecoder;

import feign.codec.ErrorDecoder;

@EnableFeignClients(basePackages = {"com.fargotest.core.client"})
public class FeignClientConfiguration {
    
    @Bean
    public ErrorDecoder errorDecoder() {
        return new FeignCustomErrorDecoder();
    }
}

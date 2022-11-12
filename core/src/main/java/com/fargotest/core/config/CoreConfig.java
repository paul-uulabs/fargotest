package com.fargotest.core.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableReactiveMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import reactivefeign.spring.config.EnableReactiveFeignClients;

@Configuration
@ComponentScan("com.fargotest.core")
@EnableReactiveMongoRepositories(basePackages = {"com.fargotest.core.repository"} )
@EnableReactiveMongoAuditing
@EnableReactiveFeignClients(basePackages = {"com.fargotest.core.client"})
public class CoreConfig {

}

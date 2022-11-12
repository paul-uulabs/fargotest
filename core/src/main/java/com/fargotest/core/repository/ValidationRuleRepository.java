package com.fargotest.core.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import com.fargotest.core.entity.ValidationRule;
import reactor.core.publisher.Flux;

public interface ValidationRuleRepository  extends ReactiveMongoRepository<ValidationRule, String> {
    Flux<ValidationRule> findAllByApplicationType(String type);
}

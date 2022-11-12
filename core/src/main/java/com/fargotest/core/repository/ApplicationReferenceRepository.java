package com.fargotest.core.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import com.fargotest.core.entity.ApplicationReference;
import reactor.core.publisher.Flux;

public interface ApplicationReferenceRepository  extends ReactiveMongoRepository<ApplicationReference, String> {  
    Flux<ApplicationReference> findAllByApplicationId(String id);
}

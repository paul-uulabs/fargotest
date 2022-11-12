package com.fargotest.core.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import com.fargotest.core.entity.Validation;
import reactor.core.publisher.Flux;

public interface ValidationRepository  extends ReactiveMongoRepository<Validation, String> {  
    Flux<Validation> findAllByApplicationReferenceId(ObjectId id);
}

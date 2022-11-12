package com.fargotest.core.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import com.fargotest.core.entity.ApplicationMetaData;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ApplicationMetaDataRepository  extends ReactiveMongoRepository<ApplicationMetaData, String> {  
    Flux<ApplicationMetaData> findAllByApplicationReferenceId(ObjectId id);
    Mono<ApplicationMetaData> findByValidationId(ObjectId id);
}
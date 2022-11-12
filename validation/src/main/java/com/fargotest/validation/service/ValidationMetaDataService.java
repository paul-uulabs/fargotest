package com.fargotest.validation.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import com.fargotest.core.client.GitHubClient;
import com.fargotest.core.dto.ApplicationRequest;
import com.fargotest.core.entity.ApplicationReference;
import com.fargotest.core.entity.ApplicationMetaData;
import com.fargotest.core.entity.Validation;
import com.fargotest.core.repository.ApplicationMetaDataRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class ValidationMetaDataService {
    
    private final GitHubClient gitHubClient;
    private final ApplicationMetaDataRepository applicationMetaDataRepository;

    public Mono<ApplicationMetaData> getMetaDataForApplicationValidation (Validation validation, ApplicationReference application) {
        return applicationMetaDataRepository.findByValidationId(validation.getId())
                .switchIfEmpty(cacheMetaDataFromCommits(validation, application));
    }

    private Mono<ApplicationMetaData> cacheMetaDataFromCommits(Validation validation, ApplicationReference application) {
        return gitHubClient.getCommits(new ModelMapper().map(application, ApplicationRequest.class))
                .flatMap(commits -> {
                    log.info("Caching metadata for validation {}", validation.getId());
                    return applicationMetaDataRepository.save(ApplicationMetaData.builder()
                                                                .applicationReferenceId(application.getId())
                                                                .metaData(commits)
                                                                .validationId(validation.getId())
                                                                .build());
                });
    }
}

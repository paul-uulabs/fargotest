package com.fargotest.validation.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import com.fargotest.core.dto.ApplicationRequest;
import com.fargotest.core.dto.RuleResult;
import com.fargotest.core.dto.ValidationResult;
import com.fargotest.core.entity.ApplicationReference;
import com.fargotest.core.entity.Validation;
import com.fargotest.core.exception.BadRequestException;
import com.fargotest.core.repository.ApplicationReferenceRepository;
import com.fargotest.core.repository.ValidationRepository;
import com.fargotest.core.repository.ValidationRuleRepository;
import com.fargotest.validation.service.rules.RuleInterface;

@Slf4j
@Service
@RequiredArgsConstructor
public class ValidationService {
    
    private final ApplicationContext applicationContext;
    private final ApplicationReferenceRepository applicationReferenceRepository;
    private final ValidationRuleRepository validationRuleRepository;
    private final ValidationRepository validationRepository;

    public Mono<ValidationResult> process(ApplicationRequest request) {
        log.info("Processing validation for application {}",request);
        return updateOrCreateApplicationReference(request)
                .flatMap(this::validateApplication);
    }

    public Mono<ValidationResult> validateApplication(ApplicationReference application) {
        return validationRepository.save(Validation.builder().applicationReferenceId(application.getId()).build())
                        .flatMap(validation -> processValidationRulesForApplication (validation, application));
    }

    public Mono<ValidationResult> processValidationRulesForApplication (Validation validation, ApplicationReference application) {
        return validationRuleRepository.findAllByApplicationType(application.getApplicationType())
                .flatMap(validationRule -> {
                    RuleInterface rule = loadRuleForClass (validationRule.getClassName());
                    if (rule == null) {
                        return Mono.error(new Exception("Rule for " + validationRule.getClassName() + " not found during validation.", new Exception()));
                    }
                    return rule.process(validation, application, validationRule);
                })
                .collectList()
                .flatMap(results ->  Mono.just(ValidationResult.builder()
                                        .validationId(validation.getId().toString())
                                        .applicationId(application.getApplicationId())
                                        .applicationReferenceId(application.getId().toString())
                                        .validationResults(results)
                                        .processedAt(LocalDateTime.now())
                                        .validationState(!results.stream()
                                                            .map(RuleResult::getRuleState)
                                                            .filter(Objects::nonNull)
                                                            .collect(Collectors.toList())
                                                            .contains(Boolean.FALSE))
                                        .build()));
    }

    private Mono<ApplicationReference> updateOrCreateApplicationReference(ApplicationRequest request) {
        return applicationReferenceRepository.findAllByApplicationId(request.getApplicationId())
                    .collectList()
                    .flatMap(result -> {
                        if (result.isEmpty()) {
                            log.info("No application reference exists, creating for {}", request.getApplicationId());
                            return applicationReferenceRepository.save(new ModelMapper().map(request, ApplicationReference.class));
                        }
                        if (result.size() > 1) {
                            return Mono.error(new BadRequestException("Multiple records exist for this applicationId, query for applicationReferenceId instead.", new Exception()));
                        }
                        ApplicationReference existing = result.get(0);
                        new ModelMapper().map(request, existing);
                        return applicationReferenceRepository.save(existing);
                    });
    }

    private RuleInterface loadRuleForClass (String className) {
        try {
            return (RuleInterface) applicationContext.getBean(Class.forName(className));
        } catch ( Exception e) {
            log.error("Failed to load rule class bean while validating.", e.getMessage());
        }
        return null;
    }

    
}

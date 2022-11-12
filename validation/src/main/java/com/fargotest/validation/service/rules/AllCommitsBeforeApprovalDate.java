package com.fargotest.validation.service.rules;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import com.fargotest.core.dto.RuleResult;
import com.fargotest.core.entity.ApplicationReference;
import com.fargotest.core.entity.Validation;
import com.fargotest.core.entity.ValidationRule;
import com.fargotest.validation.service.ValidationMetaDataService;

@Slf4j
@Component
@RequiredArgsConstructor
public class AllCommitsBeforeApprovalDate implements RuleInterface {
    
    private final ValidationMetaDataService validationMetaDataService;

    @Override
    public Mono<RuleResult> process(Validation validation, ApplicationReference application,  ValidationRule validationRule) {
        log.info("Processing rule AllCommitsBeforeApprovalDate for application {}", application.getApplicationId());
        return validationMetaDataService.getMetaDataForApplicationValidation(validation, application)
                    .flatMap( metaData -> {
                        Set<String> invalidCommits = metaData.getMetaData().stream()
                                                            .filter(value -> value.getCommit()
                                                                                    .getCommitter()
                                                                                    .getDate()
                                                                                    .isAfter(application.getApprovalDate()))
                                                            .map( value -> value.getCommit().getCommitter().getDate().toString())
                                                            .collect(Collectors.toSet());
                        Boolean isValid = invalidCommits.isEmpty();
                        String failureMessage = (isValid.equals(Boolean.TRUE)) ? "" : "Invalid dates : " + invalidCommits.toString();
                        return Mono.just(RuleResult.builder()
                                            .ruleState(isValid)
                                            .ruleTitle(validationRule.getRuleTitle())
                                            .ruleDescription(validationRule.getRuleDescription())
                                            .failureReason(failureMessage)
                                            .build());
                    });
    }
}

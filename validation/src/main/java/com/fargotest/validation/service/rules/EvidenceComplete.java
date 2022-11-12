package com.fargotest.validation.service.rules;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import org.springframework.stereotype.Component;
import com.fargotest.core.dto.RuleResult;
import com.fargotest.core.entity.ApplicationReference;
import com.fargotest.core.entity.Validation;
import com.fargotest.core.entity.ValidationRule;

@Slf4j
@Component
@RequiredArgsConstructor
public class EvidenceComplete implements RuleInterface {
    
    @Override
    public Mono<RuleResult> process(Validation validation, ApplicationReference application,  ValidationRule validationRule) {
        log.info("Processing rule EvidenceComplete for application {}", application.getApplicationId());
        return Mono.just(RuleResult.builder()
                            .ruleState(Boolean.FALSE)
                            .ruleTitle(validationRule.getRuleTitle())
                            .ruleDescription(validationRule.getRuleDescription())
                            .failureReason("Not implemented")
                            .build());
    }
}

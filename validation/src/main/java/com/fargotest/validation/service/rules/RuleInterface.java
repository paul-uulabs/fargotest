package com.fargotest.validation.service.rules;

import com.fargotest.core.dto.RuleResult;
import com.fargotest.core.entity.ApplicationReference;
import com.fargotest.core.entity.Validation;
import com.fargotest.core.entity.ValidationRule;

import reactor.core.publisher.Mono;

public interface RuleInterface {
    Mono<RuleResult> process(Validation validation, ApplicationReference application, ValidationRule validationRule);
}

package com.fargotest.validation.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import org.springframework.stereotype.Service;
import com.fargotest.core.constant.ApplicationType;
import com.fargotest.core.entity.ValidationRule;
import com.fargotest.core.repository.ValidationRuleRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class RuleInitService {

    public static final String PACKAGE_PATH = "com.fargotest.validation.service.rules";
    private final ValidationRuleRepository validationRuleRepository;

    public Mono<Boolean> resetRules() {
        
        log.warn("Rule reset called");

        return validationRuleRepository.deleteAll()
            .then( validationRuleRepository.save(ValidationRule.builder()
                                                .className(PACKAGE_PATH + ".AllCommitsBeforeApprovalDate")
                                                .ruleTitle("All commits before approval date.")
                                                .ruleDescription("Release must not include any commits dated beyond the approved release date.")
                                                .applicationType(ApplicationType.GITHUB)
                                                .build()))
            .then( validationRuleRepository.save(ValidationRule.builder()
                                                .className(PACKAGE_PATH + ".AllCommitsByAuthorizedDevelopers")
                                                .ruleTitle("All commits made by authorized developers.")
                                                .ruleDescription("Release must not include any commits from unauthorized developers.")
                                                .applicationType(ApplicationType.GITHUB)
                                                .build()))
            .then( validationRuleRepository.save(ValidationRule.builder()
                                                .className(PACKAGE_PATH + ".CodeScanPass")
                                                .ruleTitle("Repository must successfully pass a code scan.")
                                                .ruleDescription("Release must meet the requirements defined in the code scan.")
                                                .applicationType(ApplicationType.GITHUB)
                                                .build()))
            .then(validationRuleRepository.save(ValidationRule.builder()
                                                .className(PACKAGE_PATH + ".EvidenceComplete")
                                                .ruleTitle("Evidence of testing approval and regulatory compliance must be provided.")
                                                .ruleDescription("Successful testing approval and regulatory compliance must be accompanied with appropriate documentary evidence.")
                                                .applicationType(ApplicationType.GITHUB)
                                                .build()))
            .then(validationRuleRepository.save(ValidationRule.builder()
                                                .className(PACKAGE_PATH + ".RegulatoryCompliancePass")
                                                .ruleTitle("Release must meet regulatory compliance standards.")
                                                .ruleDescription("A new release must comply with regulatory standards.")
                                                .applicationType(ApplicationType.GITHUB)
                                                .build()))
            .then(validationRuleRepository.save(ValidationRule.builder()
                                                .className(PACKAGE_PATH + ".TestingApprovalsComplete")
                                                .ruleTitle("Release must be tested and approved.")
                                                .ruleDescription("A new release be fully tested by dev teams and product owner.")
                                                .applicationType(ApplicationType.GITHUB)
                                                .build()))
            .then(Mono.just(Boolean.TRUE));
    }
    
}


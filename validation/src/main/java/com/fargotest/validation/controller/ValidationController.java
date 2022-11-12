package com.fargotest.validation.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fargotest.core.dto.ApplicationRequest;
import com.fargotest.core.dto.ValidationResult;
import com.fargotest.validation.service.RuleInitService;
import com.fargotest.validation.service.ValidationService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value="/api")
public class ValidationController {
    
    private final ValidationService validationService;
    private final RuleInitService ruleInitService;

    @PostMapping("/validation/validate")
    public Mono<ResponseEntity<ValidationResult>> validate(@Valid @RequestBody ApplicationRequest request ) {
        log.info("Request to validate Application {}",request.getApplicationId());
        return validationService.process(request)
                    .flatMap( response -> Mono.just(ResponseEntity.ok().body(response)));
    }

    @GetMapping("/validation/rules/init")
    public Mono<ResponseEntity<Boolean>> initRules() {
        log.info("Deleting and recreating validation rule set.");
        return ruleInitService.resetRules()
                    .flatMap( result -> Mono.just(ResponseEntity.ok().body(result)));
    }



}

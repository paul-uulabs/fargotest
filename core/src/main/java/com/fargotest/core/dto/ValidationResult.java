package com.fargotest.core.dto;

import java.time.LocalDateTime;
import java.util.List;
import org.bson.types.ObjectId;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ValidationResult {
    private String validationId;
    private String applicationReferenceId;
    private String applicationId;
    private Boolean validationState;
    private List<RuleResult> validationResults;
    @JsonSerialize(using = ToStringSerializer.class)
    private LocalDateTime processedAt;
}

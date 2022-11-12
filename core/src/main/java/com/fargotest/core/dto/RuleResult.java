package com.fargotest.core.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RuleResult {
    private Boolean ruleState;
    private String ruleTitle;
    private String ruleDescription;
    private String failureReason;
}

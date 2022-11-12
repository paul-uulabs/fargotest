package com.fargotest.core.dto;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApplicationRequest {
    
    @NotBlank(message = "Application id must be provided to evaluate release.")
    private String applicationId;

    @NotBlank(message = "Please specify application repository type.")
    private String applicationType;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime approvalDate;

    @NotEmpty(message = "Please specify usernames of developers authorized for this application release.")
    private List<String> authorizedDevelopers;
    
}

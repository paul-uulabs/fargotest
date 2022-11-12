package com.fargotest.core.entity;

import java.time.LocalDateTime;
import javax.persistence.Id;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "rule")
public class ValidationRule {
    
    @Id
    private ObjectId id;

    private String className;
    private String ruleTitle;
    private String ruleDescription;
    private String applicationType;

    @CreatedDate
    private LocalDateTime created;
    private String createdBy;

    @LastModifiedDate
    private LocalDateTime modified;
    private String modifiedBy;

}

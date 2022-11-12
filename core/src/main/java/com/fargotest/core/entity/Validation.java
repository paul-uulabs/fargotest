package com.fargotest.core.entity;

import java.time.LocalDateTime;
import java.util.List;

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
@Document(collection = "validation")
public class Validation {
    
    @Id
    private ObjectId id;
    private ObjectId applicationReferenceId;
   
    private String validationState;
    private List<String> validationResults;

    @CreatedDate
    private LocalDateTime created;
    private String createdBy;

    @LastModifiedDate
    private LocalDateTime modified;
    private String modifiedBy;
    
}

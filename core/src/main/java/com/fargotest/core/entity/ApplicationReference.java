package com.fargotest.core.entity;

import java.time.LocalDateTime;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "application")
public class ApplicationReference {

    @Id
    private ObjectId id;
    private String applicationId;
    private String applicationType;
    private LocalDateTime approvalDate;
    private List<String> authorizedDevelopers;

    @CreatedDate
    private LocalDateTime created;
    private String createdBy;

    @LastModifiedDate
    private LocalDateTime modified;
    private String modifiedBy;

    
}

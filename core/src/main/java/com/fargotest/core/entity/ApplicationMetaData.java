package com.fargotest.core.entity;

import java.time.LocalDateTime;
import java.util.List;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fargotest.core.dto.metadata.Commit;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "metadata")
public class ApplicationMetaData {
    
    @Id
    private ObjectId id;
    private ObjectId applicationReferenceId;
    private ObjectId validationId;
    private List<Commit> metaData;

    @CreatedDate
    private LocalDateTime created;
    private String createdBy;

    @LastModifiedDate
    private LocalDateTime modified;
    private String modifiedBy;
}

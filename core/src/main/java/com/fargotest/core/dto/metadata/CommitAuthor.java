package com.fargotest.core.dto.metadata;

import java.time.LocalDateTime;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommitAuthor {
    String name;
    String email;

    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    LocalDateTime date;
}

package com.fargotest.core.dto.metadata;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommitDetails {
    private String message;
    private String url;
    private CommitAuthor author;
    private CommitAuthor committer;
}

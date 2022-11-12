package com.fargotest.core.dto.metadata;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Commit {
    private String sha;
    private String nodeId;
    private String url;
    private CommitDetails commit;
}

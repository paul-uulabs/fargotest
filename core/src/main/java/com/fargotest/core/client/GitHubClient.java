package com.fargotest.core.client;

import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Mono;

import java.util.List;
import javax.validation.Valid;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.fargotest.core.dto.ApplicationRequest;
import com.fargotest.core.dto.metadata.Commit;

@Component
@ReactiveFeignClient(url="${microservices.urls.github}", value="github")
public interface GitHubClient {
    
    @PostMapping("/api/github/repo/commits")
    public Mono<List<Commit>> getCommits(@Valid @RequestBody ApplicationRequest request );
}

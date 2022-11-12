package com.fargotest.github.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fargotest.core.dto.ApplicationRequest;
import com.fargotest.core.dto.metadata.Commit;
import com.fargotest.github.service.GitHubService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value="/api")
public class GitHubController {

    private final GitHubService gitHubService;
    
    @PostMapping("/github/repo/commits")
    public Mono<ResponseEntity<List<Commit>>> getCommits(@Valid @RequestBody ApplicationRequest request ) {
        log.info("Request to process GitHub repository commits for {}",request.getApplicationId());
        return gitHubService.getCommits(request)
                    .flatMap( response -> Mono.just(ResponseEntity.ok().body(response)));
    }
    
}

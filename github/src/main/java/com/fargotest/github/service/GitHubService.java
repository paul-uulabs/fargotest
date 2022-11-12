package com.fargotest.github.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import com.fargotest.core.dto.ApplicationRequest;
import com.fargotest.core.dto.metadata.Commit;

@Slf4j
@Service
@RequiredArgsConstructor
public class GitHubService {

    @Value("${github.apiKey}")
    private String apiKey;

    @Value("${github.apiSecret}")
    private String secretKey;

    @Value("${github.apiUrl}")
    private String apiUrl;

    public Mono<List<Commit>> getCommits(ApplicationRequest request) {
        String endpoint = apiUrl + "repos" + request.getApplicationId() + "/commits";
        log.info("Requesting commits from {}", endpoint);
        WebClient webClient = WebClient.builder().build();
        return webClient.get()
                .uri(endpoint)
                .retrieve()
                .bodyToFlux(Commit.class)
                .collectList();
    }

}

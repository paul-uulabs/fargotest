package com.fargotest.filter;

import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static  org.springframework.cloud.gateway.support.ServerWebExchangeUtils.*;

import java.net.URI;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Configuration
public class GlobalRequestFilter {
    
    @Bean
    public GlobalFilter globalFilter() {
        return (exchange, chain) -> {
            long startTime = System.nanoTime();

            return chain.filter(exchange).then(Mono.fromRunnable(() -> {

                long executionTimeInMs = getExecutionTimeInMs(startTime);
                ServerHttpRequest request = exchange.getRequest();
                MultiValueMap<String, String> queryParams = request.getQueryParams();
                HttpMethod httpMethod = request.getMethod();
                String originalUri = getOriginalUri(exchange);
                Route route = exchange.getAttribute(GATEWAY_ROUTE_ATTR);
                URI routeUri = exchange.getAttribute(GATEWAY_REQUEST_URL_ATTR);

                log.info("routeId: {} | HttpMethod: {} | routeUrl: {}" 
                            + "| executionTime: {}mss | originalBeforeRewrite: {} | queryParams: {}",
                            route.getId(), httpMethod, routeUri, executionTimeInMs, originalUri, queryParams );

            }));
        };
    }

    private long getExecutionTimeInMs(long startTime) {
        long stopTime = System.nanoTime();
        long executionTime = stopTime - startTime;
        return TimeUnit.NANOSECONDS.toMillis(executionTime);
    }

    private String getOriginalUri(ServerWebExchange exchange) {
        Set<URI> uris = exchange.getAttributeOrDefault(GATEWAY_ORIGINAL_REQUEST_URL_ATTR, Collections.emptySet());
        return (uris.isEmpty()) ? "Unknown" : uris.iterator().next().toString();
    }


}

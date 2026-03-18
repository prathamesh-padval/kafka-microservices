package com.example.gateway.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Configuration
public class CorrelationIdFilter {

    private static final Logger logger = LoggerFactory.getLogger(CorrelationIdFilter.class);
    private static final String CORRELATION_ID = "X-Correlation-ID";

    @Bean
    public GlobalFilter correlationIdFilter() {
        return (exchange, chain) -> {
            String existingCorrelationId = exchange.getRequest().getHeaders().getFirst(CORRELATION_ID);
            final String correlationId = existingCorrelationId != null ? existingCorrelationId : UUID.randomUUID().toString();

            if (existingCorrelationId == null) {
                logger.info("Generated new correlation ID: {}", correlationId);
            } else {
                logger.info("Using existing correlation ID: {}", correlationId);
            }

            exchange.getRequest().mutate().header(CORRELATION_ID, correlationId).build();

            return chain.filter(exchange.mutate().request(exchange.getRequest()).build())
                    .then(Mono.fromRunnable(() -> {
                        exchange.getResponse().getHeaders().add(CORRELATION_ID, correlationId);
                    }));
        };
    }
}

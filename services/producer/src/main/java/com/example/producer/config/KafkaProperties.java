package com.example.producer.config;

import java.util.List;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "spring.kafka")
@Data
public class KafkaProperties {

  private List<String> bootstrapServers;

  private String acks;

  private int retries;

  private int batchSize;

  private int lingerMs;

  private String compressionType;

  private String topic;

}

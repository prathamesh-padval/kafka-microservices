package com.example.producer.service;

import java.nio.charset.StandardCharsets;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProducerService {

  @Value("${spring.kafka.topic}")
  private String topic;

  private final KafkaTemplate<String, Object> kafkaTemplate;

  public ProducerService(KafkaTemplate<String, Object> kafkaTemplate) {
    this.kafkaTemplate = kafkaTemplate;
  }


  public String processAndPublish() {
    byte[] payload = "Event produced".getBytes(StandardCharsets.UTF_8);
    kafkaTemplate.send(topic, String.valueOf(UUID.randomUUID()), payload);

    return "OK";
  }
}

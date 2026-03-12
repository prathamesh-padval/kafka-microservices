package com.example.producer.config;

import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.ByteArraySerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@Configuration
public class ProducerConfiguration {

  private final KafkaProperties properties;

  public ProducerConfiguration(KafkaProperties properties){
    this.properties = properties;
  }


  @Bean
  public ProducerFactory<String, Object> producerFactory() {
    Map<String,Object> config = new HashMap<>();

    config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, String.join(",",properties.getBootstrapServers()));
    config.put(ProducerConfig.ACKS_CONFIG, properties.getAcks());
    config.put(ProducerConfig.BATCH_SIZE_CONFIG, properties.getBatchSize());
    config.put(ProducerConfig.COMPRESSION_TYPE_CONFIG, properties.getCompressionType());
    config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, ByteArraySerializer.class);
    config.put(ProducerConfig.RETRIES_CONFIG, properties.getRetries());

    return new DefaultKafkaProducerFactory<>(config);
  }

  @Bean
  public KafkaTemplate<String,Object> kafkaTemplate(){
    return new KafkaTemplate<>(producerFactory());
  }
}

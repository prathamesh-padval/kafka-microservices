package com.example.consumer.listener;

import org.apache.kafka.clients.consumer.internals.Acknowledgements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class TestConsumer {


  @KafkaListener(topics="${spring.kafka.topic}", groupId = "${spring.kafka.consumer.group-id}")
  public void consume(String message){
    System.out.println("Received Message:: "+message);


  }

}

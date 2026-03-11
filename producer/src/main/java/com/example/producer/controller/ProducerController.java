package com.example.producer.controller;

import com.example.producer.service.ProducerService;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/produce")
public class ProducerController {

  private final ProducerService service;

  public ProducerController(ProducerService service){
    this.service = service;
  }

  @PostMapping
  public ResponseEntity<?> publish(@RequestBody Map<String,String> input){
    String result =  service.processAndPublish();
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

}

package com.example.demo11;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class Consumer {

  private final KafkaTemplate<String, String> template;

  @KafkaListener(
    id = "spring-listener",
    topics = "words"
  )
  public void consumeWords(final ConsumerRecord<String, String> record) {
    log.info("Received on topic {}, offset {}, partition {}: {}={}",
      record.topic(),
      record.offset(),
      record.partition(),
      record.key(), record.value());

    template.send("spring-words", record.key(), "spring-" + record.value());

  }

}

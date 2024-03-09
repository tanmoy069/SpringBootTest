package com.tanmoy.springboottest.service;

import com.google.gson.Gson;
import com.tanmoy.springboottest.client.KafkaAdminClient;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class KafkaProducerService {

    private static final Logger logger = LoggerFactory.getLogger(KafkaProducerService.class);
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final KafkaAdminClient kafkaAdminClient;

    public void sendMessage(Object object, String topic) {
        if (kafkaAdminClient.verifyConnection()) {
            logger.info(String.format("$$$$ => Producing message: %s", object));
            String message = new Gson().toJson(object);
            CompletableFuture<SendResult<String, String>> future = this.kafkaTemplate.send(topic, "user", message);
            future.whenComplete((result, ex) -> {
                if (ex == null) {
                    logger.info("Sent message=[" + message +
                            "] with offset=[" + result.getRecordMetadata().offset() + "]");
                } else {
                    logger.info("Unable to send message=[" +
                            message + "] due to : " + ex.getMessage());
                }
            });
        } else {
            logger.info(String.format("$$$$ => Failed to produce message due to connection issue: %s", object));
        }
    }

}

package com.tanmoy.springboottest.service;

import com.tanmoy.springboottest.entity.UserEventLogs;
import com.tanmoy.springboottest.repository.UserEventLogsRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaConsumerService {

    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumerService.class);
    private final UserEventLogsRepository repository;

    @KafkaListener(topics = "user-events", groupId = "user")
    public void listenUserEvents(String message) {
        logger.info("Received Message in user events topic: " + message);
        UserEventLogs logs = new UserEventLogs();
        logs.setLog(message);
        repository.save(logs);
        logger.info("Received Message from user events topic has been saved");
    }
}

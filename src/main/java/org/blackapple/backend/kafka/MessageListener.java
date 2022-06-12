package org.blackapple.backend.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;

@Service
@Slf4j
public class MessageListener {

    private final CountDownLatch latch = new CountDownLatch(2);

    @KafkaListener(topics = "${message.topic.name}", groupId = "msg", containerFactory = "msgKafkaListenerContainerFactory")
    public void listenMessage(String message) {
        log.info("Received message: {}", message);
        latch.countDown();
    }
}

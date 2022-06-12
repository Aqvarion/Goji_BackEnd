package org.blackapple.backend.user.message.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.blackapple.backend.user.message.model.Message;
import org.blackapple.backend.user.message.repositories.MessageRepository;
import org.blackapple.backend.user.message.service.MessageDBWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;

@Service
@Slf4j
public class MessageDBWriterImpl implements MessageDBWriter {

    @Autowired
    MessageRepository messageRepository;

    private final CountDownLatch latch = new CountDownLatch(2);

    @Override
    @KafkaListener(topics = "${message.topic.name}", groupId = "msg", containerFactory = "msgKafkaListenerContainerFactory")
    public void writeMessageToDB(Message message) {
            log.info("Received message for DB: {}", message);
            messageRepository.save(message);
            latch.countDown();
    }
}

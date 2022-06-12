package org.blackapple.backend.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.blackapple.backend.user.message.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Slf4j
@Service
public class MessageProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value(value = "${message.topic.name}")
    private String topicName;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public void sendMessage(Message message) {

        log.info("Send message to kafka: {}", message.toString());
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate
                .send(topicName, writeValueAsString(message));

        future.addCallback(new ListenableFutureCallback<>() {
            @Override
            public void onFailure(Throwable ex) {
                log.warn("Unable to send message=[{}] due to : {}", message, ex.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, String> result) {
                log.info("Sent message=[{}] with offset=[ {}", message, result.getRecordMetadata());
            }
        });
    }

    private String writeValueAsString(Message message) {
        try {
            return objectMapper.writeValueAsString(message);
        } catch (JsonProcessingException e){
            throw new RuntimeException("Writing value to JSON failed: " + message.toString());
        }
    }
}

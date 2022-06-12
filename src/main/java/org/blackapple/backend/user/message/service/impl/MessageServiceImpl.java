package org.blackapple.backend.user.message.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.blackapple.backend.kafka.MessageProducer;
import org.blackapple.backend.user.message.model.Message;
import org.blackapple.backend.user.message.model.MessageDTO;
import org.blackapple.backend.user.message.repositories.MessageRepository;
import org.blackapple.backend.user.message.service.MessageService;
import org.blackapple.backend.user.message.utils.DateComparator;
import org.blackapple.backend.user.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class MessageServiceImpl implements MessageService {

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    MessageProducer messageProducer;

    @Override
    public List<Message> getAllMessages() {
        List<Message> messages = new ArrayList<>();
        messageRepository.findAll().forEach(messages::add);
        return messages;
    }

    @Override
    public List<Message> getIncomingMessages(int id) {
        return messageRepository.getAllIncomingMessageById(id);
    }

    @Override
    public List<Message> getOutgoingMessages(int id) {
        return messageRepository.getAllOutgoingMessageByIdUser(id);
    }

    @Override
    public List<MessageDTO> getAllMessagesDTO() {
        List<Message> messages = messageRepository.getAllMessages();
        DateComparator dateComparator = new DateComparator();
        messages.sort(dateComparator);
        List<MessageDTO> messageDTOS = new ArrayList<>();
        messages.forEach(message -> {
            MessageDTO messageDTO = new MessageDTO();
            messageDTO.setMessage(message.getText());
            messageDTO.setDate(message.getDate());
            messageDTO.setSender(userRepository.getUsernameById(message.getId_sender()));
            messageDTO.setRecipient(userRepository.getUsernameById(message.getId_recipient()));
            messageDTOS.add(messageDTO);
        });
        return messageDTOS;
    }

    @Override
    public void sendMessage(Message message) {
        log.info("Message: {}", message.toString());
        messageProducer.sendMessage(message);
    }
}

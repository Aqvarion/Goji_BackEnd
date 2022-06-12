package org.blackapple.backend.user.message.service;


import org.blackapple.backend.authorization.payload.MessageResponse;
import org.blackapple.backend.user.message.model.Message;
import org.blackapple.backend.user.message.model.MessageDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MessageService {

    List<Message> getIncomingMessages(int id);

    List<Message> getOutgoingMessages(int id);

    List<Message> getAllMessages();

    List<MessageDTO> getAllMessagesDTO();

    void sendMessage(Message message);
}

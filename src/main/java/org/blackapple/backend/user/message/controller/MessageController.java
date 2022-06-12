package org.blackapple.backend.user.message.controller;

import org.blackapple.backend.authorization.payload.MessageResponse;
import org.blackapple.backend.user.message.model.Message;
import org.blackapple.backend.user.message.model.MessageDTO;
import org.blackapple.backend.user.message.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @GetMapping("/all")
    public List<Message> getAllMessages() {
        return messageService.getAllMessages();
    }

    @GetMapping("/incoming")
    public List<Message> getIncomingMessages(@RequestParam int id) {
        return messageService.getIncomingMessages(id);
    }

    @GetMapping("/outgoing")
    public List<Message> getOutgoingMessages(@RequestParam int id) {
        return messageService.getOutgoingMessages(id);
    }

    @GetMapping("/allDTOs")
    public List<MessageDTO> getAllMessageDTOs() {
        return messageService.getAllMessagesDTO();
    }

    @PostMapping("/send")
    public ResponseEntity<MessageResponse> sendMessage(@RequestBody Message message) {
        messageService.sendMessage(message);
        return ResponseEntity.ok(new MessageResponse("Message successfully send"));
    }
}

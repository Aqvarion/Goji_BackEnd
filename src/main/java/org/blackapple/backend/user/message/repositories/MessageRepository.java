package org.blackapple.backend.user.message.repositories;

import org.blackapple.backend.user.message.model.Message;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends CrudRepository<Message, Integer> {

    @Query(nativeQuery = true, value = "SELECT * FROM message WHERE id_recipient =:id")
    List<Message> getAllIncomingMessageById(int id);

    @Query(nativeQuery = true, value = "SELECT * FROM message WHERE id_sender =:id")
    List<Message> getAllOutgoingMessageByIdUser(int id);

    @Query(nativeQuery = true, value = "SELECT * FROM message")
    List<Message> getAllMessages();
}

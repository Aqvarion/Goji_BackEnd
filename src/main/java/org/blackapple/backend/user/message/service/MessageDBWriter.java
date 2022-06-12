package org.blackapple.backend.user.message.service;

import org.blackapple.backend.user.message.model.Message;

public interface MessageDBWriter {

    void writeMessageToDB(Message message);
}

package org.blackapple.backend.user.message.model;


import lombok.Data;

import java.util.Date;

@Data
public class MessageDTO {
    private Date date;
    private String sender;
    private String recipient;
    private String message;
}

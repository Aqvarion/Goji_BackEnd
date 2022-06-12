package org.blackapple.backend.user.message.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "message")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "text")
    private String text;
    @Column(name = "id_sender")
    private int id_sender;
    @Column(name = "id_recipient")
    private int id_recipient;
    @Column(name = "date")
    private Date date;
}

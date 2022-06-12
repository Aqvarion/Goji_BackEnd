package org.blackapple.backend.communication.group.model;

import lombok.Data;

@Data
public class GroupDTO {
    private String title;
    private String description;
    private int adminId;
}

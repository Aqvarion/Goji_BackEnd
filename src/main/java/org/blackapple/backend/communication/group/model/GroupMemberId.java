package org.blackapple.backend.communication.group.model;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class GroupMemberId implements Serializable {

    int group_id;
    int member_id;

    public GroupMemberId(int groupId, int memberId) {
        this.group_id = groupId;
        this.member_id = memberId;
    }

    public GroupMemberId() {
    }
}

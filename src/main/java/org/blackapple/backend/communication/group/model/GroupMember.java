package org.blackapple.backend.communication.group.model;

import org.blackapple.backend.communication.util.StateRequest;
import org.hibernate.annotations.Type;
import org.springframework.web.bind.annotation.Mapping;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "group_member")
public class GroupMember {

    @EmbeddedId
    GroupMemberId groupMemberId = new GroupMemberId();

    @Column(name = "group_id", insertable = false, updatable = false)
    int groupId;
    @Column(name = "member_id", insertable = false, updatable = false)
    int memberId;
    @Column(name = "state" )
    String state;

    public GroupMember() {
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}



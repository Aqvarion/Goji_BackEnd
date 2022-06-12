package org.blackapple.backend.user.message.utils;

import org.blackapple.backend.user.message.model.Message;

import java.util.Comparator;

public class DateComparator implements Comparator<Message> {

    @Override
    public int compare(Message o1, Message o2) {
        return o1.getDate().compareTo(o2.getDate());
    }
}

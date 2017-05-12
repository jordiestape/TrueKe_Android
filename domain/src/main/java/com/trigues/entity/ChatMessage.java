package com.trigues.entity;

/**
 * Created by mbaque on 04/05/2017.
 */

public class ChatMessage {

    int fromUserId;
    Long date;

    public ChatMessage() {
    }

    public ChatMessage(int fromUserId, Long date) {
        this.fromUserId = fromUserId;
        this.date = date;
    }

    public int getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(int fromUserId) {
        this.fromUserId = fromUserId;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }
}
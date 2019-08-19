package com.mario.molitvenik.data.remote;

import java.util.Date;
import java.util.UUID;

public class LiveMessage {

  private String messageId;
  private String userId;
  private String message;
  private Date postedAt;

  public LiveMessage() {
  }

  public LiveMessage(String userId, String message, Date postedAt) {
    this.messageId = UUID.randomUUID().toString();
    this.userId = userId;
    this.message = message;
    this.postedAt = postedAt;
  }

  public String getMessageId() {
    return messageId;
  }

  public String getUserId() {
    return userId;
  }

  public String getMessage() {
    return message;
  }

  public Date getPostedAt() {
    return postedAt;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public void setPostedAt(Date postedAt) {
    this.postedAt = postedAt;
  }
}

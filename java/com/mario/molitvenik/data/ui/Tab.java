package com.mario.molitvenik.data.ui;

import android.widget.Button;

import java.util.UUID;

public class Tab<T> {

  private String id;
  private T data;
  private Button button;

  public Tab(T data) {
    this.id = UUID.randomUUID().toString();
    this.data = data;
  }

  public String getId() {
    return id;
  }

  public T getData() {
    return data;
  }

  public void setButton(Button button) {
    this.button = button;
  }

  public Button getButton() {
    return button;
  }
}

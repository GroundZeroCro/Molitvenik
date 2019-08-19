package com.mario.molitvenik.data.common;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "prayers")
public class Prayer {

  @PrimaryKey(autoGenerate = true)
  private long id;
  @SerializedName("title")
  private String title;
  @SerializedName("text")
  private String text;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }
}
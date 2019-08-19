package com.mario.molitvenik.data.persistance.alarm;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "alarms")
public class Alarm {

  @PrimaryKey(autoGenerate = true)
  private long id;
  @SerializedName("title")
  private String title;
  @SerializedName("time")
  private long time;
  @SerializedName("days")
  private boolean[] activeDays;
  @SerializedName("alarm_active")
  private boolean isActive;

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

  public long getTime() {
    return time;
  }

  public void setTime(long time) {
    this.time = time;
  }

  public boolean[] getActiveDays() {
    return activeDays;
  }

  public void setActiveDays(boolean[] activeDays) {
    this.activeDays = activeDays;
  }

  public boolean isActive() {
    return isActive;
  }

  public void setActive(boolean active) {
    isActive = active;
  }
}

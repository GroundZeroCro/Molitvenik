package com.mario.molitvenik.data.persistance.settings;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.mario.molitvenik.util.Sort;

@Entity(tableName = "user_settings")
public class UserSettings {

  private static final String ID = "user_settings";
  // Initial SeekBar size is initially increased by this number.
  public static final float TEXT_SIZE_INCREASE = 16.0f;
  // Initial textSize if textSize is not present in SQLite
  private static final float INITIAL_TEXT_SIZE = 18.0f;

  @NonNull
  @PrimaryKey
  @ColumnInfo(name = "id")
  private String id;
  @ColumnInfo(name = "visibility")
  private boolean isControllerVisible;
  // Is used to LiveFragment message identification
  @ColumnInfo(name = "userId")
  private String userId;
  @ColumnInfo(name = "text_size")
  private float textSize;
  @ColumnInfo(name = "prayers_sort")
  private Sort prayersSort;

  public UserSettings() {
    this.id = ID;
    this.isControllerVisible = false;
    this.prayersSort = Sort.TITLE;
  }

  public void setId(@NonNull String id) {
    this.id = id;
  }

  @NonNull
  public String getId() {
    return id;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public float getTextSize() {
    return textSize == 0 ? INITIAL_TEXT_SIZE : textSize;
  }

  public boolean isControllerVisible() {
    return isControllerVisible;
  }

  public void setControllerVisible(boolean controllerVisible) {
    isControllerVisible = controllerVisible;
  }

  public void setTextSize(float textSize) {
    this.textSize = textSize;
  }

  public Sort getPrayersSort() {
    return prayersSort;
  }

  public void setPrayersSort(Sort prayersSort) {
    this.prayersSort = prayersSort;
  }
}

package com.mario.molitvenik.data.common;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PrayerCategory {
  @SerializedName("category_id")
  public String id;
  @SerializedName("category_name")
  public String categoryName;
  @SerializedName("prayers")
  public List<Prayer> prayers;

  public String getCategoryName() {
    return categoryName;
  }

  public void setCategoryName(String categoryName) {
    this.categoryName = categoryName;
  }

  public String getId() {
    return id;
  }

  public List<Prayer> getPrayers() {
    return prayers;
  }

  public void setPrayers(List<Prayer> prayers) {
    this.prayers = prayers;
  }
}



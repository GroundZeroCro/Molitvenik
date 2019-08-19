package com.mario.molitvenik.data.json;

import com.google.gson.annotations.SerializedName;
import com.mario.molitvenik.data.common.PrayerCategory;

import java.util.List;

public class Prayers {

  @SerializedName("categories")
  public List<PrayerCategory> categories;

  public List<PrayerCategory> getCategories() {
    return categories;
  }

  public void setCategories(List<PrayerCategory> categories) {
    this.categories = categories;
  }
}


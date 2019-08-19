package com.mario.molitvenik.data.json;

import android.content.Context;

import com.google.gson.Gson;
import com.mario.molitvenik.data.common.Prayer;
import com.mario.molitvenik.data.common.PrayerCategory;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class LocalService implements LocalDao {

  private Context context;
  private List<PrayerCategory> categories;

  @Inject
  public LocalService(Context context) {
    this.context = context;
    this.categories = new Gson().fromJson(getJsonAsString(), Prayers.class).categories;
  }

  String getJsonAsString() {
    return new LocalWorker(context).jsonStringify();
  }

  @Override
  public List<PrayerCategory> getPrayerCategories() {
    List<PrayerCategory> prayerCategories = new ArrayList<>();
    for (PrayerCategory prayerCategory : categories) {
      List<Prayer> prayers = new ArrayList<>();
      for (Prayer prayer : prayerCategory.getPrayers()) {
        if (prayer.getText() != null && prayer.getTitle() != null) {
          prayers.add(prayer);
        }
      }
      if (prayers.size() != 0) {
        prayerCategory.setPrayers(prayers);
        prayerCategories.add(prayerCategory);
      }
    }
    return prayerCategories;
  }

  @Override
  public List<Prayer> fetchSingleCategoryPrayers(String categoryId) {
    for (PrayerCategory pc : categories) {
      if (pc.id.equals(categoryId)) {
        return pc.getPrayers();
      }
    }
    return null;
  }
}
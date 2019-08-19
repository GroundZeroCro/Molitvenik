package com.mario.molitvenik.data.json;

import android.content.Context;

import com.mario.molitvenik.data.common.Prayer;
import com.mario.molitvenik.data.common.PrayerCategory;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class LocalRepository {

  private LocalDao localDao;

  @Inject
  LocalRepository(Context context) {
    localDao = new LocalService(context);
  }

  public List<PrayerCategory> fetchCategories() {
    return localDao.getPrayerCategories();
  }

  public List<Prayer> getPrayersSorted(CharSequence inputKey) {
    if (fetchCategories() == null) {
      return null;
    }
    List<Prayer> unsortedPrayers = new ArrayList<>();
    for (PrayerCategory category : localDao.getPrayerCategories()) {
      for (Prayer prayer : category.getPrayers()) {
        if (prayer.getTitle().contains(inputKey) || prayer.getText().contains(inputKey))
          unsortedPrayers.add(prayer);
      }
    }
    return unsortedPrayers;
  }
}

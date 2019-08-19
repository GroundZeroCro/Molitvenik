package com.mario.molitvenik.data.json;

import com.mario.molitvenik.data.common.Prayer;
import com.mario.molitvenik.data.common.PrayerCategory;

import java.util.List;

interface LocalDao {
  List<PrayerCategory> getPrayerCategories();
  List<Prayer> fetchSingleCategoryPrayers(String categoryId);
}

package com.mario.molitvenik.ui.common;

import com.mario.molitvenik.data.common.PrayerCategory;
import com.mario.molitvenik.data.ui.Tab;

public interface TabSelection<T> {
  void setSelectedTab(Tab<T> tab);
}

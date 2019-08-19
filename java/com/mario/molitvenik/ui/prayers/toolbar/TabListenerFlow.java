package com.mario.molitvenik.ui.prayers.toolbar;

import com.mario.molitvenik.data.ui.Tab;

public interface TabListenerFlow<T> {
  void manageSelectedTab(Tab<T> selectedTab);
}

package com.mario.molitvenik.ui.prayers.toolbar;

import android.content.Context;
import android.graphics.Color;
import android.view.animation.AnimationUtils;

import com.mario.molitvenik.R;
import com.mario.molitvenik.data.common.PrayerCategory;
import com.mario.molitvenik.data.ui.Tab;
import com.mario.molitvenik.ui.common.TabSelection;

import java.util.List;

public class CustomToolbarImp<T> implements CustomToolbar, TabListenerFlow<T> {

  private Context context;
  private List<Tab<T>> tabs;
  private TabSelection<T> tabSelection;
  private TabButton<T> tabButton;
  private Class<T> classType;

  public CustomToolbarImp(Context context, List<Tab<T>> tabs, TabSelection<T> tabSelection, Class<T> classType) {
    this.context = context;
    this.tabs = tabs;
    this.tabSelection = tabSelection;
    this.classType = classType;
    tabButton = new TabButton<>(this.context, this);
    // Generating tabs on class implementation
    updateTabsWithButtons();
  }

  private void updateTabsWithButtons() {
    for (Tab<T> tab : tabs) {
      String tabName = "uninitialized";


      if (this.classType == PrayerCategory.class) {
        tabName = ((Tab<PrayerCategory>) tab).getData().categoryName;
      }
      tab.setButton(tabButton.generate(tabName, tab));
    }
  }

  @Override
  public void manageTabLayout(String id) {
    for (Tab<T> tab : tabs) {
      if (this.classType == PrayerCategory.class) {
        if (((Tab<PrayerCategory>) tab).getData().id.equals(id)) {
          tab.getButton().setBackgroundResource(R.drawable.prayer_tab_highlight);
          tab.getButton().setTextColor(context.getResources().getColor(R.color.primaryTextColor));
          tab.getButton().startAnimation(AnimationUtils.loadAnimation(context, R.anim.pulse));
        } else {
          tab.getButton().setBackgroundColor(Color.TRANSPARENT);
          tab.getButton().setTextColor(context.getResources().getColor(R.color.primaryTextColorDark));
        }
      }
    }
  }

  @Override
  public void manageSelectedTab(Tab<T> selectedTab) {
    tabSelection.setSelectedTab(selectedTab);
  }
}
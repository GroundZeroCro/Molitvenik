package com.mario.molitvenik.ui.prayers.toolbar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.widget.AppCompatButton;

import com.mario.molitvenik.R;
import com.mario.molitvenik.data.common.PrayerCategory;
import com.mario.molitvenik.data.ui.Tab;

class TabButton<T> {

  private Context context;
  private TabListenerFlow<T> tabListenerFlow;

  TabButton(Context context, TabListenerFlow<T> tabListenerFlow) {
    this.context = context;
    this.tabListenerFlow = tabListenerFlow;
  }

  Button generate(String buttonName, Tab<T> tab) {
    Button tabButton = (AppCompatButton) LayoutInflater.from(context).inflate(R.layout.item_tab, null);
    tabButton.setText(buttonName);
    tabButton.setOnClickListener(tabListener(tab));
    return tabButton;
  }

  private View.OnClickListener tabListener(Tab<T> tab) {
    return view -> tabListenerFlow.manageSelectedTab(tab);
  }
}

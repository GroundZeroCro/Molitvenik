package com.mario.molitvenik.ui.prayers;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mario.molitvenik.data.handler.Response;
import com.mario.molitvenik.data.common.Prayer;
import com.mario.molitvenik.data.common.PrayerCategory;
import com.mario.molitvenik.data.json.LocalRepository;
import com.mario.molitvenik.data.ui.Tab;
import com.mario.molitvenik.ui.common.TabSelection;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class PrayersViewModel extends ViewModel implements TabSelection<PrayerCategory> {

  private MutableLiveData<Response<Tab<PrayerCategory>>> tabsLiveData = new MutableLiveData<>();
  private MutableLiveData<Tab<PrayerCategory>> selectedTabLiveData = new MutableLiveData<>();
  private MutableLiveData<Prayer> selectedPrayerLiveData = new MutableLiveData<>();

  private LocalRepository localRepository;

  @Inject
  public PrayersViewModel(LocalRepository localRepository) {
    this.localRepository = localRepository;
  }

  LiveData<Response<Tab<PrayerCategory>>> getTabsLiveData() {
    prayersIntoTabs();
    return tabsLiveData;
  }

  private void prayersIntoTabs() {
    tabsLiveData.setValue(Response.loading());
    List<Tab<PrayerCategory>> tabs = new ArrayList<>();
    for (PrayerCategory prayerCategory : localRepository.fetchCategories()) {
      tabs.add(new Tab<>(prayerCategory));
    }
    if (tabs.size() != 0) {
      tabsLiveData.setValue(Response.success(tabs));
    } else {
      tabsLiveData.setValue(Response.error(new Throwable()));
    }
  }

  void setSelectedTabLiveData(Tab<PrayerCategory> tab) {
    this.selectedTabLiveData.setValue(tab);
  }

  LiveData<Tab<PrayerCategory>> getSelectedTabLiveData() {
    return selectedTabLiveData;
  }

  public void setSelectedPrayer(Prayer selectedPrayer) {
    selectedPrayerLiveData.setValue(selectedPrayer);
  }

  public LiveData<Prayer> getSelectedPrayerLiveData() {
    return selectedPrayerLiveData;
  }

  @Override
  public void setSelectedTab(Tab<PrayerCategory> tab) {
    this.selectedTabLiveData.setValue(tab);
  }
}
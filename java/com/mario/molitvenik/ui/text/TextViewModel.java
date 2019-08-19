package com.mario.molitvenik.ui.text;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.mario.molitvenik.core.base.BaseViewModel;
import com.mario.molitvenik.data.common.Prayer;
import com.mario.molitvenik.data.persistance.favorites.FavoritesRepository;
import com.mario.molitvenik.data.persistance.settings.UserSettings;
import com.mario.molitvenik.data.persistance.settings.UserSettingsRepository;

import javax.inject.Inject;

public class TextViewModel extends BaseViewModel {

  private FavoritesRepository favoritesRepository;
  private MutableLiveData<Boolean> isPrayerFavorite = new MutableLiveData<>();

  @Inject
  public TextViewModel(UserSettingsRepository userSettingsRepository, FavoritesRepository favoritesRepository) {
    super(userSettingsRepository);
    this.favoritesRepository = favoritesRepository;
  }

  void setUserSettingsMutableLiveData(UserSettings userSettings) {
    this.userSettingsRepository.insertUserSettings(userSettings);
    this.userSettingsMutableLiveData.setValue(userSettings);
  }

  void changeTextSize(float textSize) {
    UserSettings userSettings = userSettingsMutableLiveData.getValue();
    userSettings.setTextSize(textSize + UserSettings.TEXT_SIZE_INCREASE);
    updateTextLiveData(userSettings);
  }

  private void updateTextLiveData(UserSettings userSettings) {
    this.userSettingsMutableLiveData.setValue(userSettings);
    userSettingsRepository.insertUserSettings(userSettings);
  }

  LiveData<Boolean> getIsPrayerFavoriteLive(String prayerText) {
    this.isPrayerFavorite.setValue(this.favoritesRepository.getSinglePrayer(prayerText) != null);
    return isPrayerFavorite;
  }

  void setPrayerToFavoriteLive(boolean isAddedToFavorites) {
    this.isPrayerFavorite.setValue(isAddedToFavorites);
  }

  void addPrayerToFavorites(Prayer prayer) {
    favoritesRepository.addPrayerToFavorites(prayer);
  }

  void removePrayerFromFavorites(String prayerText) {
    favoritesRepository.removePrayerFromFavorites(prayerText);
  }
}

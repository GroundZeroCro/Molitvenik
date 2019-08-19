package com.mario.molitvenik.core.base;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mario.molitvenik.data.persistance.settings.UserSettings;
import com.mario.molitvenik.data.persistance.settings.UserSettingsRepository;

public class BaseViewModel extends ViewModel {

  protected UserSettingsRepository userSettingsRepository;
  protected MutableLiveData<UserSettings> userSettingsMutableLiveData = new MutableLiveData<>();

  public BaseViewModel(UserSettingsRepository userSettingsRepository) {
    this.userSettingsRepository = userSettingsRepository;
  }

  public LiveData<UserSettings> getUserSettingsMutableLiveData() {
    if (userSettingsMutableLiveData.getValue() == null) {
      this.userSettingsMutableLiveData.setValue(userSettingsRepository.getUserSettings());
    }
    return userSettingsMutableLiveData;
  }
}

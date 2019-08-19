package com.mario.molitvenik.data.persistance.settings;

import javax.inject.Inject;

public class UserSettingsRepository {

  private UserSettingsDao userSettingsDao;

  @Inject
  UserSettingsRepository(UserSettingsDao userSettingsDao) {
    this.userSettingsDao = userSettingsDao;
  }

  public void insertUserSettings(UserSettings userSettings) {
    userSettingsDao.insertUserSettings(userSettings);
  }

  public UserSettings getUserSettings() {
    if (userSettingsDao.getUserSettings() != null) {
      return userSettingsDao.getUserSettings();
    } else {
      return new UserSettings();
    }
  }
}
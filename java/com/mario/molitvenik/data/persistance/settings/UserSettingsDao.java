package com.mario.molitvenik.data.persistance.settings;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface UserSettingsDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  void insertUserSettings(UserSettings userSettings);

  @Query("SELECT * FROM user_settings")
  UserSettings getUserSettings();
}
package com.mario.molitvenik.data.persistance;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.mario.molitvenik.data.common.Prayer;
import com.mario.molitvenik.data.persistance.alarm.Alarm;
import com.mario.molitvenik.data.persistance.alarm.AlarmDao;
import com.mario.molitvenik.data.persistance.alarm.AlarmDaysConverter;
import com.mario.molitvenik.data.persistance.favorites.FavoritesDao;
import com.mario.molitvenik.data.persistance.settings.UserSettings;
import com.mario.molitvenik.data.persistance.settings.UserSettingsConverter;
import com.mario.molitvenik.data.persistance.settings.UserSettingsDao;

@TypeConverters({UserSettingsConverter.class, AlarmDaysConverter.class})
@Database(entities = {Alarm.class, Prayer.class, UserSettings.class}, version = 1, exportSchema = false)
public abstract class PersistenceDatabase extends RoomDatabase {

  public abstract AlarmDao alarmDao();

  public abstract FavoritesDao favoritesDao();

  public abstract UserSettingsDao userSettingsDao();
}
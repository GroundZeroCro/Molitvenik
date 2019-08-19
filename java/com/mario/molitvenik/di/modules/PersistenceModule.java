package com.mario.molitvenik.di.modules;

import android.content.Context;

import androidx.room.Room;

import com.mario.molitvenik.data.persistance.PersistenceDatabase;
import com.mario.molitvenik.data.persistance.alarm.AlarmDao;
import com.mario.molitvenik.data.persistance.favorites.FavoritesDao;
import com.mario.molitvenik.data.persistance.settings.UserSettingsDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class PersistenceModule {

  private static final String DATABASE_NAME = "persistance_database";

  @Singleton
  @Provides
  PersistenceDatabase provideDatabase(Context context) {
    return Room.databaseBuilder(context, PersistenceDatabase.class, DATABASE_NAME)
            .allowMainThreadQueries()
            .build();
  }

  @Provides
  UserSettingsDao provideUserSettingsDao(PersistenceDatabase persistenceDatabase) {
    return persistenceDatabase.userSettingsDao();
  }

  @Provides
  FavoritesDao provideFavoritesDao(PersistenceDatabase persistenceDatabase) {
    return persistenceDatabase.favoritesDao();
  }

  @Provides
  AlarmDao provideAlarmDao(PersistenceDatabase persistenceDatabase) {
    return persistenceDatabase.alarmDao();
  }
}
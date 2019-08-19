package com.mario.molitvenik.data.persistance.alarm;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface AlarmDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  void insertAlarm(Alarm alarm);

  @Delete
  void deleteAlarm(Alarm alarm);

  @Query("SELECT * from alarms")
  Single<List<Alarm>> getAlarms();

  @Update
  void updateAlarm(Alarm alarm);
}
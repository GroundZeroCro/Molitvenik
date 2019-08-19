package com.mario.molitvenik.data.persistance.favorites;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.mario.molitvenik.data.common.Prayer;

import java.util.List;

import io.reactivex.Single;
// TODO replace prayerText with internalPrayerID UUID
@Dao
public interface FavoritesDao {

  @Insert
  void insertPrayer(Prayer prayer);

  @Query("DELETE FROM prayers WHERE text = :prayerText")
  void removePrayer(String prayerText);

  @Query("SELECT * from prayers")
  Single<List<Prayer>> getFavoritePrayers();

  @Query("SELECT * FROM prayers WHERE text = :prayerText")
  Prayer getSinglePrayer(String prayerText);
}
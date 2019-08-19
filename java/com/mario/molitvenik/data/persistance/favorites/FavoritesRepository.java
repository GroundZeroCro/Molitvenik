package com.mario.molitvenik.data.persistance.favorites;

import android.util.Log;

import com.mario.molitvenik.data.common.Prayer;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class FavoritesRepository {

  private FavoritesDao favoritesDao;

  @Inject
  public FavoritesRepository(FavoritesDao favoritesDao) {
    this.favoritesDao = favoritesDao;
  }

  public void addPrayerToFavorites(Prayer prayer) {
    favoritesDao.insertPrayer(prayer);
  }

  public Single<List<Prayer>> getFavoritePrayers() {
    return favoritesDao.getFavoritePrayers();
  }

  public Prayer getSinglePrayer(String prayerText) {
    return favoritesDao.getSinglePrayer(prayerText);
  }

  public void removePrayerFromFavorites(String prayerText) {
    favoritesDao.removePrayer(prayerText);
  }
}
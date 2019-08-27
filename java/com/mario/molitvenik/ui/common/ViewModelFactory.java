package com.mario.molitvenik.ui.common;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.mario.molitvenik.data.json.LocalRepository;
import com.mario.molitvenik.data.persistance.alarm.AlarmRepository;
import com.mario.molitvenik.data.persistance.alarm.RingtoneService;
import com.mario.molitvenik.data.persistance.favorites.FavoritesRepository;
import com.mario.molitvenik.data.persistance.settings.UserSettingsRepository;
import com.mario.molitvenik.data.remote.LiveRepository;
import com.mario.molitvenik.ui.alarm.AlarmViewModel;
import com.mario.molitvenik.ui.favorites.FavoritesViewModel;
import com.mario.molitvenik.ui.live.LiveViewModel;
import com.mario.molitvenik.ui.prayers.PrayersViewModel;
import com.mario.molitvenik.ui.search.SearchViewModel;
import com.mario.molitvenik.ui.text.TextViewModel;

import javax.inject.Inject;

public class ViewModelFactory implements ViewModelProvider.Factory {

  private final UserSettingsRepository userSettingsRepository;
  private final FavoritesRepository favoritesRepository;
  private final LocalRepository localRepository;
  private final LiveRepository liveRepository;
  private final AlarmRepository alarmRepository;
  private final RingtoneService ringtoneService;

  @Inject
  public ViewModelFactory(
          UserSettingsRepository userSettingsRepository,
          FavoritesRepository favoritesRepository,
          LocalRepository localRepository,
          LiveRepository liveRepository,
          AlarmRepository alarmRepository,
          RingtoneService ringtoneService
  ) {
    this.userSettingsRepository = userSettingsRepository;
    this.favoritesRepository = favoritesRepository;
    this.localRepository = localRepository;
    this.liveRepository = liveRepository;
    this.alarmRepository = alarmRepository;
    this.ringtoneService = ringtoneService;
  }

  @NonNull
  @Override
  public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
    if (modelClass.isAssignableFrom(TextViewModel.class)) {
      return (T) new TextViewModel(userSettingsRepository, favoritesRepository);
    } else if (modelClass.isAssignableFrom(SearchViewModel.class)) {
      return (T) new SearchViewModel(localRepository);
    } else if (modelClass.isAssignableFrom(PrayersViewModel.class)) {
      return (T) new PrayersViewModel(localRepository);
    } else if (modelClass.isAssignableFrom(LiveViewModel.class)) {
      return (T) new LiveViewModel(liveRepository, userSettingsRepository);
    } else if (modelClass.isAssignableFrom(FavoritesViewModel.class)) {
      return (T) new FavoritesViewModel(favoritesRepository);
    } else if (modelClass.isAssignableFrom(AlarmViewModel.class)) {
      return (T) new AlarmViewModel(alarmRepository, userSettingsRepository, ringtoneService);
    }
    throw new IllegalArgumentException("Unknown ViewModel class");
  }
}

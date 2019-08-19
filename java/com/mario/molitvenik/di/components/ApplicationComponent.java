package com.mario.molitvenik.di.components;

import com.mario.molitvenik.core.base.PrayersBaseFragment;
import com.mario.molitvenik.di.modules.ApplicationModule;
import com.mario.molitvenik.di.modules.PersistenceModule;
import com.mario.molitvenik.di.modules.RemoteModule;
import com.mario.molitvenik.ui.alarm.AlarmFragment;
import com.mario.molitvenik.ui.alarm.SchedulerFragment;
import com.mario.molitvenik.ui.favorites.FavoritesFragment;
import com.mario.molitvenik.ui.live.LiveFragment;
import com.mario.molitvenik.ui.search.SearchFragment;
import com.mario.molitvenik.ui.text.TextFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, PersistenceModule.class, RemoteModule.class})
public interface ApplicationComponent {

  void inject(LiveFragment liveFragment);

  void inject(PrayersBaseFragment prayersBaseFragment);

  void inject(FavoritesFragment favoritesFragment);

  void inject(SearchFragment searchFragment);

  void inject(TextFragment textFragment);

  void inject(AlarmFragment alarmFragment);

  void inject(SchedulerFragment schedulerFragment);
}
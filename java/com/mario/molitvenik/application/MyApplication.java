package com.mario.molitvenik.application;

import android.app.Application;

import androidx.annotation.UiThread;

import com.mario.molitvenik.di.components.ApplicationComponent;
import com.mario.molitvenik.di.components.DaggerApplicationComponent;
import com.mario.molitvenik.di.modules.ApplicationModule;
import com.mario.molitvenik.di.modules.PersistenceModule;
import com.mario.molitvenik.di.modules.RemoteModule;

public class MyApplication extends Application {

  private ApplicationComponent mApplicationComponent;

  @UiThread
  public ApplicationComponent getApplicationComponent() {
    if (mApplicationComponent == null) {
      mApplicationComponent = DaggerApplicationComponent.builder()
              .applicationModule(new ApplicationModule(this))
              .persistenceModule(new PersistenceModule())
              .remoteModule(new RemoteModule())
              .build();
    }
    return mApplicationComponent;
  }
}
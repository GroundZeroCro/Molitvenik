package com.mario.molitvenik.di.modules;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RemoteModule {

  private static final String LIVE_COLLECTION = "latest_messages";

  @Provides
  @Singleton
  FirebaseDatabase provideFirebaseDatabase() {
    return FirebaseDatabase.getInstance();
  }

  @Provides
  @Singleton
  DatabaseReference provideDatabaseReference(FirebaseDatabase firebaseDatabase) {
    return firebaseDatabase.getReference(LIVE_COLLECTION);
  }
}
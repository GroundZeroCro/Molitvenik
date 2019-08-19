package com.mario.molitvenik.data.persistance.alarm;

import android.net.Uri;

public class Ringtone {

  private String name;
  private Uri uri;

  public Ringtone(String name, Uri uri) {
    this.name = name;
    this.uri = uri;
  }

  public Uri getUri() {
    return uri;
  }

  public void setUri(Uri uri) {
    this.uri = uri;
  }
}

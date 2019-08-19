package com.mario.molitvenik.data.json;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;

public class LocalWorker {

  private Context context;

  LocalWorker(Context context) {
    this.context = context;
  }

  public String jsonStringify() {

    String jsonString;
    InputStream inputStream = getJsonInputStream();

    try {
      int size = inputStream != null ? inputStream.available() : 0;
      byte[] buffer = new byte[size];
      if (inputStream != null) {
        inputStream.read(buffer);
        inputStream.close();
      }

      jsonString = new String(buffer, "UTF-8");
    } catch (IOException e) {
      return "";
    }

    return jsonString;
  }

  private InputStream getJsonInputStream() {
    try {
      return context.getAssets().open("data/prayers");
    } catch (IOException e) {
      return null;
    }
  }
}

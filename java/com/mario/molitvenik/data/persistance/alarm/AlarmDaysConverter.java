package com.mario.molitvenik.data.persistance.alarm;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class AlarmDaysConverter {
  @TypeConverter
  public static boolean[] fromString(String value) {
    Type listType = new TypeToken<boolean[]>() {
    }.getType();
    return new Gson().fromJson(value, listType);
  }

  @TypeConverter
  public static String fromArray(boolean[] array) {
    Gson gson = new Gson();
    String json = gson.toJson(array);
    return json;
  }
}
package com.mario.molitvenik.data.persistance.settings;

import androidx.room.TypeConverter;

import com.mario.molitvenik.util.Sort;

public class UserSettingsConverter {

  private static final String TITLE = "title";
  private static final String LENGTH = "length";

  @TypeConverter
  public static String fromSort(Sort sort) {
    if (sort == Sort.TITLE) {
      return TITLE;
    } else if (sort == Sort.LENGTH) {
      return LENGTH;
    }
    return null;
  }

  @TypeConverter
  public static Sort toSort(String sortStringyfied) {
    if (sortStringyfied.equals(TITLE)) {
      return Sort.TITLE;
    } else if (sortStringyfied.equals(LENGTH)) {
      return Sort.LENGTH;
    }
    return null;
  }
}

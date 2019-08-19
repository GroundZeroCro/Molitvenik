package com.mario.molitvenik.util;

import android.content.Context;
import android.view.View;

import com.mario.molitvenik.R;

import java.text.SimpleDateFormat;

public class AlarmUtils {

  private static SimpleDateFormat simpleDateFormat;

  public static String getHoursAndMinutes(long time) {
    simpleDateFormat = new SimpleDateFormat("HH:mm");
    return simpleDateFormat.format(time);
  }

  // User desired days to be notified will be true, array length is 7,
  // for each day of week, starting of monday as index 0
  public static String getDays(Context context, boolean[] activeDays) {
    String daySignature = "";
    for (int i = 0; i < activeDays.length; i++) {
      if (activeDays[i]) {
        daySignature += context.getResources().getStringArray(R.array.days_of_week)[i] + ", ";
      }
    }
    if (!daySignature.equals("")) {
      daySignature = daySignature.substring(0, daySignature.length() - 2);
    }
    return daySignature;
  }

  public static Daytime getDaytime(long time) {
    simpleDateFormat = new SimpleDateFormat("HH");
    int hour = Integer.parseInt(simpleDateFormat.format(time));
    return (hour > 7 && hour < 19) ? Daytime.DAY : Daytime.NIGHT;
  }

  public static int getShadedEffectVisibility(boolean isAlarmActive) {
    return isAlarmActive ? View.GONE : View.VISIBLE;
  }

  public static String getHour(long time) {
    simpleDateFormat = new SimpleDateFormat("HH");
    return simpleDateFormat.format(time);
  }

  public static String getMinutes(long time) {
    simpleDateFormat = new SimpleDateFormat("mm");
    return simpleDateFormat.format(time);
  }

}

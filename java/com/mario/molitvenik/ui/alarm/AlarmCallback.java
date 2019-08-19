package com.mario.molitvenik.ui.alarm;

import com.mario.molitvenik.data.persistance.alarm.Alarm;

public interface AlarmCallback {
  void changeAlarmActivity(Alarm alarm);

  void onAlarmSelected(Alarm alarm);
}

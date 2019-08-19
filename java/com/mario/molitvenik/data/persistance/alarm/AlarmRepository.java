package com.mario.molitvenik.data.persistance.alarm;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class AlarmRepository {

  private AlarmDao alarmDao;

  @Inject
  public AlarmRepository(AlarmDao alarmDao) {
    this.alarmDao = alarmDao;
  }

  public void addAlarm(Alarm alarm) {
    alarmDao.insertAlarm(alarm);
  }

  public void updateAlarm(Alarm alarm) {
    alarmDao.updateAlarm(alarm);
  }

  public Single<List<Alarm>> getAlarms() {
    return alarmDao.getAlarms();
  }

  public void removeAlarm(Alarm alarm) {
    alarmDao.deleteAlarm(alarm);
  }
}
package com.mario.molitvenik.ui.alarm;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.mario.molitvenik.core.base.BaseViewModel;
import com.mario.molitvenik.data.persistance.alarm.Alarm;
import com.mario.molitvenik.data.persistance.alarm.AlarmRepository;
import com.mario.molitvenik.data.persistance.alarm.Ringtone;
import com.mario.molitvenik.data.persistance.alarm.RingtoneService;
import com.mario.molitvenik.data.persistance.settings.UserSettingsRepository;
import com.mario.molitvenik.util.AlarmUtils;

import java.util.Calendar;
import java.util.List;

import javax.inject.Singleton;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AlarmViewModel extends BaseViewModel {

  private AlarmRepository alarmRepository;
  private RingtoneService ringtoneService;
  private Disposable disposable;
  private MutableLiveData<List<Alarm>> alarmsLive = new MutableLiveData<>();
  private MutableLiveData<Alarm> selectedAlarmLive = new MutableLiveData<>();

  private Alarm alarm;

  public AlarmViewModel(
          AlarmRepository alarmRepository,
          UserSettingsRepository userSettingsRepository,
          RingtoneService ringtoneService) {
    super(userSettingsRepository);
    this.alarmRepository = alarmRepository;
    this.ringtoneService = ringtoneService;
  }

  private void fetchAlarms() {
    disposable = this.alarmRepository.getAlarms()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe((alarms, throwable) -> alarmsLive.setValue(alarms));
  }

  LiveData<List<Alarm>> getAlarmsLive() {
    fetchAlarms();
    return alarmsLive;
  }

  LiveData<Alarm> getSelectedAlarm() {
    if (selectedAlarmLive.getValue() == null) {
      alarm = new Alarm();
      alarm.setActiveDays(new boolean[]{false, false, false, false, false, false, false});
      alarm.setTime(Calendar.getInstance().getTimeInMillis());
      alarm.setTitle("");
      alarm.setActive(true);
      selectedAlarmLive.setValue(alarm);
    }
    return selectedAlarmLive;
  }

  void setTitleScheduler(String title) {
    alarm = selectedAlarmLive.getValue();
    alarm.setTitle(title);
    selectedAlarmLive.setValue(alarm);
  }

  void setHourScheduler(boolean isIncremented) {
    int hourChange;
    if (isIncremented) {
      hourChange = 1;
    } else {
      hourChange = -1;
    }
    int hour = Integer.parseInt(AlarmUtils.getHour(selectedAlarmLive.getValue().getTime())) + hourChange;
    int minutes = Integer.parseInt(AlarmUtils.getMinutes(selectedAlarmLive.getValue().getTime()));
    Calendar calendar = Calendar.getInstance();
    calendar.set(Calendar.HOUR_OF_DAY, hour);
    calendar.set(Calendar.MINUTE, minutes);
    alarm = selectedAlarmLive.getValue();
    alarm.setTime(calendar.getTimeInMillis());
    selectedAlarmLive.setValue(alarm);
  }

  void setMinutesScheduler(boolean isIncremented) {
    int minutesChanged;
    if (isIncremented) {
      minutesChanged = 1;
    } else {
      minutesChanged = -1;
    }
    int hour = Integer.parseInt(AlarmUtils.getHour(selectedAlarmLive.getValue().getTime()));
    int minutes = Integer.parseInt(AlarmUtils.getMinutes(selectedAlarmLive.getValue().getTime())) + minutesChanged;
    Calendar calendar = Calendar.getInstance();
    calendar.set(Calendar.HOUR_OF_DAY, hour);
    calendar.set(Calendar.MINUTE, minutes);
    alarm = selectedAlarmLive.getValue();
    alarm.setTime(calendar.getTimeInMillis());
    selectedAlarmLive.setValue(alarm);
  }

  void changeDayScheduled(int dayIndex) {
    alarm = selectedAlarmLive.getValue();
    alarm.getActiveDays()[dayIndex] = !alarm.getActiveDays()[dayIndex];
    selectedAlarmLive.setValue(alarm);
  }

  void changeActivity(Alarm alarm) {
    alarm.setActive(!alarm.isActive());
    alarmRepository.updateAlarm(alarm);
    fetchAlarms();
  }

  void addAlarm() {
    alarmRepository.addAlarm(this.selectedAlarmLive.getValue());
  }

  void selectAlarm(Alarm alarm) {
    this.selectedAlarmLive.setValue(alarm);
  }

  public List<Ringtone> getRingtones() {
    return ringtoneService.getRingtones();
  }

  void onDestroy() {
    disposable.dispose();
  }
}
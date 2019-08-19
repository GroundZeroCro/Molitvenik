package com.mario.molitvenik.ui.alarm;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.Navigation;

import com.mario.molitvenik.R;
import com.mario.molitvenik.core.base.BaseFragment;
import com.mario.molitvenik.util.AlarmUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SchedulerFragment extends BaseFragment {

  @Inject
  AlarmViewModel alarmViewModel;
  @BindView(R.id.title_scheduler)
  EditText titleSchedulerInput;
  @BindView(R.id.scheduler_time_hours)
  TextView scheduledHour;
  @BindView(R.id.scheduler_time_minutes)
  TextView scheduledMinutes;
  @BindViews({
          R.id.day_mo_button, R.id.day_tu_button, R.id.day_we_button,
          R.id.day_th_button, R.id.day_fr_button, R.id.day_sa_button, R.id.day_su_button
  })
  Button[] weekDayButtons;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    getApplicationComponent().inject(this);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_scheduler, container, false);
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    ButterKnife.bind(this, view);

    for (int i = 0; i < weekDayButtons.length; i++) {
      int finalI = i;
      weekDayButtons[i].setOnClickListener(v -> {
        alarmViewModel.changeDayScheduled(finalI);
      });
    }

    alarmViewModel.getSelectedAlarm().observe(getViewLifecycleOwner(), alarm -> {
      for (int i = 0; i < alarm.getActiveDays().length; i++) {
        if (alarm.getActiveDays()[i]) {
          weekDayButtons[i].setBackgroundColor(getResources().getColor(R.color.primaryLightestColor));
        } else {
          weekDayButtons[i].setBackgroundColor(getResources().getColor(R.color.primaryLightColor));
        }
      }

      titleSchedulerInput.setText(alarm.getTitle());
      scheduledHour.setText(AlarmUtils.getHour(alarm.getTime()));
      scheduledMinutes.setText(AlarmUtils.getMinutes(alarm.getTime()));
    });
  }

  @OnClick(R.id.scheduler_hour_up_button)
  void onHourUp() {
    alarmViewModel.setHourScheduler(true);
  }

  @OnClick(R.id.scheduler_hour_down_button)
  void onHourDown() {
    alarmViewModel.setHourScheduler(false);
  }

  @OnClick(R.id.scheduler_minutes_up_button)
  void onMinutesUp() {
    alarmViewModel.setMinutesScheduler(true);
  }

  @OnClick(R.id.scheduler_minutes_down_button)
  void onMinutesDown() {
    alarmViewModel.setMinutesScheduler(false);
  }

  @OnClick(R.id.submit_scheduler)
  void onSubmitScheduler(View view) {
    alarmViewModel.setTitleScheduler(titleSchedulerInput.getText().toString());
    alarmViewModel.addAlarm();
    Navigation.findNavController(view).popBackStack();
  }

  @OnClick(R.id.exit_scheduler)
  void onExitScheduler(View view) {
    Navigation.findNavController(view).popBackStack();
  }
}
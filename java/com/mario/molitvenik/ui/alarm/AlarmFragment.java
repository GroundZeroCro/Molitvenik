package com.mario.molitvenik.ui.alarm;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.mario.molitvenik.R;
import com.mario.molitvenik.core.activity.BottomNavigationCallback;
import com.mario.molitvenik.core.base.BaseFragment;
import com.mario.molitvenik.data.persistance.alarm.Alarm;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AlarmFragment extends BaseFragment implements AlarmCallback {

  @BindView(R.id.alarm_recycler_view)
  RecyclerView alarmRecyclerView;

  @Inject
  AlarmViewModel alarmViewModel;

  private BottomNavigationCallback bottomNavigationCallback;
  private AlarmAdapter alarmAdapter;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    getApplicationComponent().inject(this);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_alarm, container, false);
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    ButterKnife.bind(this, view);
    alarmAdapter = new AlarmAdapter(getContext(), this, new ArrayList<>());
    mutualRecyclerView(alarmRecyclerView, false).setAdapter(alarmAdapter);

    alarmViewModel.getAlarmsLive().observe(getViewLifecycleOwner(), alarms -> alarmAdapter.updateData(alarms));
  }

  @OnClick(R.id.add_alarm_button)
  void onScheduleAlarm(View view) {
    Navigation.findNavController(view).navigate(R.id.schedulerFragment);
  }

  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    if (bottomNavigationCallback == null) {
      bottomNavigationCallback = (BottomNavigationCallback) getActivity();
    }
    bottomNavigationCallback.setVisibility(View.GONE);
  }

  @Override
  public void onDetach() {
    super.onDetach();
    bottomNavigationCallback.setVisibility(View.VISIBLE);
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
    alarmViewModel.onDestroy();
  }

  @Override
  public void changeAlarmActivity(Alarm alarm) {
    alarmViewModel.changeActivity(alarm);
  }

  @Override
  public void onAlarmSelected(Alarm alarm) {
    alarmViewModel.selectAlarm(alarm);
    Navigation.findNavController(this.getView()).navigate(R.id.schedulerFragment);
  }
}
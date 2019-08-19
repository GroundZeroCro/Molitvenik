package com.mario.molitvenik.ui.alarm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.mario.molitvenik.R;
import com.mario.molitvenik.data.persistance.alarm.Alarm;
import com.mario.molitvenik.util.AlarmUtils;
import com.mario.molitvenik.util.Daytime;

import java.util.List;

public class AlarmAdapter extends RecyclerView.Adapter<AlarmAdapter.CustomViewHolder> {

  private Context context;
  private AlarmCallback alarmCallback;
  private List<Alarm> alarms;

  AlarmAdapter(Context context, AlarmCallback alarmCallback, List<Alarm> alarms) {
    this.context = context;
    this.alarmCallback = alarmCallback;
    this.alarms = alarms;
  }

  @NonNull
  @Override
  public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    return new CustomViewHolder(LayoutInflater.from(context), parent);
  }

  void updateData(List<Alarm> alarms) {
    this.alarms = alarms;
    notifyDataSetChanged();
  }

  @Override
  public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
    holder.bind(alarms.get(position));
  }

  @Override
  public int getItemCount() {
    return alarms.size();
  }

  class CustomViewHolder extends RecyclerView.ViewHolder {

    private TextView alarmTitle, alarmTime, alarmDays;
    private ImageView alarmIcon;
    private RelativeLayout alarmShadedEffect;
    private SwitchCompat alarmActivitySwitch;

    CustomViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup) {
      super(layoutInflater.inflate(R.layout.item_alarm_scheduler, viewGroup, false));
      this.alarmTitle = itemView.findViewById(R.id.alarm_title);
      this.alarmTime = itemView.findViewById(R.id.alarm_time);
      this.alarmDays = itemView.findViewById(R.id.alarm_days);
      this.alarmIcon = itemView.findViewById(R.id.alarm_icon);
      this.alarmShadedEffect = itemView.findViewById(R.id.alarm_shaded_effect);
      this.alarmActivitySwitch = itemView.findViewById(R.id.alarm_activity_switch);
    }

    void bind(Alarm alarm) {
      this.alarmTitle.setText(alarm.getTitle());
      this.alarmTime.setText(AlarmUtils.getHoursAndMinutes(alarm.getTime()));
      this.alarmDays.setText(AlarmUtils.getDays(context, alarm.getActiveDays()));
      this.alarmIcon.setImageResource(
              AlarmUtils.getDaytime(alarm.getTime()) == Daytime.DAY ?
                      R.drawable.scheduler_sun_svg : R.drawable.scheduler_moon_svg
      );
      this.alarmShadedEffect.setVisibility(AlarmUtils.getShadedEffectVisibility(alarm.isActive()));
      this.alarmActivitySwitch.setChecked(alarm.isActive());
      this.alarmActivitySwitch.setOnClickListener(view -> alarmCallback.changeAlarmActivity(alarm));
      this.itemView.setOnClickListener(view -> alarmCallback.onAlarmSelected(alarm));
    }
  }
}
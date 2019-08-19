package com.mario.molitvenik.data.persistance.alarm;

import android.content.Context;
import android.database.Cursor;
import android.media.RingtoneManager;
import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class RingtoneService {

  private Context context;

  @Inject
  public RingtoneService(Context context) {
    this.context = context;
  }

  public List<Ringtone> getRingtones() {

    List<Ringtone> ringtones = new ArrayList<>();

    RingtoneManager ringtoneManager = new RingtoneManager(context);
    ringtoneManager.setType(RingtoneManager.TYPE_RINGTONE);
    Cursor cursor = ringtoneManager.getCursor();
    while (cursor.moveToNext()) {
      String name = cursor.getString(RingtoneManager.TITLE_COLUMN_INDEX);
      Uri ringtoneUri = ringtoneManager.getRingtoneUri(cursor.getPosition());
      ringtones.add(new Ringtone(name, ringtoneUri));
    }
    return ringtones;
  }
}
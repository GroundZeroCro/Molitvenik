package com.mario.molitvenik.ui.common.toast;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mario.molitvenik.R;

public class ResultToast extends Toast {

  private ImageView img;
  private TextView txt;

  public ResultToast(Context context, Activity activity) {
    super(context);
    LayoutInflater inflater = activity.getLayoutInflater();
    View view = inflater.inflate(R.layout.external_casual_toast, null);
    img = view.findViewById(R.id.casual_toast_icon);
    txt = view.findViewById(R.id.casual_toast_text);
    setGravity(Gravity.BOTTOM, 0, 200);
    setDuration(Toast.LENGTH_LONG);
    setView(view);
  }

  public ResultToast generate(String message, boolean isSuccessful) {
    if (isSuccessful) {
      img.setImageResource(R.drawable.checked_svg);
    } else {
      img.setImageResource(R.drawable.cancel_svg);
    }
    txt.setText(message);

    return this;
  }
}
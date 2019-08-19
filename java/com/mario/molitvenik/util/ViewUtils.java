package com.mario.molitvenik.util;

import android.view.View;
import android.widget.EditText;

public class ViewUtils {

  public static String getInputText(View view) {
    if (view instanceof EditText) {
      return ((EditText) view).getText().toString();
    }
    return null;
  }
}
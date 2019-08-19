package com.mario.molitvenik.ui.live;

import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

class LiveAnimations {

  void animateDeleteButtonEntrance(LinearLayout itemMenu) {
    // TODO implement slide in and slide out animations
    itemMenu.setVisibility(View.VISIBLE);
  }

  void animateDeleteButtonExit(LinearLayout itemMenu) {
    // TODO implement slide in and slide out animations
    itemMenu.setVisibility(View.GONE);
  }
}

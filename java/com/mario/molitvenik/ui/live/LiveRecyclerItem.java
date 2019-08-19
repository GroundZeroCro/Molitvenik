package com.mario.molitvenik.ui.live;

import android.view.View;
import android.widget.LinearLayout;

import com.mario.molitvenik.data.remote.LiveMessage;

public interface LiveRecyclerItem {
  View.OnClickListener onLiveRecyclerItemClick(LiveMessage liveMessage);

  View.OnClickListener deleteMessageClickListener();

  void indicateUserMessage(View view, LinearLayout itemMenu, LiveMessage liveMessage);
}

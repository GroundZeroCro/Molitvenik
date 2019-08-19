package com.mario.molitvenik.ui.prayers;

import android.view.View;

import com.mario.molitvenik.data.common.Prayer;
import com.mario.molitvenik.util.Sort;

public interface PrayersRecyclerItem {
  View.OnClickListener onRecyclerItemClickListener(Prayer prayer);
  Sort sortType();
}

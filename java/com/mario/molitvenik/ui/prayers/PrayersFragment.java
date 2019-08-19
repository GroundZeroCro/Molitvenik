package com.mario.molitvenik.ui.prayers;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.mario.molitvenik.R;
import com.mario.molitvenik.data.handler.Response;
import com.mario.molitvenik.core.base.PrayersBaseFragment;
import com.mario.molitvenik.data.common.PrayerCategory;
import com.mario.molitvenik.data.ui.Tab;
import com.mario.molitvenik.ui.prayers.toolbar.CustomToolbar;
import com.mario.molitvenik.ui.prayers.toolbar.CustomToolbarImp;

import java.util.List;

public class PrayersFragment extends PrayersBaseFragment {

  private RecyclerView prayersRecyclerView;
  private LinearLayout linearTabLayout;
  private CustomToolbar customToolbar;
  private ProgressBar prayersProgressBar;

  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_prayers, container, false);
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    loadViews(view);
    subscribeData();
  }

  private void loadViews(View view) {
    prayersRecyclerView = mutualRecyclerView(view.findViewById(R.id.prayers_recycler_view));
    linearTabLayout = view.findViewById(R.id.linear_tab);
    prayersProgressBar = view.findViewById(R.id.prayers_progress_bar);
  }

  private void subscribeData() {
    // Subscribing to all data.
    prayersViewModel.getTabsLiveData().observe(getViewLifecycleOwner(), response -> {
      switch (response.status) {
        case LOADING:
          progressBarVisibility(prayersProgressBar, View.VISIBLE);
          break;
        case SUCCESS:
          onToolbarCreationSuccess(response);
          progressBarVisibility(prayersProgressBar, View.GONE);
          break;
        case ERROR:
          Toast.makeText(getContext(), getResources().getString(R.string.error_fetching_tab_data), Toast.LENGTH_LONG).show();
          progressBarVisibility(prayersProgressBar, View.GONE);
          break;
        default:
          progressBarVisibility(prayersProgressBar, View.VISIBLE);
      }
    });
    // Subscribing to data selected by user.
    prayersViewModel.getSelectedTabLiveData().observe(getViewLifecycleOwner(), tab -> {
      prayersRecyclerView.setAdapter(getMutualPrayersAdapter(tab.getData().prayers));
      customToolbar.manageTabLayout(tab.getData().getId());
    });
  }

  // TODO LiveFragment possibly has same two methods below onToolbarCreationSuccess() and inflateToolbarWIthTabs();
  private void onToolbarCreationSuccess(Response<Tab<PrayerCategory>> response) {
    customToolbar = new CustomToolbarImp<>(getContext(), response.listData, prayersViewModel, PrayerCategory.class);
    if (response.listData != null) {
      inflateToolbarWithTabs(response.listData);
    }
    if (prayersViewModel.getSelectedTabLiveData().getValue() == null) {
      prayersViewModel.setSelectedTabLiveData(response.listData.get(0));
    }
  }

  private void inflateToolbarWithTabs(List<Tab<PrayerCategory>> tabs) {
    for (Tab<PrayerCategory> tab : tabs) {
      linearTabLayout.addView(tab.getButton());
    }
  }
}
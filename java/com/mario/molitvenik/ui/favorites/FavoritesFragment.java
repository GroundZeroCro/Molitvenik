package com.mario.molitvenik.ui.favorites;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.mario.molitvenik.R;
import com.mario.molitvenik.core.base.PrayersBaseFragment;
import com.mario.molitvenik.data.handler.Status;
import com.mario.molitvenik.ui.live.LiveViewModel;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FavoritesFragment extends PrayersBaseFragment {

  private FavoritesViewModel favoritesViewModel;

  @BindView(R.id.favorites_recycler_view)
  RecyclerView favoritesRecyclerView;
  @BindView(R.id.favorites_progress_bar)
  ProgressBar progressBar;
  @BindView(R.id.no_favorites_warning)
  TextView noFavoritesWarning;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    getApplicationComponent().inject(this);
    favoritesViewModel = ViewModelProviders.of(this, viewModelFactory).get(FavoritesViewModel.class);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_favorites, container, false);
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    ButterKnife.bind(this, view);
    subscribeData();
  }

  private void subscribeData() {
    favoritesViewModel.getFavoriteLivePrayers().observe(getViewLifecycleOwner(), response -> {
      if (response.status == Status.LOADING) {
        progressBar.setVisibility(View.VISIBLE);
      } else if (response.status == Status.SUCCESS) {
        mutualRecyclerView(favoritesRecyclerView).setAdapter(getMutualPrayersAdapter(response.listData));
        progressBar.setVisibility(View.GONE);
        if (response.listData.size() == 0) {
          noFavoritesWarning.setVisibility(View.VISIBLE);
        }
      } else if (response.status == Status.ERROR) {
        resultToast.generate(getResources().getString(R.string.error_fetching_data), false).show();
        progressBar.setVisibility(View.GONE);
      }
    });
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
    favoritesViewModel.onDestroy();
  }
}
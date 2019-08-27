package com.mario.molitvenik.ui.search;


import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.mario.molitvenik.R;
import com.mario.molitvenik.core.base.PrayersBaseFragment;
import com.mario.molitvenik.data.handler.Status;
import com.mario.molitvenik.ui.text.TextViewModel;

import javax.inject.Inject;

public class SearchFragment extends PrayersBaseFragment {

  private SearchViewModel searchViewModel;
  private RecyclerView searchRecyclerView;
  private ProgressBar searchProgressBar;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    getApplicationComponent().inject(this);
    searchViewModel = ViewModelProviders.of(this, viewModelFactory).get(SearchViewModel.class);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_search, container, false);
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    loadViews(view);
    subscribeData();
  }

  private void loadViews(View view) {
    searchRecyclerView = mutualRecyclerView(view.findViewById(R.id.search_recycler_view));
    EditText searchPrayer = view.findViewById(R.id.search_prayer_input);
    searchPrayer.addTextChangedListener(onSearchPrayerTextWatcher());
    searchProgressBar = view.findViewById(R.id.search_progress_bar);
  }

  private TextWatcher onSearchPrayerTextWatcher() {
    return new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
      }

      @Override
      public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        searchViewModel.setPrayersUnsortedLiveData(charSequence);
      }

      @Override
      public void afterTextChanged(Editable editable) {
      }
    };
  }

  private void subscribeData() {
    // Prayers LiveData
    searchViewModel.getPrayersUnsortedLiveData().observe(getViewLifecycleOwner(), data -> {
      if (data.status == Status.LOADING) {
        progressBarVisibility(searchProgressBar, View.VISIBLE);
      } else if (data.status == Status.SUCCESS) {
        searchRecyclerView.setAdapter(getMutualPrayersAdapter(data.listData));
        progressBarVisibility(searchProgressBar, View.GONE);
      } else if (data.status == Status.ERROR) {
        //TODO make this in base fragment
        Toast.makeText(getContext(), getResources().getString(R.string.error_fetching_data), Toast.LENGTH_LONG).show();
        progressBarVisibility(searchProgressBar, View.GONE);
      }
    });
  }
}
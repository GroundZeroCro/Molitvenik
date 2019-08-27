package com.mario.molitvenik.core.base;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mario.molitvenik.R;
import com.mario.molitvenik.data.common.Prayer;
import com.mario.molitvenik.data.json.LocalService;
import com.mario.molitvenik.ui.common.PrayersAdapter;
import com.mario.molitvenik.ui.prayers.PrayersRecyclerItem;
import com.mario.molitvenik.ui.prayers.PrayersViewModel;
import com.mario.molitvenik.util.Sort;

import java.util.List;

import javax.inject.Inject;

public class PrayersBaseFragment extends BaseFragment implements PrayersRecyclerItem {

  @Inject
  LocalService localService;

  private PrayersAdapter prayersAdapter;
  private Sort sortType;
  protected PrayersViewModel prayersViewModel;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    getApplicationComponent().inject(this);
    prayersViewModel = ViewModelProviders.of(getActivity(), viewModelFactory).get(PrayersViewModel.class);
  }

  protected RecyclerView mutualRecyclerView(RecyclerView recyclerView) {
    recyclerView.setHasFixedSize(true);
    RecyclerView.LayoutManager linearLayout = new LinearLayoutManager(getContext());
    recyclerView.setLayoutManager(linearLayout);
    return recyclerView;
  }

  protected PrayersAdapter getMutualPrayersAdapter(List<Prayer> prayers) {
    if (prayersAdapter == null) {
      prayersAdapter = new PrayersAdapter(getContext(), prayers, this);
    } else {
      prayersAdapter.updateData(prayers);
    }
    return prayersAdapter;
  }

  @Override
  public View.OnClickListener onRecyclerItemClickListener(Prayer prayer) {
    return view -> {
      prayersViewModel.setSelectedPrayer(prayer);
      Navigation.findNavController(view).navigate(R.id.textFragment);
    };
  }

  // Used to sort prayers in all recycler views
  public void setSortType(Sort sortType) {
    this.sortType = sortType;
  }

  @Override
  public Sort sortType() {
    if (this.sortType != null) {
      return this.sortType;
    }
    return Sort.TITLE;
  }
}
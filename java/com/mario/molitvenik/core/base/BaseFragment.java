package com.mario.molitvenik.core.base;

import android.os.Bundle;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mario.molitvenik.application.MyApplication;
import com.mario.molitvenik.di.components.ApplicationComponent;
import com.mario.molitvenik.ui.common.ViewModelFactory;
import com.mario.molitvenik.ui.common.dialog.decision.DecisionDialog;
import com.mario.molitvenik.ui.common.toast.ResultToast;

import javax.inject.Inject;

public class BaseFragment extends Fragment {

  protected DecisionDialog decisionDialog;
  protected ResultToast resultToast;

  @Inject
  public ViewModelFactory viewModelFactory;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    decisionDialog = DecisionDialog.instance();
    decisionDialog.setTargetFragment(this, 200);
    resultToast = new ResultToast(getContext(), getActivity());
  }

  protected ApplicationComponent getApplicationComponent() {
    return ((MyApplication) getActivity().getApplication()).getApplicationComponent();
  }

  protected void progressBarVisibility(ProgressBar progressBar, int visibility) {
    progressBar.setVisibility(visibility);
  }

  protected RecyclerView mutualRecyclerView(RecyclerView recyclerView, boolean reversedLayout) {
    recyclerView.setHasFixedSize(true);
    RecyclerView.LayoutManager linearLayout = new LinearLayoutManager(getContext());
    ((LinearLayoutManager) linearLayout).setReverseLayout(reversedLayout);
    recyclerView.setLayoutManager(linearLayout);
    return recyclerView;
  }
}
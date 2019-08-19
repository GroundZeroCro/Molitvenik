package com.mario.molitvenik.ui.common.dialog.decision;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

public class DecisionDialogViewModel extends ViewModel {

  private MutableLiveData<Boolean> isDialogAcceptedLive = new MutableLiveData<>();

  @Inject
  public DecisionDialogViewModel() {
  }

  public void setDialogAccepted(boolean dialogAccepted) {
    isDialogAcceptedLive.setValue(dialogAccepted);
  }

  public LiveData<Boolean> getIsDialogAcceptedLive() {
    return isDialogAcceptedLive;
  }
}

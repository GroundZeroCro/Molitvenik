package com.mario.molitvenik.ui.common.dialog.decision;

import android.content.Context;

import com.mario.molitvenik.R;

public class DecisionRemovePrayerFavorites implements Decision {

  private Context context;

  DecisionRemovePrayerFavorites(Context context) {
    this.context = context;
  }

  @Override
  public void implement(DecisionResponse decisionResponse, boolean isConfirmed) {
    decisionResponse.implementDialogDecision(DecisionType.REMOVE_PRAYER_FROM_FAVORITES, isConfirmed);
  }

  @Override
  public String dialogDecisionTitle() {
    return context.getResources().getString(R.string.dialog_remove_prayer_favorites_title);
  }

  @Override
  public String dialogDecisionMessage() {
    return context.getResources().getString(R.string.dialog_remove_prayer_favorites_message);
  }

  @Override
  public String dialogPositiveButtonText() {
    return context.getResources().getString(R.string.dialog_remove_prayer_favorites_button_yes);
  }

  @Override
  public String dialogNegativeButtonText() {
    return context.getResources().getString(R.string.dialog_remove_prayer_favorites_button_no);
  }
}

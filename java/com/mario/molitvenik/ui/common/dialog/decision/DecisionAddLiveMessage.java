package com.mario.molitvenik.ui.common.dialog.decision;

import android.content.Context;

import com.mario.molitvenik.R;

public class DecisionAddLiveMessage implements Decision {

  private Context context;

  DecisionAddLiveMessage(Context context) {
    this.context = context;
  }

  @Override
  public void implement(DecisionResponse decisionResponse, boolean addItem) {
    decisionResponse.implementDialogDecision(DecisionType.ADD_LIVE_MESSAGE, addItem);
  }

  @Override
  public String dialogDecisionTitle() {
    return context.getResources().getString(R.string.dialog_add_live_message_title);
  }

  @Override
  public String dialogDecisionMessage() {
    return context.getResources().getString(R.string.dialog_add_live_message_message);
  }

  @Override
  public String dialogPositiveButtonText() {
    return context.getResources().getString(R.string.dialog_add_live_message_button_yes);
  }

  @Override
  public String dialogNegativeButtonText() {
    return context.getResources().getString(R.string.dialog_add_live_message_button_no);
  }
}

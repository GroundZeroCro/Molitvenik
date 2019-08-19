package com.mario.molitvenik.ui.common.dialog.decision;

import android.content.Context;

import com.mario.molitvenik.R;

public class DecisionDeleteLiveMessage implements Decision {

  private Context context;

  public DecisionDeleteLiveMessage(Context context) {
    this.context = context;
  }

  @Override
  public void implement(DecisionResponse decisionResponse, boolean deleteItem) {
    decisionResponse.implementDialogDecision(DecisionType.DELETE_LIVE_MESSAGE, deleteItem);
  }

  @Override
  public String dialogDecisionTitle() {
    return context.getResources().getString(R.string.dialog_delete_live_message_title);
  }

  @Override
  public String dialogDecisionMessage() {
    return context.getResources().getString(R.string.dialog_delete_live_message_message);
  }

  @Override
  public String dialogPositiveButtonText() {
    return context.getResources().getString(R.string.dialog_delete_live_message_button_yes);
  }

  @Override
  public String dialogNegativeButtonText() {
    return context.getResources().getString(R.string.dialog_delete_live_message_button_no);
  }
}

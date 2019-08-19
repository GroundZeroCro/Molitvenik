package com.mario.molitvenik.ui.common.dialog.decision;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class DecisionDialog extends DialogFragment {

  private static DecisionDialog decisionDialog;
  private Decision decision;
  private DecisionDialogFactory decisionDialogFactory;

  public static DecisionDialog instance() {
    if (decisionDialog == null) {
      decisionDialog = new DecisionDialog();
    }
    return decisionDialog;
  }

  @NonNull
  @Override
  public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

    DecisionResponse decisionResponse = (DecisionResponse) getTargetFragment();
    decisionDialogFactory = new DecisionDialogFactory(this);
    decision = decisionDialogFactory.takeDecision();

    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
    alertDialogBuilder.setTitle(decision.dialogDecisionTitle());
    alertDialogBuilder.setMessage(decision.dialogDecisionMessage());
    alertDialogBuilder.setPositiveButton(decision.dialogPositiveButtonText(), (dialog, which) ->
            decision.implement(decisionResponse, true));
    alertDialogBuilder.setNegativeButton(decision.dialogNegativeButtonText(), (dialog, which) -> {
      decision.implement(decisionResponse, false);
      dialog.dismiss();
    });
    return alertDialogBuilder.create();
  }
}

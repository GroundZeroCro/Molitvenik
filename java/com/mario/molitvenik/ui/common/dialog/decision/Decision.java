package com.mario.molitvenik.ui.common.dialog.decision;

public interface Decision {
  void implement(DecisionResponse decisionResponse, boolean deleteItem);
  String dialogDecisionTitle();
  String dialogDecisionMessage();
  String dialogPositiveButtonText();
  String dialogNegativeButtonText();
}

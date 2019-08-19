package com.mario.molitvenik.ui.common.dialog.decision;

public enum DecisionType {
  DELETE_LIVE_MESSAGE("delete_live_message"),
  ADD_LIVE_MESSAGE("add_live_message"),
  ADD_PRAYER_TO_FAVORITES("add_prayer_to_favorites"),
  REMOVE_PRAYER_FROM_FAVORITES("add_prayer_from_favorites");

  public final String tag;

  DecisionType(String tag) {
    this.tag = tag;
  }
}
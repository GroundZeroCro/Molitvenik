package com.mario.molitvenik.ui.common.dialog.decision;

import androidx.fragment.app.Fragment;

class DecisionDialogFactory {

  private Fragment fragment;

  DecisionDialogFactory(Fragment fragment) {
    this.fragment = fragment;
  }

  Decision takeDecision() {
    if (fragment.getTag().equals(DecisionType.DELETE_LIVE_MESSAGE.tag)) {
      return new DecisionDeleteLiveMessage(fragment.getContext());
    } else if (fragment.getTag().equals(DecisionType.ADD_LIVE_MESSAGE.tag)) {
      return new DecisionAddLiveMessage(fragment.getContext());
    } else if (fragment.getTag().equals(DecisionType.ADD_PRAYER_TO_FAVORITES.tag)) {
      return new DecisionAddPrayerFavorites(fragment.getContext());
    } else if (fragment.getTag().equals(DecisionType.REMOVE_PRAYER_FROM_FAVORITES.tag)) {
      return new DecisionRemovePrayerFavorites(fragment.getContext());
    }
    return null;
  }
}

package com.mario.molitvenik.data.ui;

import android.view.View;

import com.mario.molitvenik.data.remote.LiveMessage;

// Used to control messages in LiveFragment
public class LiveMenu {
  private LiveMessage selectedLiveMessage;
  private boolean isMenuOpen;

  public LiveMenu() {
    selectedLiveMessage = null;
    isMenuOpen = false;
  }

  public LiveMessage getSelectedLiveMessage() {
    return selectedLiveMessage;
  }

  public boolean isMenuOpen() {
    return isMenuOpen;
  }

  public void setSelectedLiveMessage(LiveMessage selectedLiveMessage) {
    this.selectedLiveMessage = selectedLiveMessage;
  }

  public void setMenuOpen(boolean menuOpen) {
    isMenuOpen = menuOpen;
  }
}

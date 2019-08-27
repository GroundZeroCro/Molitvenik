package com.mario.molitvenik.ui.live;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.mario.molitvenik.data.handler.Response;
import com.mario.molitvenik.core.base.BaseViewModel;
import com.mario.molitvenik.data.persistance.settings.UserSettings;
import com.mario.molitvenik.data.persistance.settings.UserSettingsRepository;
import com.mario.molitvenik.data.remote.LiveMessage;
import com.mario.molitvenik.data.remote.LiveRepository;
import com.mario.molitvenik.data.ui.LiveMenu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;
import javax.inject.Singleton;

// TODO since there are to many messages in database, load paged from firebase if possible
@Singleton
public class LiveViewModel extends BaseViewModel {

  private LiveRepository liveRepository;
  private MutableLiveData<Response<LiveMessage>> liveMessageMutableLiveData = new MutableLiveData<>();
  private MutableLiveData<LiveMenu> liveMenuMutableLiveData = new MutableLiveData<>();
  private LiveMenu liveMenu = new LiveMenu();

  public LiveViewModel(LiveRepository liveRepository, UserSettingsRepository userSettingsRepository) {
    super(userSettingsRepository);
    this.liveRepository = liveRepository;

    liveMessageMutableLiveData.setValue(Response.loading());
    liveRepository.fetchChatLogMessages(new ValueEventListener() {
      @Override
      public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        List<LiveMessage> liveMessages = new ArrayList<>();
        for (DataSnapshot ds : dataSnapshot.getChildren()) {
          LiveMessage liveMessage = ds.getValue(LiveMessage.class);
          liveMessages.add(liveMessage);
        }
        Collections.reverse(liveMessages);
        liveMessageMutableLiveData.setValue(Response.success(liveMessages));
      }

      @Override
      public void onCancelled(@NonNull DatabaseError databaseError) {
        liveMessageMutableLiveData.setValue(Response.error(databaseError.toException()));
      }
    });
  }

  LiveData<Response<LiveMessage>> getLiveMessageMutableLiveData() {
    return liveMessageMutableLiveData;
  }

  void updateLiveMenu(LiveMessage liveMessage) {
    if (isUserMessageOwner(liveMessage)) {
      liveMenu.setMenuOpen(true);
    } else {
      liveMenu.setMenuOpen(false);
    }
    liveMenu.setSelectedLiveMessage(liveMessage);
    liveMenuMutableLiveData.setValue(liveMenu);
  }

  private boolean isUserMessageOwner(LiveMessage liveMessage) {
    return liveMessage.getUserId().equals(userSettingsRepository.getUserSettings().getUserId());
  }

  LiveData<LiveMenu> getLiveMenuMutableLiveData() {
    if (liveMenuMutableLiveData.getValue() == null) {
      liveMenuMutableLiveData.setValue(new LiveMenu());
    }
    return liveMenuMutableLiveData;
  }

  void onMessageSend(String message) {

    if (!userHasId()) {
      giveUserId();
    }
    liveRepository.sendMessage(new LiveMessage(getUserId(), message, new Date()));
  }

  boolean isSelectedMessageIdEqualTo(String messageId) {
    return messageId.equals(getSelectedMessageId());
  }

  private boolean userHasId() {
    return getUserId() != null;
  }

  boolean isSelectedMessageUserIdEqualTo(String messageUserId) {
    return messageUserId.equals(getUserId());
  }

  boolean isMessageSelected() {
    return getLiveMenuMutableLiveData().getValue().getSelectedLiveMessage() != null;
  }

  private String getSelectedMessageId() {
    return getLiveMenuMutableLiveData().getValue().getSelectedLiveMessage().getMessageId();
  }

  private String getUserId() {
    return userSettingsRepository.getUserSettings().getUserId();
  }

  private void giveUserId() {
    UserSettings userSettings = userSettingsRepository.getUserSettings();
    userSettings.setUserId(UUID.randomUUID().toString());
    userSettingsRepository.insertUserSettings(userSettings);
  }

  void onMessageDelete(LiveMessage liveMessage) {
    liveMenu.setMenuOpen(false);
    liveRepository.deleteMessage(liveMessage);
    liveMenuMutableLiveData.setValue(liveMenu); // Closing the LiveItemMenu
  }
}
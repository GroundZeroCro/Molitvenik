package com.mario.molitvenik.data.remote;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import javax.inject.Inject;
import javax.inject.Singleton;

// TODO use return values on these methods in this class
public class LiveRepository {

  private DatabaseReference databaseReference;

  @Inject
  LiveRepository(DatabaseReference databaseReference) {
    this.databaseReference = databaseReference;
  }

  public ValueEventListener fetchChatLogMessages(ValueEventListener valueEventListener) {
    return databaseReference.addValueEventListener(valueEventListener);
  }

  public Task<Void> sendMessage(LiveMessage liveMessage) {
    return databaseReference.child(liveMessage.getMessageId()).setValue(liveMessage);
  }

  public Task<Void> deleteMessage(LiveMessage liveMessage) {
    return databaseReference.child(liveMessage.getMessageId()).removeValue();
  }
}
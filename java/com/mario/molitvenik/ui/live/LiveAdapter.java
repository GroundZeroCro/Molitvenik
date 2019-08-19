package com.mario.molitvenik.ui.live;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mario.molitvenik.R;
import com.mario.molitvenik.data.remote.LiveMessage;

import java.util.Collections;
import java.util.List;
// TODO test time sort if mobile phone is in different time zone.
public class LiveAdapter extends RecyclerView.Adapter<LiveAdapter.LiveViewHolder> {

  private Context context;
  private List<LiveMessage> liveMessages;
  private LiveRecyclerItem liveRecyclerItem;

  LiveAdapter(Context context, List<LiveMessage> liveMessages, LiveRecyclerItem liveRecyclerItem) {
    this.context = context;
    sortByDate(liveMessages);
    this.liveMessages = liveMessages;
    this.liveRecyclerItem = liveRecyclerItem;
  }

  void updateData(List<LiveMessage> liveMessages) {
    sortByDate(liveMessages);
    this.liveMessages = liveMessages;
    notifyDataSetChanged();
  }

  private void sortByDate(List<LiveMessage> unsortedList) {
    Collections.sort(unsortedList, (liveMessage1, liveMessage2) -> liveMessage2.getPostedAt().compareTo(liveMessage1.getPostedAt()));
  }

  @NonNull
  @Override
  public LiveViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    LayoutInflater inflater = LayoutInflater.from(context);
    return new LiveViewHolder(inflater, parent);
  }

  @Override
  public void onBindViewHolder(@NonNull LiveViewHolder holder, int position) {
    holder.bind(liveMessages.get(position));
  }

  @Override
  public int getItemCount() {
    return liveMessages.size();
  }

  class LiveViewHolder extends RecyclerView.ViewHolder {

    private TextView message;
    private ImageButton deleteMessageButton;
    private LinearLayout itemMenu;

    LiveViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup) {
      super(layoutInflater.inflate(R.layout.item_live_message_recycler_view, viewGroup, false));
      message = itemView.findViewById(R.id.message);
      deleteMessageButton = itemView.findViewById(R.id.delete_message_button);
      itemMenu = itemView.findViewById(R.id.live_item_menu_parent);
    }

    void bind(LiveMessage liveMessage) {
      message.setText(liveMessage.getMessage());
      itemView.setTag(liveMessage.getMessageId());
      itemView.setOnClickListener(liveRecyclerItem.onLiveRecyclerItemClick(liveMessage));
      liveRecyclerItem.indicateUserMessage(itemView, itemMenu, liveMessage);
      deleteMessageButton.setOnClickListener(liveRecyclerItem.deleteMessageClickListener());
    }
  }
}

package com.mario.molitvenik.ui.common;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mario.molitvenik.R;
import com.mario.molitvenik.data.common.Prayer;
import com.mario.molitvenik.ui.prayers.PrayersRecyclerItem;
import com.mario.molitvenik.util.SortUtils;

import java.util.List;

public class PrayersAdapter extends RecyclerView.Adapter<PrayersAdapter.PrayersViewHolder> {

  private Context context;
  private List<Prayer> prayers;
  private PrayersRecyclerItem prayersRecyclerItem;

  public PrayersAdapter(Context context, List<Prayer> prayers, PrayersRecyclerItem prayersRecyclerItem) {
    this.context = context;
    this.prayers = SortUtils.sortList(prayersRecyclerItem.sortType(), prayers);
    this.prayersRecyclerItem = prayersRecyclerItem;
  }

  public void updateData(List<Prayer> prayers) {
    this.prayers = SortUtils.sortList(prayersRecyclerItem.sortType(), prayers);
    notifyDataSetChanged();
  }

  @NonNull
  @Override
  public PrayersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    LayoutInflater inflater = LayoutInflater.from(context);
    return new PrayersViewHolder(inflater, parent);
  }

  @Override
  public void onBindViewHolder(@NonNull PrayersViewHolder holder, int position) {
    holder.bind(prayers.get(position));
  }

  @Override
  public int getItemCount() {
    return prayers.size();
  }

  class PrayersViewHolder extends RecyclerView.ViewHolder {

    private TextView prayerTitle, prayerText, prayerLength;

    PrayersViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup) {
      super(layoutInflater.inflate(R.layout.item_prayers_recycler_view, viewGroup, false));

      prayerTitle = itemView.findViewById(R.id.prayer_title);
      prayerText = itemView.findViewById(R.id.prayer_text);
      prayerLength = itemView.findViewById(R.id.prayer_length);
    }

    void bind(Prayer prayer) {
      prayerTitle.setText(prayer.getTitle());
      prayerText.setText(prayer.getText());
      prayerLength.setText(context.getResources().getString(R.string.prayer_length, getPrayerLength(prayerText.getText())));

      itemView.setOnClickListener(prayersRecyclerItem.onRecyclerItemClickListener(prayer));
    }

    private String getPrayerLength(CharSequence text) {
      return String.valueOf(text.length() > 0 ? text.length() : 0);
    }
  }
}

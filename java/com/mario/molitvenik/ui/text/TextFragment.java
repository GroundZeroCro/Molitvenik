package com.mario.molitvenik.ui.text;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.mario.molitvenik.R;
import com.mario.molitvenik.core.base.PrayersBaseFragment;
import com.mario.molitvenik.data.common.Prayer;
import com.mario.molitvenik.data.persistance.settings.UserSettings;
import com.mario.molitvenik.ui.common.dialog.decision.DecisionResponse;
import com.mario.molitvenik.ui.common.dialog.decision.DecisionType;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TextFragment extends PrayersBaseFragment implements DecisionResponse {

  @BindView(R.id.title)
  TextView title;
  @BindView(R.id.text)
  TextView text;
  @BindView(R.id.text_size_seek_bar)
  SeekBar textSizeSeekBarController;
  @BindView(R.id.text_controller)
  ImageButton textController;
  @BindView(R.id.add_to_favorites_button)
  LinearLayout addToFavoritesButton;
  @BindView(R.id.add_to_favorites_text)
  TextView addToFavoritesText;
  @BindView(R.id.remove_from_favorites_button)
  LinearLayout removeFromFavoritesButton;
  @BindView(R.id.remove_from_favorites_text)
  TextView removeFromFavoritesText;

  @Inject
  TextViewModel textViewModel;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    getApplicationComponent().inject(this);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_text, container, false);
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    ButterKnife.bind(this, view);
    loadViews();
    subscribeData();
  }

  private void loadViews() {
    loadTextSizeListener();
    loadTextSettingsControllerListener();
    loadAddToFavoritesListener();
    loadRemoveFromFavoritesListener();
  }

  private void loadTextSizeListener() {
    textSizeSeekBarController.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
      @Override
      public void onProgressChanged(SeekBar seekBar, int textSize, boolean b) {
        textViewModel.changeTextSize(textSize);
      }

      @Override
      public void onStartTrackingTouch(SeekBar seekBar) {

      }

      @Override
      public void onStopTrackingTouch(SeekBar seekBar) {
        changeControllerVisibility();
      }
    });
  }

  private void loadTextSettingsControllerListener() {
    textController.setOnClickListener(view -> changeControllerVisibility());
  }

  private void changeControllerVisibility() {
    UserSettings userSettings = textViewModel.getUserSettingsMutableLiveData().getValue();
    userSettings.setControllerVisible(!userSettings.isControllerVisible());
    textViewModel.setUserSettingsMutableLiveData(userSettings);
    updateTextSizeView(userSettings);
  }

  private void loadAddToFavoritesListener() {
    addToFavoritesButton.setOnClickListener(view -> decisionDialog.show(getFragmentManager(), DecisionType.ADD_PRAYER_TO_FAVORITES.tag));
  }

  private void loadRemoveFromFavoritesListener() {
    removeFromFavoritesButton.setOnClickListener(view -> decisionDialog.show(getFragmentManager(), DecisionType.REMOVE_PRAYER_FROM_FAVORITES.tag));
  }

  @Override
  public void implementDialogDecision(DecisionType decisionType, boolean isConfirmed) {
    if (isConfirmed) {
      if (decisionType == DecisionType.ADD_PRAYER_TO_FAVORITES) {
        textViewModel.setPrayerToFavoriteLive(true);
        textViewModel.addPrayerToFavorites(prayersViewModel.getSelectedPrayerLiveData().getValue());
        resultToast.generate(getResources().getString(R.string.toast_prayer_added_to_favorites), true).show();
      } else if (decisionType == DecisionType.REMOVE_PRAYER_FROM_FAVORITES) {
        textViewModel.setPrayerToFavoriteLive(false);
        textViewModel.removePrayerFromFavorites(prayersViewModel.getSelectedPrayerLiveData().getValue().getText());
        resultToast.generate(getResources().getString(R.string.toast_prayer_removed_from_favorites), true).show();
      }
    }
  }

  private void subscribeData() {
    // Prayer LiveData
    prayersViewModel.getSelectedPrayerLiveData().observe(getViewLifecycleOwner(), this::showData);
    // UI LiveData
    textViewModel.getUserSettingsMutableLiveData().observe(getViewLifecycleOwner(), userSettings -> {
      setControllerVisibility(userSettings);
      updateTextSizeView(userSettings);
    });
    // Add/Remove prayer from favorites button
    textViewModel.getIsPrayerFavoriteLive(prayersViewModel.getSelectedPrayerLiveData().getValue().getText())
            .observe(getViewLifecycleOwner(), this::controlFavoriteButtonVisibility);
  }

  private void controlFavoriteButtonVisibility(boolean isPrayerFavorite) {
    if (isPrayerFavorite) {
      addToFavoritesButton.setVisibility(View.GONE);
      removeFromFavoritesButton.setVisibility(View.VISIBLE);
    } else {
      addToFavoritesButton.setVisibility(View.VISIBLE);
      removeFromFavoritesButton.setVisibility(View.GONE);
    }
  }

  private void showData(Prayer prayer) {
    title.setText(prayer.getTitle());
    text.setText(prayer.getText());
  }

  private void setControllerVisibility(UserSettings userSettings) {
    textController.setVisibility(userSettings.isControllerVisible() ? View.GONE : View.VISIBLE);
    textSizeSeekBarController.setVisibility(userSettings.isControllerVisible() ? View.VISIBLE : View.GONE);
  }

  private void updateTextSizeView(UserSettings userSettings) {
    textSizeSeekBarController.setProgress((int) (userSettings.getTextSize() - UserSettings.TEXT_SIZE_INCREASE));
    title.setTextSize(userSettings.getTextSize());
    text.setTextSize(userSettings.getTextSize());
    addToFavoritesText.setTextSize(userSettings.getTextSize());
    removeFromFavoritesText.setTextSize(userSettings.getTextSize());
  }
}
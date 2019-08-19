package com.mario.molitvenik.ui.live;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.mario.molitvenik.R;
import com.mario.molitvenik.core.base.BaseFragment;
import com.mario.molitvenik.data.remote.LiveMessage;
import com.mario.molitvenik.ui.common.dialog.decision.DecisionResponse;
import com.mario.molitvenik.ui.common.dialog.decision.DecisionType;
import com.mario.molitvenik.util.AndroidUtils;
import com.mario.molitvenik.util.ViewUtils;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LiveFragment extends BaseFragment implements LiveRecyclerItem, DecisionResponse {

  @BindView(R.id.live_progress_bar)
  ProgressBar liveProgressBar;
  @BindView(R.id.send_message_input)
  EditText sendMessageInput;
  @BindView(R.id.send_message_button)
  ImageButton sendMessageButton;

  private RecyclerView liveRecyclerView;
  private LiveAdapter liveAdapter;
  private LiveAnimations liveAnimations = new LiveAnimations();

  @Inject
  LiveViewModel liveViewModel;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    getApplicationComponent().inject(this);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    return inflater.inflate(R.layout.fragment_live, container, false);
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    ButterKnife.bind(this, view);
    setViewState(view);
    liveAdapter = new LiveAdapter(getContext(), new ArrayList<>(), this);
    liveRecyclerView.setAdapter(liveAdapter);
    subscribeLiveData();
  }

  private void setViewState(View view) {
    liveRecyclerView = mutualRecyclerView(view.findViewById(R.id.live_recycler_view), true);
    sendMessageButton.setOnClickListener(sendMessageClickListener());
  }

  private void subscribeLiveData() {
    // All messages
    liveViewModel.getLiveMessageMutableLiveData().observe(getViewLifecycleOwner(), data -> {
      switch (data.status) {
        case LOADING:
          progressBarVisibility(liveProgressBar, View.VISIBLE);
          break;
        case SUCCESS:
          liveAdapter.updateData(data.listData);
          progressBarVisibility(liveProgressBar, View.GONE);
          break;
        case ERROR:
          Toast.makeText(getContext(), getResources().getString(R.string.error_fetching_data), Toast.LENGTH_LONG).show();
          progressBarVisibility(liveProgressBar, View.GONE);
          break;
        default:
          progressBarVisibility(liveProgressBar, View.VISIBLE);
      }
    });
    // User selected message
    liveViewModel.getLiveMenuMutableLiveData().observe(getViewLifecycleOwner(), liveMenu -> {
      if (liveViewModel.getLiveMessageMutableLiveData().getValue().listData != null) {
        liveAdapter.updateData(liveViewModel.getLiveMessageMutableLiveData().getValue().listData);
      }
    });
  }

  private View.OnClickListener sendMessageClickListener() {
    return view -> {
      decisionDialog.show(getFragmentManager(), DecisionType.ADD_LIVE_MESSAGE.tag);
    };
  }

  @Override
  public View.OnClickListener deleteMessageClickListener() {
    return view -> {
      decisionDialog.show(getFragmentManager(), DecisionType.DELETE_LIVE_MESSAGE.tag);
    };
  }

  @Override
  public void implementDialogDecision(DecisionType decisionType, boolean isConfirmed) {
    if (isConfirmed) {
      switch (decisionType) {
        case DELETE_LIVE_MESSAGE:
          liveViewModel.onMessageDelete(liveViewModel.getLiveMenuMutableLiveData().getValue().getSelectedLiveMessage());
          resultToast.generate(getResources().getString(R.string.toast_live_message_deleted), true).show();
          break;
        case ADD_LIVE_MESSAGE:
          liveViewModel.onMessageSend(ViewUtils.getInputText(sendMessageInput));
          AndroidUtils.hideKeyboard(getActivity());
          sendMessageInput.setText("");
          sendMessageInput.clearFocus();
          resultToast.generate(getResources().getString(R.string.toast_live_message_added), true).show();
          break;
      }
    }
  }

  @Override
  public View.OnClickListener onLiveRecyclerItemClick(LiveMessage liveMessage) {
    return view -> liveViewModel.updateLiveMenu(liveMessage);
  }

  @Override
  public void indicateUserMessage(View itemView, LinearLayout itemMenu, LiveMessage liveMessage) {
    if (liveViewModel.isSelectedMessageUserIdEqualTo(liveMessage.getUserId())) {
      itemView.setBackground(getResources().getDrawable(R.drawable.item_prayer_list_underline_background_green));
    } else {
      itemView.setBackground(getResources().getDrawable(R.drawable.item_prayer_list_underline_background_white));
    }

    liveAnimations.animateDeleteButtonExit(itemMenu);

    if (liveViewModel.isMessageSelected()) {
      if (liveViewModel.isSelectedMessageIdEqualTo(liveMessage.getMessageId())) {
        itemView.setBackground(getResources().getDrawable(R.drawable.item_prayer_list_underline_background_green_selected));
        if (liveViewModel.isSelectedMessageUserIdEqualTo(liveMessage.getUserId()) && itemMenu.getVisibility() == View.GONE) {
          itemMenu.setVisibility(View.VISIBLE);
          liveAnimations.animateDeleteButtonEntrance(itemMenu);
        }
      }
    }
  }
}
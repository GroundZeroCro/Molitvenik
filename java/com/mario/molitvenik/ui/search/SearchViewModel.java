package com.mario.molitvenik.ui.search;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mario.molitvenik.data.handler.Response;
import com.mario.molitvenik.data.common.Prayer;
import com.mario.molitvenik.data.json.LocalRepository;

import javax.inject.Inject;

public class SearchViewModel extends ViewModel {

  private MutableLiveData<Response<Prayer>> prayersUnsortedLiveData = new MutableLiveData<>();

  private LocalRepository localRepository;

  public SearchViewModel(LocalRepository localRepository) {
    this.localRepository = localRepository;
  }

  void setPrayersUnsortedLiveData(CharSequence inputKey) {
    prayersUnsortedLiveData.setValue(Response.loading());
    if (this.localRepository.getPrayersSorted(inputKey) == null) {
      prayersUnsortedLiveData.setValue(Response.error(new Throwable()));
    } else {
      prayersUnsortedLiveData.setValue(Response.success(this.localRepository.getPrayersSorted(inputKey)));
    }

  }

  LiveData<Response<Prayer>> getPrayersUnsortedLiveData() {
    setPrayersUnsortedLiveData("");
    return prayersUnsortedLiveData;
  }
}
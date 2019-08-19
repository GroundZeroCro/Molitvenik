package com.mario.molitvenik.ui.favorites;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.mario.molitvenik.data.common.Prayer;
import com.mario.molitvenik.data.handler.Response;
import com.mario.molitvenik.data.persistance.favorites.FavoritesRepository;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class FavoritesViewModel extends ViewModel {

  private MutableLiveData<Response<Prayer>> favoritePrayersLive = new MutableLiveData<>();
  private CompositeDisposable disposables = new CompositeDisposable();

  private FavoritesRepository favoritesRepository;

  @Inject
  public FavoritesViewModel(FavoritesRepository favoritesRepository) {
    this.favoritesRepository = favoritesRepository;
  }

  LiveData<Response<Prayer>> getFavoriteLivePrayers() {
    disposables.add(favoritesRepository.getFavoritePrayers()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe(disposable -> favoritePrayersLive.setValue(Response.loading()))
            .subscribe(
                    prayers -> favoritePrayersLive.setValue(Response.success(prayers)),
                    throwable -> favoritePrayersLive.setValue(Response.error(throwable)))
    );
    return favoritePrayersLive;
  }

  void onDestroy() {
    disposables.clear();
  }
}
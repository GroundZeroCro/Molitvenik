package com.mario.molitvenik.data.handler;

import androidx.annotation.Nullable;

import java.util.List;

public class Response<T> {

  public final Status status;
  @Nullable
  public final List<T> listData;
  @Nullable
  private final Throwable throwable;

  private Response(Status status, @Nullable List<T> listData, @Nullable Throwable throwable) {
    this.status = status;
    this.listData = listData;
    this.throwable = throwable;
  }

  public static <T> Response<T> loading() {
    return new Response<>(Status.LOADING, null, null);
  }

  public static <T> Response<T> success(List<T> data) {
    return new Response<>(Status.SUCCESS, data, null);
  }

  public static <T> Response<T> error(Throwable throwable) {
    return new Response<>(Status.ERROR, null, throwable);
  }
}
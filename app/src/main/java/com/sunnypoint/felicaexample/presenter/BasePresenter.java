package com.sunnypoint.felicaexample.presenter;

/**
 * Created by NhanCao on 07-Oct-15.
 */
public interface BasePresenter<V> {
    void onStart(V view);

    void onStop();
}

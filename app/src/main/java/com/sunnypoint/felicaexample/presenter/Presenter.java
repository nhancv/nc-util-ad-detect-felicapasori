package com.sunnypoint.felicaexample.presenter;

import com.sunnypoint.felicaexample.utils.RxHelper;

import rx.Observable;
import rx.Subscription;
import rx.functions.Func0;

/**
 * Created by NhanCao on 07-Oct-15.
 */
public class Presenter implements BasePresenter {

    private Subscription subscription;

    @Override
    public void onStart(Object view) {
        subscription = Observable.defer(new Func0<Observable<Object>>() {
            @Override
            public Observable<Object> call() {
                return null;
            }
        })
                .compose(RxHelper.applySchedulers())
                .subscribe();
    }

    @Override
    public void onStop() {
        if (subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }
}

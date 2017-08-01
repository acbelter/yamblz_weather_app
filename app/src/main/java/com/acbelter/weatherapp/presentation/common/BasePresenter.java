package com.acbelter.weatherapp.presentation.common;

import com.arellomobile.mvp.MvpPresenter;
import com.arellomobile.mvp.MvpView;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class BasePresenter<V extends MvpView> extends MvpPresenter<V> {

    private CompositeDisposable disposable = new CompositeDisposable();

    public void unsubscribeOnDetach(Disposable... disposables) {
        disposable.addAll(disposables);
    }

    public void onDetach() {
        disposable.clear();
    }
}

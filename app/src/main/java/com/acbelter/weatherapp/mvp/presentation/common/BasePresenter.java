package com.acbelter.weatherapp.mvp.presentation.common;

import android.support.annotation.Nullable;

import com.acbelter.weatherapp.mvp.view.common.BaseView;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class BasePresenter<V extends BaseView> {

    private V view;

    private CompositeDisposable disposable = new CompositeDisposable();

    public void unSubscribeOnDetach(Disposable... disposables) {
        disposable.addAll(disposables);
    }

    public void onAttach(V view) {
        this.view = view;
    }

    public void onDetach() {
        disposable.clear();
        this.view = null;
    }

    protected
    @Nullable
    V getView() {
        return view;
    }
}

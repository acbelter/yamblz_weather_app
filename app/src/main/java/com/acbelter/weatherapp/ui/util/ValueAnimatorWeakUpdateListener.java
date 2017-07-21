package com.acbelter.weatherapp.ui.util;

import android.animation.ValueAnimator;

import java.lang.ref.WeakReference;

public abstract class ValueAnimatorWeakUpdateListener<T> implements ValueAnimator.AnimatorUpdateListener {
    private WeakReference<T> mViewWeakRef;

    public ValueAnimatorWeakUpdateListener(T view) {
        mViewWeakRef = new WeakReference<>(view);
    }

    @Override
    public void onAnimationUpdate(ValueAnimator animation) {
        T view = mViewWeakRef.get();
        if (view != null) {
            onAnimationUpdated(animation, view);
        }
    }

    public abstract void onAnimationUpdated(ValueAnimator animation, T view);
}

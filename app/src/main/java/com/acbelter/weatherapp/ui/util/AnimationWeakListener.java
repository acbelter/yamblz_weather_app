package com.acbelter.weatherapp.ui.util;

import android.view.animation.Animation;

import java.lang.ref.WeakReference;

public abstract class AnimationWeakListener<T> implements Animation.AnimationListener {
    private WeakReference<T> mViewWeakRef;

    public AnimationWeakListener(T view) {
        mViewWeakRef = new WeakReference<>(view);
    }

    @Override
    public void onAnimationStart(Animation animation) {
        T view = mViewWeakRef.get();
        if (view != null) {
            onAnimationStarted(animation, view);
        }
    }

    @Override
    public void onAnimationEnd(Animation animation) {
        T view = mViewWeakRef.get();
        if (view != null) {
            onAnimationEnded(animation, view);
        }
    }

    @Override
    public void onAnimationRepeat(Animation animation) {
        T view = mViewWeakRef.get();
        if (view != null) {
            onAnimationRepeated(animation, view);
        }
    }

    public abstract void onAnimationStarted(Animation animation, T view);

    public abstract void onAnimationEnded(Animation animation, T view);

    public abstract void onAnimationRepeated(Animation animation, T view);
}

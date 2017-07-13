package com.acbelter.weatherapp.ui.weather;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.acbelter.weatherapp.App;
import com.acbelter.weatherapp.R;
import com.acbelter.weatherapp.domain.model.WeatherData;
import com.acbelter.weatherapp.presentation.WeatherPresenter;
import com.acbelter.weatherapp.ui.SimpleAnimationListener;
import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import java.lang.ref.WeakReference;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import timber.log.Timber;
import xyz.matteobattilana.library.Common.Constants;

public class WeatherFragment extends MvpAppCompatFragment implements WeatherView {
    @Inject
    @InjectPresenter
    WeatherPresenter mPresenter;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.content_layout)
    ViewGroup mContentLayout;
    @BindView(R.id.date_text)
    TextView mDateText;
    @BindView(R.id.temperature_text)
    TextView mTemperatureText;
    @BindView(R.id.units_text)
    TextView mUnitsText;
    @BindView(R.id.location_text)
    TextView mLocationText;
    @BindView(R.id.weather_view)
    xyz.matteobattilana.library.WeatherView mWeatherView;
    @BindView(R.id.weather_image)
    ImageView mWeatherImage;
    private Unbinder mUnbinder;
    private WeatherData mWeatherData;
    private WeatherStateRes mWeatherStateRes;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        App.getComponentManager().addWeatherComponent().inject(this);
        super.onCreate(savedInstanceState);
        Timber.d("Add weather component");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Timber.d("Save weather fragment state");
        if (mWeatherData != null) {
            outState.putParcelable("weather_data", mWeatherData);
        }
        if (mWeatherStateRes != null) {
            outState.putString("weather_state_res", mWeatherStateRes.name());
        }
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        Timber.d("Restore weather fragment state");
        if (savedInstanceState != null) {
            mWeatherData = savedInstanceState.getParcelable("weather_data");
            String weatherStateResName = savedInstanceState.getString("weather_state_res");
            if (weatherStateResName != null) {
                mWeatherStateRes = WeatherStateRes.valueOf(weatherStateResName);
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        App.getComponentManager().removeWeatherComponent();
        Timber.d("Remove weather component");
        mUnbinder.unbind();
    }

    @ProvidePresenter
    public WeatherPresenter provideWeatherPresenter() {
        return mPresenter;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather, container, false);
        mUnbinder = ButterKnife.bind(this, view);

        mSwipeRefreshLayout.setOnRefreshListener(() -> {
            mPresenter.getWeather();
        });

        mSwipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        mWeatherView.setWeather(Constants.weatherStatus.SUN)
                .setCurrentLifeTime(5000)
                .setCurrentParticles(15)
                .setFPS(60)
                .setCurrentAngle(-10)
                .setOrientationMode(Constants.orientationStatus.ENABLE);

        return view;
    }

    private void showWeather(Context context, WeatherData newWeatherData) {
        // FIXME For testing
        WeatherStateRes newWeatherStateRes = WeatherUtils.getRandomStateRes();
        Timber.d("Change weather state res: " + mWeatherStateRes + "->" + newWeatherStateRes);

        int textColor = newWeatherStateRes.getTextColor(context);
        setWeatherTextColor(textColor);

        mDateText.setText(newWeatherData.date);
        mTemperatureText.setText(newWeatherData.temperature);
        mUnitsText.setText(R.string.celsius);
        mLocationText.setText(newWeatherData.location);

        int colorFrom;
        int colorTo = newWeatherStateRes.getBackgroundColor(context);
        final long animBgDuration = context.getResources().getInteger(R.integer.anim_bg_duration);

        WeakReference<xyz.matteobattilana.library.WeatherView> weatherViewWeakRef =
                new WeakReference<>(mWeatherView);
        WeakReference<ImageView> weatherImageWeakRef = new WeakReference<>(mWeatherImage);
        WeakReference<ViewGroup> contentLayoutWeakRef = new WeakReference<>(mContentLayout);

        if (mWeatherData == null || mWeatherStateRes == null) {
            // Show weather first time
            colorFrom = ContextCompat.getColor(context, R.color.colorBgWeatherDefault);
            mWeatherImage.setImageResource(newWeatherStateRes.getWeatherImageResId());

            Animation fadeIn = AnimationUtils.loadAnimation(context, R.anim.anim_fade_in);
            fadeIn.setAnimationListener(new SimpleAnimationListener() {
                @Override
                public void onAnimationEnd(Animation animation) {
                    xyz.matteobattilana.library.WeatherView weatherView = weatherViewWeakRef.get();
                    if (weatherView != null) {
                        weatherView.setWeather(WeatherUtils.getWeatherStatus(newWeatherStateRes));
                        weatherView.startAnimation();
                    }
                }
            });
            mWeatherImage.startAnimation(fadeIn);

            ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), colorFrom, colorTo);
            colorAnimation.setDuration(animBgDuration);
            colorAnimation.addUpdateListener(animator -> {
                ViewGroup contentLayout = contentLayoutWeakRef.get();
                if (contentLayout != null) {
                    contentLayout.setBackgroundColor((int) animator.getAnimatedValue());
                }
            });
            colorAnimation.start();
        } else if (mWeatherStateRes != newWeatherStateRes) {
            // Replace weather
            Animation fadeOut = AnimationUtils.loadAnimation(context, R.anim.anim_fade_out);
            Animation fadeIn = AnimationUtils.loadAnimation(context, R.anim.anim_fade_in);
            fadeOut.setAnimationListener(new SimpleAnimationListener() {
                @Override
                public void onAnimationEnd(Animation animation) {
                    ImageView weatherImage = weatherImageWeakRef.get();
                    if (weatherImage != null) {
                        weatherImage.setImageResource(newWeatherStateRes.getWeatherImageResId());
                        weatherImage.startAnimation(fadeIn);
                    }
                }
            });
            fadeIn.setAnimationListener(new SimpleAnimationListener() {
                @Override
                public void onAnimationEnd(Animation animation) {
                    xyz.matteobattilana.library.WeatherView weatherView = weatherViewWeakRef.get();
                    if (weatherView != null) {
                        weatherView.setWeather(WeatherUtils.getWeatherStatus(newWeatherStateRes));
                        weatherView.startAnimation();
                    }
                }
            });
            mWeatherImage.startAnimation(fadeOut);

            colorFrom = mWeatherStateRes.getBackgroundColor(context);
            mWeatherView.cancelAnimation();

            ValueAnimator colorAnimation = ValueAnimator.ofObject(new ArgbEvaluator(), colorFrom, colorTo);
            colorAnimation.setDuration(animBgDuration);
            colorAnimation.addUpdateListener(animator -> {
                ViewGroup contentLayout = contentLayoutWeakRef.get();
                if (contentLayout != null) {
                    contentLayout.setBackgroundColor((int) animator.getAnimatedValue());
                }
            });
            colorAnimation.start();
        }

        mWeatherData = newWeatherData;
        mWeatherStateRes = newWeatherStateRes;
    }

    private void setWeatherTextColor(int color) {
        mDateText.setTextColor(color);
        mTemperatureText.setTextColor(color);
        mUnitsText.setTextColor(color);
        mLocationText.setTextColor(color);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mWeatherData != null && mWeatherStateRes != null) {
            mWeatherView.startAnimation();
        }
        // FIXME For testing
        mPresenter.getWeather();
    }

    @Override
    public void onPause() {
        super.onPause();
        mWeatherView.cancelAnimation();
    }

    @Override
    public void showWeatherLoading() {
        if (mSwipeRefreshLayout.isRefreshing()) {
            return;
        }
        mSwipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void showWeather(List<WeatherData> weatherDataList) {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showError() {
        mSwipeRefreshLayout.setRefreshing(false);
        // FIXME For testing
        showWeather(getContext(), WeatherData.newDebugInstance());
//        Snackbar errorSnackbar =
//                Snackbar.make(mContentLayout, R.string.text_weather_error, Snackbar.LENGTH_LONG);
//        errorSnackbar.setAction(R.string.ok, v -> errorSnackbar.dismiss());
//        errorSnackbar.show();
    }

    public static String tag() {
        return WeatherFragment.class.getSimpleName();
    }
}

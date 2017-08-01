package com.acbelter.weatherapp.ui.weather;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
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
import com.acbelter.weatherapp.domain.model.weather.WeatherData;
import com.acbelter.weatherapp.presentation.WeatherPresenter;
import com.acbelter.weatherapp.ui.util.AnimationWeakListener;
import com.acbelter.weatherapp.ui.util.ValueAnimatorWeakUpdateListener;
import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import timber.log.Timber;
import xyz.matteobattilana.library.Common.Constants;

public class WeatherFragment extends MvpAppCompatFragment implements WeatherView {
    private SimpleDateFormat mDateFormat =
            new SimpleDateFormat("dd MMM yyyy HH:mm:ss", Locale.getDefault());

    @Inject
    @InjectPresenter
    WeatherPresenter mPresenter;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.content_layout)
    ViewGroup mContentLayout;
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
    private long mAnimBgDuration;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        App.getComponentManager().addWeatherComponent().inject(this);
        super.onCreate(savedInstanceState);
        Timber.d("Add weather component");
        mAnimBgDuration = getContext().getResources().getInteger(R.integer.anim_bg_duration);
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

        mSwipeRefreshLayout.setColorSchemeResources(
                R.color.colorPrimary,
                R.color.colorPrimaryDark);

        mSwipeRefreshLayout.setOnRefreshListener(() -> mPresenter.getCachedWeather());

        mWeatherView.setWeather(Constants.weatherStatus.SUN)
                .setRainTime(6000)
                .setSnowTime(6000)
                .setRainAngle(20)
                .setSnowAngle(20)
                .setRainParticles(25)
                .setSnowParticles(25)
                .setFPS(60)
                .setOrientationMode(Constants.orientationStatus.ENABLE);

        return view;
    }

    @SuppressLint("SetTextI18n")
    private void showNewWeather(Context context, WeatherData newWeatherData) {
        Timber.d("Show new weather for timestamp: %s", newWeatherData.getTimestamp());
        WeatherRes newWeatherRes = new WeatherRes(newWeatherData);
        setWeatherTextColor(context, newWeatherRes.getTextColorResId());
        mTemperatureText.setText(Integer.toString((int) newWeatherData.getTemperatureC()));
        mUnitsText.setText(R.string.celsius);
        mLocationText.setText(newWeatherData.getCity());
        WeatherRes currentWeatherRes = new WeatherRes(newWeatherData);
        if (!newWeatherRes.equals(currentWeatherRes)) {
            replaceWeather(context, currentWeatherRes, newWeatherRes);
        } else {
            Timber.d("Weather res is not changed");
            mContentLayout.setBackgroundColor(
                    ContextCompat.getColor(context, newWeatherRes.getBackgroundColorResId()));
            mWeatherImage.setImageResource(newWeatherRes.getWeatherImageResId());
            mWeatherView.cancelAnimation();
            mWeatherView.setWeather(newWeatherRes.getWeatherStatus());
            mWeatherView.startAnimation();
        }
    }

    private void showWeatherFirstTime(Context context, WeatherRes newWeatherRes) {
        Timber.d("Show weather first time");

        mWeatherImage.setImageResource(newWeatherRes.getWeatherImageResId());

        Animation fadeIn = AnimationUtils.loadAnimation(context, R.anim.fade_in);
        fadeIn.setAnimationListener(
                new AnimationWeakListener<xyz.matteobattilana.library.WeatherView>(mWeatherView) {
                    @Override
                    public void onAnimationStarted(Animation animation,
                                                   xyz.matteobattilana.library.WeatherView view) {
                    }

                    @Override
                    public void onAnimationEnded(Animation animation,
                                                 xyz.matteobattilana.library.WeatherView view) {
                        view.setWeather(newWeatherRes.getWeatherStatus());
                        view.startAnimation();
                    }

                    @Override
                    public void onAnimationRepeated(Animation animation,
                                                    xyz.matteobattilana.library.WeatherView view) {
                    }
                });
        mWeatherImage.startAnimation(fadeIn);

        final int colorFrom = ContextCompat.getColor(context, R.color.colorBgWeatherDefault);
        final int colorTo = ContextCompat.getColor(context, newWeatherRes.getBackgroundColorResId());
        animateBackground(colorFrom, colorTo);
    }

    private void replaceWeather(Context context,
                                WeatherRes currentWeatherRes,
                                WeatherRes newWeatherRes) {
        Timber.d("Replace weather res");
        mWeatherView.cancelAnimation();

        Animation fadeOut = AnimationUtils.loadAnimation(context, R.anim.fade_out);
        Animation fadeIn = AnimationUtils.loadAnimation(context, R.anim.fade_in);
        fadeOut.setAnimationListener(new AnimationWeakListener<ImageView>(mWeatherImage) {
            @Override
            public void onAnimationStarted(Animation animation, ImageView view) {
            }

            @Override
            public void onAnimationEnded(Animation animation, ImageView view) {
                view.setImageResource(newWeatherRes.getWeatherImageResId());
                view.startAnimation(fadeIn);
            }

            @Override
            public void onAnimationRepeated(Animation animation, ImageView view) {
            }
        });
        fadeIn.setAnimationListener(
                new AnimationWeakListener<xyz.matteobattilana.library.WeatherView>(mWeatherView) {
                    @Override
                    public void onAnimationStarted(Animation animation,
                                                   xyz.matteobattilana.library.WeatherView view) {
                    }

                    @Override
                    public void onAnimationEnded(Animation animation,
                                                 xyz.matteobattilana.library.WeatherView view) {
                        view.setWeather(newWeatherRes.getWeatherStatus());
                        view.startAnimation();
                    }

                    @Override
                    public void onAnimationRepeated(Animation animation,
                                                    xyz.matteobattilana.library.WeatherView view) {
                    }
                });
        mWeatherImage.startAnimation(fadeOut);

        final int colorFrom = ContextCompat.getColor(context, currentWeatherRes.getBackgroundColorResId());
        final int colorTo = ContextCompat.getColor(context, newWeatherRes.getBackgroundColorResId());
        animateBackground(colorFrom, colorTo);
    }

    private void animateBackground(int colorFrom, int colorTo) {
        ValueAnimator colorAnimation =
                ValueAnimator.ofObject(new ArgbEvaluator(), colorFrom, colorTo);
        colorAnimation.setDuration(mAnimBgDuration);
        colorAnimation.addUpdateListener(
                new ValueAnimatorWeakUpdateListener<ViewGroup>(mContentLayout) {
                    @Override
                    public void onAnimationUpdated(ValueAnimator animation, ViewGroup view) {
                        view.setBackgroundColor((int) animation.getAnimatedValue());
                    }
                });
        colorAnimation.start();
    }

    private void setWeatherTextColor(Context context, @ColorRes int colorRes) {
        int color = ContextCompat.getColor(context, colorRes);
        mTemperatureText.setTextColor(color);
        mUnitsText.setTextColor(color);
        mLocationText.setTextColor(color);
    }

    @Override
    public void onResume() {
        super.onResume();

        mWeatherView.startAnimation();
        mPresenter.getCachedWeather();
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
    public void showWeather(WeatherData weatherData) {
        mSwipeRefreshLayout.setRefreshing(false);
        showNewWeather(getContext(), weatherData);
    }

    @Override
    public void showError() {
        mSwipeRefreshLayout.setRefreshing(false);
        Snackbar errorSnackbar =
                Snackbar.make(mContentLayout, R.string.text_weather_error, Snackbar.LENGTH_LONG);
        errorSnackbar.setAction(R.string.ok, v -> {
        });
        errorSnackbar.show();
    }

    public static String tag() {
        return WeatherFragment.class.getSimpleName();
    }
}

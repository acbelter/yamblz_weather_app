package com.acbelter.weatherapp.mvp.view.weather.details;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.acbelter.weatherapp.App;
import com.acbelter.weatherapp.R;
import com.acbelter.weatherapp.domain.model.fullmodel.FullWeatherModel;
import com.acbelter.weatherapp.domain.model.weather.ForecastWeatherElement;
import com.acbelter.weatherapp.domain.utils.TemperatureMetric;
import com.acbelter.weatherapp.domain.utils.TemperatureMetricConverter;
import com.acbelter.weatherapp.mvp.presentation.DetailPresenter;
import com.acbelter.weatherapp.mvp.view.activity.drawer.DrawerLocker;
import com.acbelter.weatherapp.mvp.view.error.ErrorFragment;
import com.acbelter.weatherapp.mvp.view.fragment.BaseFragment;
import com.acbelter.weatherapp.mvp.view.weather.resources.ForecastWeatherRes;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import timber.log.Timber;

import static com.acbelter.weatherapp.domain.utils.TemperatureMetric.CELSIUS;

public class DetailFragment extends BaseFragment implements DetailView {

    private static final String ARG_POSITION = "position";

    @BindView(R.id.ivDetais)
    ImageView weatherImage;
    @BindView(R.id.tvDetailsDate)
    TextView tvDate;
    @BindView(R.id.tvDetaisMaxTemp)
    TextView tvMaxTemp;
    @BindView(R.id.tvDetailsMinTemp)
    TextView tvMinTemp;
    @BindView(R.id.tvDetailsMetricMax)
    TextView tvMetricMax;
    @BindView(R.id.tvDetailsMetricMin)
    TextView tvMetricMin;
    @BindView(R.id.tvDetaisDescription)
    TextView tvDescription;
    @BindView(R.id.tvDetaisPressure)
    TextView tvPressure;
    @BindView(R.id.tvDetaisHumidity)
    TextView tvHumidity;
    @BindView(R.id.tvDetaisWind)
    TextView tvWind;
    @BindView(R.id.tvDetailsms)
    TextView tvMs;
    @BindView(R.id.tvDetailsmm)
    TextView tvMm;
    @BindView(R.id.tvDetailsPressureText)
    TextView tvPressureText;
    @BindView(R.id.tvDetailsHumidityText)
    TextView getTvHumidityText;
    @BindView(R.id.tvDetailsWindText)
    TextView tvWindText;

    @Inject
    DetailPresenter presenter;

    @Nullable
    Unbinder unbinder;

    private int position;

    public static DetailFragment newInstance(int position) {
        DetailFragment fragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_POSITION, position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.onAttach(this);
        if (getArguments() != null) {
            position = getArguments().getInt(ARG_POSITION);
            Timber.v("position = " + position);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        this.unbinder = ButterKnife.bind(this, view);
        presenter.getWeather();
    }

    private String convertMetricToString(TemperatureMetric metric, Context context) {
        if (metric == CELSIUS)
            return context.getString(R.string.celsius);
        else
            return context.getString(R.string.fahrenheit);
    }

    private void setWeatherView(ForecastWeatherElement weather) {
        ForecastWeatherRes forecastWeatherRes = new ForecastWeatherRes(weather);
        weatherImage.setImageResource(forecastWeatherRes.getWeatherImageResId());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        App.getInstance().releaseActivityComponent();
        Timber.d("Remove weather component");
        if (unbinder != null)
            unbinder.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDetach();
    }

    @Override
    protected void setTitle() {

    }

    @Override
    protected void setDrawableEnabled() {
        ((DrawerLocker) getActivity()).setDrawerEnable(false);
    }

    @Override
    protected void inject() {
        App.getInstance().plusActivityComponent().inject(this);
    }

    @Override
    public void showWeather(FullWeatherModel weather) {
        ForecastWeatherElement forecast = weather.getForrecast().get(position);
        setWeatherView(forecast);
        int maxTemp = TemperatureMetricConverter.getSupportedTemperature(forecast.getMaxTemp(), forecast.getTemperatureMetric());
        int minTemp = TemperatureMetricConverter.getSupportedTemperature(forecast.getMinTemp(), forecast.getTemperatureMetric());
        tvMaxTemp.setText(String.valueOf(maxTemp));
        tvMinTemp.setText(String.valueOf(minTemp));
        tvDate.setText(forecast.getDate());
        tvMetricMin.setText(convertMetricToString(forecast.getTemperatureMetric(), getContext()));
        tvMetricMax.setText(convertMetricToString(forecast.getTemperatureMetric(), getContext()));
        tvDescription.setText(forecast.getDescription());
        tvHumidity.setText(String.valueOf(forecast.getHumidity()));
        tvPressure.setText(String.valueOf(forecast.getPressure()));
        tvWind.setText(String.valueOf(forecast.getWindSpeed()));
    }
}

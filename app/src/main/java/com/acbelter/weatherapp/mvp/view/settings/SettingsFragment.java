package com.acbelter.weatherapp.mvp.view.settings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.acbelter.weatherapp.App;
import com.acbelter.weatherapp.R;
import com.acbelter.weatherapp.domain.model.settings.SettingsData;
import com.acbelter.weatherapp.domain.utils.TemperatureMetric;
import com.acbelter.weatherapp.mvp.presentation.SettingsPresenter;
import com.acbelter.weatherapp.mvp.view.activity.drawer.DrawerLocker;
import com.acbelter.weatherapp.mvp.view.fragment.BaseFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.acbelter.weatherapp.domain.utils.TemperatureMetric.CELSIUS;
import static com.acbelter.weatherapp.domain.utils.TemperatureMetric.FAHRENHEIT;

public class SettingsFragment extends BaseFragment implements SettingsView {

    private Unbinder unbinder;

    @BindView(R.id.rgTempMetric)
    RadioGroup rgTempMetric;
    @BindView(R.id.celsius)
    RadioButton rbCelsius;
    @BindView(R.id.fahrenheit)
    RadioButton rbFahrenheit;
    @BindView(R.id.rbUpdateInterval)
    RadioGroup rgUpdateInterval;

    @Inject
    @InjectPresenter
    SettingsPresenter presenter;

    private static final long INTERVAL_MULTIPLEXOR = 1 * 60 * 1000;

    @ProvidePresenter
    public SettingsPresenter provideSettingsPresenter() {
        return presenter;
    }

    public static SettingsFragment newInstance() {
        return new SettingsFragment();
    }

    @Override
    protected void setTitle() {
        getActivity().setTitle(R.string.settings);
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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        App.getInstance().plusActivityComponent().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        unbinder = ButterKnife.bind(this, view);

        rgTempMetric.setOnCheckedChangeListener((radioGroup, checkedId) -> saveTemperatureMetric(radioGroup));
        rgUpdateInterval.setOnCheckedChangeListener((radioGroup, checkedId) -> saveUpdateInterval(radioGroup));
    }

    @Override
    public void onResume() {
        super.onResume();

        presenter.showSettings();
    }


    private void saveTemperatureMetric(RadioGroup radioGroup) {
        TemperatureMetric metric;
        switch (radioGroup.getCheckedRadioButtonId()) {
            case R.id.celsius:
                metric = CELSIUS;
                break;
            case R.id.fahrenheit:
                metric = FAHRENHEIT;
                break;
            default:
                metric = CELSIUS;
                break;
        }
        presenter.saveTemperatureMetric(metric);
    }

    private void saveUpdateInterval(RadioGroup radioGroup) {
        long interval = 0;
        switch (radioGroup.getCheckedRadioButtonId()) {
            case R.id.rbMin15:
                interval = 15 * INTERVAL_MULTIPLEXOR;
                break;
            case R.id.rbMin30:
                interval = 30 * INTERVAL_MULTIPLEXOR;
                break;
            case R.id.rbMin60:
                interval = 60 * INTERVAL_MULTIPLEXOR;
                break;
            case R.id.rbMin180:
                interval = 180 * INTERVAL_MULTIPLEXOR;
                break;
            default:
                interval = 60 * INTERVAL_MULTIPLEXOR;
                break;
        }
        presenter.saveUpdateInterval(interval);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();

        unbinder.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        presenter.onDetach();
    }

    @Override
    public void setSettings(SettingsData settingsData) {
        setTemperatureMetric(settingsData.getMetric());
        setUpdateInterval(settingsData.getUpdateWeatherInterval());
    }

    private void setTemperatureMetric(TemperatureMetric metric) {
        if (metric == CELSIUS)
            rgTempMetric.check(R.id.celsius);
        else
            rgTempMetric.check(R.id.fahrenheit);
    }

    private void setUpdateInterval(long interval) {
        if (interval == 15 * INTERVAL_MULTIPLEXOR)
            rgUpdateInterval.check(R.id.rbMin15);
        else if (interval == 30 * INTERVAL_MULTIPLEXOR)
            rgUpdateInterval.check(R.id.rbMin30);
        else if (interval == 60 * INTERVAL_MULTIPLEXOR)
            rgUpdateInterval.check(R.id.rbMin60);
        else
            rgUpdateInterval.check(R.id.rbMin180);
    }
}
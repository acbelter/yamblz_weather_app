package com.acbelter.weatherapp.mvp.view.error;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.acbelter.weatherapp.App;
import com.acbelter.weatherapp.R;
import com.acbelter.weatherapp.mvp.presentation.ErrorPresenter;
import com.acbelter.weatherapp.mvp.view.fragment.BaseFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ErrorFragment extends BaseFragment implements ErrorView {

    @BindView(R.id.tvError)
    TextView tvError;

    @Nullable
    private Unbinder unbinder;

    @Inject
    ErrorPresenter presenter;

    public static ErrorFragment newInstance() {
        return new ErrorFragment();
    }

    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);

        presenter.onAttach(this);
    }

    @Override
    public void onActivityCreated(Bundle saveInstanceState) {
        super.onActivityCreated(saveInstanceState);

        setTitle();
        setDrawableEnabled();
    }

    @Override
    protected void setTitle() {
    }

    @Override
    protected void setDrawableEnabled() {
    }

    @Override
    protected void inject() {
        App.getInstance().plusActivityComponent().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.error_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        unbinder = ButterKnife.bind(this, view);
        presenter.showNoCityError();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unbinder != null)
            unbinder.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        presenter.onDetach();
    }

    @Override
    public void showNoCityError() {
        tvError.setText(R.string.no_data);
    }
}
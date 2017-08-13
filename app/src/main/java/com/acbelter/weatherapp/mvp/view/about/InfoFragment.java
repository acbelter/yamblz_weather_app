package com.acbelter.weatherapp.mvp.view.about;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.acbelter.weatherapp.App;
import com.acbelter.weatherapp.BuildConfig;
import com.acbelter.weatherapp.R;
import com.acbelter.weatherapp.mvp.presentation.InfoPresenter;
import com.acbelter.weatherapp.mvp.view.activity.drawer.DrawerLocker;
import com.acbelter.weatherapp.mvp.view.fragment.BaseFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class InfoFragment extends BaseFragment implements InfoView {

    @BindView(R.id.version_text)
    TextView versionText;

    @Nullable
    private Unbinder unbinder;

    @Inject
    InfoPresenter presenter;

    public static InfoFragment newInstance() {
        return new InfoFragment();
    }

    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);

        presenter.onAttach(this);
    }

    @Override
    protected void setTitle() {
        getActivity().setTitle(R.string.about);
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_info, container, false);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        unbinder = ButterKnife.bind(this, view);

        presenter.showAppVersion();
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
    public void showAppVersion() {
        versionText.setText(getString(R.string.version, BuildConfig.VERSION_NAME));
    }
}

package com.acbelter.weatherapp.ui.search;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.acbelter.weatherapp.R;
import com.acbelter.weatherapp.ui.about.AboutView;
import com.arellomobile.mvp.MvpAppCompatActivity;

public class SearchActivity extends MvpAppCompatActivity implements AboutView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
    }

    @Override
    public void close() {

    }
}

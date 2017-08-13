package com.acbelter.weatherapp.mvp.presentation;

import com.acbelter.weatherapp.mvp.view.about.InfoView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class InfoPresenterTest {

    @Mock
    InfoView mockView;

    @InjectMocks
    InfoPresenter presenter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        presenter = new InfoPresenter();
        presenter.onAttach(mockView);
    }

    @Test
    public void testShowAppVersion() {
        presenter.showAppVersion();
        verify(mockView).showAppVersion();
    }
}

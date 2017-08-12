package com.acbelter.weatherapp.mvp.presentation;

import com.acbelter.weatherapp.mvp.view.error.ErrorView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ErrorPresenterTest {

    @Mock
    ErrorView mockView;

    @InjectMocks
    ErrorPresenter presenter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        presenter = new ErrorPresenter();
        presenter.onAttach(mockView);
    }

    @Test
    public void testShowNoCityError() {
        presenter.showNoCityError();
        verify(mockView).showNoCityError();
    }

}

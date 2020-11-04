package com.cardflight.mobilebowling.view;

import com.cardflight.mobilebowling.model.Frame;
import com.cardflight.mobilebowling.model.Roll;
import com.cardflight.mobilebowling.presenter.ScorePresenter;

public interface ScorePresenterView {

    void setFrameListener();

    void showErrorView();
}

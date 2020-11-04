package com.cardflight.mobilebowling.presenter;

import com.cardflight.mobilebowling.model.Roll;

public interface ScorePresenter {

    void initialize();

    void startPresenting();

    void stopPresenting();

    public void resetGame();

    public void processRoll(int frameNumber, Roll roll, boolean overwrite);

    public void getScores(int frameNumber);

    public int getCumulativeScore(int frameNumber);

    public void update(int j, Roll roll);
}

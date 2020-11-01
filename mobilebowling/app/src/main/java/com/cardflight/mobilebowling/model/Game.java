package com.cardflight.mobilebowling.model;

import java.util.ArrayList;
import java.util.List;

public class Game {

    public static final int NUMBER_OF_FRAMES = 10;
    public static List<Frame> userFrames = new ArrayList<Frame>();

    private int totalScore = 0;

    //Getters and Setters
    public Frame[] getFrames() {
        return userFrames;
    }
    public void setFrames(Frame[] frames) {
        this.userFrames = userFrames;
    }
    public int getTotalScore() {
        return this.totalScore;
    }

}


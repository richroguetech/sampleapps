package com.cardflight.mobilebowling.model;

import java.util.ArrayList;
import java.util.List;

public class Game {

    public static final int NUMBER_OF_FRAMES = 10;
    public static List<Frame> userFrames = new ArrayList<Frame>();

    private int totalScore = 0;

    //Getters and Setters
    public List<Frame> getFrames() {
        return this.userFrames;
    }

    public void setFrames(ArrayList<Frame> userFrames) {
        this.userFrames = userFrames;
    }

    public int getTotalScore() {
        for (Frame frame : userFrames) {
            totalScore += frame.getTotalFrameScore();
        }
        return totalScore;
    }

}


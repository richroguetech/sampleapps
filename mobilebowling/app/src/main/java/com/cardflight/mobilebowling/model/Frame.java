package com.cardflight.mobilebowling.model;

public class Frame {

    private int frameScore = 0;   // number of pins knocked down in frame
    private int totalScore = 0;   // total game score up to this point.
    private int totalFrameScore = 0; // frameScore + 1 or 2 next balls.

    private int roll1 = -1;   // first roll
    private int roll2 = -1;   // second roll
    private int roll3 = -1;   // 3rd roll - only applies to 10th frame...

    private int bonus = 0;  // bonus for spare
    private int bonus2 = 0;  //bonus for strike...

    private boolean isSpare = false;
    private boolean isStrike = false;
    private boolean isFinished = false; // isFinished is false if strike or spare
     // and bonus not calculated....

    private boolean isLastFrame = false;

    public Frame(Roll roll) {
        // process the frameNumber and 1st roll value....
        if (roll.getRollValue() == "X") {
            setFirstRoll(roll);
        }
    }

    public void updateSecondRoll(Roll roll) {
        // check to make sure roll1 != 10
        setSecondRoll(roll);
    }

    public boolean isLastFrame() {
        return isLastFrame;
    }

    public void setLastFrame() {
        this.isLastFrame = true;
    }

    public boolean isSpare() {
        return isSpare;
    }

    public boolean isStrike() {
        return isStrike;
    }

    public void setStrike(boolean isStrike) {
        this.isStrike = isStrike;
    }

    public void setSpare(boolean isSpare) {
        this.isSpare = isSpare;
    }

    public void setFrameScore(int score) {

        this.frameScore = score;
    }

    public void setTotalFrameScore(int totalFrameScore) {

        this.totalFrameScore = totalFrameScore;
    }

    // means scoring isn't finished...
    public void setFinished(boolean isFinished) {
        this.isFinished = isFinished;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public int getFrameScore() {
        return frameScore;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public int getTotalFrameScore() {
        return totalFrameScore;
    }

    public void setFirstRoll(Roll roll) {
        this.roll1 = roll.getNumPins();
    }

    public int getFirstRoll() {
        return this.roll1;
    }

    public void setSecondRoll(Roll roll2) {
        this.roll2 = roll2.getNumPins();
    }

    public int getSecondRoll() {
        return this.roll2;
    }

    public void setThirdRoll(Roll roll3) {
        this.roll3 = roll3.getNumPins();
    }

    public int getThirdRoll() {
        return this.roll3;
    }
}


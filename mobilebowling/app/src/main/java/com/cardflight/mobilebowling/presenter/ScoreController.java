package com.cardflight.mobilebowling.presenter;

import com.cardflight.mobilebowling.model.Frame;
import com.cardflight.mobilebowling.model.Game;
import com.cardflight.mobilebowling.model.Roll;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ScoreController {

    //Declaring Class Variable
    private Game game=new Game ();

    // constants
    public static final int NUMBER_OF_FRAMES = 10;
    public static List<Frame> userFrames = new ArrayList<>();

    public static int frameCounter = 0;  // initialized to first frame.
    public static int TEN_PINS = 10;

    public boolean addFrame(Roll roll) {
        return false;
    }

    //startGame method starts
    public void processRoll(int frameNumber, Roll roll, boolean overwrite) {

        Frame frame = userFrames.get(frameNumber -1);
        if (overwrite == true) {
            frame = null; // need to process the scores all over again....
        }
        if (frame == null) {
            frame = new Frame(frameNumber, roll);
            if (frame.getFirstRoll().getType() == Roll.RollType.STRIKE) {
                frame.setStrike(true);
            }
        } else {
          if (frameNumber == NUMBER_OF_FRAMES -1) {
              // this is last Frame....
              // check and see if this first roll or second roll or third roll
              if (frame.getFirstRoll() == null) {
                  frame.setFirstRoll(roll);
              } else if (frame.getSecondRoll() == null) {
                  frame.insertSecondRoll(roll);
              } else if (frame.getThirdRoll() == null) {
                  frame.insertThirdRoll(roll);
              }
          } else {
              // process the second rolll
              frame.setSecondRoll(roll);
              // check for spare...
              if (frame.getSecondRoll().getType() == Roll.RollType.SPARE) {
                  frame.setSpare(true); // update num pins from the first roll..
                  int numFirstPins = frame.getFirstRoll().getNumPins(); // should be less than 10
                  frame.getSecondRoll().setNumPins(10 - numFirstPins);
              }
          }
          update(frameNumber, roll); // to process the bonus... scores...
        }
        // update the total score...
        System.out.println(getCumulativeScore(frameNumber));
        userFrames.set(frameNumber, frame);
    }

    public static void getScores(int i) {
        //printing starts
        System.out.println("SCORES - ");
        for(int j=0;j<=i;j++)
        {
            //Normal case (Neither STRIKE nor SPARE)
            if((userFrames.get(i).isStrike()==false)&& (userFrames.get(i).isSpare()==false))
            {
                //System.out.println("The Individual score for "+(j+1)+" Frame is :"+this.getGame().getFrames()[j].getAbsolute_score_of_frame());
                System.out.println("The Individual score for "+(j+1)+" Frame is :"+userFrames.get(i).getTotalFrameScore());
            }
            //STRIKE not calculated yet
            if((userFrames.get(i).isStrike()) && (userFrames.get(i).getTotalFrameScore()==-99))
            {
                System.out.println("The Individual score for "+(j+1)+" Frame is : X");
            }
            //STRIKE Calculated
            if((userFrames.get(i).isStrike())&&(userFrames.get(i).getTotalFrameScore()!=-99))
            {
                System.out.println("The Individual score for "+(j+1)+" Frame is : "+userFrames.get(i).getTotalFrameScore());
            }
            //SPARE not calculated yet
            if((userFrames.get(i).isSpare()) && (userFrames.get(i).getTotalFrameScore()==-88))
            {
                System.out.println("The Individual score for "+(j+1)+" Frame is : /");
            }
            //SPARE Calculated
            if((userFrames.get(i).isSpare()) && (userFrames.get(i).getTotalFrameScore()!=-88))
            {
                System.out.println("The Individual score for "+(j+1)+" Frame is : "+userFrames.get(i).getTotalFrameScore());
            }

            //System.out.println("and the Cumulative score for Frame "+(j+1)+" is :"+getCumulativeScore(j));
        }
    }

    public int getCumulativeScore(int frameNumber)
    {
        int sum = 0;
        for(int j=0;j <= frameNumber; j++ )
        {
            if(!userFrames.get(frameNumber).isFinished()) {
                sum = sum + userFrames.get(frameNumber).getFrameScore();
                // the cumulative score at this point is the total pins knocked down w/o bonus calculation.
            } else {
                sum = sum + userFrames.get(frameNumber).getTotalFrameScore();
            }
        }
        return sum;
    }

    public void update(int j, Roll roll)  //Update the score of previous frames as per the score of current frame in case of SPARE/STRIKE
    {
        for(int i= j-1; i >= 0; i--) // if i = 0, first frame...
        {
            if ((userFrames.get(i).isStrike()) && (!userFrames.get(i).isFinished()))
            {
                userFrames.get(i).setFrameScore(TEN_PINS);
                userFrames.get(i).getFirstRoll().getNumPins();
                // process bonus
                userFrames.get(i).processBonus(roll.getNumPins());
                continue;
            }
            if ((userFrames.get(i).isStrike()) && (userFrames.get(i).isBonusProcessed()))
            {
                userFrames.get(i).processBonus2(roll.getNumPins());
                continue;
            }

            if((userFrames.get(i).isSpare()) && (!userFrames.get(i).isFinished()))
            {
                userFrames.get(i).processBonus(roll.getNumPins());
                userFrames.get(i).setTotalFrameScore(userFrames.get(i).getTotalFrameScore());
                continue;
            }
        }
    }

    //Getters and Setters
    public Game getGame() {
        return game;
    }


    public void setGame(Game game) {
        this.game = game;
    }

}
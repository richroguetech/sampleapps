package com.cardflight.mobilebowling.presenter;

import com.cardflight.mobilebowling.model.Frame;
import com.cardflight.mobilebowling.model.Game;
import com.cardflight.mobilebowling.model.Roll;

import java.util.ArrayList;
import java.util.List;

public class ScoreController {

    //Declaring Class Variable
    private Game game=new Game ();

    // constants
    public static final int NUMBER_OF_FRAMES = 10;
    public static List<Frame> userFrames = new ArrayList<>();

    public static int TEN_PINS = 10;
    public static int totalScore = 0;

    //startGame method starts
    public void processRoll(int frameNumber, Roll roll, boolean overwrite) {

        Frame frame = userFrames.size() >= frameNumber ? userFrames.get(frameNumber -1) : null;
        if (overwrite == true) {
            frame = null; // need to process the scores all over again....
        }

        if (frame == null) {
            frame = new Frame(frameNumber, roll);
            if (frame.getFirstRoll().getType() == Roll.RollType.STRIKE) {
                frame.setStrike(true);
            }
            userFrames.add(frameNumber -1, frame);
        } else {
          if (frameNumber == NUMBER_OF_FRAMES -1) {
              // this is last Frame....
              // check and see if this first roll or second roll or third roll
              if (frame.getFirstRoll() == null) {
                  frame.insertFirstRoll(roll);
              } else if (frame.getSecondRoll() == null) {
                  frame.insertSecondRoll(roll);
              } else if (frame.getThirdRoll() == null) {
                  frame.insertThirdRoll(roll);
              }
          } else {
              // process the second rolll
              frame.insertSecondRoll(roll);
              // check for spare...
              if (frame.getSecondRoll().getType() == Roll.RollType.SPARE) {
                  frame.setSpare(true); // update num pins from the first roll..
                  int numFirstPins = frame.getFirstRoll().getNumPins(); // should be less than 10
                  frame.getSecondRoll().setNumPins(10 - numFirstPins);
              }
          }
            userFrames.remove(frameNumber -1);
            userFrames.add(frameNumber -1, frame);
        }
        update(frameNumber -1, roll); // to process the bonus... scores...
        // update the total score...
        totalScore = getCumulativeScore(frameNumber);
        System.out.println(totalScore);
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
        for(int j=0;j < frameNumber; j++ )
        {
            sum = sum + userFrames.get(j).getTotalFrameScore();
        }
        return sum;
    }

    public void update(int j, Roll roll)  //Update the score of previous frames as per the score of current frame in case of SPARE/STRIKE
    {
        for(int i= j-1; i >= 0; i--) // if i = 0, first frame...
        {
            if ((userFrames.get(i).isStrike()) && (!userFrames.get(i).isBonusProcessed()))
            {
                // process bonus
                userFrames.get(i).processBonus(roll.getNumPins());
                continue;
            }
            if ((userFrames.get(i).isStrike()) && (userFrames.get(i).isBonusProcessed() &&
                    !userFrames.get(i).isBonus2Processed()))
            {
                userFrames.get(i).processBonus2(roll.getNumPins());
                continue;
            }

            if((userFrames.get(i).isSpare()) && (!userFrames.get(i).isFinished()))
            {
                userFrames.get(i).processBonus(roll.getNumPins());
                userFrames.get(i).setTotalFrameScore(userFrames.get(i).getTotalFrameScore());
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
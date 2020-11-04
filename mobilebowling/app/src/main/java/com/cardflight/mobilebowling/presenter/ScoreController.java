package com.cardflight.mobilebowling.presenter;

import com.cardflight.mobilebowling.model.Frame;
import com.cardflight.mobilebowling.model.Game;
import com.cardflight.mobilebowling.model.Roll;

import java.util.ArrayList;
import java.util.List;

public class ScoreController {

    //Declaring Class Variable
    private Game game=new Game ();

    public static final int NUMBER_OF_FRAMES = 10;
    public static List<Frame> userFrames = new ArrayList<>();

    public static int TEN_PINS = 10;
    public static int totalScore = 0;

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
          if (frameNumber == NUMBER_OF_FRAMES) {
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
    }

    public static void getScores(int frameNumber) {
        for(int j = frameNumber -1; j < frameNumber; j++)
        {
            if((!userFrames.get(j).isStrike()) && (!userFrames.get(j).isSpare()))
            {
                int firstroll = userFrames.get(j).getFirstRoll().getNumPins();
                int secondroll = 0;
                //int secondroll = userFrames.get(j).getSecondRoll().getNumPins();
                System.out.println("Frame #" + frameNumber + ":" + firstroll + secondroll +  " ");
            }
            if((userFrames.get(j).isStrike()) && (!userFrames.get(j).isFinished()))
            {
                System.out.println(userFrames.get(j).getFirstRoll().getType());
            }
            if((userFrames.get(j).isStrike()) && (userFrames.get(j).isFinished()))
            {
                int firstroll = userFrames.get(j).getFirstRoll().getNumPins();
                System.out.println("Frame #" + frameNumber + ":" + firstroll + " ");
            }
            if((userFrames.get(j).isSpare()) && (!userFrames.get(j).isFinished()))
            {
                int firstroll = userFrames.get(j).getFirstRoll().getNumPins();
                String secondroll = userFrames.get(j).getSecondRoll().getType().toString();
                System.out.println("Frame #" + frameNumber + ":" + firstroll + " " + secondroll + "");
            }
            if((userFrames.get(j).isSpare()) && (!userFrames.get(j).isFinished()))
            {
                int firstroll = userFrames.get(j).getFirstRoll().getNumPins();
                String secondroll = userFrames.get(j).getSecondRoll().getType().toString();
                System.out.println("Frame #" + frameNumber + ":" + firstroll + " " + secondroll + "");
            }
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

    public void update(int j, Roll roll)  //Update the score of previous frames as in case of SPARE/STRIKE
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
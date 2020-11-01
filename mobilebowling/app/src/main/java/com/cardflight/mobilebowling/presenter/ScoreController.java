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
        //if (roll)     // check if roll is valid, otherwise send error message...

        return false;
    }

    //startGame method starts
    /*
    public void processRoll(Roll roll) {

        for(int i = 0; i < NUMBER_OF_FRAMES; i++)  //for loop for 10 Frames
        {
            Frame frame =new Frame(roll);  //temporary frame object
            int firstHit = roll.getNumPins();
            int secondHit = roll.getNumPins();
            int thirdHit = roll.getNumPins();

            frame.setFirstRoll(roll);
              update(i, roll);

            if(roll.getRollValue() == "X"  && (frameCounter != 9))//condition for STRIKE
            {
                frame.setStrike(true);
                frame.setFrameScore(10);
                frame.setFinished(false); // needs the next 2 balls to finish....
                frame.setTotalFrameScore(-99); // -99 stand for X(STRIKE)
            }
            else  //If it is not a STRIKE
            {
                //getting the no of pins knocked in the Second Roll from User/Controller
                frame.setSecondRoll(roll);
                update(i,roll);
            }

            if((firstHit < TEN_PINS)&& (secondHit < TEN_PINS ) && ((firstHit+secondHit)==TEN_PINS))//Condition for SPARE
            {
                frame.setSpare(true);
                frame.setFinished(false);
                frame.setFrameScore(-88); // -88 stand for / (SPARE)
            }

            //Considering cases for 10th Frame
            if((firstHit==TEN_PINS) && (i == 9))//condition for STRIKE in 10th Frame
            {
                frame.setThirdRoll(roll);
                update(i,roll);
            }
            if((firstHit+secondHit==TEN_PINS) && (i == 9 ))//condition for SPARE in 10th Frame
            {
                System.out.println("You hit a SPARE in the last frame.You get an extra roll.");
                frame.setThirdRoll(roll);
                update(i, roll);
            }


            frame.setFrameScore((firstHit+secondHit));//setting ABSOLUTE score per frame=first hit + second hit

            if((frame.isSpare()==false)  && (frame.isStrike()==false))
            {
                frame.setTotalFrameScore((firstHit+secondHit));//setting TOTAL score per frame=first hit + second hit
            }

            (Frame)userFrames.get(i)=frame;  //Finally setting the ith frame object in Game

            if(i == 9) //setting the score for frame 10
            {
                userFrames.get(9).setFrameScore(firstHit+secondHit+thirdHit);
                userFrames.get(9).setTotalFrameScore(firstHit+secondHit+thirdHit);
            }

            frame =null;  //Freeing the memory.

            //printing starts
            System.out.println("SCORES - ");
            for(int j=0;j<=i;j++)
            {

                //Normal case (Neither STRIKE nor SPARE)
                if((userFrames.get(i).isStrike()==false)&& (userFrames.get(i).isSpare()==false))
                {
                    //System.out.println("The Individual score for "+(j+1)+" Frame is :"+this.getGame().getFrames()[j].getAbsolute_score_of_frame());
                    System.out.println("The Individual score for "+(j+1)+" Frame is :"+userFrames.get(i).getTotal_score_of_this_frame());
                }
                //STRIKE not calculated yet
                if((userFrames.get(i).isStrike()) && (userFrames.get(i).getTotal_score_of_this_frame()==-99))
                {
                    System.out.println("The Individual score for "+(j+1)+" Frame is : X");
                }
                //STRIKE Calculated
                if((userFrames.get(i).isStrike())&&(userFrames.get(i).getTotal_score_of_this_frame()!=-99))
                {
                    System.out.println("The Individual score for "+(j+1)+" Frame is : "+userFrames.get(i).getTotal_score_of_this_frame());
                }
                //SPARE not calculated yet
                if((userFrames.get(i).isSpare()) && (userFrames.get(i).getTotal_score_of_this_frame()==-88))
                {
                    System.out.println("The Individual score for "+(j+1)+" Frame is : /");
                }
                //SPARE Calculated
                if((userFrames.get(i).isSpare()) && (userFrames.get(i).getTotal_score_of_this_frame()!=-88))
                {
                    System.out.println("The Individual score for "+(j+1)+" Frame is : "+userFrames.get(i).getTotal_score_of_this_frame());
                }

                System.out.println("and the Cumulative score for Frame "+(j+1)+" is :"+getCumulativeScore(j));
            }
            //printing ends
        }//end of for


    }
*/
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

    /*
    public void update(int j, Roll roll)  //Update the score of previous frames as per the score of current frame in case of SPARE/STRIKE
    {
        for(int i=j-1;i>=0;i--)
        {
            if ((userFrames.get(i).isStrike()) && (!userFrames.get(i).isFinished()))
            {
                userFrames.get(i).setIs_first_updated(true);
                userFrames.get(i).setTotalFrameScore(TEN_PINS+roll.getNumPins());
                continue;
            }
            if((userFrames.get(i).isStrike()==true) && (!userFrames.get(i).isFinished())
                    && (userFrames.get(i).isIs_first_updated()==true)&&(tuserFrames.get(i).isIs_second_updated()==false) )
            {
                userFrames.get(i).setIs_second_updated(true);
                userFrames.get(i).setTotalFrameScore((userFrames.get(i).getAbsolute_score_of_frame())+(pins_hit));
                userFrames.get(i).setTotalFrameScore(userFrames.get(i).getAbsolute_score_of_frame());

                continue;
            }

            if((userFrames.get(i).isSpare()) && (userFrames.get(i).getTotalFrameScore()==-88)&&
                    (userFrames.get(i).isIs_first_updated()==false))
            {
                userFrames.get(i).setIs_first_updated(true);
                userFrames.get(i).setAbsolute_score_of_frame(TEN_PINS+pins_hit);
                userFrames.get(i).setTotalFrameScore(userFrames.get(i).getTotalFrameScore());

                continue;
            }
        }
    }
*/

    //Getters and Setters
    public Game getGame() {
        return game;
    }


    public void setGame(Game game) {
        this.game = game;
    }

}
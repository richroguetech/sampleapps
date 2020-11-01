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

    public boolean addFrame(Roll roll) {
        //if (roll)     // check if roll is valid, otherwise send error message...

        return false;
    }

    //startGame method starts
    public void inputRolls(Roll roll) {

        for(int i = 0; i < NUMBER_OF_FRAMES; i++)  //for loop for 10 Frames
        {
            Frame frame =new Frame();  //temporary frame object
            String firstHit ="0";
            String secondHit ="0";
            String thirdHit = "0";

            String firstHit = "5";
            Roll roll1 = new Roll(firstHit);

            frame.setFirstRoll(roll1);
              update(i, roll1);

            if(first_hit==10 && (i!=9))//condition for STRIKE
            {
                System.out.println("This is s STRIKE !! Your "+(i+1)+"(th) Frame ends.");
                f.setFrame_Strike(true);
                f.setTotal_score_of_this_frame(-99); // -99 stand for X(STRIKE)
            }
            else  //If it is not a STRIKE
            {
                //getting the no of pins knocked in the Second Roll from User/Controller
                System.out.println("Please enter the number of pins knocked down in Second Roll of the "+(i+1)+"(th) Frame ");
                second_hit=in.nextInt();
                f.setRoll_2_pins_knocked_down(second_hit);
                update(i,second_hit);
            }

            if((first_hit<10)&& (second_hit<10) && ((first_hit+second_hit)==10))//Condition for SPARE
            {
                f.setFrame_Spare(true);
                System.out.println("Your "+(i+1)+"(th) Frame is a SPARE !!");
                f.setTotal_score_of_this_frame(-88); // -88 stand for / (SPARE)
            }

            //Considering cases for 10th Frame
            if((first_hit==10) && (i==9))//condition for STRIKE in 10th Frame
            {
                System.out.println("You hit a STRIKE in the last frame.You get an extra roll.");
                System.out.println("Please enter the number of pins knocked down in Third Roll of the 10(th) Frame ");
                third_hit=in.nextInt();
                f.setRoll_3_pins_knocked_down(third_hit);
                //update(i,third_hit);
            }
            if((first_hit+second_hit==10) && (i==9))//condition for SPARE in 10th Frame
            {
                System.out.println("You hit a SPARE in the last frame.You get an extra roll.");
                System.out.println("Please enter the number of pins knocked down in Third Roll of the 10(th) Frame ");
                third_hit=in.nextInt();
                f.setRoll_3_pins_knocked_down(third_hit);
                //update(i,third_hit);
            }


            f.setAbsolute_score_of_frame((first_hit+second_hit));//setting ABSOLUTE score per frame=first hit + second hit

            if((f.isFrame_Spare()==false)  && (f.isFrame_Strike()==false))
            {
                f.setTotal_score_of_this_frame((first_hit+second_hit));//setting TOTAL score per frame=first hit + second hit
            }

            this.getGame().getFrames()[i]=f;  //Finally setting the ith frame object in Game

            if(i==9)//setting the score for frame 10
            {
                this.getGame().getFrames()[9].setTotal_score_of_this_frame(first_hit+second_hit+third_hit);
                this.getGame().getFrames()[9].setAbsolute_score_of_frame(first_hit+second_hit+third_hit);
            }



            f=null;  //Freeing the memory.

            //printing starts
            System.out.println("SCORES - ");
            for(int j=0;j<=i;j++)
            {

                //Normal case (Neither STRIKE nor SPARE)
                if((this.getGame().getFrames()[j].isFrame_Strike()==false)&& (this.getGame().getFrames()[j].isFrame_Spare()==false))
                {
                    //System.out.println("The Individual score for "+(j+1)+" Frame is :"+this.getGame().getFrames()[j].getAbsolute_score_of_frame());
                    System.out.println("The Individual score for "+(j+1)+" Frame is :"+this.getGame().getFrames()[j].getTotal_score_of_this_frame());
                }
                STRIKE not calculated yet
                if((this.getGame().getFrames()[j].isFrame_Strike()==true)&&(this.getGame().getFrames()[j].getTotal_score_of_this_frame()==-99))
                {
                    System.out.println("The Individual score for "+(j+1)+" Frame is : X");
                }
                //STRIKE Calculated
                if((this.getGame().getFrames()[j].isFrame_Strike()==true)&&(this.getGame().getFrames()[j].getTotal_score_of_this_frame()!=-99))
                {
                    System.out.println("The Individual score for "+(j+1)+" Frame is : "+this.getGame().getFrames()[j].getTotal_score_of_this_frame());
                }
                //SPARE not calculated yet
                if((this.getGame().getFrames()[j].isFrame_Spare()==true)&&(this.getGame().getFrames()[j].getTotal_score_of_this_frame()==-88))
                {
                    System.out.println("The Individual score for "+(j+1)+" Frame is : /");
                }
                //SPARE Calculated
                if((this.getGame().getFrames()[j].isFrame_Spare()==true)&&(this.getGame().getFrames()[j].getTotal_score_of_this_frame()!=-88))
                {
                    System.out.println("The Individual score for "+(j+1)+" Frame is : "+this.getGame().getFrames()[j].getTotal_score_of_this_frame());
                }

                System.out.println("and the Cumulative score for Frame "+(j+1)+" is :"+getCumulativeScore(j));
            }
            //printing ends
        }//end of for


    }

    public int getCumulativeScore(int frameNumber)
    {
        int sum = 0;

        for(int j=0;j <= frameNumber; j++ )
        {
            if((userFrames.get(frameNumber).getTotalFrameScore() == -99) || (userFrames.get(frameNumber).getTotalFrameScore() ==- 88))
                sum= sum + 0;
            else
                sum= sum + (Frame)userFrames[frameNumber].
        }
        return sum;
    }

    public void update(int j, Roll roll)//Update the score of previous frames as per the score of current frame in case of SPARE/STRIKE

    {
        for(int i=j-1;i>=0;i--)
        {
            if((userFrames.get(i).isStrike()) && (userFrames.get(i).getTotalFrameScore() == -99)&&
                    (userFrames.get(i).isIs_first_updated()==false)&&(userFrames.get(i).isIs_second_updated()==false))
            {
                userFrames.get(i).setIs_first_updated(true);
                userFrames.get(i).setAbsolute_score_of_frame(10+pins_hit);

                continue;
            }
            if((userFrames.get(i).isFrame_Strike()==true) && (userFrames.get(i).getTotal_score_of_this_frame()==-99)
                    && (userFrames.get(i).isIs_first_updated()==true)&&(tuserFrames.get(i).isIs_second_updated()==false) )
            {
                userFrames.get(i).setIs_second_updated(true);
                userFrames.get(i).setAbsolute_score_of_frame((userFrames.get(i).getAbsolute_score_of_frame())+(pins_hit));
                userFrames.get(i).setTotal_score_of_this_frame(userFrames.get(i).getAbsolute_score_of_frame());

                continue;
            }

            if((userFrames.get(i).isFrame_Spare()==true) && (userFrames.get(i).getTotal_score_of_this_frame()==-88)&&
                    (userFrames.get(i).isIs_first_updated()==false))
            {
                userFrames.get(i).setIs_first_updated(true);
                userFrames.get(i).setAbsolute_score_of_frame(10+pins_hit);
                userFrames.get(i).setTotal_score_of_this_frame(userFrames.get(i).getAbsolute_score_of_frame());

                continue;
            }
        }//end of for
    }


    //Getters and Setters
    public Game getGame() {
        return game;
    }


    public void setGame(Game game) {
        this.game = game;
    }

}
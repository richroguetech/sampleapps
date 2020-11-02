package com.cardflight.mobilebowling;

import com.cardflight.mobilebowling.model.Frame;
import com.cardflight.mobilebowling.model.Roll;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class FrameUnitTest {
    private Roll roll1;  // a strike
    private Roll roll2; // not a strike...
    private Roll roll3; // gutter
    private Roll roll4;
    private Frame frame;


    @Before //This is executed before the @Test executes
    public void setUp(){
        roll1 = new Roll("X");
        roll2 = new Roll("/");
        roll3 = new Roll("-");
        roll4 = new Roll("5");
    }

    @Test
    public void testFrameStrike() {
        int numPins = roll1.getNumPins();
        frame = new Frame(1, roll1);
        assertEquals(10, frame.getFirstRoll().getNumPins());
        assertEquals(true, frame.isStrike());
        assertEquals(10, frame.getTotalFrameScore());
        assertEquals(false, frame.isFinished());
    }

    @Test
    public void testFrameStrikeWithBonusBall() {
        int numPins = roll1.getNumPins();
        frame = new Frame(1, roll1);
        frame.processBonus(5);
        frame.processBonus2(10);
        assertEquals(10, frame.getFirstRoll().getNumPins());
        assertEquals(true, frame.isStrike());
        assertEquals(25, frame.getTotalFrameScore());
        assertEquals(true, frame.isFinished());
    }

    @Test
    public void testFrameStrikeCantHaveAdditionalBalls() {
        int numPins = roll1.getNumPins();
        frame = new Frame(1, roll1);
        frame.insertSecondRoll(roll2);
        assertNull(frame.getSecondRoll());
        assertEquals(10, frame.getTotalFrameScore());
        assertEquals(false, frame.isFinished());
    }

    @Test
    public void testProcessFrameNoStrike() {
        Roll roll1 = new Roll("6");
        Roll roll2 = new Roll("3");
        frame = new Frame(1, roll1);
        frame.insertSecondRoll(roll2);
        assertEquals(9, frame.getTotalFrameScore());
        assertEquals(true, frame.isFinished());
    }

    @After //This is executed after the @Test executes
    public void tearDown(){
        //
    }

}


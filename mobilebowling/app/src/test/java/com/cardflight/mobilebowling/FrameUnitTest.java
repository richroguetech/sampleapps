package com.cardflight.mobilebowling;

import com.cardflight.mobilebowling.model.Frame;
import com.cardflight.mobilebowling.model.Roll;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

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
        frame = new Frame(roll1);
        assertEquals(10, frame.getFirstRoll());
        assertEquals(true, frame.isStrike());
        assertEquals(10, frame.getTotalFrameScore());
        assertEquals(false, frame.isFinished());
    }


    @After //This is executed after the @Test executes
    public void tearDown(){
        System.out.println("Done with testing");
    }

}


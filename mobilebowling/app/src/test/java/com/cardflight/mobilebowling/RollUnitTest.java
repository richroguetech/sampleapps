package com.cardflight.mobilebowling;

import com.cardflight.mobilebowling.model.Roll;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class RollUnitTest {
    private Roll roll1;  // a strike
    private Roll roll2; // not a strike...
    private Roll roll3; // gutter
    private Roll roll4;

    private Roll invalidRoll1;

    @Before //This is executed before the @Test executes
    public void setUp(){
        roll1 = new Roll("X");
        roll2 = new Roll("/");
        roll3 = new Roll("-");
        roll4 = new Roll("5");
        invalidRoll1 = new Roll("11"); // outside of bounds
    }

    @Test
    public void testRollSetups() {
        int numPins = roll1.getNumPins();
        String rollValue = roll1.getRollValue();
        assertEquals(10, roll1.getNumPins());
        assertEquals(1, roll1.ballNumber);
        System.out.println(rollValue);

        assertEquals(5, roll4.getNumPins());
        assertEquals(1, roll4.ballNumber);
    }

    @Test
    public void testInvalidRolls() {
        int numPins = invalidRoll1.getNumPins();
        String rollValue = invalidRoll1.getRollValue();
        assertEquals(-1, invalidRoll1.getNumPins());
        assertNull(invalidRoll1.getType());
    }


    @After //This is executed after the @Test executes
    public void tearDown(){
        System.out.println("Done with testing");
    }

}

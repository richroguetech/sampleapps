package com.cardflight.mobilebowling;

import com.cardflight.mobilebowling.model.Frame;
import com.cardflight.mobilebowling.model.Roll;
import com.cardflight.mobilebowling.presenter.ScoreController;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ScoreControllerUnitTest {
    private Roll roll1;  // a strike
    private Roll roll2; // not a strike...
    private Roll roll3; // gutter
    private Roll roll4;
    private Frame frame;
    private ScoreController scoreController;


    @Before //This is executed before the @Test executes
    public void setUp(){
        roll1 = new Roll("X");
        roll2 = new Roll("/");
        roll3 = new Roll("-");
        roll4 = new Roll("5");
        scoreController = new ScoreController();
    }

    @Test
    public void testFrameStrike() {
        Roll roll2 = new Roll("6");
        Roll roll3 = new Roll("3");
       scoreController.processRoll(1, roll1, false);
       scoreController.processRoll(2, roll2, false);
       scoreController.processRoll(2, roll3, false);
       assertEquals(19, scoreController.getCumulativeScore(1));
        assertEquals(28, scoreController.getCumulativeScore(2));
    }

    @Test
    public void testFrameSpare() {
        Roll roll1 = new Roll("6");
        Roll roll2 = new Roll("/");
        Roll roll3 = new Roll("3");
        scoreController.processRoll(1, roll1, false);
        scoreController.processRoll(1, roll2, false);
        scoreController.processRoll(2, roll3, false);
        assertEquals(13, scoreController.getCumulativeScore(1));
        assertEquals(16, scoreController.getCumulativeScore(2));
    }

    @Test
    public void testGame1() {
        Roll roll1 = new Roll("5");
        Roll roll2 = new Roll("/");
        Roll roll3 = new Roll("4");
        Roll roll4 = new Roll("5");

        Roll roll5 = new Roll("8");
        Roll roll6 = new Roll("/");
        Roll roll7 = new Roll("X");
        Roll roll8 = new Roll("-");
        Roll roll9 = new Roll("/");
        Roll roll10 = new Roll("X");
        Roll roll11 = new Roll("6");
        Roll roll12 = new Roll("2");
        Roll roll13 = new Roll("X");
        Roll roll14 = new Roll("4");
        Roll roll15 = new Roll("/");
        Roll roll16 = new Roll("X");
        Roll roll17 = new Roll("X");
        Roll roll18 = new Roll("-");
        scoreController.processRoll(1, roll1, false);
        scoreController.processRoll(1, roll2, false);
        scoreController.processRoll(2, roll3, false);
        scoreController.processRoll(2, roll4, false);
        scoreController.processRoll(3, roll5, false);
        scoreController.processRoll(3, roll6, false);
        scoreController.processRoll(4, roll7, false);
        scoreController.processRoll(5, roll8, false);
        scoreController.processRoll(5, roll9, false);
        scoreController.processRoll(6, roll10, false);
        scoreController.processRoll(7, roll11, false);
        scoreController.processRoll(7, roll12, false);
        scoreController.processRoll(8, roll13, false);
        scoreController.processRoll(9, roll14, false);
        scoreController.processRoll(9, roll15, false);
        scoreController.processRoll(10, roll16, false);
        scoreController.processRoll(10, roll17, false);
        scoreController.processRoll(10, roll18, false);

        assertEquals(14, scoreController.getCumulativeScore(1));
        assertEquals(23, scoreController.getCumulativeScore(2));
        assertEquals(43, scoreController.getCumulativeScore(3));
        assertEquals(63, scoreController.getCumulativeScore(4));
        assertEquals(83, scoreController.getCumulativeScore(5));
        assertEquals(101, scoreController.getCumulativeScore(6));
        assertEquals(109, scoreController.getCumulativeScore(7));
        assertEquals(129, scoreController.getCumulativeScore(8));
        assertEquals(149, scoreController.getCumulativeScore(9));
        assertEquals(169, scoreController.getCumulativeScore(10));
    }

    @After //This is executed after the @Test executes
    public void tearDown(){
        //
    }

}


package com.cardflight.mobilebowling;

import com.cardflight.mobilebowling.model.Frame;
import com.cardflight.mobilebowling.model.Roll;
import com.cardflight.mobilebowling.presenter.ScorePresenter;
import com.cardflight.mobilebowling.presenter.ScorePresenterImpl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ScorePresenterUnitTest {
    private Frame frame;
    private ScorePresenter scorePresenter;

    @Before //This is executed before the @Test executes
    public void setUp(){
        scorePresenter = new ScorePresenterImpl();
        scorePresenter.resetGame();
    }

    @Test
    public void testFrameStrike() {
        Roll roll1 = new Roll("X");
        Roll roll2 = new Roll("6");
        Roll roll3 = new Roll("3");
        scorePresenter.processRoll(1, roll1, false);
        scorePresenter.processRoll(2, roll2, false);
        scorePresenter.processRoll(2, roll3, false);
        assertEquals(19, scorePresenter.getCumulativeScore(1));
        assertEquals(28, scorePresenter.getCumulativeScore(2));
    }

    @Test
    public void testFrameSpare() {
        Roll roll1 = new Roll("6");
        Roll roll2 = new Roll("/");
        Roll roll3 = new Roll("3");
        scorePresenter.processRoll(1, roll1, false);
        scorePresenter.processRoll(1, roll2, false);
        scorePresenter.processRoll(2, roll3, false);
        assertEquals(13, scorePresenter.getCumulativeScore(1));
        assertEquals(16, scorePresenter.getCumulativeScore(2));
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
        scorePresenter.processRoll(1, roll1, false);
        scorePresenter.processRoll(1, roll2, false);
        scorePresenter.processRoll(2, roll3, false);
        scorePresenter.processRoll(2, roll4, false);
        scorePresenter.processRoll(3, roll5, false);
        scorePresenter.processRoll(3, roll6, false);
        scorePresenter.processRoll(4, roll7, false);
        scorePresenter.processRoll(5, roll8, false);
        scorePresenter.processRoll(5, roll9, false);
        scorePresenter.processRoll(6, roll10, false);
        scorePresenter.processRoll(7, roll11, false);
        scorePresenter.processRoll(7, roll12, false);
        scorePresenter.processRoll(8, roll13, false);
        scorePresenter.processRoll(9, roll14, false);
        scorePresenter.processRoll(9, roll15, false);
        scorePresenter.processRoll(10, roll16, false);
        scorePresenter.processRoll(10, roll17, false);
        scorePresenter.processRoll(10, roll18, false);

        assertEquals(14, scorePresenter.getCumulativeScore(1));
        assertEquals(23, scorePresenter.getCumulativeScore(2));
        assertEquals(43, scorePresenter.getCumulativeScore(3));
        assertEquals(63, scorePresenter.getCumulativeScore(4));
        assertEquals(83, scorePresenter.getCumulativeScore(5));
        assertEquals(101, scorePresenter.getCumulativeScore(6));
        assertEquals(109, scorePresenter.getCumulativeScore(7));
        assertEquals(129, scorePresenter.getCumulativeScore(8));
        assertEquals(149, scorePresenter.getCumulativeScore(9));
        assertEquals(169, scorePresenter.getCumulativeScore(10));
    }

    @After //This is executed after the @Test executes
    public void tearDown(){
        //
    }

}


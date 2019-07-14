package it.polimi.se2018.controller;

import it.polimi.se2018.controller.effects.checkMethods.checkDieChecker.CheckDieDecrement;
import it.polimi.se2018.model.Die;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * DieReduceChecker Tester
 * This class tests if a die correctly reduces his value
 * @author Luca Massini
 * @author Davide Lorenzi (JDocs)
 */
public class TestCheckDieDecrement {
    private CheckDieDecrement checkDieDecrement;
    private Die tmp1;
    private Die tmp2;
    private Die tmp3;
    private Die tmp4;

    /**
     * SetUp
     * This method prepare the test ambient
     */
    @Before
    public void setUp(){
        this.checkDieDecrement = new CheckDieDecrement();
        tmp1 = new Die("Red",5);
        tmp2 = new Die("Purple", 1);
        tmp3 = new Die("Red", 3);
        tmp4 = new Die("Purple", 2);
    }

    /**
     * Tear Down
     * This method prepare the test ambient for a new test
     */
    @After
    public void tearDown(){
        this.checkDieDecrement = null;
        tmp1 = null;
        tmp2 = null;
        tmp3 = null;
        tmp4 = null;
    }

    /**
     * DieReducerChecker Tester
     * This method tests if a die correctly reduces his value
     */
    @Test
    public void testCheckDecrement(){
        assertTrue(checkDieDecrement.checkDieChecker(tmp1));
        assertFalse(checkDieDecrement.checkDieChecker(tmp2));
        assertTrue(checkDieDecrement.checkDieChecker(tmp3));
        assertTrue(checkDieDecrement.checkDieChecker(tmp4));
    }

}

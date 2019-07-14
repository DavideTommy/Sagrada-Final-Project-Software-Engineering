package it.polimi.se2018.controller;

import it.polimi.se2018.controller.effects.checkMethods.checkDieChecker.CheckDieEnhance;
import it.polimi.se2018.model.Die;

import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Die Turner Tester
 * This class tests if a die correctly increase his value
 * @author Luca Massini
 * @author Davide Lorenzi (JDocs)
 */
public class TestCheckDieEnhance {
    private CheckDieEnhance enhancerCheck;
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
        enhancerCheck = new CheckDieEnhance();
        tmp1 = new Die("Red",6);
        tmp2 = new Die("Purple",1);
        tmp3 = new Die("Red", 2);
        tmp4 = new Die("Yellow", 5);
    }

    /**
     * Tear Down
     * This method prepare the test ambient for a new test
     */
    @After
    public void tearDown(){
        enhancerCheck = null;
        tmp1 = null;
        tmp2 = null;
        tmp3 = null;
        tmp4 = null;
    }

    /**
     * DieEnhancerChecker Tester
     * This method tests if a die correctly increase his value
     */
    @Test
    public void test(){
        assertFalse(enhancerCheck.checkDieChecker(tmp1));
        assertTrue(enhancerCheck.checkDieChecker(tmp2));
        assertTrue(enhancerCheck.checkDieChecker(tmp3));
        assertTrue(enhancerCheck.checkDieChecker(tmp4));
    }

}

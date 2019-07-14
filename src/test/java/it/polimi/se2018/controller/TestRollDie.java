package it.polimi.se2018.controller;

import it.polimi.se2018.controller.effects.diceModifiersMethods.RollDie;
import it.polimi.se2018.model.Die;

import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Die Roller Tester
 * This class tests if a die correctly rolls his value
 * @author Luca Massini
 * @author Davide Lorenzi (JDocs)
 */
public class TestRollDie{

    private RollDie roller;
    private Die tmp1;

    /**
     * SetUp
     * This method prepare the test ambient
     */
    @Before
    public void setUp(){
        roller = new RollDie();
    }

    /**
     * Tear Down
     * This method prepare the test ambient for a new test
     */
    @After
    public void tearDown(){
        roller = null;
        tmp1 = null;
    }

    /**
     * Turner Tester 1
     * This method tests if a die correctly rolls his value
     */
    @Test
    public void test(){
        tmp1 = new Die("Red",2);
        roller.modifyDie(tmp1);
        assertTrue(tmp1.getDieNumber()>=1 && tmp1.getDieNumber()<=6);
        tmp1 = new Die("Red",3);
        assertTrue(tmp1.getDieNumber()>=1 && tmp1.getDieNumber()<=6);
        tmp1 = new Die("Red",6);
        assertTrue(tmp1.getDieNumber()>=1 && tmp1.getDieNumber()<=6);
    }

    /**
     * Turner Tester2
     * This method test if a die correctly rolls his value
     */
    @Test
    public void test1(){
        boolean testPassed = false;
        tmp1 = new Die("Red",5);
        for(int i=0; i<1000000; i++){
            roller.modifyDie(tmp1);
            if(tmp1.getDieNumber()!=5){
                testPassed = true;
                i = 1000000;
            }
        }
        assertTrue(testPassed);
    }
}

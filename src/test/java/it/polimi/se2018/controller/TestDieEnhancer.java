package it.polimi.se2018.controller;

import it.polimi.se2018.controller.effects.diceModifiersMethods.DieEnhancer;
import it.polimi.se2018.model.Die;

import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Die Enhancer Tester
 * This class tests if a die correctly increase his value
 * @author Luca Massini
 * @author Davide Lorenzi (JDocs)
 */
public class TestDieEnhancer {

    private DieEnhancer enhancer;
    private Die tmp;

    /**
     * SetUp
     * This method prepare the test ambient
     */
    @Before
    public void setUp(){
        enhancer = new DieEnhancer();
    }

    /**
     * Tear Down
     * This method prepare the test ambient for a new test
     */
    @After
    public void tearDown(){
        enhancer = null;
        tmp= null;
    }

    /**
     * DieEnhancer Tester
     * This method tests if a die correctly increase his value
     */
    @Test
    public void test(){
        tmp = new Die("Red", 5);
        assertEquals(5, tmp.getDieNumber());
        enhancer.modifyDie(tmp);
        assertEquals(6, tmp.getDieNumber());
        tmp = new Die("Purple", 3);
        enhancer.modifyDie(tmp);
        assertEquals(4, tmp.getDieNumber());
    }

}

package it.polimi.se2018.controller;

import it.polimi.se2018.controller.effects.diceModifiersMethods.DieReducer;
import it.polimi.se2018.model.Die;

import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Die Reducer Tester
 * This class tests if the function correctly reduces the value of a die
 * @author FLuca Massini
 * @author Davide Lorenzi (JDocs)
 */
public class TestDieReducer {
    private DieReducer reducer;
    private Die tmp;

    /**
     * SetUp
     * This method prepare the test ambient
     */
    @Before
    public void setUp(){
        reducer = new DieReducer();
    }

    /**
     * Tear Down
     * This method prepare the test ambient for a new test
     */
    @After
    public void tearDown(){
        tmp = null;
        reducer = null;
    }

    /**
     * Reducer Tester
     * This method tests if the function correctly reduces the value of a die
     */
    @Test
    public void test(){
        tmp = new Die("Red", 3);
        assertEquals(3,tmp.getDieNumber());
        reducer.modifyDie(tmp);
        assertEquals(2,tmp.getDieNumber());
        tmp = new Die( "Yellow", 6);
        reducer.modifyDie(tmp);
        assertEquals(5,tmp.getDieNumber());
    }
}

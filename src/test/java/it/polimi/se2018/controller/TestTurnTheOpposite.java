package it.polimi.se2018.controller;

import it.polimi.se2018.controller.effects.diceModifiersMethods.TurnTheDieOpposite;
import it.polimi.se2018.model.Die;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Die Turner Tester
 * This class tests if a die correctly turns on the other side
 * @author Luca Massini
 * @author Davide Lorenzi (JDocs)
 */
public class TestTurnTheOpposite {
    private TurnTheDieOpposite turn;
    private Die tmp;

    /**
     * SetUp
     * This method prepare the test ambient
     */
    @Before
    public void setUp(){
        turn = new TurnTheDieOpposite();
    }

    /**
     * Tear Down
     * This method prepare the test ambient for a new test
     */
    @After
    public void tearDown(){
        turn = null;
        tmp = null;
    }

    /**
     * Turner Tester
     * This method tests if a die correctly turns on the other side
     */
    @Test
    public void test(){
        tmp = new Die("Yellow",3);
        assertEquals(tmp.getDieNumber(),new Die("Yellow",3).getDieNumber());
        assertEquals("Yellow", tmp.getDieColor());
        assertEquals(3,tmp.getDieNumber());
        turn.modifyDie(tmp);
        assertEquals(4,tmp.getDieNumber());
        tmp = new Die("Red",1);
        turn.modifyDie(tmp);
        assertEquals(6,tmp.getDieNumber());
        tmp = new Die("Purple",2);
        turn.modifyDie(tmp);
        assertEquals(5,tmp.getDieNumber());
    }

}

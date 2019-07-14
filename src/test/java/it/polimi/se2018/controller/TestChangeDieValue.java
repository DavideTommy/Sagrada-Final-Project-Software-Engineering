package it.polimi.se2018.controller;

import it.polimi.se2018.controller.effects.diceModifiersMethods.ChangeDieValue;
import it.polimi.se2018.model.Die;

import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Value Changer Tester
 * This class tests if a die correctly changes his value
 * @author Luca Massini
 * @author Davide Lorenzi (JDocs)
 */
public class TestChangeDieValue {

    private ChangeDieValue changer;
    private Die tmp1;
    private Die tmp2;

    /**
     * SetUp
     * This method prepare the test ambient
     */
    @Before
    public void setUp(){
        tmp1 = new Die("Red",4);
        tmp2 = new Die("Yellow",3);
    }

    /**
     * Tear Down
     * This method prepare the test ambient for a new test
     */
    @After
    public void tearDown(){
        changer = null;
        tmp1 = null;
        tmp2 = null;
    }

    /**
     * ValueChangerChecker Tester
     * This method tests if a die correctly changes his value
     */
    @Test
    public void test(){
        assertEquals(4,tmp1.getDieNumber());
        assertEquals(3,tmp2.getDieNumber());
        changer = new ChangeDieValue(3);
        changer.modifyDie(tmp1);
        changer.modifyDie(tmp2);
        assertEquals(3,tmp1.getDieNumber());
        assertEquals(tmp1.getDieNumber(),tmp2.getDieNumber());
    }

}

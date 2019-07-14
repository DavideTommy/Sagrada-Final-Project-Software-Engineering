package it.polimi.se2018.controller;

import it.polimi.se2018.controller.effects.diceModifiersMethods.*;
import it.polimi.se2018.model.Die;

import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Chose Die Tester
 * This class tests if a die is correctly chosen for a modification
 * @author Luca Massini
 * @author Davide Lorenzi (JDocs)
 */
public class TestChooseDieModifier {
    private ChooseDieModifier choose;
    private Die tmp;

    /**
     * SetUp
     * This method prepare the test ambient
     */
    @Before
    public void setUp(){
        choose = new ChooseDieModifier(new DieReducer());
    }

    /**
     * Tear Down
     * This method prepare the test ambient for a new test
     */
    @After
    public void tearDown(){
        choose = null;
        tmp = null;
    }

    /**
     * DieChooser Tester
     * This method tests if a die is correctly chosen for a modification
     */
    @Test
    public void test(){
        tmp = new Die("Red",5);
        choose.modifyDie(tmp);
        assertEquals(4,tmp.getDieNumber());
        choose = new ChooseDieModifier(new DieEnhancer());
        choose.modifyDie(tmp);
        assertEquals(5,tmp.getDieNumber());
        choose = new ChooseDieModifier(new TurnTheDieOpposite());
        choose.modifyDie(tmp);
        assertEquals(2,tmp.getDieNumber());
        choose = new ChooseDieModifier(new RollDie());
        Die tmp2 = new Die(tmp.getDieColor(),tmp.getDieNumber());
        int i;
        int max=1000000;
        boolean testPassed=false;
        for(i=0;i<max;i++){
            choose.modifyDie(tmp);
            if(tmp.getDieNumber()!=tmp2.getDieNumber()){
                testPassed=true;
                i = max;
            }
        }
        assertTrue(testPassed);
    }
}

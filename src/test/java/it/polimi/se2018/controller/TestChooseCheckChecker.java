package it.polimi.se2018.controller;
import it.polimi.se2018.controller.effects.checkMethods.checkDieChecker.CheckDieDecrement;
import it.polimi.se2018.controller.effects.checkMethods.checkDieChecker.CheckDieEnhance;
import it.polimi.se2018.controller.effects.checkMethods.checkDieChecker.ChooseCheckChecker;
import it.polimi.se2018.model.Die;

import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Choose Check Checker Tester
 * This class tests if the function chooses the correct check checker
 * @author Luca Massini
 * @author Davide Lorenzi (JDocs)
 */
public class TestChooseCheckChecker{
    private ChooseCheckChecker choose;
    private Die tmp1;
    private Die tmp2;

    /**
     * SetUp
     * This method prepare the test ambient
     */
    @Before
    public void setUp(){
        tmp1 = new Die("Red",6);
        tmp2 = new Die("Yellow",1);
    }

    /**
     * Tear Down
     * This method prepare the test ambient for a new test
     */
    @After
    public void tearDown(){
        tmp1 = null;
        tmp2 = null;
        choose = null;
    }

    /**
     * ChoseCheckChecker Tester
     * This method tests if the function chooses the correct check checker
     */
    @Test
    public void test(){
        choose = new ChooseCheckChecker(new CheckDieEnhance());
        assertFalse(choose.checkModify(tmp1));
        choose = new ChooseCheckChecker(new CheckDieDecrement());
        assertFalse(choose.checkModify(tmp2));
    }

}

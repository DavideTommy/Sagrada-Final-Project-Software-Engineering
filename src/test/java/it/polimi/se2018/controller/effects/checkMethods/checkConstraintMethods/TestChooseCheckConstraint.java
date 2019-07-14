package it.polimi.se2018.controller.effects.checkMethods.checkConstraintMethods;

import org.junit.Assert;
import org.junit.Test;

/**
 * ChoseCheckConstraint Tester
 * This class tests if has been chosen the correct constraints checker
 * @author Federico Lichinchi
 * @author Davide Lorenzi (JDocs)
 */
public class TestChooseCheckConstraint {

    private ChooseCheckConstraint chooseCheckConstraint;

    /**
     * ConstraintChecker Test
     * This method checks if has been chosen the correct constraints checker
     */
    @Test
    public void checkConstraint() {
        chooseCheckConstraint = new ChooseCheckConstraint(new CheckDieNoColorConstraints());
        Assert.assertNotNull(chooseCheckConstraint.getConstraint());
        Assert.assertTrue(chooseCheckConstraint.getConstraint() instanceof CheckDieNoColorConstraints);
    }
}
package it.polimi.se2018.model.rules;

import it.polimi.se2018.model.BoxOfSchemeCards;
import it.polimi.se2018.model.Die;
import it.polimi.se2018.model.SchemeCard;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 * TestValueConstraint
 * Test the efficiency of the application of ValueConstraint
 * @author Luca Massini
 * @author Davide Lorenzi (JDocs)
 */
public class TestValueConstraint {
    private BoxOfSchemeCards box;
    private SchemeCard card;
    private ValueConstraint valueConstraint;


    /**
     * SetUp Method
     * This method prepare the test ambient
     */
    @Before
    public void setUp(){
        int[][] matrixOfNumbers;
        box = BoxOfSchemeCards.newBox();
        card = box.request("Virtus");
        matrixOfNumbers = card.getMatrixOfNumbers();
        valueConstraint = new ValueConstraint(matrixOfNumbers);
    }


    /**
     * TearDown Method
     * This method prepare the test ambient foe a new test
     */
    @After
    public void tearDown(){
        box = null;
        card = null;
    }

    /**
     * ValueConstraint Tester
     * This test verifies that the value constraint are effectively respected
     */
    @Test
    public void testIsValidConstraint(){
        assertTrue(valueConstraint.isValidConstraint(new Die("Green",5),0,3));
        assertTrue(valueConstraint.isValidConstraint(new Die("Green",1),0,1));
        assertTrue(valueConstraint.isValidConstraint(new Die("Green",3),2,1));
        assertFalse(valueConstraint.isValidConstraint(new Die("Yellow",3),0,2));
        assertTrue(valueConstraint.isValidConstraint(new Die("Green",4),2,3));
        assertFalse(valueConstraint.isValidConstraint(new Die("Green",5),0,0));
        assertTrue(valueConstraint.isValidConstraint(new Die("Green",5),1,0));
    }

}

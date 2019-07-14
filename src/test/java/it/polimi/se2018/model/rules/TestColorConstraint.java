package it.polimi.se2018.model.rules;
import it.polimi.se2018.model.BoxOfSchemeCards;
import it.polimi.se2018.model.Die;
import it.polimi.se2018.model.SchemeCard;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * TestCard Class
 * This class tests the correct application of constraint
 * @author Luca Massini
 * @author Davide Lorenzi (JDocs)
 */
public class TestColorConstraint {
    private ColorConstraint constraint;
    private SchemeCard card;
    private BoxOfSchemeCards box;

    /**
     * SetUp Method
     * This method initializes the test class
     */
    @Before
    public void setUp(){
        box = BoxOfSchemeCards.newBox();
        card = box.request("Virtus");
        constraint = new ColorConstraint(card.getMatrixOfColors());
    }

    /**
     * TearDown Method
     * Prepare the test ambient to a new test
     */
    @After
    public void tearDown(){
        box = null;
        card = null;
        constraint = null;
    }

    /**
     * ValidConstraint Test
     * This method test the correct application of Valid Constraint
     */
    @Test
    public void testIsValidConstraint(){
        assertTrue(constraint.isValidConstraint(new Die("Yellow",3),0,0));
        assertTrue(constraint.isValidConstraint(new Die("Green",3),0,4));
        assertFalse(constraint.isValidConstraint(new Die("Yellow",3),3,1));
        assertTrue(constraint.isValidConstraint(new Die("Green",3),3,1));
        assertTrue(constraint.isValidConstraint(new Die("Green",3),3,4));
        assertTrue(constraint.isValidConstraint(new Die("Green",3),1,0));
    }
}

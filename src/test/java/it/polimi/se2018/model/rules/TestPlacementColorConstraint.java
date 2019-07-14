package it.polimi.se2018.model.rules;
import it.polimi.se2018.model.BoxOfSchemeCards;
import it.polimi.se2018.model.Die;
import it.polimi.se2018.model.Grid;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * TestPlacementColorConstraint Tester
 * Test the efficiency of the application of PlacementColor
 * @author Luca Massini
 * @author Davide Lorenzi (JDocs)
 */
public class TestPlacementColorConstraint {
    private Grid grid1;
    private Grid grid2;
    private PlacementColorConstraint constraint;
    private BoxOfSchemeCards box = BoxOfSchemeCards.newBox();


    /**
     * SetUp Method
     * This method create the test ambient
     */
    @Before
    public void setUp(){
        grid2 = new Grid(box.request("Virtus"));
        constraint = new PlacementColorConstraint();
        grid1  = new Grid(box.request("Virtus"));
        grid1.addDie(new Die("Green",2),0,0);
    }

    /**
     * SetUp Method
     * This method set the test ambient for a new test
     */
    @After
    public void tearDown(){
        constraint = null;
    }

    /**
     * ColorPlacementChecker Tester One
     * This test verifies the correct functioning of the ColorPlacementChecker
     */
    @Test
    public void test1CheckPlacements(){
        Die dice = new Die("Green",3);
        assertFalse(constraint.checkPlacements(1,0, grid1, dice));
        dice = new Die("Yellow",5);
        assertTrue(constraint.checkPlacements(1,0, grid1, dice));
        grid1.addDie(dice,1,0);
        Die dice1 = new Die("Yellow",6);
        assertTrue(constraint.checkPlacements(2,2, grid1, dice1));
        dice1 = new Die("Yellow",5);
        assertFalse(constraint.checkPlacements(1,1, grid1, dice1));
        Die dice2 = new Die("Purple",7);
        grid1.addDie(dice2,3,3);
        Die dice3 = new Die("Purple",3);
        assertFalse(constraint.checkPlacements(3,4, grid1,  dice3));
        dice3 = new Die("Yellow",5);
        assertTrue(constraint.checkPlacements(3,4, grid1,dice3));
    }


    /**
     * ColorPlacementChecker Tester Two
     * This test verifies the correct functioning of the ColorPlacementChecker
     */
    @Test
    public void test2CheckPlacements(){
        assertTrue(constraint.checkPlacements(1,2,grid2,new Die("Blue",3)));
        grid2.addDie(new Die("Blue",3),1,2);
        assertFalse(constraint.checkPlacements(1,3,grid2,new Die("Blue",3)));
        grid2.addDie(new Die("Blue",3),1,0);
        assertFalse(constraint.checkPlacements(0,0,grid2,new Die("Blue",5)));
        assertTrue(constraint.checkPlacements(0,0,grid2,new Die("Yellow",3)));
    }

}

package it.polimi.se2018.model.rules;
import static org.junit.Assert.*;

import it.polimi.se2018.model.BoxOfSchemeCards;
import it.polimi.se2018.model.Die;
import it.polimi.se2018.model.Grid;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * TestPlacementOnlyConstraint Tester
 * Test the efficiency of the application of PlacementOnly
 * @author Luca Massini
 * @author Davide Lorenzi (JDocs)
 */
public class TestPlacementOnlyConstraint {
    private Grid grid1;
    private Grid grid2;
    private PlacementOnlyConstraint constraint;
    private BoxOfSchemeCards box = BoxOfSchemeCards.newBox();

    /**
     * SetUp Method
     * This method prepare the test ambient
     */
    @Before
    public void setUp(){
        grid1 = new Grid(box.request("Virtus"));
        grid2 = new Grid(box.request("Virtus"));
        grid1.addDie(new Die("Blue",2),2,2);
        constraint = new PlacementOnlyConstraint();
    }

    /**
     * SetUpTearDown Method
     * This method prepare the test ambient for a new test
     */
    @After
    public void tearDown(){
        grid1 = null;
        grid2 = null;
        constraint = null;
    }

    /**
     * PlacementChecker Tester One
     * This test verifies the correct functioning of the PlacementChecker
     */
    @Test
    public void test1CheckPlacements(){
        assertFalse(constraint.checkPlacements(0,0,grid2,new Die("Green",2)));
    }

    /**
     * PlacementChecker Tester Two
     * This test verifies the correct functioning of the PlacementChecker
     */
    @Test
    public void test2CheckPlacements(){
        assertTrue(constraint.checkPlacements(1,1,grid1,new Die("Green",2)));
        assertTrue(constraint.checkPlacements(1,3,grid1,new Die("Green",2)));

        grid1.addDie(new Die("Green",2),1,1);
        assertFalse(constraint.checkPlacements(0,4,grid1,new Die("Green",2)));
        assertFalse(constraint.checkPlacements(0,3,grid1,new Die("Green",2)));
        assertTrue(constraint.checkPlacements(0,0,grid1,new Die("Green",2)));
        assertTrue(constraint.checkPlacements(2,1,grid1,new Die("Green",2)));

        grid1.addDie(new Die("Green",2),2,1);
        assertTrue(constraint.checkPlacements(3,0,grid1,new Die("Green",2)));
        assertTrue(constraint.checkPlacements(3,1,grid1,new Die("Green",2)));
        assertTrue(constraint.checkPlacements(2,0,grid1,new Die("Green",2)));
        assertTrue(constraint.checkPlacements(2,3,grid1,new Die("Green",2)));
        assertTrue(constraint.checkPlacements(3,0,grid1,new Die("Green",2)));
        assertTrue(constraint.checkPlacements(1,2,grid1,new Die("Green",2)));
        assertTrue(constraint.checkPlacements(1,0,grid1,new Die("Green",2)));
        assertTrue(constraint.checkPlacements(3,2,grid1,new Die("Green",2)));




    }

}

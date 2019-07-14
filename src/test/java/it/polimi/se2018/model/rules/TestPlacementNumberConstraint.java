package it.polimi.se2018.model.rules;
import static org.junit.Assert.*;

import it.polimi.se2018.model.BoxOfSchemeCards;
import it.polimi.se2018.model.Die;
import it.polimi.se2018.model.Grid;
import org.junit.Before;
import org.junit.Test;

/**
 * TestPlacementValueConstraint
 * Test the efficiency of the application of PlacementValue
 * @author Luca Massini
 * @author Davide Lorenzi (JDocs)
 */
public class TestPlacementNumberConstraint {
    private PlacementNumberConstraint constraint;
    private Grid grid;
    private BoxOfSchemeCards box = BoxOfSchemeCards.newBox();


    /**
     * SetUp Method
     * This method prepare the test ambient
     */
    @Before
    public void setUp(){
           constraint = new PlacementNumberConstraint();
           grid = new Grid(box.request("Virtus"));
           grid.addDie(new Die("Green",5),2,2);
    }

    /**
     * NumberPlacementChecker Tester
     * This test verifies the correct functioning of the NumberPlacementChecker
     */
    @Test
    public void testCheckPlacements(){
        assertFalse(constraint.checkPlacements(1,2,grid,new Die("Green",5)));
        assertTrue(constraint.checkPlacements(1,2,grid,new Die("Green",4)));
        assertTrue(constraint.checkPlacements(2,3,grid,new Die("Green",2)));
        assertFalse(constraint.checkPlacements(2,3,grid,new Die("Green",5)));
        assertTrue(constraint.checkPlacements(3,2,grid,new Die("Green",2)));
        assertFalse(constraint.checkPlacements(3,2,grid,new Die("Green",5)));
        assertTrue(constraint.checkPlacements(2,1,grid,new Die("Green",2)));
        assertFalse(constraint.checkPlacements(2,1,grid,new Die("Green",5)));
        assertTrue(constraint.checkPlacements(0,0,grid,new Die("Green",2)));
    }
}

package it.polimi.se2018.model.parsers;

import it.polimi.se2018.model.Grid;
import it.polimi.se2018.model.SchemeCard;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * GridParser Tester
 * This class verifies if the grid parser works properly
 * @author Federico Lichinchi
 * @author Davide Lorenzi (JDocs)
 */
public class TestGridObjectParser {

    GridObjectParser gridObjectParser = new GridObjectParser();

    /**
     * Object Name Getter tester
     * This method verifies if is correctly gotten the name of the type of the object
     */
    @Test
    public void getObjectName() {
        assertEquals("Grid",gridObjectParser.getObjectName());
    }

    /**
     * Object Parser Tester
     * This method verifies if grids are correctly parsed
     */
    @Test
    public void toStringObject() {
        Grid grid = new Grid(new SchemeCard("Virtus"));
        String s = gridObjectParser.toStringObject(grid);
        assertTrue(s.indexOf(grid.getCardName()) > 0);
    }
}
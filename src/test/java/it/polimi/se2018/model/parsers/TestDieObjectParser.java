package it.polimi.se2018.model.parsers;

import it.polimi.se2018.model.Die;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * DieParser Tester
 * This class verifies if the die parser works properly
 * @author Federico Lichinchi
 * @author Davide Lorenzi (JDocs)
 */
public class TestDieObjectParser {

    DieObjectParser dieObjectParser = new DieObjectParser();

    /**
     * Object Name Getter tester
     * This method verifies if is correctly gotten the name of the type of the object
     */
    @Test
    public void getObjectName() {
        assertEquals("Die", dieObjectParser.getObjectName());
    }

    /**
     * Object Parser Tester
     * This method verifies if dice are correctly parsed
     */
    @Test
    public void toStringObject() {
        String s = dieObjectParser.toStringObject(new Die("Green", 4));
        Die die = dieObjectParser.getDie();
        assertTrue(die.getDieNumber() >= 1 && die.getDieNumber() <= 6);
        assertTrue(die.getDieColor().equals("Purple") || die.getDieColor().equals("Red") ||
                die.getDieColor().equals("Yellow") || die.getDieColor().equals("Blue") || die.getDieColor().equals("Green"));
    }
}
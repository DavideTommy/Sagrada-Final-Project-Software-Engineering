package it.polimi.se2018.model.parsers;

import it.polimi.se2018.model.Player;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * PlayerParser Tester
 * This class verifies if the player parser works properly
 * @author Federico Lichinchi
 * @author Davide Lorenzi (JDocs)
 */
public class TestPlayerObjectParser {

    PlayerObjectParser playerObjectParser = new PlayerObjectParser();

    /**
     * Object Name Getter tester
     * This method verifies if is correctly gotten the name of the type of the object
     */
    @Test
    public void getObjectName() {
        assertEquals("Player", playerObjectParser.getObjectName());
    }

    /**
     * Object Parser Tester
     * This method verifies if players are correctly parsed
     */
    @Test
    public void toStringObject() {
        Player player = new Player("amsdf", "adasdf", "Green", null, null);
        String s = playerObjectParser.toStringObject(player);
        assertTrue(s.indexOf(player.getPlayerName()) > 0);
    }
}
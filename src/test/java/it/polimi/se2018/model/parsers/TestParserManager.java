package it.polimi.se2018.model.parsers;

import it.polimi.se2018.model.*;
import it.polimi.se2018.model.cards.CardParser;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.*;

import static org.junit.Assert.*;

/**
 * ParserManager Tester
 * This class verifies if the die parser works properly
 * @author Federico Lichinchi
 * @author Davide Lorenzi (JDocs)
 */
public class TestParserManager {

    ParserManager parserManager = ParserManager.newParserManager();

    /**
     * ParserManager Tester
     * This method verifies if parserManagers calls the right parser
     * @throws CloneNotSupportedException if is not possible to parse a card
     */
    @Test
    public void toStringRequiredObject() throws CloneNotSupportedException {
        assertNull(parserManager.toStringRequiredObject(new Reserve(new ArrayList<>())));
        try {
            assertNotNull(parserManager.toStringRequiredObject(CardParser.newCardParser().getRandomPublicObjectiveCard()));
            assertNotNull(parserManager.toStringRequiredObject(CardParser.newCardParser().getRandomToolCard()));
            assertNotNull(parserManager.toStringRequiredObject(CardParser.newCardParser().getRandomPrivateObjectiveCard()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assertNotNull(parserManager.toStringRequiredObject(new Player("asd","asd","Green",null,null)));
        assertNotNull(parserManager.toStringRequiredObject(new Grid(new SchemeCard("Virtus"))));
    }
}
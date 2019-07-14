package it.polimi.se2018.model.parsers;

import it.polimi.se2018.model.cards.CardParser;
import it.polimi.se2018.model.cards.PrivateObjectiveCard;
import it.polimi.se2018.model.cards.PublicObjectiveCard;
import it.polimi.se2018.model.cards.ToolCard;
import it.polimi.se2018.model.parsers.PrivateObjectiveCardObjectParser;
import it.polimi.se2018.model.parsers.PublicObjectiveCardObjectParser;
import it.polimi.se2018.model.parsers.ToolCardObjectParser;
import org.junit.Test;

import java.io.FileNotFoundException;

import static org.junit.Assert.*;

/**
 * Card Parser Tester
 * This class tests if a card is correctly parsed
 * @author Federico Lichinchi
 * @author Davide Lorenzi (JDocs)
 */
public class TestCardObjectParser {

    ToolCardObjectParser toolCardObjectParser = new ToolCardObjectParser();
    PublicObjectiveCardObjectParser publicObjectiveCardObjectParser = new PublicObjectiveCardObjectParser();
    PrivateObjectiveCardObjectParser privateObjectiveCardObjectParser = new PrivateObjectiveCardObjectParser();


    /**
     * NameObject Getter Tester
     * This method verifies if is taken the correct object name
     */
    @Test
    public void getObjectName() {
        assertEquals("ToolCard", toolCardObjectParser.getObjectName());
        assertEquals("PublicObjectiveCard", publicObjectiveCardObjectParser.getObjectName());
        assertEquals("PrivateObjectiveCard", privateObjectiveCardObjectParser.getObjectName());
    }

    /**
     * String Parser Tester
     * This method verifies that the object is correctly parsed into a string
     * @throws CloneNotSupportedException if is not possible to clone a card
     */
    @Test
    public void toStringObject() throws CloneNotSupportedException {
        CardParser cardParser = null;
        try {
            cardParser = CardParser.newCardParser();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ToolCard toolCard = cardParser.getRandomToolCard();
        PrivateObjectiveCard privateObjectiveCard = cardParser.getRandomPrivateObjectiveCard();
        PublicObjectiveCard publicObjectiveCard = cardParser.getRandomPublicObjectiveCard();
        String s = toolCardObjectParser.toStringObject(toolCard);
        assertTrue(s.indexOf(toolCard.getCardName()) > 0 && s.indexOf(toolCard.getCardDescription()) > 0);
        s = publicObjectiveCardObjectParser.toStringObject(publicObjectiveCard);
        assertTrue(s.indexOf(publicObjectiveCard.getCardName()) > 0 && s.indexOf(publicObjectiveCard.getCardDescription()) > 0);
        s = privateObjectiveCardObjectParser.toStringObject(privateObjectiveCard);
        assertTrue(s.indexOf(privateObjectiveCard.getCardName()) > 0 && s.indexOf(privateObjectiveCard.getCardDescription()) > 0);
    }
}

package it.polimi.se2018.model.cards;

import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;

import static org.junit.Assert.*;

/**
 * CardParser Tester
 * This class verifies the correct functioning of the card parser
 * @author Federico Lichinchi
 * @author Davide Lorenzi (JDocs)
 */
public class TestCardParser {

    CardParser cardParser;

    {
        try {
            cardParser = CardParser.newCardParser();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * PublicObjectiveCard Require Tester
     * This method verifies that the PublicObjectiveCard is correctly required
     */
    @Test
    public void requirePublicObjectiveCard() {
        Assert.assertNotNull(cardParser.requirePublicObjectiveCard("Light Shades"));
        Assert.assertNull(cardParser.requirePublicObjectiveCard("asdasdf"));
    }

    /**
     * ToolCard Require Tester
     * This method verifies that the ToolCard is correctly required
     */
    @Test
    public void requireToolCard() {
        Assert.assertNotNull(cardParser.requireToolCard("Lathekin"));
        Assert.assertNull(cardParser.requireToolCard("asdfasdf"));
    }

    /**
     * PrivateObjectiveCard Require Tester
     * This method verifies that the PrivateObjectiveCard is correctly required
     */
    @Test
    public void requirePrivateObjectiveCard() {
        Assert.assertNotNull(cardParser.requirePrivateObjectiveCard("Violet Shades"));
        Assert.assertNull(cardParser.requirePrivateObjectiveCard("SDFASDF"));
    }

    /**
     * Random ToolCard Getter Tester
     * This method verifies if is correctly generated a new random ToolCard
     */
    @Test
    public void getRandomToolCard() {
        try {
            Assert.assertNotNull(cardParser.getRandomToolCard());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Random PublicObjectiveCard Getter Tester
     * This method verifies if is correctly generated a new random PublicObjectiveCard
     */
    @Test
    public void getRandomPublicObjectiveCard() {
        try {
            Assert.assertNotNull(cardParser.getRandomPublicObjectiveCard());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Random PrivateObjectiveCard Getter Tester
     * This method verifies if is correctly generated a new random PrivateObjectiveCard
     */
    @Test
    public void getRandomPrivateObjectiveCard() {
        try {
            Assert.assertNotNull(cardParser.getRandomPrivateObjectiveCard());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
}
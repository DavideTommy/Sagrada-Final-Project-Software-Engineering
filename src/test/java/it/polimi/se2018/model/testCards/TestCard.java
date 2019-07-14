package it.polimi.se2018.model.testCards;

import it.polimi.se2018.model.cards.Card;
import it.polimi.se2018.model.cards.PrivateObjectiveCard;
import it.polimi.se2018.model.cards.PublicObjectiveCard;
import it.polimi.se2018.model.cards.ToolCard;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * TestCard Class
 * This class tests the creation of any type of card
 * @author Davide Lorenzi
 */
public class TestCard {
    private Card testToolCard;
    private Card testPublicObjectiveCard;
    private Card testPrivateObjectiveCard;

    /**
     * Card Initialization
     * This method initializes the test class
     */
    @Before
    public void setUp() {
        testToolCard = new ToolCard("Roughing Forceps", "After choosing a die, increase or decrease its value by 1. You can not change a 6 in 1 and vice versa.", 1, 0,1, "Violet");
        testPublicObjectiveCard = new PublicObjectiveCard("Different Colors - Rows", "Sum of the values on all the red Die",1,6);
        testPrivateObjectiveCard = new PrivateObjectiveCard("Red Shades", "Sum of the values on all the red Die", 1, "Red");

    }

    /**
     * CardDescription Tester
     * This test verifies that the method return the correct description of the ToolCard Asked by the user.
     */
    @Test
    public void getCardDescription() {
        Assert.assertNotNull(testToolCard.getCardDescription());
        Assert.assertSame("After choosing a die, increase or decrease its value by 1. You can not change a 6 in 1 and vice versa.", testToolCard.getCardDescription());
        Assert.assertNotNull(testPublicObjectiveCard.getCardDescription());
        Assert.assertSame("Sum of the values on all the red Die", testPublicObjectiveCard.getCardDescription());
        Assert.assertNotNull(testPrivateObjectiveCard.getCardDescription());
        Assert.assertSame("Sum of the values on all the red Die", testPrivateObjectiveCard.getCardDescription());
    }

    /**
     * GetCardName Tester
     * This test verifies that the method return the correct CardName
     */
    @Test
    public void getCardName() {
        Assert.assertNotNull(testToolCard.getCardName());
        Assert.assertSame("Roughing Forceps", testToolCard.getCardName());
        Assert.assertNotSame("Eglomise Brush", testToolCard.getCardName());
        Assert.assertNotNull(testPublicObjectiveCard.getCardName());
        Assert.assertSame("Different Colors - Rows", testPublicObjectiveCard.getCardName());
        Assert.assertNotSame("Different colors - Columns", testPublicObjectiveCard.getCardName());
        Assert.assertNotNull(testPrivateObjectiveCard.getCardName());
        Assert.assertSame("Red Shades", testPrivateObjectiveCard.getCardName());
        Assert.assertNotSame("Blue Shades", testPrivateObjectiveCard.getCardName());


    }

    /**
     * GetNumMarker Tester
     * This test verifies that the method return the correct (or a possible one) Favour Markers number
     * and verifies that PublicObjectiveCards and PrivateObjectiveCards return 404 because they haven't
     * Favour Markers
     */
    @Test
    public void getNumMarkers() {
        Assert.assertTrue(testToolCard.getNumMarkers() >= 0);
        Assert.assertFalse(testToolCard.getNumMarkers() < 0);
        Assert.assertEquals(404, testPublicObjectiveCard.getNumMarkers());
        Assert.assertFalse(testPublicObjectiveCard.getNumMarkers() != 404);
        Assert.assertEquals(404, testPrivateObjectiveCard.getNumMarkers());
        Assert.assertFalse(testPrivateObjectiveCard.getNumMarkers() != 404);


    }

    /**
     * GetCard Number Tester
     * This test verifies that the method return the correct CardNumber.
     */
    @Test
    public void getCardNumber() {
        Assert.assertEquals(1, testToolCard.getCardNumber());
        Assert.assertFalse(testToolCard.getCardNumber() != 1);
        Assert.assertEquals(1, testPublicObjectiveCard.getCardNumber());
        Assert.assertFalse(testPublicObjectiveCard.getCardNumber() != 1);
        Assert.assertEquals(1, testPrivateObjectiveCard.getCardNumber());
        Assert.assertFalse(testPrivateObjectiveCard.getCardNumber() != 1);


    }

    /**
     * GetCardType Tester
     * This test verifies that the method return the correct CardType
     */
    @Test
    public void getCardType() {
        Assert.assertSame("ToolCard", testToolCard.getCardType());
        Assert.assertNotSame("PrivateObjectiveCard", testToolCard.getCardType());
        Assert.assertSame("PublicObjectiveCard", testPublicObjectiveCard.getCardType());
        Assert.assertNotSame("PrivateObjectiveCard", testPublicObjectiveCard.getCardType());
        Assert.assertSame("PrivateObjectiveCard", testPrivateObjectiveCard.getCardType());
        Assert.assertNotSame("PublicObjectiveCard", testPrivateObjectiveCard.getCardType());


    }

    /**
     * GetDieColor Tester
     * This test verifies that the method return the correct color of the die to sacrifice to active the ToolCard in
     * Single Player mode
     * This test also verifies that the method return noColor error because PublicObjectiveCards haven't this attribute.
     * This test also verifies that the method return the correct color of the Die that will be used for the
     * count of the PrivateScore
     */
    @Test
    public void getDieColor() {
        Assert.assertSame("Violet", testToolCard.getColor());
        Assert.assertFalse(testToolCard.getColor() == "Yellow" || testToolCard.getColor() == "Blue" ||
                testToolCard.getColor() == "Green" || testToolCard.getColor() == "Red");
        Assert.assertSame("noColor", testPublicObjectiveCard.getColor());
        Assert.assertFalse(testPublicObjectiveCard.getColor() == "Yellow" || testPublicObjectiveCard.getColor() == "Blue" ||
                testPublicObjectiveCard.getColor() == "Green" || testPublicObjectiveCard.getColor() == "Red" ||
                testPublicObjectiveCard.getColor() == "Violet");
        Assert.assertSame("Red", testPrivateObjectiveCard.getColor());
        Assert.assertFalse(testPrivateObjectiveCard.getColor() == "Yellow" || testPrivateObjectiveCard.getColor() == "Blue" ||
                testPrivateObjectiveCard.getColor() == "Green" || testPrivateObjectiveCard.getColor() == "Violet");


    }

    /**
     * Get Price Tester
     * This test verifies that the method returns the correct Price of a ToolCard
     * This test verifies that the method returns 404 error because PublicObjectiveCards and
     * PrivateObjectiveCards haven't Price
     */
    @Test
    public void getPrice() {
        Assert.assertTrue(testToolCard.getPrice() == 1 || testToolCard.getPrice() == 2);
        Assert.assertFalse(testToolCard.getPrice() != 1 && testToolCard.getPrice() != 2);
        Assert.assertEquals(404, testPublicObjectiveCard.getPrice());
        Assert.assertFalse(testPublicObjectiveCard.getPrice() != 404);
        Assert.assertEquals(404, testPrivateObjectiveCard.getPrice());
        Assert.assertFalse(testPrivateObjectiveCard.getPrice() != 404);

    }

    /**
     * Favour Marker Adder Tester
     * This test verifies that the method returns the correct number of Favour Markers on a ToolCard
     * This test verifies that the method returns 404 error because PublicObjectiveCards and
     * PrivateObjectiveCards haven't Favour Markers
     */

    @Test
    public void addFavMarkers() {
        Assert.assertEquals(0, testToolCard.getNumMarkers());
        testToolCard.addFavMarkers(1);
        Assert.assertEquals(1, testToolCard.getNumMarkers());
        testToolCard.addFavMarkers(2);
        Assert.assertEquals(3, testToolCard.getNumMarkers());
        Assert.assertNotEquals(34, testToolCard.getNumMarkers());
    }

    /**
     * Public Victory Points Getter Tester
     * This test verifies that the method returns the correct number of Public Victory Points
     * This test verifies that the method returns 404 error because ToolCards and
     * PrivateObjectiveCards haven't Favour Markers
     */

    @Test
    public void getPublicVictoryPoint() {
        Assert.assertEquals(404, testToolCard.getPublicVictoryPoint());
        Assert.assertFalse(testToolCard.getPublicVictoryPoint() != 404);
        Assert.assertEquals(6, testPublicObjectiveCard.getPublicVictoryPoint());
        Assert.assertFalse(testPublicObjectiveCard.getPublicVictoryPoint() != 6);
        Assert.assertEquals(404, testPrivateObjectiveCard.getPublicVictoryPoint());
        Assert.assertFalse(testPrivateObjectiveCard.getPublicVictoryPoint() != 404);


    }

    /**
     * Clone Tester
     * This method tests that the cloning process run and produce all the cards attribute and checks that all attributes
     * are the same of the original Card
     * @throws CloneNotSupportedException if is not possible to clone a card
     */
    @Test
    public void cloneCard() throws CloneNotSupportedException {

        ToolCard testCloneToolCard = (ToolCard) testToolCard.clone();
        PublicObjectiveCard testClonePublicObjectiveCard = (PublicObjectiveCard) testPublicObjectiveCard.clone();
        PrivateObjectiveCard testClonePrivateObjectiveCard = (PrivateObjectiveCard) testPrivateObjectiveCard.clone();

        Assert.assertSame(testToolCard.getCardName(), testCloneToolCard.getCardName());
        Assert.assertEquals(testToolCard.getNumMarkers(), testCloneToolCard.getNumMarkers());
        Assert.assertEquals(testToolCard.getPrice(), testCloneToolCard.getPrice());
        Assert.assertSame(testToolCard.getCardDescription(), testCloneToolCard.getCardDescription());
        Assert.assertEquals(testToolCard.getCardNumber(),testCloneToolCard.getCardNumber());
        Assert.assertSame(testToolCard.getColor(), testCloneToolCard.getColor());

        Assert.assertSame(testPublicObjectiveCard.getCardName(), testClonePublicObjectiveCard.getCardName());
        Assert.assertSame(testPublicObjectiveCard.getCardDescription(), testClonePublicObjectiveCard.getCardDescription());
        Assert.assertEquals(testPublicObjectiveCard.getPublicVictoryPoint(), testClonePublicObjectiveCard.getPublicVictoryPoint());
        Assert.assertEquals(testPublicObjectiveCard.getCardNumber(),testClonePublicObjectiveCard.getCardNumber());

        Assert.assertSame(testPrivateObjectiveCard.getCardName(), testClonePrivateObjectiveCard.getCardName());
        Assert.assertSame(testPrivateObjectiveCard.getCardDescription(), testClonePrivateObjectiveCard.getCardDescription());
        Assert.assertEquals(testPrivateObjectiveCard.getCardNumber(),testClonePrivateObjectiveCard.getCardNumber());
        Assert.assertSame(testPrivateObjectiveCard.getColor(), testClonePrivateObjectiveCard.getColor());
    }
}
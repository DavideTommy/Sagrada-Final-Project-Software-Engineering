package it.polimi.se2018.model.testCards;

import it.polimi.se2018.model.cards.PrivateObjectiveCard;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * TestPrivateObjectiveCard Class
 * This class tests the creation of Private Objective Card
 * @author Davide Lorenzi
 */
public class TestPrivateObjectiveCard {

    private PrivateObjectiveCard testPrivateObjectiveCard;

    /**
     * Test ToolCard Initialization
     * This method prepare the test ambient
     */

    @Before
    public void setUp() {
        testPrivateObjectiveCard = new PrivateObjectiveCard("Red Shades", "Sum of the values on all the red Die", 1, "Red");
    }

    /**
     * CardDescription Tester
     *  This test verifies that the method return the correct description of the ToolCard Asked by the user.
     */
    @Test
    public void getCardDescription() {
        Assert.assertNotNull(testPrivateObjectiveCard.getCardDescription());
        Assert.assertSame("Sum of the values on all the red Die", testPrivateObjectiveCard.getCardDescription());
    }

    /**
     * GetCardName Tester
     * This test verifies that the method return the correct CardName
     */
    @Test
    public void getCardName() {
        Assert.assertNotNull(testPrivateObjectiveCard.getCardName());
        Assert.assertSame("Red Shades", testPrivateObjectiveCard.getCardName());
        Assert.assertNotSame("Blue Shades", testPrivateObjectiveCard.getCardName());
    }

    /**
     * GetCardNumber Tester
     * This test verifies that the method return the correct CardNumber
     */
    @Test
    public void getNumMarkers() {
        Assert.assertEquals(404, testPrivateObjectiveCard.getNumMarkers());
        Assert.assertFalse(testPrivateObjectiveCard.getNumMarkers() != 404);
    }

    /**
     * GetCardNumberType Tester
     * This test verifies that the method return the correct CardType
     */
    @Test
    public void getCardNumber() {
        Assert.assertEquals(1, testPrivateObjectiveCard.getCardNumber());
        Assert.assertFalse(testPrivateObjectiveCard.getCardNumber() != 1); 
    }

    /**
     * GetCardType Tester
     * This test verifies that the method return the correct CardType
     */
    @Test
    public void getCardType() {
        Assert.assertSame("PrivateObjectiveCard", testPrivateObjectiveCard.getCardType());
        Assert.assertSame("PrivateObjectiveCard", testPrivateObjectiveCard.getCardType());
    }

    /**
     * GetDieColor Tester
     * This test verifies that the method return the correct color of the chosen die
     */
    @Test
    public void getColor() {
        Assert.assertSame("Red", testPrivateObjectiveCard.getColor());
        Assert.assertFalse(testPrivateObjectiveCard.getColor().equals("Yellow") || testPrivateObjectiveCard.getColor().equals("Blue") ||
                testPrivateObjectiveCard.getColor().equals("Green") || testPrivateObjectiveCard.getColor().equals("Violet"));
    }


    /**
     * Get Price Tester
     * This test verifies that the method returns 404 error because PrivateObjectiveCards haven't Price
     */
    @Test
    public void getPrice() {
        Assert.assertEquals(404, testPrivateObjectiveCard.getPrice());
        Assert.assertFalse(testPrivateObjectiveCard.getPrice() != 404);
    }

    /**
     * Victory Point getter Tester
     * This test verifies that the method return 404 error because PrivateObjectiveCards haven't Victory Points
     */
    @Test
    public void getPublicVictoryPoint() {
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

        PrivateObjectiveCard testClonePrivateObjectiveCard = testPrivateObjectiveCard.clone();

        Assert.assertSame(testPrivateObjectiveCard.getCardName(), testClonePrivateObjectiveCard.getCardName());
        Assert.assertSame(testPrivateObjectiveCard.getCardDescription(), testClonePrivateObjectiveCard.getCardDescription());
        Assert.assertEquals(testPrivateObjectiveCard.getCardNumber(),testClonePrivateObjectiveCard.getCardNumber());
        Assert.assertSame(testPrivateObjectiveCard.getColor(), testClonePrivateObjectiveCard.getColor());

    }
}
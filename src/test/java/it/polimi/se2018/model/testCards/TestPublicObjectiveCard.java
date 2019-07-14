package it.polimi.se2018.model.testCards;

import it.polimi.se2018.model.cards.PublicObjectiveCard;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * TestCard Class
 * This class tests the creation of any type of card
 * @author Davide Lorenzi
 */
public class TestPublicObjectiveCard {

    private PublicObjectiveCard testPublicObjectiveCard;

    /**
     * Test ToolCard Initialization
     * This method prepare the test ambient
     */

    @Before
    public void setUp() {
        testPublicObjectiveCard = new PublicObjectiveCard("Different Colors - Rows", "Rows without repeated colors",1,6);
    }


    /**
     * CardDescription Tester
     *  This test verifies that the method return the correct description of the ToolCard Asked by the user.
     */

    @Test
    public void getCardDescription() {
        Assert.assertNotNull(testPublicObjectiveCard.getCardDescription());
        Assert.assertSame("Rows without repeated colors", testPublicObjectiveCard.getCardDescription());
        Assert.assertNotSame("Sum of the values on all the red Die", testPublicObjectiveCard.getCardDescription());
    }

    /**
     * GetCardName Tester
     * This test verifies that the method return the correct CardName
     */
    @Test
    public void getCardName() {
        Assert.assertTrue(testPublicObjectiveCard.getCardName() != null);
        Assert.assertSame("Different Colors - Rows", testPublicObjectiveCard.getCardName());
        Assert.assertNotSame("Different Colors - Columns", testPublicObjectiveCard.getCardName());
    }

    /**
     * GetNumMarker Tester
     * This test verifies that the method return 404 error because PublicObjectiveCards haven't Favour Markers
     */
    @Test
    public void getNumMarkers() {
        Assert.assertEquals(404, testPublicObjectiveCard.getNumMarkers());
        Assert.assertFalse(testPublicObjectiveCard.getNumMarkers() != 404);
    }

    /**
     * GetCardNumber Tester
     * This test verifies that the method return the correct CardNumber
     */
    @Test
    public void getCardNumber() {
        Assert.assertEquals(1, testPublicObjectiveCard.getCardNumber());
        Assert.assertFalse(testPublicObjectiveCard.getCardNumber() != 1);
    }

    /**
     * GetCardType Tester
     * This test verifies that the method return the correct CardType
     */
    @Test
    public void getCardType() {
        Assert.assertSame("PublicObjectiveCard", testPublicObjectiveCard.getCardType());
        Assert.assertSame("PublicObjectiveCard", testPublicObjectiveCard.getCardType());
    }

    /**
     * GetDieColor Tester
     * This test verifies that the method return noColor error because PublicObjectiveCards haven't this attribute
     */
    @Test
    public void getColor() {
        Assert.assertSame("noColor", testPublicObjectiveCard.getColor());
        Assert.assertFalse(testPublicObjectiveCard.getColor().equals("Yellow") || testPublicObjectiveCard.getColor().equals("Blue") ||
                testPublicObjectiveCard.getColor().equals("Green") || testPublicObjectiveCard.getColor().equals("Red") ||
                testPublicObjectiveCard.getColor().equals("Violet"));
    }

    /**
     * Get Price Tester
     * This test verifies that the method returns 404 error because PublicObjectiveCards haven't Price
     */
    @Test
    public void getPrice() {
        Assert.assertEquals(404, testPublicObjectiveCard.getPrice());
        Assert.assertFalse(testPublicObjectiveCard.getPrice() != 404);
    }

    /**
     * Victory Point getter Tester
     * This test verifies that the method return the correct number of Victory Points that will be used for
     * the Public Score
     */
        @Test
    public void getPublicVictoryPoint() {
        Assert.assertEquals(6, testPublicObjectiveCard.getPublicVictoryPoint());
        Assert.assertFalse(testPublicObjectiveCard.getPublicVictoryPoint() !=6);
    }

    /**
     * Clone Tester
     * This method tests that the cloning process run and produce all the cards attribute
     * @throws CloneNotSupportedException if is not possible to clone the card
     */
    @Test
    public void cloneCard() throws CloneNotSupportedException {
        PublicObjectiveCard testClonePublicObjectiveCard = testPublicObjectiveCard.clone();

        Assert.assertSame(testPublicObjectiveCard.getCardName(), testClonePublicObjectiveCard.getCardName());
        Assert.assertSame(testPublicObjectiveCard.getCardDescription(), testClonePublicObjectiveCard.getCardDescription());
        Assert.assertEquals(testPublicObjectiveCard.getPublicVictoryPoint(), testClonePublicObjectiveCard.getPublicVictoryPoint());
        Assert.assertEquals(testPublicObjectiveCard.getCardNumber(),testClonePublicObjectiveCard.getCardNumber());

    }
}
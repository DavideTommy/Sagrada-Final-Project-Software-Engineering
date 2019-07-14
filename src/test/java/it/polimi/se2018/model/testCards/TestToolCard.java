package it.polimi.se2018.model.testCards;

import it.polimi.se2018.model.cards.ToolCard;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * TestToolCard Class
 * This class tests the creation of ToolCards
 * @author Davide Lorenzi
 */
public class TestToolCard {
    private ToolCard testToolCard;

    /**
     * Test ToolCard initialization
     * This method prepare the test ambient
     */
    @Before
    public void setUp()   {
        testToolCard = new ToolCard("Roughing Forceps", "After choosing a die, increase or decrease its value by 1. You can not change a 6 in 1 and vice versa.", 1, 0,1, "Violet");
    }

    /**
     * CardDescription Tester
     *  This test verifies that the method return the correct description of the ToolCard Asked by the user.
     */
    @Test
    public void getCardDescription() {
        Assert.assertNotNull(testToolCard.getCardDescription());
        Assert.assertSame("After choosing a die, increase or decrease its value by 1. You can not change a 6 in 1 and vice versa.", testToolCard.getCardDescription());
        Assert.assertNotSame("Move any Die in your window while ignoring the color restrictions. You must respect all other placement restrictions", testToolCard.getCardDescription());
    }

    /**
     * GetCardName Tester
     * This test verifies that the method return the correct CardName
     */
    @Test
    public void getCardName() {
        Assert.assertNotNull(testToolCard.getCardName());
        Assert.assertSame("Roughing Forceps", testToolCard.getCardName());
        Assert.assertNotSame("Brush for Eglomise", testToolCard.getCardName());
    }

    /**
     * GetNumMarker Tester
     * This test verifies that the method return the correct (or a possible one) Favour Markers number.
     */
    @Test
    public void getNumMarkers() {
        Assert.assertTrue(testToolCard.getNumMarkers() >= 0);
        Assert.assertFalse(testToolCard.getNumMarkers() < 0);
    }

    /**
     * GetCardNumber Tester
     * This test verifies that the method return the correct CardNumber
     */

    @Test
    public void getCardNumber() {
        Assert.assertEquals(1, testToolCard.getCardNumber());
        Assert.assertFalse(testToolCard.getCardNumber() != 1);
        Assert.assertTrue(testToolCard.getCardNumber() <= 12);
    }

    /**
     * GetCardType Tester
     * This test verifies that the method return the correct CardType
     */

    @Test
    public void getCardType() {
        Assert.assertSame("ToolCard", testToolCard.getCardType());
        Assert.assertSame("ToolCard", testToolCard.getCardType());
    }

    /**
     * GetDieColor Tester
     * This test verifies that the method return the correct color of the die to sacrifice to active the ToolCard in
     * Single Player mode
     */
    @Test
    public void getDieColor() {
        Assert.assertSame("Violet", testToolCard.getColor());
        Assert.assertFalse(testToolCard.getColor().equals("Yellow") || testToolCard.getColor().equals("Blue") ||
                testToolCard.getColor().equals("Green") || testToolCard.getColor().equals("Red"));
    }


    /**
     * Get Price Tester
     * This test verifies that the method returns the correct Price of a ToolCard
     */
     public void getPrice() {
        Assert.assertTrue(testToolCard.getPrice() == 1 || testToolCard.getPrice() == 2);
        Assert.assertFalse(testToolCard.getPrice() != 1 && testToolCard.getPrice() != 2 );
    }

    /**
     * FavourMarker Adder Tester
     * This test verifies that the method return the correct number of Favour Markers after the incrementation
     * that happens wen a player buy a ToolCard
     */
    @Test
    public void addFavMarkers() {
        Assert.assertEquals(0, testToolCard.getNumMarkers());
        testToolCard.addFavMarkers(1);
        Assert.assertEquals(1, testToolCard.getNumMarkers());
        testToolCard.addFavMarkers(2);
        Assert.assertEquals(3, testToolCard.getNumMarkers());
        Assert.assertNotEquals(2, testToolCard.getNumMarkers());
    }

    /**
     * Victory Point getter Tester
     * This test verifies that the method return 404 error because ToolCard haven't Victory Points
     */
    @Test
    public void getPublicVictoryPoint() {
        Assert.assertEquals(404, testToolCard.getPublicVictoryPoint());
        Assert.assertFalse(testToolCard.getPublicVictoryPoint() != 404);
    }

    /**
     * Clone Tester
     * This method tests that the cloning process run and produce all the cards attribute and checks that all attributes
     * are the same of the original Card
     * @throws CloneNotSupportedException if is not possible to clone the card
     */

    @Test
    public void cloneCard() throws CloneNotSupportedException {
        ToolCard testCloneToolCard = testToolCard.clone();

        Assert.assertSame(testToolCard.getCardName(), testCloneToolCard.getCardName());
        Assert.assertEquals(testToolCard.getNumMarkers(), testCloneToolCard.getNumMarkers());
        Assert.assertEquals(testToolCard.getPrice(), testCloneToolCard.getPrice());
        Assert.assertSame(testToolCard.getCardDescription(), testCloneToolCard.getCardDescription());
        Assert.assertEquals(testToolCard.getCardNumber(),testCloneToolCard.getCardNumber());
        Assert.assertSame(testToolCard.getColor(), testCloneToolCard.getColor());
    }
}
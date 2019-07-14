package it.polimi.se2018.model.rules;

import it.polimi.se2018.model.ChoseInputMove;
import it.polimi.se2018.model.cards.CardParser;
import it.polimi.se2018.model.cards.PrivateObjectiveCard;
import it.polimi.se2018.model.cards.PublicObjectiveCard;
import it.polimi.se2018.model.cards.ToolCard;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * ChoseInputMove Tester
 * This method verifies the correct functioning of the ChoseInputMove class
 * @author Federico Lichinchi
 * @author Davide Lorenzi (JDocs)
 */
public class TestChoseInputMove {

    ChoseInputMove choseInputMove;
    private PublicObjectiveCard publicObjectiveCard;
    private PrivateObjectiveCard privateObjectiveCard;
    private ToolCard toolCard;

    /**
     * Test ChoseInputMove initialization
     * This method prepare the test ambient
     */
    @Before
    public void setUp() {
        this.publicObjectiveCard = new PublicObjectiveCard("Different Colors - Rows","Rows without repeated colors",1,6);
        this.privateObjectiveCard = new PrivateObjectiveCard("Red Shades", "Sum of the values on all the red Die",1, "Red");
        this.toolCard = new ToolCard("Roughing Forceps", "After choosing a die, increase or decrease its value by 1. You can not change a 6 in 1 and vice versa.",1,0, 1, "Purple");
        this.choseInputMove = new ChoseInputMove("4", publicObjectiveCard.getClass(), "192.168.1.1");
    }

    /**
     * Test ChoseInputMove TearDown
     * This method reset the test ambient after any test
     */
    @After
    public void tearDown() {
        this.choseInputMove = new ChoseInputMove(null, null, null);

    }

    /**
     * GetMessage Tester
     * This method verifies that the function get the attended message contained into the ChoseInputMove
     */
    @Test
    public void getMessage() {
        Assert.assertSame("4", choseInputMove.getMessage());
        Assert.assertNotSame("5", choseInputMove.getMessage());
    }

    /**
     * GetElement Tester
     * This method verifies that the function get the attended element contained into the ChoseInputMove
     */
    @Test
    public void getElement() {
        Assert.assertSame(choseInputMove.getElement(), publicObjectiveCard.getClass());
    }

    /**
     * GetPlayerAddress Tester
     * This method verifies that the function get the attended PlayerAddress contained into the ChoseInputMove
     */
    @Test
    public void getPlayerAddress() {
        Assert.assertSame("192.168.1.1", choseInputMove.getPlayerAddress());
        Assert.assertNotSame("192.168.1.255", choseInputMove.getPlayerAddress());
    }

    /**
     * Element Setter Tester
     * This method verifies if an object is correctly set
     */
    @Test
    public void setElement() {
        choseInputMove.setElement(privateObjectiveCard);
        Assert.assertEquals(privateObjectiveCard,choseInputMove.getElement());

    }

    /**
     * GetPlayerAddress Tester
     * This method verifies that the function get the attended PlayerAddress modified from the original
     */
    @Test
    public void setPlayerAddress() {
        choseInputMove.setPlayerAddress("192.168.1.2");
        Assert.assertSame("192.168.1.2", choseInputMove.getPlayerAddress());
        Assert.assertNotSame("192.168.1.1", choseInputMove.getPlayerAddress());
    }

    /**
     * ElementToString Tester
     * This method verifies if an object is correctly parse into a String
     * @throws CloneNotSupportedException if is not possible to clone an object
     */
    @Test
    public void toStringElement() throws CloneNotSupportedException {
        choseInputMove.setElement(privateObjectiveCard);
        Assert.assertEquals(privateObjectiveCard.showCard(),choseInputMove.toStringElement());
        choseInputMove.setElement(publicObjectiveCard);
        Assert.assertEquals(publicObjectiveCard.showCard(),choseInputMove.toStringElement());
        ArrayList<ToolCard> toolCardArrayList = new ArrayList<>();
        try {
            toolCardArrayList.add(CardParser.newCardParser().getRandomToolCard());
            toolCardArrayList.add(CardParser.newCardParser().getRandomToolCard());
            toolCardArrayList.add(CardParser.newCardParser().getRandomToolCard());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        choseInputMove.setElement(toolCardArrayList);
        String s = choseInputMove.toStringElement();
        for(ToolCard toolCard : toolCardArrayList)
            Assert.assertTrue(s.indexOf(toolCard.showCard()) > 0);
    }

    /**
     * Clone Tester
     * This method verifies that the cloned move has the same parameters of the original one
     * @throws CloneNotSupportedException if is not possible to clone the move
     */
    @Test
    public void cloneMove() throws CloneNotSupportedException {
        ChoseInputMove testChoseInputMove = choseInputMove.clone();

        Assert.assertEquals(choseInputMove.getMessage(), testChoseInputMove.getMessage());
        Assert.assertEquals(choseInputMove.getPlayerAddress(), testChoseInputMove.getPlayerAddress());
        Assert.assertEquals(choseInputMove.getElement(), testChoseInputMove.getElement());
    }
}
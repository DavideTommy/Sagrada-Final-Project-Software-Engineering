package it.polimi.se2018.model.mv;

import it.polimi.se2018.controller.MoveParser;
import it.polimi.se2018.model.ChoseInputMove;
import it.polimi.se2018.model.Grid;
import it.polimi.se2018.model.Player;
import it.polimi.se2018.model.SchemeCard;
import it.polimi.se2018.model.cards.CardParser;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileNotFoundException;

import static org.junit.Assert.*;

/**
 * ModelClass Tester
 * This class verifies the correct wor k of the model manager class
 * @author Federico Lichinchi
 * @author Davide Lorenzi(JDocs)
 */
public class TestModel {

    Model model;

    {
        try {
            model = new Model(3);
            model.setMove(MoveParser.newMoveParser().answerMessage("colour"));
            try {
                model.getPlayers().add(new Player("adscwd","asdf","Blue",
                        new Grid(new SchemeCard("Virtus")), CardParser.newCardParser().getRandomPrivateObjectiveCard()));
                model.getPlayers().add(new Player("aklsfd","asasdqw","Blue",
                        new Grid(new SchemeCard("Kaleidoscopic Dream")), CardParser.newCardParser().getRandomPrivateObjectiveCard()));
                model.getPlayers().add(new Player("awdfcwd","dqweqff","Blue",
                        new Grid(new SchemeCard("Aurorae Magnificus")), CardParser.newCardParser().getRandomPrivateObjectiveCard()));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Model Clone Tester
     * This method verifies if the clone of the model works properly
     */
    @Test
    public void cloneModel() {
        try {
            Model model2 = model.clone();
            Assert.assertEquals(model2.getPlayers(), model.getPlayers());
            Assert.assertEquals(model2.getMove(), model.getMove());
            Assert.assertEquals(model2.getMatch(), model.getMatch());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Match Getter Tester
     * This test verifies if the model is correctly exported
     */
    @Test
    public void getMatch() {
        try {
            model.newMatch();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(model.getMatch());
    }

    /**
     * Move Getter Tester
     * This test verifies if a move is correctly exported
     */
    @Test
    public void getMove() {
        Assert.assertNotNull(model.getMove());
    }

    /**
     * players Getter Tester
     * This test verifies if players are correctly exported
     */
    @Test
    public void getPlayers() {
        Assert.assertNotNull(model.getPlayers());
    }

    /**
     * Move Setter Tester
     * This test verifies that a move is correctly received by the controller
     */
    @Test
    public void setMove() {
        ChoseInputMove choseInputMove = MoveParser.newMoveParser().answerMessage("finalScore");
        model.setMove(choseInputMove);
        Assert.assertEquals(model.getMove().getMessage(),choseInputMove.getMessage());
    }
}
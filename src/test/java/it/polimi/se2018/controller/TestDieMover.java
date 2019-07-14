package it.polimi.se2018.controller;

import it.polimi.se2018.controller.effects.structuresModifiersMethods.DieMover;
import it.polimi.se2018.model.BoxOfSchemeCards;
import it.polimi.se2018.model.Die;
import it.polimi.se2018.model.Grid;
import it.polimi.se2018.model.Player;
import it.polimi.se2018.model.cards.PrivateObjectiveCard;

import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Die Mover Tester
 * This class tests if a die is correctly moved into the grid
 * @author Luca Massini
 * @author Davide Lorenzi (JDocs)
 */
public class TestDieMover {

    private DieMover mover;
    private Player player;
    private Die tmp;
    private Grid playerGrid;

    /**
     * SetUp
     * This method prepare the test ambient
     */
    @Before
    public void setUp(){
        mover = new DieMover();
        BoxOfSchemeCards box = BoxOfSchemeCards.newBox();
        playerGrid =  new Grid(box.request("Virtus"));
        player = new Player ("Boh","non so","Green", playerGrid, new PrivateObjectiveCard(null,null,0,null));
    }

    /**
     * Tear Down
     * This method prepare the test ambient for a new test
     */
    @After
    public void tearDown(){
        mover = null;
        player = null;
        tmp = null;
        playerGrid = null;
    }

    /**
     * DieMover Tester
     * This method tests if a die is correctly moved into the grid
     */
    @Test
    public void Test(){
        tmp = new Die("Red",2);
        mover.moveDie(tmp,1,1,player);
        assertEquals(playerGrid.getDie(1,1),tmp);
        tmp = new Die("Yellow",5);
        mover.moveDie(tmp,2,1,player);
        assertEquals(playerGrid.getDie(2,1),tmp);
    }

}

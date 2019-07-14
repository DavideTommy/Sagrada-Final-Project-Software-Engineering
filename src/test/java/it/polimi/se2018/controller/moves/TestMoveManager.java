package it.polimi.se2018.controller.moves;

import it.polimi.se2018.controller.Controller;
import it.polimi.se2018.model.mv.VirtualView;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Move Manager Tester
 * This class tests the correct functioning of the move manager
 * @author Federico Lichinchi
 * @author Davide Lorenzi (JDocs)
 */
public class TestMoveManager {

    ArrayList<String> nicknames = new ArrayList<>(3);
    ArrayList<VirtualView> virtualViews = new ArrayList<>(3);
    MoveManager moveManager;

    {
        try {
            Controller controller = new Controller(nicknames, virtualViews);
            moveManager = MoveManager.newMoveManager(controller);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Move Getter Tester
     * This method verifies if a move is correctly gotten
     */
    @Test
    public void getMove() {
        Assert.assertEquals(1, moveManager.getMove(1).getMoveNumber());
        Assert.assertEquals(2, moveManager.getMove(2).getMoveNumber());
        Assert.assertNull(moveManager.getMove(3));
    }
}
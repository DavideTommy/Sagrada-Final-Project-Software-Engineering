package it.polimi.se2018.controller.moves;

import it.polimi.se2018.controller.Controller;

import java.util.ArrayList;
/**
 * Move Manager Class
 * This Interface is used to manage all player's moves
 * @author Federico Lichinchi
 * @author Davide Lorenzi (JDocs)
 */
public class MoveManager {

    private static MoveManager singleton = null;
    private ArrayList<Move> moveArrayList;
    private Controller controller;


    /**
     * Move Manager Creator
     * This method creates an instance of a Move Manager
     * @param controller game logic controller
     * @return the instance of the move manager
     */
    public static MoveManager newMoveManager(Controller controller){
        if(singleton == null)
            singleton = new MoveManager(controller);
        return singleton;
    }

    /**
     * Move Manager Constructor
     * This method initializes all elements required for the application of a move
     * @param controller game logic controller
     */
    private MoveManager(Controller controller) {
        this.controller = controller;
        moveArrayList = new ArrayList<>();
        moveArrayList.add(new DiceAdder());
        moveArrayList.add(new ToolCardApplier());
    }

    /**
     * Move Applier
     * This method applies a moved required by the ToolCard
     * @param requireMoveNumber is the number of the move to apply
     */
    public void applyRequiredMove(int requireMoveNumber) {
        for(Move move : moveArrayList) {
            if(move.getMoveNumber() == requireMoveNumber) {
                move.applyMove(controller);
            }
        }
    }

    /**
     * Move Getter
     * This method gets a required move
     * @param number is the mumber of the required move
     * @return required move
     */
    public Move getMove(int number) {
        for(Move move : moveArrayList)
            if(move.getMoveNumber() == number)
                return move;
        return null;
    }
}

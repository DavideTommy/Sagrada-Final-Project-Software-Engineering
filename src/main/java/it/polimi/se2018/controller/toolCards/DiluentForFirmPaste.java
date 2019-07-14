package it.polimi.se2018.controller.toolCards;

import it.polimi.se2018.controller.Controller;
import it.polimi.se2018.controller.effects.checkMethods.checkConstraintMethods.CheckDiePlacement;
import it.polimi.se2018.controller.effects.checkMethods.checkConstraintMethods.ChooseCheckConstraint;
import it.polimi.se2018.controller.effects.structuresModifiersMethods.DieMover;
import it.polimi.se2018.controller.effects.structuresModifiersMethods.ExchangeDieBetweenReserveAndBag;
import it.polimi.se2018.model.Die;
import it.polimi.se2018.model.Grid;

/**
 * DiluentForFirmPaste Class implements ModelSideToolCard
 * This class execute the ToolCard with the same name
 * @author Luca Massini
 * @author Davide Lorenzi (JDocs)
 */

public class DiluentForFirmPaste implements ModelSideToolCard {

    private static final int CARDNUMBER = 11;
    private Die dieFromReserve;
    private Die dieFromBag;
    private int newDieValue;
    private int[] coordinates;


    /**
     * Die Exchanger
     * This method allows to exchange die between Reserve and Bag. In particular the die harvest
     * from the reserve became the die from the bag and vice versa.
     * @param pickedDie die to exchange
     * @param bagDice Die of the bag
     */
    private void exchange(Die pickedDie, Die bagDice){
        ExchangeDieBetweenReserveAndBag exchanger = new ExchangeDieBetweenReserveAndBag();
        exchanger.exchange(pickedDie, bagDice);
    }

    /**
     * This method put the chosen die in the grid
     * @param controller the controller of the game
     * @param die the die that must be putted in the grid
     */
    public void move(Controller controller,Die die){
        DieMover mover = new DieMover();
        mover.moveDie(die,coordinates[0],coordinates[1], controller.getPlayerOfCurrentTurn());
    }

    /**
     * Constraint Checker
     * This method checks constraints
     * @param controller is the game logic controller
     * @return true if is correct the application of a constraints, false otherwise
     */
    private boolean checkConstrain(Controller controller){
        Grid playerGrid = controller.getPlayerOfCurrentTurn().getGrid();
        if(playerGrid.isGridEmpty())
            playerGrid.setFirstTimeToTrue();
        ChooseCheckConstraint constraint = new ChooseCheckConstraint(new CheckDiePlacement());
        return constraint.checkConstraint(coordinates[0],coordinates[1],dieFromReserve,controller.getPlayerOfCurrentTurn());
    }

    /**
     * CardNumber Getter
     * This method catch the correct card number
     * @return the ToolCard Number
     */
    @Override
    public int getCardNumber() {
        return CARDNUMBER;
    }

    /**
     * ToolCard Applier
     * This method verifies the application of the ToolCard-
     * @param controller is the game controller
     * @return true if the ToolCard has been correctly applied, false otherwise
     */
    @Override
    public boolean applyToolCard(Controller controller) {
        getParameters(controller);
        if(dieFromReserve != null || (coordinates[0] != -1)) {
            exchange(dieFromReserve, dieFromBag);
            dieFromReserve.setDieNumber(newDieValue);
            if (!checkConstrain(controller)) {
                controller.showBadConstraint();
                return false;
            }
            move(controller, dieFromReserve);
            controller.getModel().getMatch().getBag().reInsertDie(dieFromBag);
            return true;
        } else
            return false;
    }

    /**
     * Parameters Getter
     * This method catch all parameters needed for the application of the ToolCard.
     * @param controller is the game controller
     */
    @Override
    public void getParameters(Controller controller) {
       dieFromReserve = controller.getReserveChosenDie();
       if(dieFromReserve != null) {
           controller.getModel().getMatch().getReserve().removeDie(dieFromReserve);
           dieFromBag = controller.getRandomDieFromBag(controller.getPlayerOfCurrentTurn().getPlayerAddress());
           newDieValue = controller.getDieNewValue(controller.getPlayerOfCurrentTurn().getPlayerAddress());
           if(newDieValue != -1)
               coordinates = controller.getDiePositionChangeCoordinates();
           else
               coordinates = new int[] {-1, -1};
       }
    }
}
package it.polimi.se2018.controller.toolCards;

import it.polimi.se2018.controller.Controller;
import it.polimi.se2018.controller.effects.checkMethods.checkConstraintMethods.CheckDiePlacement;
import it.polimi.se2018.controller.effects.checkMethods.checkConstraintMethods.ChooseCheckConstraint;
import it.polimi.se2018.controller.effects.structuresModifiersMethods.DieMover;
import it.polimi.se2018.controller.effects.structuresModifiersMethods.DieRemover;
import it.polimi.se2018.model.Die;
import it.polimi.se2018.model.Grid;
import it.polimi.se2018.model.Player;
import java.util.ArrayList;

/**
 * ManualCutter Class extends CheckAndPick implements ModelSideToolCard
 * This class is the logic of the Manual Cutter ToolCard
 * @author Federico Lichinchi
 * @author Davide Lorenzi (JDocs)
 */
public class ManualCutter extends CheckAndPick implements ModelSideToolCard {

    private static final int CARDNUMBER = 12;

    private Die diceOne;
    private Die diceTwo;
    private int[] coordinates;

    /**
     * Die Picker
     * This method verifies if is possible or not to pick a die
     * @param controller is the game logic controller
     * @param die is the chosen die
     * @return true if is possible to pick a die, false otherwise
     */
    private boolean isPossibleToPick(Controller controller, Die die) {
        return die!= null && isSameColourOfRoundTrack(controller,die);
    }

    /**
     * Controller Interaction Manager
     * This method manages all controllers interactions
     * @param controller game logic controller
     */
    @Override
    public void controllerInteraction(Controller controller) {
        Grid grid = controller.getPlayerOfCurrentTurn().getGrid();
        String address = controller.getPlayerOfCurrentTurn().getPlayerAddress();
        do{
           coordinates = controller.getDieToMoveFromSchemeCard(address,1);
           if(coordinates[0] != -1) {
               diceOne = grid.getDie(coordinates[0], coordinates[1]);
               if (!isSameColourOfRoundTrack(controller, diceOne))
                   controller.showInvalidColourDie(address);
               if (diceOne == null)
                   controller.showInvalidInput(address);
           }
        }while(!isPossibleToPick(controller,diceOne) && coordinates[0] != -1);
        if(coordinates[0] != -1) {
            DieRemover remover = new DieRemover();
            remover.removeDie(coordinates[0], coordinates[1], controller.getPlayerOfCurrentTurn());
            do {
                coordinates = controller.getDieToMoveFromSchemeCard(address, 2);
                if(coordinates[0] != -1)
                    diceTwo = grid.getDie(coordinates[0], coordinates[1]);
            } while (coordinates[0] != -1 && (diceTwo == null || !(diceTwo.getDieColor().equals(diceOne.getDieColor()))));
            if(coordinates[0] != -1) {
                remover.removeDie(coordinates[0], coordinates[1], controller.getPlayerOfCurrentTurn());
                do {
                    coordinates = controller.askForNewPositionDie(address);
                    if(coordinates[0] != -1 && !arePlacementConstraintsRespected(coordinates[0], coordinates[1], diceOne, controller.getPlayerOfCurrentTurn()))
                        controller.showBadConstraint();
                }
                while (!arePlacementConstraintsRespected(coordinates[0], coordinates[1], diceOne, controller.getPlayerOfCurrentTurn()));
            }
        }
    }

    /**
     * CardNumber Getter
     * This method catch the correct card number
     * @return required card number
     */
    @Override
    public int getCardNumber() {
        return CARDNUMBER;
    }

    /**
     * Color Checker
     * This method verifies if a die is of the same color of one on the RoundTrack
     * @param controller is the game controller unit
     * @param die is the chosen die
     * @return true if the die is the same color, false otherwise
     */
    private boolean isSameColourOfRoundTrack(Controller controller,Die die){
        ArrayList<Die> dice = controller.getModel().getMatch().getRoundTrack().getRemainingDice();
        if(dice == null || die==null)
            return false;
        for (Die dice1 : dice) {
            if(dice1 != null && die.getDieColor().equals(dice1.getDieColor()))
                return true;
        }
        return false;
    }

    /**
     * ToolCard Applier
     * This method verifies the application of the ToolCard
     * @param controller is the game controller
     * @return true if ToolCard has been correctly applied, false otherwise
     */
    @Override
    public boolean applyToolCard(Controller controller) {
        Player player = controller.getPlayerOfCurrentTurn();
        getParameters(controller);
        DieMover mover = new DieMover();
        if(coordinates[0] == -1)
            return false;
        else {
            mover.moveDie(diceOne, coordinates[0], coordinates[1], player);
            do {
                coordinates = controller.askForNewPositionDie(player.getPlayerAddress());
                if(coordinates[0] != -1 && !arePlacementConstraintsRespected(coordinates[0], coordinates[1], diceTwo, player))
                    controller.showBadConstraint();
            } while (!arePlacementConstraintsRespected(coordinates[0], coordinates[1], diceTwo, player) &&
                    coordinates[0] != -1);
            if(coordinates[0] == -1)
                return false;
            else {
                mover.moveDie(diceTwo, coordinates[0], coordinates[1], player);
                return true;
            }
        }
    }

    /**
     * Parameters Getter
     * This method catch all parameters needed for the application of the ToolCard
     * @param controller is the game controller
     */
    @Override
    public void getParameters(Controller controller) {
        controllerInteraction(controller);

    }

    /**
     * Placement Constraint Checker
     * This method verifies if all placement constraints are respected
     * @param newRow    row in which the player wanna set the die after the application of the ToolCard
     * @param newColumn column in which the player wanna set the die after the application of the ToolCard
     * @param chosenDie is the die chosen on which wil be applied the effects of the chosen ToolCard
     * @param player    is the player that bought the ToolCard
     * @return true if placement constraint are respected or not
     */
    @Override
    public boolean arePlacementConstraintsRespected(int newRow, int newColumn, Die chosenDie, Player player) {
        ChooseCheckConstraint constraint = new ChooseCheckConstraint(new CheckDiePlacement());
        return constraint.checkConstraint(newRow,newColumn,chosenDie,player);
    }
}

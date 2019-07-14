package it.polimi.se2018.controller.toolCards;

import it.polimi.se2018.controller.Controller;
import it.polimi.se2018.controller.effects.checkMethods.CheckGridPicker;
import it.polimi.se2018.controller.effects.pickerMethods.GridDiePicker;
import it.polimi.se2018.controller.effects.structuresModifiersMethods.DieMover;
import it.polimi.se2018.model.Die;
import it.polimi.se2018.model.Player;


/**
 * CheckAndPick Abstract Class
 * This class is used for check the application of die moving controls
 * @author Luca Massini
 * @author Davied Lorenzi (JDocs)
 */
public abstract class CheckAndPick {
    private Die pickedDie;
    private CheckGridPicker pickChecker;
    private GridDiePicker picker;
    private DieMover mover;
    private int[] oldCoordinates = new int[2];
    private int[] newCoordinates = new int[2];
    private Player player;

    /**
     * CheckAndPick Constructor
     * This method creates all elements of the class
     */
    public CheckAndPick(){
        pickChecker = new CheckGridPicker();
        picker = new GridDiePicker();
        mover = new DieMover();
    }

    /**
     * Pick Validator
     * This method verifies if is possible to pick a die or not
     * @return true if is possible to pick a die, false otherwise
     */
    public boolean isPossibleToPick(){
        return pickChecker.isPossibleToPick(oldCoordinates[0],oldCoordinates[1],player);
    }

    /**
     * Die Picker
     * This method picks a die from the player's grid
     */
    public void pickADie(){
        pickedDie = picker.getPickedDie(oldCoordinates[0],oldCoordinates[1],player);
    }

    /**
     * Die Mover
     * This method moves a die
     */
    public void moveTheDie(){
        mover.moveDie(pickedDie,newCoordinates[0],newCoordinates[1],player);
    }

    /**
     * PickedDie Getter
     * This method returns the picked die
     * @return the picked die
     */
    private Die getPickedDie() {
        return pickedDie;
    }

    /**
     * Controller Interaction Manager
     * This method manages all interactions on the controller
     * @param controller game logic controller
     */
    public void controllerInteraction(Controller controller){
        this.player = controller.getPlayerOfCurrentTurn();
        this.oldCoordinates = controller.getDiePositionChangeCoordinates();
        if(oldCoordinates[0] != -1)
            this.newCoordinates = controller.getNewDiePositionCoordinates();
    }


    /**
     * Position Reset
     * This method move a picked die into old position if is not possible to apply ToolCards
     */
    private void restartPosition(){
        mover.moveDie(pickedDie,oldCoordinates[0],oldCoordinates[1],player);
    }

    /**
     * Applier
     * This method applies the ToolCard
     * @param controller is the game controller
     * @return true if the ToolCard has been correctly applied, false otherwise
     */
    public boolean apply(Controller controller){
        controllerInteraction(controller);
        if(oldCoordinates[0] != -1 && newCoordinates[0] != -1 && isPossibleToPick()) {
            pickADie();
            try {
                if(arePlacementConstraintsRespected(newCoordinates[0],newCoordinates[1],getPickedDie().clone(),getPlayer())) {
                    moveTheDie();
                    return true;
                }
                else {
                    restartPosition();
                    controller.showBadConstraint();
                    return false;
                }
            } catch (CloneNotSupportedException e) {
                System.out.println("Die clone failed.");
            }
        }
        else {
            return false;
        }
        return false;
    }


    /**
     * Player Getter
     * This method returns the required player
     * @return the required player
     */
    public Player getPlayer(){
        return player;
    }

    /**
     * PlacementConstraint Checker
     * This method verifies if constraints are respected or not
     * @param newRow row in which the player wanna set the die after the application of the ToolCard
     * @param newColumn column in which the player wanna set the die after the application of the ToolCard
     * @param chosenDie is the die chosen on which wil be applied the effects of the chosen ToolCard
     * @param player is the player that bought the ToolCard
     * @return true if is possible to apply the move after the application of the ToolCard, false otherwise
     */
    public abstract boolean arePlacementConstraintsRespected(int newRow, int newColumn, Die chosenDie, Player player);
}

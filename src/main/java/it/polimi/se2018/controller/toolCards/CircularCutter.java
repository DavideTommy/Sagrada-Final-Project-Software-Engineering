package it.polimi.se2018.controller.toolCards;

import it.polimi.se2018.controller.Controller;
import it.polimi.se2018.controller.effects.checkMethods.CheckRoundTrackPicker;
import it.polimi.se2018.controller.effects.checkMethods.checkConstraintMethods.CheckDiePlacement;
import it.polimi.se2018.controller.effects.checkMethods.checkConstraintMethods.ChooseCheckConstraint;
import it.polimi.se2018.controller.effects.pickerMethods.RoundTrackDiePicker;
import it.polimi.se2018.controller.effects.structuresModifiersMethods.PutDieInRoundTrack;
import it.polimi.se2018.model.Die;
import it.polimi.se2018.model.Player;
import it.polimi.se2018.model.mv.Model;

/**
 * CircularCutter Class extends CheckAndPick implements ModelSideToolCard
 * This class represents the ToolCard CircularCutter
 * @author Federico Lichinchi
 * @author Davide Lorenzi (JDocs)
 */
public class CircularCutter extends CheckAndPick implements ModelSideToolCard {

     /*
    NB: this class extends CheckAndPick class and so it also can obviously contains his method that are:
    isPossibleToPick, pickADie and finally moveTheDie
     */

    private static final int CARDNUMBER = 5;

    private PutDieInRoundTrack move;
    private CheckRoundTrackPicker checker;
    private RoundTrackDiePicker picker;
    private Model model;
    private Die reserveDie;
    private Die roundTrackDie;

    /**
     * CircularCutter Constructor
     * This method initializes the circular cuter ToolCard
     * @param model ist the logic model of the game
     */
    public CircularCutter(Model model){
        super();
        move = new PutDieInRoundTrack();
        checker = new CheckRoundTrackPicker();
        picker = new RoundTrackDiePicker();
        this.model = model;
    }

    /**
     * Picker Checker
     * This method verifies if the chosen die to pick exists or not
     * @param index is
     * @return true if is possible to apply the move after the application of the ToolCard, false otherwise
     */
    public boolean isPossibleToPick(int index){
        return checker.isPossibleToPick(index,model);
    }

    /**
     * Die Picker
     * This method picks the chosen die
     * @param index index of the cell on the RoundTrack
     */
    public void pickADie(int index){
        reserveDie = picker.getPickedDie(index,model);
    }

    /**
     * Die Mover
     * This method moves the chosen die
     */
    @Override
    public void moveTheDie(){
        move.putInRoundTrack(reserveDie,model);
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
    @Override
    public boolean arePlacementConstraintsRespected(int newRow, int newColumn, Die chosenDie, Player player) {
        if(player.getGrid().isGridEmpty())
            player.getGrid().setFirstTimeToTrue();
        ChooseCheckConstraint choseConstraint = new ChooseCheckConstraint(new CheckDiePlacement()) ;
        return choseConstraint.checkConstraint(newRow,newColumn,chosenDie,player);
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
     * This method verifies the application of the ToolCard
     * @param controller is the game controller
     * @return true if the ToolCard has been correctly applied, false otherwise
     */
    @Override
    public boolean applyToolCard(Controller controller) throws CloneNotSupportedException {
        getParameters(controller);
        if(roundTrackDie == null || reserveDie == null)
            return false;
        else {
            controller.removeDieFromReserve(reserveDie.clone());
            controller.addDieInRoundTrack(reserveDie);

            controller.removeDieFromRoundTrack(roundTrackDie.clone());
            controller.addDieInReserve(roundTrackDie);
            return true;
        }
    }

    /**
     * Parameters Getter
     * This method catch all parameters needed for the application of the ToolCard
     * @param controller is the game controller
     */
    @Override
    public void getParameters(Controller controller) {
        reserveDie = controller.getReserveChosenDie();
        if(reserveDie != null) {
            if (controller.getRoundTrackDice().isEmpty())
                roundTrackDie = null;
            else
                roundTrackDie = controller.getRoundTrackChosenDie();
        }
    }
}

package it.polimi.se2018.controller.toolCards;

import it.polimi.se2018.controller.Controller;
import it.polimi.se2018.controller.effects.diceModifiersMethods.ChooseDieModifier;
import it.polimi.se2018.controller.effects.diceModifiersMethods.RollDie;
import it.polimi.se2018.model.Die;
import it.polimi.se2018.model.Grid;

/**
 * BrushForSoldPaste Class implements ModelSideToolCard
 * This class represents the ToolCard BrushForSoldPaste
 * @author Federico lichinchi
 * @author Davide Lorenzi (JDocs)
 */
public class BrushForSoldPaste implements ModelSideToolCard {

    private static final int  CARDNUMBER = 6;
    private Die reserveDie;

    /**
     * Die Roller
     * This method chooses a new random value for the chosen die
     * @param die chosen die
     */
    public void rollTheDie(Die die){
        ChooseDieModifier modifier = new ChooseDieModifier(new RollDie());
        modifier.modifyDie(die);
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
    public boolean applyToolCard(Controller controller) {
        getParameters(controller);
        if(reserveDie != null) {
            rollTheDie(reserveDie);
            if (controller.askForUseNewDiceValue(reserveDie)) {
                String playerAddress = controller.getPlayerOfCurrentTurn().getPlayerAddress();
                Grid grid = controller.getPlayerOfCurrentTurn().getGrid();
                controller.addDie(playerAddress, grid, reserveDie);
            }
            return true;
        }
        else
            return false;
    }

    /**
     * Parameters Getter
     * This method catch all parameters needed for the application of the ToolCard
     * @param controller is the game controller
     */
    @Override
    public void getParameters(Controller controller) {
        reserveDie = controller.getReserveChosenDie();
    }
}

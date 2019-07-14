package it.polimi.se2018.controller.toolCards;

import it.polimi.se2018.controller.Controller;
import it.polimi.se2018.controller.effects.diceModifiersMethods.ChooseDieModifier;
import it.polimi.se2018.controller.effects.diceModifiersMethods.TurnTheDieOpposite;
import it.polimi.se2018.model.Die;

/**
 * DiamondPad Class implements ModelSideToolCard
 * This class represents the ToolCard DiamondPad
 * @author Luca Massini
 * @author Davide Lorenzi (JDocs)
 */
public class DiamondPad implements ModelSideToolCard {

    private static final int CARDNUMBER = 10;
    private Die die;

    /**
     * Die Turner
     * This methods turn the chosen die on the other opposite face
     * @param die chosen die
     */
    private void turnDie(Die die){
        ChooseDieModifier modifier = new ChooseDieModifier(new TurnTheDieOpposite());
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
        if(die == null)
            return false;
        else {
            turnDie(die);
            controller.showRolledDie(controller.getPlayerOfCurrentTurn().getPlayerAddress(), die);
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
        controller.chooseDieFromReserve(controller.getPlayerOfCurrentTurn().getPlayerAddress());
        if(controller.isConnected(controller.getPlayerOfCurrentTurn().getPlayerAddress()) && controller.getNumberOfPlayerMove() >= 0)
            die = controller.getDiceInReserve().get(controller.getNumberOfPlayerMove());
        else
            die = null;
    }
}

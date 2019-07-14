package it.polimi.se2018.controller.toolCards;

import it.polimi.se2018.controller.Controller;
import it.polimi.se2018.controller.effects.checkMethods.CheckSecondTurn;
import it.polimi.se2018.controller.effects.structuresModifiersMethods.RollTheReserve;
import it.polimi.se2018.model.mv.Model;

/**
 * Gavel Class implements ModelSideToolCard
 * This class represents the ToolCard Gavel
 * @author Luca Massini
 * @author Davide Lorenzi (JDocs)
 */
public class Gavel implements ModelSideToolCard {

    private static final int CARDNUMBER = 7;
    private Model model;

    /**
     * Gavel Constructor
     * This method creates the Gavel elements
     * @param  model class core of the game
     */
    public Gavel(Model model){
        this.model = model;
    }

    /**
     * Reserve Roller
     * This method changes the value of all Die into the reserve
     */
    public void rollTheReserve(){
        new RollTheReserve().rollTheReserve(model);
    }

    /**
     * SecondTurn Checker
     * This method verifies if is the second turn of a player or not
     * @return true if is the second turn, false otherwise
     */
    private boolean isSecondTurn(){
        return new CheckSecondTurn().isThisTheSecondTurn(model);
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
        if(!isSecondTurn())
            return false;
        else{
            if(controller.hasAlreadyBeenChosen())
                return false;
            else {
                rollTheReserve();
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
        controller.showReserve();
    }
}

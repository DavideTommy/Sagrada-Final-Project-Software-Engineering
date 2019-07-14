package it.polimi.se2018.controller.toolCards;

import it.polimi.se2018.controller.Controller;
import it.polimi.se2018.controller.effects.checkMethods.checkDieChecker.CheckDieDecrement;
import it.polimi.se2018.controller.effects.checkMethods.checkDieChecker.CheckDieEnhance;
import it.polimi.se2018.controller.effects.checkMethods.checkDieChecker.ChooseCheckChecker;
import it.polimi.se2018.controller.effects.diceModifiersMethods.ChooseDieModifier;
import it.polimi.se2018.controller.effects.diceModifiersMethods.DieReducer;
import it.polimi.se2018.controller.effects.diceModifiersMethods.DieEnhancer;
import it.polimi.se2018.model.Die;

/**
 * RoughingForceps Class implements ModelSideToolCard
 * This class represents the ToolCard RoughingForceps
 * @author Luca Massini
 * @author Davide Lorenzi (JDocs)
 */
public class RoughingForceps implements ModelSideToolCard {

    private static final int CARDNUMBER = 1;

    private ChooseCheckChecker checker;
    private ChooseDieModifier modifier;
    private Die die;
    private boolean decrement;


    /**
     * Decrement Checker
     * This method verifies if is possible to reduce the value of a die
     * @param die chosen die
     * @return true if is possible to apply the move after the application of the ToolCard, false otherwise
     */
    private boolean isPossibleToDecrement(Die die){
        checker = new ChooseCheckChecker(new CheckDieDecrement());
        return checker.checkModify(die);
    }

    /**
     * Enhancer Verifier
     * This method verifies if is it possible to enhance the chosenDie.
     * @param die is the chosen die
     * @return true if is possible to apply the move after the application of the ToolCard, false otherwise
     */
    private boolean isPossibleToEnhance(Die die){
        checker = new ChooseCheckChecker(new CheckDieEnhance());
        return checker.checkModify(die);
    }

    /**
     * Die Enhancer
     * This method increments by 1 the value of the chosen die
     * @param die the chosen die
     */
    private void enhanceDie(Die die){
        modifier = new ChooseDieModifier(new DieEnhancer());
        modifier.modifyDie(die);
    }

    /**
     * Die Reducer
     * This method reduces by 1 the value of the chosen die
     * @param die the chosen die
     */
    private void decrementDie(Die die){
        modifier = new ChooseDieModifier(new DieReducer());
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
        if(die != null) {
            if (decrement) {
                if (isPossibleToDecrement(die)) {
                    decrementDie(die);
                    return true;
                }
            } else if (isPossibleToEnhance(die)) {
                enhanceDie(die);
                return true;
            }
        }
        return false;
    }


    /**
     * Parameters Getter
     * This method catch all parameters needed for the application of the ToolCard
     * @param controller is the game controller
     */
    @Override
    public void getParameters(Controller controller) {
        this.decrement = controller.getChoseDieValueOption();
        this.die = controller.getReserveChosenDie();
    }
}

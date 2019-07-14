package it.polimi.se2018.controller.effects.structuresModifiersMethods;

import it.polimi.se2018.controller.effects.diceModifiersMethods.ChooseDieModifier;
import it.polimi.se2018.controller.effects.diceModifiersMethods.RollDie;
import it.polimi.se2018.model.Die;
import it.polimi.se2018.model.Reserve;
import it.polimi.se2018.model.mv.Model;

/**
 * ReserveRoller Class
 * This class allows exchange the value of all Die into the reserve
 * @author Luca Massini
 * @author Davide Lorenzi (JDocs)
 */
public class RollTheReserve {


    /**
     * Reserve Roller Constructor
     * This method changes the value of all Die into the reserve
     * @param model is the model class of the game
     */
    public void rollTheReserve(Model model){
        Reserve reserve = model.getMatch().getReserve();
        ChooseDieModifier modifier = new ChooseDieModifier(new RollDie());
        for (Die die : reserve.getDie()) {
            modifier.modifyDie(die);
        }
    }

}

package it.polimi.se2018.controller.effects.structuresModifiersMethods;

import it.polimi.se2018.model.Die;

/**
 * ReserveExchanger Class
 * This class allows to exchange a die between reserve and bag
 * @author Luca Massini
 * @author Davide Lorenzi (JDocs)
 */
public class ExchangeDieBetweenReserveAndBag {

    /**
     * Die Exchanger
     * This method allow to exchange Die between the Bag and the Reserve
     * @param dicePickedFromReserve the chosen die in the reserve
     * @param dicePickedFromBag a random die extracted from the Die bag
     */
    public void exchange(Die dicePickedFromReserve, Die dicePickedFromBag){
        int reserveValue = dicePickedFromReserve.getDieNumber();
        int bagValue = dicePickedFromBag.getDieNumber();
        String reserveColor = dicePickedFromReserve.getDieColor();
        String bagColor = dicePickedFromBag.getDieColor();
        dicePickedFromBag.setDieNumber(reserveValue);
        dicePickedFromReserve.setDieNumber(bagValue);
        dicePickedFromBag.setDieColor(reserveColor);
        dicePickedFromReserve.setDieColor(bagColor);
    }

}

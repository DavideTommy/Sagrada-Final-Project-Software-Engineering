package it.polimi.se2018.model.parsers;

import it.polimi.se2018.model.Player;

/**
 * PlayerObjectParser Class implements ObjectParser
 * This class is used to parse the Object Player into a string
 * @author Federico Lichinchi
 * @author Davide Lorenzi (JDocs)
 */
public class PlayerObjectParser implements ObjectParser {

    private static final String OBJECT_NAME = "Player";

    /**
     * ObjectName Getter
     * This method gets the name of the required Object
     * @return the required Object name
     */
    @Override
    public String getObjectName() {
        return OBJECT_NAME;
    }

    /**
     * Grid Object Converter
     * This method parse the Player on a string
     * @param element is the object to parse
     * @return the string of the required object
     */
    @Override
    public String toStringObject(Object element) {
        Player player = (Player) element;
        return "PLAYER: " + player.getPlayerName() + ", "+player.getScore()+"\n";
    }
}

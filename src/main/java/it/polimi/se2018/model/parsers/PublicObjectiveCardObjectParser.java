package it.polimi.se2018.model.parsers;

import it.polimi.se2018.model.cards.PublicObjectiveCard;

/**
 * PublicObjectiveCardObjectParser Class implements ObjectParser
 * This class is used to parse the Object PublicObjectiveCard into a string
 * @author Federico Lichinchi
 * @author Davide Lorenzi (JDocs)
 */
public class PublicObjectiveCardObjectParser implements ObjectParser {

    private static final String OBJECT_NAME = "PublicObjectiveCard";

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
     * PublicObjectiveCard Object Converter
     * This method parse the PublicObjectiveCard on a string
     * @param element is the object to parse
     * @return the string of the required object
     */
    @Override
    public String toStringObject(Object element) {
        return ((PublicObjectiveCard) element).showCard();
    }
}

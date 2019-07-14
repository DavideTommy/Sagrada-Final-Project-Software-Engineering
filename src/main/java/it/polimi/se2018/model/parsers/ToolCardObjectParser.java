package it.polimi.se2018.model.parsers;

import it.polimi.se2018.model.cards.ToolCard;

/**
 * ToolCardObjectParser Class implements ObjectParser
 * This class is used to parse the Object ToolCard into a string
 * @author Federico Lichinchi
 * @author Davide Lorenzi (JDocs)
 */
public class ToolCardObjectParser implements ObjectParser {

    private static final String OBJECT_NAME = "ToolCard";

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
     * ToolCard Object Converter
     * This method parse the ToolCard on a string
     * @param element is the object to parse
     * @return the string of the required object
     */
    @Override
    public String toStringObject(Object element) {
        return ((ToolCard) element).showCard();
    }
}

package it.polimi.se2018.model.parsers;

import it.polimi.se2018.model.Die;


/**
 * DieObjectParser Class implements ObjectParser
 * This class is used to parse the Object "Die" into a string
 * @author Federico Lichinchi
 * @author Davide Lorenzi (JDocs)
 */
public class DieObjectParser implements ObjectParser {

    private static final String OBJECT_NAME = "Die";
    private Die die;

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
     * Object Converter
     * This method parse the object on a string
     * @param element is the object to parse
     * @return the string of the required object
     */
    @Override
    public String toStringObject(Object element) {
        die = ((Die) element);
        return "Color: " + die.getDieColor() + ", Number: " + die.getDieNumber();
    }

    /**
     * Die Getter
     * This method gets a required die
     * @return the required die
     */
    public Die getDie() {
        return die;
    }
}

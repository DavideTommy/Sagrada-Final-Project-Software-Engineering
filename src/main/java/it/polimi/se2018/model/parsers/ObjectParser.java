package it.polimi.se2018.model.parsers;

import java.io.Serializable;

/**
 * ObjectParser Class extends Serializable
 * This class is used to parse the Object into a string
 * @author Federico Lichinchi
 * @author Davide Lorenzi (JDocs)
 */
public interface ObjectParser extends Serializable {
    /**
     * ObjectName Getter
     * This method gets the name of the required Object
     * @return the required Object name
     */
    String getObjectName();

    /**
     * Object Converter
     * This method parse the object on a string
     * @param element is the object to parse
     * @return the string of the required object
     */
    String toStringObject(Object element);
}

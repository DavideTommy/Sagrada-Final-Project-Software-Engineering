package it.polimi.se2018.model.parsers;

import it.polimi.se2018.model.Grid;

import java.util.ArrayList;

/**
 * GridObjectParser Class implements ObjectParser
 * This class is used to parse the Object Grid into a string
 * @author Federico Lichinchi
 * @author Davide Lorenzi (JDocs)
 */
public class GridObjectParser implements ObjectParser {

    private static final String OBJECT_NAME = "Grid";

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
     * This method parse the Grid on a string
     * @param element is the object to parse
     * @return the string of the required object
     */
    @Override
    public String toStringObject(Object element) {
        StringBuilder tmp = new StringBuilder();
        ArrayList<String> strings = (ArrayList<String>) ((Grid) element).gridToArrayListOfStrings();
        tmp.append("SchemeCard name: " + ((Grid) element).getCardName() + "\n");
        tmp.append("Difficulty value: " + ((Grid) element).getDifficultyValue() + "\n");
        for(int i = 0; i < strings.size(); i++) {
            tmp.append(strings.get(i)).append("\n");
        }
        return tmp.toString();
    }
}

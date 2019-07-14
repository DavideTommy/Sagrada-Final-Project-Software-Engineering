package it.polimi.se2018.model;

import java.io.Serializable;

/**
 * SchemeCard Class
 * @author Luca Massini
 */
public class SchemeCard implements Serializable {
       private String schemeCardName;
       private int difficultValue;
       private String[][] matrixOfColors;
       private int[][] matrixOfNumbers;

    /**
     * SchemeCard Constructor
     * This method creates an effective scheme card
     * @param name is the name of the SchemeCard that is required by this class
     */
    public SchemeCard(String name) {
        BoxOfSchemeCards box = BoxOfSchemeCards.newBox();
        try {
            SchemeCard tmp;
            tmp = box.request(name);
            this.schemeCardName = tmp.getSchemeCardName();
            this.difficultValue = tmp.getDifficultValue();
            this.matrixOfNumbers = tmp.getMatrixOfNumbers();
            this.matrixOfColors = tmp.getMatrixOfColors();
        }
        catch (IllegalArgumentException e){
            throw new IllegalArgumentException();
        }
    }

    /**
     * SchemeCard Name Getter
     * This method is used to get the required SchemeCard name
     * @return the attribute name of the object
     */
    public String getSchemeCardName(){
           return schemeCardName;
       }

    /**
     * Difficult Value Getter
     * This method is used to get the required SchemeCard difficultValue
     * @return the attribute difficultValue of the object
     */
    public int getDifficultValue(){
           return difficultValue;
       }

    /**
     * MatrixOfNumbers Getter
     * This method is used to get the required MatrixOfNumbers
     * @return the attribute matrixOfNumbers of the object
     */
    public int[][] getMatrixOfNumbers(){
           return  matrixOfNumbers.clone();
       }

    /**
     * MatrixOfColor Getter
     * This method is used to get the required MatrixOfColors
     * @return the attribute matrixOfColors of the class
     */
    public String[][] getMatrixOfColors(){
           return matrixOfColors.clone();
    }
}

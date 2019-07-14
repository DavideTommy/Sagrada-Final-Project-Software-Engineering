package it.polimi.se2018.model.score;

/**
 * ScoreFactory Class
 * This class is a factory method based on the number of the card.
 * @author Luca Massini
 */
public class ScoreFactory {

    private static ScoreFactory ist = null;

    /**
     * ScoreFactory Constructor
     * Class constructor empty for singleton instance
     */
    private ScoreFactory(){

    }

    /**
     * Singleton Caller
     * This method initialize a new instance of the ScoreFactory if it hasn't been done before
     * @return a new ScoreFactory singleton object
     */
    public static ScoreFactory newScoreFactory(){
        if(ist == null) ist = new ScoreFactory();
        return ist;
    }

    /**
     * PublicScore Caller
     * This method is used to call the proper PublicObjectiveScore function related to the random extracted
     * PublicObjectiveCards
     * @param cardNumber is the number of the Public Objective Card
     * @return an object that implements the PublicObjectiveScore interface based on the public card number, select the relevant scoring object.
     */
    public PublicObjectiveScore getCalculationScoreObject(int cardNumber){
        if(cardNumber == ColoredDiagonals.CARD_NUMBER)
            return ColoredDiagonals.newColoredDiagonals();
        if(cardNumber == DeepShades.CARD_NUMBER)
            return DeepShades.newDeepShades();
        if(cardNumber == DifferentColorColumn.CARD_NUMBER)
            return DifferentColorColumn.newDifferentColorColumn();
        if(cardNumber == DifferentColorRow.CARD_NUMBER)
            return DifferentColorRow.newDifferentColorRow();
        if(cardNumber == DifferentColors.CARD_NUMBER)
            return DifferentColors.newDifferentColors();
        if(cardNumber == DifferentShadeColumn.CARD_NUMBER)
            return DifferentShadeColumn.newDifferentShadeColumn();
        if(cardNumber == DifferentShades.CARD_NUMBER)
            return DifferentShades.newDifferentShades();
        if(cardNumber == DifferentShadesRow.CARD_NUMBER)
            return DifferentShadesRow.newDifferentShadesRow();
        if(cardNumber == LightShades.CARD_NUMBER)
            return LightShades.newLightShades();
        if(cardNumber == MediumShades.CARD_NUMBER)
            return MediumShades.newMediumShades();
        return null;
    }
}

package it.polimi.se2018.controller.toolCards;

import it.polimi.se2018.controller.Controller;
import it.polimi.se2018.model.cards.ToolCard;

import java.util.ArrayList;


/**
 * ToolCardManager Class
 * This class manages all ToolCards into the controller
 * @author Federico Lichinchi
 * @author Davide Lorenzi (JDocs)
 */
public class ToolCardManager {

    private static ToolCardManager singleton = null;
    private ArrayList<ModelSideToolCard> modelSideToolCards;
    private Controller controller;

    private ToolCardManager(Controller controller) {
        this.controller = controller;
        modelSideToolCards = new ArrayList<>(12);
        modelSideToolCards.add(new BrushForEglomise());
        modelSideToolCards.add(new BrushForSoldPaste());
        modelSideToolCards.add(new CircularCutter(this.controller.getModel()));
        modelSideToolCards.add(new CopperFoilReamer());
        modelSideToolCards.add(new DiamondPad());
        modelSideToolCards.add(new Gavel(this.controller.getModel()));
        modelSideToolCards.add(new Lathekin());
        modelSideToolCards.add(new RoughingForceps());
        modelSideToolCards.add(new RulerInCork());
        modelSideToolCards.add(new DiluentForFirmPaste());
        modelSideToolCards.add(new ManualCutter());
        modelSideToolCards.add(new WheeledPincer());
    }

    /**
     * Singleton Caller
     * This method creates a new instance of the ToolCard manager
     * @param controller is the controller of the game
     * @return the status of the instance of the manager
     */
    public static ToolCardManager newToolCardManager(Controller controller){
        if(singleton == null)
            singleton = new ToolCardManager(controller);
        return singleton;
    }

    /**
     * ToolCard Applier
     * This method allows to apply the required ToolCard
     * @param toolCard required ToolCard
     * @return the call to the model manager of ToolCards
     */
    public boolean applyRequiredToolCard(ToolCard toolCard) {
        for(ModelSideToolCard modelSideToolCard : modelSideToolCards) {
            if(modelSideToolCard.getCardNumber() == toolCard.getCardNumber()) {
                try {
                    return modelSideToolCard.applyToolCard(controller);
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }
            }
        }
        System.err.println("ERROR -- ToolCard effect not found\n");
        return false;
    }
}

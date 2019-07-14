package it.polimi.se2018.gui;

import it.polimi.se2018.model.cards.PrivateObjectiveCard;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.File;


/**
 * ChoseScheme Controller Class
 * This class is used to manage the FXML of the SchemeCard choosing scene
 * @author Davide Lorenzi
 */
public class ChoseSchemeController {

    private String nickName;
    private Stage stage;
    private PrivateObjectiveCard personalPrivateObjectiveCard;


    @FXML
    protected AnchorPane mainAnchor;

    @FXML
    protected ImageView sc0;

    @FXML
    protected ImageView sc1;

    @FXML
    protected ImageView sc2;

    @FXML
    protected ImageView sc3;

    @FXML
    protected ImageView pc;

    @FXML
    private Button bt0;

    @FXML
    private Button bt1;

    @FXML
    private Button bt2;

    @FXML
    private Button bt3;


    /**
     * NickName Setter
     * This method is used to initialize the player's nickname
     * @param nickName is the player's nickname
     */
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    /**
     * GUIStarterApp Container Setter
     * This method sets the graphic container of the scene
     * @param mainAnchor is the anchorPane container
     */
    public void setMainAnchor(AnchorPane mainAnchor) {
        this.mainAnchor = mainAnchor;
    }

    /**
     * PersonalPrivateObjectiveCard Setter
     * This method is used to set the random Private objective card of the player
     * @param personalPrivateObjectiveCard is the random private objective card
     */
    public void setPersonalPrivateObjectiveCard(PrivateObjectiveCard personalPrivateObjectiveCard) {
        this.personalPrivateObjectiveCard = personalPrivateObjectiveCard;
    }

    /**
     * Stage Setter
     * This method is used to initialize the graphic scene
     * @param stage is the overall scene
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Card Setter
     * This method is used to set a Random SchemeCard on the choice table
     */
    private void showPrivateCard()  {

        Image image = null;
        try {
            //image = new Image(this.personalPrivateObjectiveCard.getImagePath());
        }
        catch (Exception e) {
            image = new Image("resources/gui/images/publicObjectiveCards/PublicBack.png");
        }

        this.pc.setImage(image);
        this.pc.setPreserveRatio(true);
        this.pc.setSmooth(true);
        this.pc.setCache(true);

    }

    /**
     * Choice Buttons Manager
     * This method is used to manage the click of a specific button for the choice of a SchemeCard
     * @param actionEvent is the click event
     */
    public void handleChosenSchemeButtonAction(ActionEvent actionEvent){
        //TODO prendere la scheme card 0 e prenderla come carta scelta
        //idea: prendo il fx id del bottone premuto e ritorno quel valore per scegliere la card
        disableOtherActions();
    }

    /**
     * Button Disable
     * This method is used to disable all choice buttons after one scheme has been chosen
     */
    private void disableOtherActions() {
        this.sc0.setDisable(true);
        this.sc1.setDisable(true);
        this.sc2.setDisable(true);
        this.sc3.setDisable(true);
    }

}

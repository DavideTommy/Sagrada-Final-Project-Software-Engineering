package it.polimi.se2018.gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import javafx.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;


/**
 * IncrementDecrement Controller Class
 * This class is used to manage the FXML of the ToolCard that allows to increment or reduce the value of a die
 * @author Davide Lorenzi
 */
public class IncrementDecrementController implements Initializable {

    @FXML
    protected AnchorPane mainAnchor;

    @FXML
    protected Button increaseBt;

    @FXML
    protected Button decreaseBt;


    /**
     * GUIStarterApp Container Setter
     * This method sets the graphic container of the scene
     * @param mainAnchor is the anchorPane container
     */
    public void setMainAnchor(AnchorPane mainAnchor) {
        this.mainAnchor = mainAnchor;
    }

    /**
     * Reduce Button Initializer
     * This method initializes the button used to reduce the value of a die
     * @param decreaseBt is the specified Button
     */
    public void setDecreaseBt(Button decreaseBt) {
        this.decreaseBt = decreaseBt;
    }

    /**
     * Increase Button Initializer
     * This method initializes the button used to increment the value of a die
     * @param increaseBt is the specified Button
     */
    public void setIncreaseBt(Button increaseBt) {
        this.increaseBt = increaseBt;
    }

    /**
     * Button Manager
     * This method call all runtime action for increase the value of the chosen die
     * @param event1 is the click of the mouse on the button
     */
    public void handleIncreaseButtonAction(ActionEvent event1) {
        //TODO send message to call increaseDie
    }

    /**
     * Button Manager
     * This method call all runtime action for decrease the value of the chosen die
     * @param event2 is the click of the mouse on the button
     */
    public void handleDecreaseButtonAction(ActionEvent event2) {
        //TODO send message to call decrease die
    }



    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  <tt>null</tt> if the location is not known.
     * @param resources The resources used to localize the root object, or <tt>null</tt> if
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}

package it.polimi.se2018.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.event.ActionEvent;


/**
 * ValueChoose Controller
 * This class manages the Value Chooser FXML
 * @author Davide Lorenzi
 */
public class ValueChooserController {


    private static ObservableList<Integer> values = FXCollections.observableArrayList(1,2,3,4,5,6);

    private Stage stage;

    @FXML
    protected ImageView backImage;

    @FXML
    protected Text text;

    @FXML
    private Text error;

    @FXML
    protected AnchorPane mainAnchor;

    @FXML
    protected Button confirm;

    @FXML
    protected static ChoiceBox<Integer> chosenValue;

    /**
     * Error Manager
     * This method returns an eventual error or the correct choice
     */
    @FXML
    private void displayValue() {
        if (chosenValue.getSelectionModel().getSelectedItem() == null) {
            error.setText("Please select a valid value for the die");
        }
        else {
            error.setText("You selected this value: " + chosenValue.getSelectionModel().getSelectedItem() + ". Please press the button to confirm");
        }
    }

    /**
     * Button Manager
     * This method call all model runtime actions after the confirmation of the value
     * @param event is the click of the mouse on the button
     */
    public void handleConfirmActionButton(ActionEvent event){
        int chosenNumber = chosenValue.getSelectionModel().getSelectedItem();
        //TODO send the chosen number to the server
    }



    public static void initialize() throws Exception {
        chosenValue.setItems(values);
        FXMLLoader valueChooserLoader = new FXMLLoader();
        valueChooserLoader.setLocation(GUIStarterApp.class.getResource("@resources/gui/layout/valueChooser.fxml"));

    }

}

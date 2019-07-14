package it.polimi.se2018.gui;

import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * LoginSuccessful Controller Class
 * This class is used to manage the FXML of the Login successful scene
 * @author Davide Lorenzi
 */
public class LoginSuccessfulController {

    @FXML
    protected AnchorPane mainAnchor;

    @FXML
    protected TextField showNick;

    @FXML
    protected ProgressBar timeBeforeStart;

    @FXML
    protected TextField specialCode;

    /**
     * Show Player's Nick
     * This method set on screen the NickName chosen by the player the previous scene
     * @param NickName player's NickName
     */
    public void setNick(String NickName) {
        showNick.setText(NickName);
    }

    /**
     * SpecialCode Setter
     * This method prints on screen the special code for reconnection.
     */
    public void setSpecialCode() {
        String specialCodeString = null;
        specialCode.setText(specialCodeString);
        //TODO catch the special code from controller by the generating function
    }

    /**
     * Progress bar updater
     * This method is used to update the timeout bar that shows the time left before the game beginning
     */
    // non so come funge il timeout se ritorna un int
    public void updateProgressBar() {

    }

}

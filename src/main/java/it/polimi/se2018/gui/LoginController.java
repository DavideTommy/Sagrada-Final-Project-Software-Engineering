package it.polimi.se2018.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Login Controller Class
 * This class is used to manage the FXML of the Login scene
 * @author Davide Lorenzi
 */
public class LoginController {

    private Stage stage;

    @FXML
    protected AnchorPane mainAnchor;

    @FXML
    protected TextField insertNick = null;

    @FXML
    protected TextField insertCode = null;

    @FXML
    protected RadioButton rmi = null;

    @FXML
    protected RadioButton socket = null;

    @FXML
    protected TextField ipInput = null;

    @FXML
    protected TextField portInput;

    @FXML
    protected Button connectionBt;

    @FXML
    protected Button reconnectBt;

    @FXML
    private ToggleGroup connectionType;

    /**
     * Stage Setter
     * This method initializes the stage of the scene
     * @param stage is the stage of the scene
     */
    public void setStage(Stage stage)
    {
        this.stage = stage;
    }


    /**
     * ConnectionButton Listener
     * This method catch the put by the player and tries to join a new match.
     */
   public void connectionButtonClicked() {
        if(insertNick.getText() != null) {
            String nick = insertNick.getText();
            RadioButton selectedMethod = (RadioButton)this.connectionType.getSelectedToggle();
            String chosenTypeConnection = selectedMethod.getText();
            if (chosenTypeConnection.toLowerCase().contains("rmi")) {
                rmiConnection(nick);
            }
            else socketConnection(nick);
        }
   }

    /**
     * ReconnectionButton Listener
     * This method catch the reconnection code put by the player and tries to reconnect to an existing match.
     */
   public void reconnectionButtonClicked() {
       if(insertCode.getText() != null) {
           String nick = insertNick.getText();
           String reconnectCode = insertCode.getText();
           RadioButton selectedMethod = (RadioButton)this.connectionType.getSelectedToggle();
           String chosenTypeConnection = selectedMethod.getText();
           if (chosenTypeConnection.toLowerCase().contains("rmi")) {
               rmiReconnection(nick, reconnectCode);
           }
           else {
               socketReconnection(nick,reconnectCode);
           }
       }
       else {
           System.out.println("Missing reconnection code, please paste it in the right field");
       }
   }

    /**
     * Connection Done Message
     * This method appears when connection has been established and gives to the player a reconnection code
     * @param reconnectionCode is the code used to reconnect to an existing match
     */
   public void connectionDone(String reconnectionCode) {
   }

    /**
     * RMI Connection Setter
     * This method calls the initialization of RMI connection
     * @param nickName is the nickname of the player that is trying to join a match
     */
   public void rmiConnection(String nickName) {
   }
    /**
     * Socket Connection Setter
     * This method calls the initialization of socket connection
     * @param nickName is the nickname of the player that is trying to join a match
     */
   public void socketConnection(String nickName) {

   }

    /**
     * RMI Reconnection Setter
     * This method calls the initialization of RMI connection to an existing match
     * @param nickName is the nickname of the player that is trying to join a match
     * @param reconnectionCode  is the code ised to reconnect to an existing match
     */
    private void rmiReconnection(String nickName, String reconnectionCode) {
    }

    /**
     * Socket Reconnection Setter
     * This method calls the initialization of socket connection to an existing match
     * @param nickName is the nickname of the player that is trying to join a match
     * @param reconnectionCode  is the code ised to reconnect to an existing match
     */
    private void socketReconnection(String nickName, String reconnectionCode) {

    }


    /**
     * NickName Getter
     * This method gets the nickname of the player
     * @return the required nickname
     */
   public String getNick() {
       return insertNick.getText();
   }


    /**
     * IP Address Getter
     * This method is used to get the server IP address to establish the connection
     * @return the Server Game IP Address
     */
   public String getIPAddress() {
       return ipInput.getText();
   }


    /**
     * ReconnectionCode Getter
     * This method is used to get the special reconnection code in case of a try to reconnect to an
     * existing match
     * @return the special code set by the player
     */
    public String getReconnectionCode() {
        return insertCode.getText();
    }

    /**
     * Port Getter
     * This port is used to get the connection port of the server in chase of multiple match instanced on it
     * @return the server port
     */
   public String getPort() {
       return portInput.getText();
   }

}

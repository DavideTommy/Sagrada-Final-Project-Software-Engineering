package it.polimi.se2018.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * GameBoard Controller Class
 * This class is used to manage the FXML of the GameBoard scene
 * @author Davide Lorenzi
 */
public class GameBoardController {

    @FXML
    private ImageView mainAnchor;

    @FXML
    private VBox pocVBox;

    @FXML
    private ImageView poc1;

    @FXML
    private ImageView poc2;

    @FXML
    private ImageView poc3;

    @FXML
    private VBox tcVBox;

    @FXML
    private ImageView tc1;

    @FXML
    private ImageView tc2;

    @FXML
    private ImageView tc3;

    @FXML
    private GridPane player1grid;

    @FXML
    private GridPane player2grid;

    @FXML
    private GridPane player3grid;

    @FXML
    private GridPane player4grid;

    @FXML
    private HBox buttonBox;

    @FXML
    private Button btnPlayToolCard;

    @FXML
    private Button btnPlaceDie;

    @FXML
    private Button passTurn;

    @FXML
    private Button undoBtn;

    @FXML
    private Text roundNumber;

    @FXML
    private Text currentPlayer;

    @FXML
    private HBox roundTrackDice;

    @FXML
    private GridPane roundTracker;

    @FXML
    private TextField tokenField;

    @FXML
    private HBox reserveDice;

    @FXML
    private GridPane roundTracker1;

    @FXML
    private GridPane mainGrid;

    @FXML
    private ImageView privateImage;

    @FXML
    private GridPane playersGrid;

}

package it.polimi.se2018.gui;


import it.polimi.se2018.model.score.ScoreTrack;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * FinalScore Controller Class
 * This class is used to manage the FXML of the Final score populating a table with specified all
 * types of score
 * @author Davide Lorenzi
 */
public class FinalScoreController {

    private String winnerNick;

    @FXML
    private AnchorPane mainAnchor;

    @FXML
    private AnchorPane scorePane;

    @FXML
    private TableView<?> scoreTable;

    @FXML
    private TableColumn<?, ?> player;

    @FXML
    private TableColumn<?, ?> publicScore;

    @FXML
    private TableColumn<?, ?> privateScore;

    @FXML
    private TableColumn<?, ?> fc;

    @FXML
    private TableColumn<?, ?> fm;

    @FXML
    private TableColumn<?, ?> ts;

    @FXML
    private Label winner;


    /**
     * GUIStarterApp Container Setter
     * This method sets the graphic container of the scene
     * @param mainAnchor is the anchorPane container
     */
    public void setMainAnchor(AnchorPane mainAnchor) {
        this.mainAnchor = mainAnchor;
    }

    /**
     * Winner Getter
     * This method is used to receive the nickname of the winner from the model
     * @return the nickname of the winner
     */
    public Label getWinner() {
        return winner;
    }

    /**
     * Scores Observable List
     * This method is used to create a list of scores that will fill the table
     * @return the list of scores
     */
    public ObservableList<ScoreTrack> getScores() {
        return scores;
    }
    /**
     * Winner Setter
     * This method is used to receive the nickname of the winner from the controller
     * @param winner is the winner of the match
     */
    public void setWinner(Label winner) {
        this.winner = winner;
        winner.setText("Nome Vincitore da passare come string");
    }

    /**
     * FavourMarkers Setter
     * This method sets the number of Favour Markers
     * @param fm number of Favour Markers
     */
    public void setFm(TableColumn<?, ?> fm) {
        this.fm = fm;
    }

    /**
     * PrivateScore Setter
     * This method sets the private score of a player
     * @param privateScore is the private score of a player
     */
    public void setPrivateScore(TableColumn<?, ?> privateScore) {
        this.privateScore = privateScore;
    }

    /**
     * Player Setter
     * This method sets a player that has played the match
     * @param player is the required player
     */
    public void setPlayer(TableColumn<?, ?> player) {
        this.player = player;
    }

    /**
     * PublicScore Setter
     * This method sets the public score of a player
     * @param publicScore is the public score of a player
     */
    public void setPublicScore(TableColumn<?, ?> publicScore) {
        this.publicScore = publicScore;
    }

    /**
     * Score Table Setter
     * This method sets the graphic element of the score table
     * @param scoreTable is the score table
     */
    public void setScoreTable(TableView<?> scoreTable) {
        this.scoreTable = scoreTable;
    }

    /**
     * TotalScore Setter
     * This method sets the total score of the players
     * @param ts is the total score
     */
    public void setTs(TableColumn<?, ?> ts) {
        this.ts = ts;
    }

    /**
     * Free Cells Setter
     * This method sets the number of free cells left by the players
     * @param fc is the number of free cells left on the grid
     */
    public void setFc(TableColumn fc) {
        this.fc = fc;
    }


    /**
     * Graphic Initializer
     * This method initializes all elements in the scene and the table
     * @param url is the path of the object that have to be initialized
     * @param resourceBundle are object to be instanced
     */
    public void initialize(URL url, ResourceBundle resourceBundle){
        TableColumn player = new TableColumn("Player");
        TableColumn publicScore = new TableColumn("PublicScore");
        TableColumn privateScore = new TableColumn("privateScore");
        TableColumn fc = new TableColumn("FreeCells");
        TableColumn fm = new TableColumn("Favour Markers");
        TableColumn ts = new TableColumn("Total Score");

        scoreTable.getColumns().addAll(player, publicScore, privateScore, fc,fm, ts);
    }


    /**
     * Observable List Score
     * This list contains all the scores for each player ant this will be passed to the table
     */
    public final ObservableList<ScoreTrack> scores = FXCollections.observableArrayList();
    //scores.add(new sc) qua gli devo passare l'array di scores
/*
    player.setCellValueFactory(new PropertyValueFactory<Player, String>("Player"));
    privateScore.setCellValueFactory(new PropertyValueFactory<Player, String>("Player"));
    publicScore.setCellValueFactory(new PropertyValueFactory<Player, String>("Player"));
    ts.setCellValueFactory(new PropertyValueFactory<Player, String>("Player"));
    fc.setCellValueFactory(new PropertyValueFactory<Player, String>("Player"));
    fm.setCellValueFactory(new PropertyValueFactory<Player, String>("Player"));

    scoreTable.setItems(scores);

*/
}

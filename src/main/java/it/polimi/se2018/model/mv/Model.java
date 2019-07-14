package it.polimi.se2018.model.mv;

import it.polimi.se2018.model.ChoseInputMove;
import it.polimi.se2018.model.Match;
import it.polimi.se2018.model.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Model Class
 * @author Federico Lichinchi
 * @author Davide Lorenzi (JDocs)
 */
public class Model extends Observable implements Cloneable {

    private Match match;
    private ArrayList<Player> players;
    private volatile ChoseInputMove move;

    /**
     * Model Constructor
     * This method sets up the match setting adding players that join the lobby and decides to start the match
     * if is reached the maximum number of players (4) or if timeout expires
     * @param numOfPlayers number of players that joined the lobby
     */
    public Model(int numOfPlayers) {
        players = new ArrayList<>(numOfPlayers);
    }

    /**
     * New Match Generator
     * This method create a new match
     * @throws CloneNotSupportedException if is not possible to create a new match
     */
    public void newMatch() throws CloneNotSupportedException {
        match = new Match(players);
    }

    /**
     * Clone Model Method
     * This method clones the Model
     * @return the clone of the model
     * @throws CloneNotSupportedException if is not possible to clone the model
     */
    public Model clone() throws CloneNotSupportedException {
        return (Model) super.clone();
    }

    /**
     * Match Getter
     * This method catch an empty Match (Without players or other feature set)
     * @return match object
     */
    public Match getMatch() {
        return match;
    }

    /**
     * Move Getter
     * This method is used to get a generic move or model status variation
     * @return the move
     */
    public synchronized ChoseInputMove getMove() {
        return move;
    }

    /**
     * Players Getter
     * This Method get the ArrayList of Players that joined the match
     * @return ArrayList of players
     */
    public List<Player> getPlayers() {
        return players;
    }

    /**
     * Move Setter
     * This method gets a move, change the status and prepare for the next turn
     * @param m is a inputMove passed like parameter
     */
    public synchronized void setMove(ChoseInputMove m) {
        try {
            move = m.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        System.out.print("message model: ");
        System.out.println(move.getMessage());
        setChanged();
        notifyObservers();
    }


}

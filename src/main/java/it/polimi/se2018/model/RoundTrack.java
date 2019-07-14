package it.polimi.se2018.model;

import it.polimi.se2018.model.cards.PrivateObjectiveCard;
import it.polimi.se2018.model.cards.PublicObjectiveCard;
import it.polimi.se2018.model.cards.ToolCard;

import java.util.ArrayList;

/**
 * RoundTrack Class
 * @author Federico Lichinchi
 */

public class RoundTrack {

    private int round;
    private int nextPlayerIndex;
    private int playerTurn;
    private ArrayList<Player> players;
    private ArrayList<Die> remainingDice;
    private ArrayList<ToolCard> toolCards;
    private ArrayList<PublicObjectiveCard> publicObjectiveCards;
    private boolean bound = true;

    /**
     * RoundTrack Empty Verifier
     * This method verifies if RoundTrack is without dice
     * @return true if the RoundTrack is empty
     */
    public boolean isRoundEmptyOfDice(){
        return remainingDice.isEmpty();
    }

    /**
     * Class Constructor
     * Initialize the match building all the needed cards.
     * The building consists in cloning and importing from JSON files 3 random PublicObjectiveCards, 3 random ToolCards
     * and one random PrivateObjectiveCard per player
     * @param players players array
     * @param toolCards usable ToolCards array
     * @param publicObjectiveCards usable PublicObjectiveCards
     * @param privateObjectiveCards is the PrivateObjectiveCards Array
     * @throws CloneNotSupportedException if is not possible to clone an item
     */

    public RoundTrack(ArrayList<Player> players, ArrayList<ToolCard> toolCards, ArrayList<PublicObjectiveCard> publicObjectiveCards,
                      ArrayList<PrivateObjectiveCard> privateObjectiveCards) throws CloneNotSupportedException {

        this.players = new ArrayList<>(players.size());
        for (int i = 0; i < players.size(); i++) {
            this.players.add(players.get(i).clone());
            this.players.get(i).setPrivateObjectiveCard(privateObjectiveCards.get(i));
        }

        this.toolCards = new ArrayList<>(3);
        this.publicObjectiveCards = new ArrayList<>(3);
        for (int j = 0; j < toolCards.size(); j++) {
            this.toolCards.add(toolCards.get(j).clone());
            this.publicObjectiveCards.add(publicObjectiveCards.get(j).clone());

        }
        remainingDice = new ArrayList<>(0);
        round = 1;
        nextPlayerIndex = 0;
        playerTurn = 1;
    }

    /**
     * Bound Checker
     * This method gets the RoundTrack dice bound limit
     * @return true if the bound is respected, false otherwise
     */
    public boolean getBound() {
        return bound;
    }

    /**
     * Bound Setter
     * This method sets a new RoundTrack Bound
     */
    public void setBound() {
        bound = !bound;
    }

    /**
     * Players Shifter Method
     * This method pops the last player to the first position of the array of players (new first player)
     * @throws CloneNotSupportedException if is not possible to clone a player
     */
    public void shiftPlayerArray() throws CloneNotSupportedException {
        if(!players.isEmpty()) {
            Player p1 = players.get(players.size() - 1).clone();
            for (int i = 1; i <= players.size(); i++) {
                if (i == players.size()) {
                    players.set(0, p1);
                } else {
                    players.set(i, players.get(i - 1).clone());
                }
            }
        }
    }

    /**
     * Next Turn Method
     * Manages the pointer to the current player at the end of the second turn and coordinates the order
     * in which players play
     */
    public void nextTurn() {
        if (playerTurn == 1) {
            nextPlayerIndex++;
            if (nextPlayerIndex == players.size()) {
                nextPlayerIndex--;
                playerTurn = 2;
            }
        }
        else {
            nextPlayerIndex--;
            if (nextPlayerIndex == -1) {
                try {
                    nextRound();
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * Next Round Method
     * Manages the succession of the rounds and the end of the match
     * @throws CloneNotSupportedException if is not possible to clone a player
     */

     public void nextRound() throws CloneNotSupportedException {
         round++;
         playerTurn = 1;
         shiftPlayerArray();
         nextPlayerIndex = 0;
         setBound();
    }

    /**
     * ToolCard Getter
     * This method returns ToolCards
     * @return the arrayList of the 3 random Chosen ToolCards
     */
    public ArrayList<ToolCard> getToolCards() {
        return toolCards;
    }

    /**
     * Player Getter
     * This method gets all players
     * @return the arrayList of the players that joined the match
     */
    public ArrayList<Player> getPlayers() {
        return players;
    }

    /**
     * PublicObjectiveCard Getter
     * This Method Gets the PublicObjectiveCards
     * @return the arrayList of the 3 random chosen PublicObjectiveCard
     */
    public ArrayList<PublicObjectiveCard> getPublicObjectiveCards() {
        return publicObjectiveCards;
    }

    /**
     *Remaining Die Getter
     * This Method Gets the remaining Die
     * @return the ArrayList of the remaining Die on the RoundTrack
     */
    public ArrayList<Die> getRemainingDice() {
        return remainingDice;
    }

    /**
     * Round Getter
     * This Method Gets the current round
     * @return the number of the current Round
     */
    public int getRound() {
        return round;
    }

    /**
     * Next Player Getter
     * This Method Gets the next player
     * @return the next player that has to play
     */
    public int getNextPlayerIndex() {
        return nextPlayerIndex;
    }

    /**
     * Player's Turn Getter
     * This Method Gets the player's turn
     * @return player's turn
     */
    public int getPlayerTurn() {
        return playerTurn;
    }

    /**
     * AddDice Method
     * Used to add a die on the RoundTrack
     * @param die Die to add into the remaining ones
     */
    public void addDie(Die die) {
        remainingDice.add(die);
    }

    /**RemoveDice Method
     * Used to remove a die from the RoundTrack
     * @param die generic die that player move from the RoundTrack using a ToolCard
     * @return the die removed, null if die not found
     */

    public Die removeDie(Die die) {
       for(Die dice: remainingDice) {
           if(dice.getDieColor().equals(die.getDieColor()) && dice.getDieNumber() == die.getDieNumber()) {
               remainingDice.remove(dice);
               return die;
           }
       }
       return null;
    }

    /**
     * CurrentTurnPlayer Getter
     * This method catch the player that's playing
     * @return the player that is playing
     */
    public Player getPlayerOfCurrentTurn() {
        return players.get(nextPlayerIndex);
    }
}

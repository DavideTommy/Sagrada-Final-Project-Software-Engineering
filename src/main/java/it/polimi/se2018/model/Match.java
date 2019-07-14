package it.polimi.se2018.model;

import it.polimi.se2018.model.cards.CardParser;
import it.polimi.se2018.model.cards.PrivateObjectiveCard;
import it.polimi.se2018.model.cards.PublicObjectiveCard;
import it.polimi.se2018.model.cards.ToolCard;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.System.out;

/**
 * Match Class
 * This class manages the match
 * @author Federico Lichinchi
 */
public class Match {

    private String idMatch;
    private RoundTrack roundTrack;
    private ArrayList<Player> players;
    private Reserve reserve;
    private Bag bag;

    /**
     * Match Class Builder
     * It assigns an identifier code to the match, it creates a RoundTrack with 3 ToolCards and 3 PublicObjectiveCards
     * casually chosen from the lists.
     * After that, it casually sets a PublicObjectiveCard for each player of the game. Before starting the first round,
     * a number of Die are extracted from the Bag and put into Reserve.
     * The Cards are imported from Json files situated in the resources folder.
     * #ExtractedDice = 2 * #Players + 1
     * @param players Array of players who join the match
     * @throws CloneNotSupportedException if is not possible to clone a player
     */
    public Match(List<Player> players) throws CloneNotSupportedException {
        idMatch = generateIdMatch();
        CardParser cardParser = null;
        try {
            cardParser = CardParser.newCardParser();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ArrayList<ToolCard> toolCards = new ArrayList<>(3);
        ArrayList<PublicObjectiveCard> publicObjectiveCards = new ArrayList<>(3);
        ArrayList<PrivateObjectiveCard> privateObjectiveCards = new ArrayList<>(players.size());
        this.players = new ArrayList<>(players.size());
        for (Player player1 : players) {
            try {
                this.players.add(player1.clone());
                privateObjectiveCards.add(cardParser.getRandomPrivateObjectiveCard());
            } catch (CloneNotSupportedException e) {
                out.println("Problem with Player clone");
            }
        }

        ToolCard t1 = cardParser.getRandomToolCard();
        ToolCard t2;
        ToolCard t3;
        do {
            t2 = cardParser.getRandomToolCard();
        } while(t1.getCardNumber() == t2.getCardNumber());
        do {
            t3 = cardParser.getRandomToolCard();
        } while(t1.getCardNumber() == t3.getCardNumber() || t2.getCardNumber() == t3.getCardNumber());
        toolCards.add(t1);
        toolCards.add(t2);
        toolCards.add(t3);

        PublicObjectiveCard p1 = cardParser.getRandomPublicObjectiveCard();
        PublicObjectiveCard p2;
        PublicObjectiveCard p3;
        do {
            p2 = cardParser.getRandomPublicObjectiveCard();
        } while(p1.getCardNumber() == p2.getCardNumber());
        do {
            p3 = cardParser.getRandomPublicObjectiveCard();
        } while(p1.getCardNumber() == p3.getCardNumber() || p2.getCardNumber() == p3.getCardNumber());
        publicObjectiveCards.add(p1);
        publicObjectiveCards.add(p2);
        publicObjectiveCards.add(p3);

        roundTrack = new RoundTrack((ArrayList<Player>) players, toolCards, publicObjectiveCards, privateObjectiveCards);
        bag = new Bag();
        ArrayList<Die> d = new ArrayList<>(2 * this.players.size() + 1);
        reserve = new Reserve(d);
    }

    /**
     * IDMatch Getter
     * This method returns the identifier code of the match
     * @return String
     */
    String getIdMatch() {
        return idMatch;
    }

    /**
     * RoundTrack Getter
     * This method returns the RoundTrack of the match
     * @return RoundTrack
     */
    public RoundTrack getRoundTrack() {
        return roundTrack;
    }

    /**
     * Players Getter
     * This method returns all the players who join the match
     * @return Array of players
     */
    public List<Player> getPlayers() {
        return players;
    }

    /**
     * Reserve Getter
     * This method returns the reserve of the match
     * @return Reserve
     */
    public Reserve getReserve() {
        return reserve;
    }

    /**
     * Bag Getter
     * This method returns the bag of the match
     * @return Bag
     */
    public Bag getBag() {
        return bag;
    }

    /**
     * MatchID Generator
     * This method generate a random ID for the current Match
     * @return MatchID
     */

    private String generateIdMatch() {
        int[] id = new int[5];
        StringBuilder buffer = new StringBuilder();
        Random r = new Random();
        for(int i = 0; i < 5; i++) {
            id[i] = r.nextInt(10);
            buffer.append(id[i]);
        }
        return buffer.toString();
    }
}

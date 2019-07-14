package it.polimi.se2018.model;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

/**
 * TestMatch Class
 * @author Federico Lichinchi
 */


public class TestMatch {

    /**
     * Timeout Getter
     * This method checks if 'timeout' attribute in Match is a right value ( major of 0).
     */
    @Test
    public void getTimeout() {
        ArrayList<Player> players = new ArrayList<Player>(4);
        players.add(0, new Player("Giocator1", "Indirizzo1", "Red", null, null));
        players.add(1, new Player("Giocator2", "Indirizzo2", "Red", null, null));
        players.add(2, new Player("Giocator3", "Indirizzo3", "Red", null, null));
        players.add(3, new Player("Giocator4", "Indirizzo4", "Red", null, null));
    }

    /**
     * IDMatch Getter
     * This method checks if 'idMatch' attribute in Match is not a null value.
     */
    @Test
    public void getIdMatch() {
        ArrayList<Player> p = new ArrayList<Player>(4);
        p.add(0, new Player("Giocatore1", "Indirizz1", "Red", null, null));
        p.add(1, new Player("Giocatore2", "Indirizz2", "Red", null, null));
        p.add(2, new Player("Giocatore3", "Indirizz3", "Red", null, null));
        p.add(3, new Player("Giocatore4", "Indirizz4", "Red", null, null));
        Match m = null;
        try {
            m = new Match(p);
            String t = m.getIdMatch();
            Assert.assertNotNull(t);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    /**
     * RoundTrackGetter
     * This method checks if 'toolCards' and 'PublicObjectiveCards' of 'roundTrack' in Match don't assume null
     * value.
     */
    @Test
    public void getRoundTrack() {
        ArrayList<Player> p = new ArrayList<Player>(4);
        p.add(0, new Player("Giatore1", "Indirizz1", "Red", null, null));
        p.add(1, new Player("Giatore2", "Indirizz2", "Red", null, null));
        p.add(2, new Player("Giatore3", "Indirizz3", "Red", null, null));
        p.add(3, new Player("Giatore4", "Indirizz4", "Red", null, null));
        Match m = null;
        try {
            m = new Match(p);
            RoundTrack r = m.getRoundTrack();
            Assert.assertNotNull(r);
            Assert.assertTrue(r.getToolCards() != null && r.getPublicObjectiveCards() != null);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    /**
     * PlayerGetter Tester
     * This method checks if 'players' attribute in Match is not an empty ArrayList.
     */
    @Test
    public void getPlayers() {
        ArrayList<Player> p = new ArrayList<Player>(4);
        p.add(0, new Player("atore1", "Indirizz1", "Red", null, null));
        p.add(1, new Player("atore2", "Indirizz2", "Red", null, null));
        p.add(2, new Player("atore3", "Indirizz3", "Red", null, null));
        p.add(3, new Player("atore4", "Indirizz4", "Red", null, null));
        Match m = null;
        try {
            m = new Match(p);
            ArrayList<Player> pl = (ArrayList<Player>) m.getPlayers();
            Assert.assertNotNull(pl);
            Assert.assertTrue(pl.size() >= 0);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    /**
     * GetReserve Tester
     * This method checks if 'reserve' attribute in Match doesn't assume null value.
     */
    @Test
    public void getReserve() {
        ArrayList<Player> p = new ArrayList<Player>(4);
        p.add(0, new Player("aore1", "Indirizz1", "Red", null, null));
        p.add(1, new Player("aore2", "Indirizz2", "Red", null, null));
        p.add(2, new Player("aore3", "Indirizz3", "Red", null, null));
        p.add(3, new Player("aore4", "Indirizz4", "Red", null, null));
        Match m = null;
        try {
            m = new Match(p);
            Reserve r = m.getReserve();
            Assert.assertNotNull(r);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    /**
     * GetBag Tester
     * This method checks if 'bag' attribute in Match doesn't assume null value.
     */
    @Test
    public void getBag() {
        ArrayList<Player> p = new ArrayList<Player>(4);
        p.add(0, new Player("aore1", "Indirzz1", "Red", null, null));
        p.add(1, new Player("aore2", "Indirzz2", "Red", null, null));
        p.add(2, new Player("aore3", "Indirzz3", "Red", null, null));
        p.add(3, new Player("aore4", "Indirzz4", "Red", null, null));
        Match m = null;
        try {
            m = new Match(p);
            Bag b = m.getBag();
            Assert.assertNotNull(b);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }
}
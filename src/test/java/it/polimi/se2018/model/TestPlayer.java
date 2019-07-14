package it.polimi.se2018.model;
import it.polimi.se2018.model.cards.PrivateObjectiveCard;
import org.junit.Assert;
import org.junit.Test;

/**
 * TestPlayer Class
 * @author Federico Lichinchi
 */
public class TestPlayer {

    /**
     * PlayerGetter Tester
     * This method checks if 'name' attribute of Player is a not null String value.
     * @throws CloneNotSupportedException From interface Cloneable
     */
    @Test
    public void getPlayerName() throws CloneNotSupportedException {
        Player p = new Player("Gio", "Ind", "Red", null, null);
        String a = p.getPlayerName();
        Assert.assertNotNull(a);
        Assert.assertTrue(a instanceof String);
    }

    /**
     * PlayerAddressGetter Tester
     * This method checks if 'address' attribute in Player is a not null String value.
     * @throws CloneNotSupportedException From interface Cloneable
     */
    @Test
    public void getPlayerAddress() throws CloneNotSupportedException {
        Player p = new Player("Gio", "Ind", "Red", null, null);
        String a = p.getPlayerAddress();
        Assert.assertNotNull(a);
        Assert.assertTrue(a instanceof String);
    }

    /**
     * ColorGetter  Tester
     * This method checks if 'color' attribute in Player is a not null String value.
     * @throws CloneNotSupportedException From interface Cloneable
     */
    @Test
    public void getColor() throws CloneNotSupportedException {
        Player p = new Player("Gio", "Ind", "Red", null, null);
        String a = p.getColor();
        Assert.assertNotNull(a);
        Assert.assertTrue(a instanceof String);
    }

    /**
     * GridGetter Tester
     * This method checks if 'grid' attribute in Player is a not null 'Grid' object.
     * @throws CloneNotSupportedException From interface Cloneable
     */
    @Test
    public void getGrid() throws CloneNotSupportedException {
        Player p = new Player("Gio", "Ind", "Red", null, null);
        Grid g = p.getGrid();
        Assert.assertTrue(g instanceof Grid || g == null);
    }

    /**
     * PrivateObjectiveCardGetter Tester
     * This method checks if 'privateObjectiveCard' attribute in Player is a not null 'PrivateObjectiveCard' object.
     * @throws CloneNotSupportedException From interface Cloneable
     */
    @Test
    public void getPrivateObjectiveCard() throws CloneNotSupportedException {
        Player p = new Player("Gio", "Ind", "Red", null, null);
        PrivateObjectiveCard g = p.getPrivateObjectiveCard();
        Assert.assertTrue(g instanceof PrivateObjectiveCard || g == null);
    }

    /**
     * MarkerPosGetter Tester
     * This method checks if 'markerPos' attribute in Player is a right number value ( major or equal to 0).
     * @throws CloneNotSupportedException From interface Cloneable
     */
    @Test
    public void getMarkerPos() throws CloneNotSupportedException {
        Player p = new Player("Gio", "Ind", "Red", null, null);
        int g = p.getMarkerPos();
        Assert.assertTrue(g >= 0);
    }

    /**
     * ScoreGetter Tester
     * This method checks if 'score' attribute in Player is a right number value ( major or equal to 0).
     * @throws CloneNotSupportedException From interface Cloneable
     */
    @Test
    public void getScore() throws CloneNotSupportedException {
        Player p = new Player("Gio", "Ind", "Red", null, null);
        int g = p.getScore();
        Assert.assertTrue(g >= 0);
    }

    /**
     * FavCounterGetter Tester
     * This method checks if 'numFavCounter' attribute in Player is a right number value ( major or equal to 0).
     * @throws CloneNotSupportedException From interface Cloneable
     */
    @Test
    public void getNumFavCounter() throws CloneNotSupportedException {
        Player p = new Player("Gio", "Ind", "Red", null, null);
        int g = p.getNumFavCounter();
        Assert.assertTrue(g >= 0);
    }

    /**
     * ScoreAdder Tester
     * This method checks if 'score' attribute value in Player is added by the parameter number of the method 'addScore'.
     * @throws CloneNotSupportedException From interface Cloneable
     */
    @Test
    public void addScore() throws CloneNotSupportedException {
        int a = 10;
        Player p = new Player("Gio", "Ind", "Red", null, null);
        int b = p.getScore();
        Assert.assertTrue(a % 1 == 0);
        p.addScore(a);
        Assert.assertTrue(p.getScore() == b + a && p.getScore() >= 0);
    }

    /**
     * ShiftMarker Tester
     * This method checks if 'markerPos' attribute value in Player is added by the parameter number of the method
     * 'shiftMarkerPos'.
     * @throws CloneNotSupportedException From interface Cloneable
     */
    @Test
    public void shiftMarkerPos() throws CloneNotSupportedException {
        Player p = new Player("Giao", "Ind", "Red", null, null);
        int a = 10;
        int b = p.getMarkerPos();
        Assert.assertTrue(a % 1 == 0);
        p.shiftMarkerPos(a);
        Assert.assertTrue(p.getMarkerPos() == b + a && p.getMarkerPos() >= 0);

    }

    /**
     * AddFavourCounter Tester
     * This method checks if 'numFavCounter' attribute value in Player is added by the parameter number of the method
     * 'decrementNumFavCounter'.
     * @throws CloneNotSupportedException From interface Cloneable
     */
    @Test
    public void addNumFavCounter() throws CloneNotSupportedException {
        Player p = new Player("Gio", "Ind", "Red", null, null);
        int a = 10;
        int b = p.getNumFavCounter();
        Assert.assertTrue(b % 1 == 0);
        p.decrementNumFavCounter(a);
        Assert.assertTrue(p.getNumFavCounter() == 0);
    }

    /**
     * NameSetter Tester
     * This method checks if the 'name' new value of Player is a not null String value.
     * @throws CloneNotSupportedException From interface Cloneable
     */
    @Test
    public void setPlayerName() throws CloneNotSupportedException {
        Player p = new Player("Gio", "Ind", "Red", null, null);
        String name= "Pinco pallino";
        Assert.assertNotNull(name);
        Assert.assertTrue(name instanceof String);
        p.setPlayerName(name);
        Assert.assertTrue(p.getPlayerName().equals(name));
    }

    /**
     * ColorSetter Tester
     * This method checks if the 'color' new value of Player is a not null String value.
     * @throws CloneNotSupportedException From interface Cloneable
     */
    @Test
    public void setColor() throws CloneNotSupportedException {
        Player player = new Player("Gio", "Iaand", "Red", null, null);
        String color = "Yellow";
        Assert.assertNotNull(color);
        Assert.assertTrue(color instanceof String);
        player.setColor(color);
        Assert.assertEquals(player.getColor(), color);
    }

    /**
     * PrivateObjectiveCardSetter Tester
     * This method checks if the 'privateObjectiveCard' new object of Player is a not null 'PrivateObjectiveCard' object.
     * @throws CloneNotSupportedException From interface Cloneable
     */
    @Test
    public void setPrivateObjectiveCard() throws CloneNotSupportedException {
        Player p = new Player("Gio", "Iaand", "Red", null, null);
        PrivateObjectiveCard pcard = new PrivateObjectiveCard("POC1","bla", 1,
                "Yellow");
        Assert.assertNotNull(pcard);
        Assert.assertTrue(pcard.getColor().equals("Red") || pcard.getColor().equals("Purple") ||
                pcard.getColor().equals("Yellow") || pcard.getColor().equals("Blue") ||
                pcard.getColor().equals("Green"));
        p.setPrivateObjectiveCard(pcard);
        Assert.assertTrue(p.getPrivateObjectiveCard().getColor().equals(pcard.getColor()) &&
        p.getPrivateObjectiveCard().getCardName().equals(pcard.getCardName()) &&
        p.getPrivateObjectiveCard().getCardDescription().equals(pcard.getCardDescription()) &&
        p.getPrivateObjectiveCard().getCardNumber() == pcard.getCardNumber() &&
        p.getPrivateObjectiveCard().getCardType().equals(pcard.getCardType()) &&
        p.getPrivateObjectiveCard().getPrice() == pcard.getPrice() &&
        p.getPrivateObjectiveCard().getNumMarkers() == pcard.getNumMarkers() &&
        p.getPrivateObjectiveCard().getPublicVictoryPoint() == pcard.getPublicVictoryPoint());
    }

    /**
     * ClonePlayer Tester
     * This method checks if the cloned Player has the same attributes of the Player object that called the method
     * 'clone'.
     * @throws CloneNotSupportedException From interface Cloneable
     */
    @Test
    public void clonePlayer() throws CloneNotSupportedException {
        Player p = new Player("Giaao", "Iaand", "Red", null, null);
        Assert.assertNotNull(p);
        Assert.assertNotNull(p.getPlayerName());
        Assert.assertNotNull(p.getPlayerAddress());
        Assert.assertNotNull(p.getColor());
        Player k = p.clone();
        Assert.assertNotNull(k);
        Assert.assertTrue(k.getPlayerAddress().equals(p.getPlayerAddress()) &&
                k.getNumFavCounter() == p.getNumFavCounter() && k.getMarkerPos() == p.getMarkerPos() &&
        k.getPlayerName().equals(p.getPlayerName()) && k.getColor().equals(p.getColor()) &&
                k.getScore() == p.getScore() && (k.getGrid() == null || k.getGrid().equals(p.getGrid()) &&
                (k.getPrivateObjectiveCard().equals(p.getPrivateObjectiveCard()) || k.getPrivateObjectiveCard() == null)));
    }
}
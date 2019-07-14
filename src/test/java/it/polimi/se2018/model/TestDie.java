package it.polimi.se2018.model;
import org.junit.Assert;
import org.junit.Test;

/**
 * Test class of Die
 * @author Federico Lichinchi
 */

public class TestDie {

    /**
     * DieNUmberGetter Tester
     * This method checks if a die has a right number value (between 1 and 6).
     */
    @Test
    public void getDieNumber() {
        Die d = new Die("Red", 5);
        int c = d.getDieNumber();
        Assert.assertNotNull(c);
        Assert.assertTrue(c >= 1 && c <= 6);
    }

    /**
     * DieColorGetter Tester
     * This method checks if a die has a right color value ('Red', 'Yellow', 'Blue', 'Green' or 'Purple').
     */
    @Test
    public void getDieColor() {
        Die d = new Die("Red", 5);
        String c = d.getDieColor();
        Assert.assertNotNull(c);
        Assert.assertTrue(c.equals("Red") || c.equals("Blue") || c.equals("Green") || c.equals("Yellow") ||
                c.equals("Purple"));
    }

    /**
     * DieColorSetter Tester
     * This method checks if the new color to set to the die is a right color value ('Red', 'Yellow', 'Blue', 'Green'
     * or 'Purple').
     */
    @Test
    public void setDieColor() {
        Die d = new Die("Red", 5);
        String a = "Yellow";
        Assert.assertTrue(a instanceof String && (a.equals("Red") || a.equals("Blue") || a.equals("Green") || a.equals("Yellow") ||
                a.equals("Purple")));
        d.setDieColor(a);
        Assert.assertEquals(d.getDieColor(), a);
    }

    /**
     * DieNumberSetter Tester
     * This method checks if the new number to set to the die is a right number value (between 1 and 6).
     */
    @Test
    public void setDieNumber() {
        Die d = new Die("Red", 6);
        int a = 4;
        Assert.assertTrue(a >= 1 && a <= 6);
        d.setDieNumber(a);
        Assert.assertTrue(d.getDieNumber() == a);
    }

    /**
     * DieClone Tester
     * This method checks if the cloned die has same values of the die which has called this method.
     * @throws CloneNotSupportedException From interface Cloneable
     */
    @Test
    public void cloneDie() throws CloneNotSupportedException {
        Die dice = new Die("Red", 5);
        Assert.assertTrue(dice instanceof Die && ((dice.getDieNumber() >= 1 && dice.getDieNumber() <= 6) &&
                (dice.getDieColor().equals("Red") || dice.getDieColor().equals("Blue") || dice.getDieColor().equals("Green") ||
                        dice.getDieColor().equals("Yellow") || dice.getDieColor().equals("Purple"))));
        Die d = dice.clone();
        Assert.assertTrue( (dice == null && d == null) ||
                (d.getDieColor().equals(dice.getDieColor()) && d.getDieNumber() == dice.getDieNumber()));
    }
}
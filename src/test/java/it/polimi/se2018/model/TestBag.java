package it.polimi.se2018.model;
import org.junit.Assert;
import org.junit.Test;

/**
 * Test class of Bag
 * This class tests the efficiency
 * @author Federico Lichinchi
 */
public class TestBag {

    /**
     * DieExtractor Tester
     * This method checks if every extracted die has a right color ('Red', 'Yellow', 'Blue', 'Green' or 'Purple') and
     * a right number (between 1 and 6). After that, it controls if attribute 'dieNum' of Bag assumes the right value
     * after a number of extractions.
     */
    @Test
    public void extractDie() {
        Bag b = new Bag();
        Die d1;
        for(int i = 0; i < 5; i++) {
            d1 = b.extractDie();
            Assert.assertNotNull(d1);
            Assert.assertTrue(d1.getDieColor().equals("Red") || d1.getDieColor().equals("Blue") ||
                    d1.getDieColor().equals("Green") || d1.getDieColor().equals("Yellow") || d1.getDieColor().equals("Purple"));
            Assert.assertTrue(d1.getDieNumber() > 0 && d1.getDieNumber() < 7);
        }
        Assert.assertEquals(85,b.getDieNum());
    }

    /**
     * DieGetter Tester
     * This method checks if the die returned from the method 'getDie' of Bag has the a right color value ('Red',
     * 'Yellow', 'Blue', 'Green' or 'Purple') and a right number value (between 1 and 6).
     */
    @Test
    public void getDie() {
        Bag b = new Bag();
        int line = 0;
        int column = 0;
        Die d = b.getDie(line, column);
        Assert.assertTrue(d == null || d.getDieColor().equals("Red") || d.getDieColor().equals("Blue") ||
                d.getDieColor().equals("Green") || d.getDieColor().equals("Yellow") ||
                d.getDieColor().equals("Purple"));
        Assert.assertTrue(d == null || (d.getDieNumber() > 0 && d.getDieNumber() < 7));
    }

    /**
     * NumberDieGetter Tester
     * This method checks if 'dieNum' attribute of Bag returned from the method 'getDieNum' has a right value (between
     * 0 and 90).
     */
    @Test
    public void getDieNum() {
        Bag b = new Bag();
        int d = b.getDieNum();
        Assert.assertTrue(d >= 0 && d <= 90);
    }

}
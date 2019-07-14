package it.polimi.se2018.model;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Test class of Reserve
 * @author Federico Lichinchi
 */
public class TestReserve {

    /**
     * LengthGetter Tester
     * This method checks if 'len' attribute of Reserve is a not null value and if it represents the number of Die in
     * the Reserve.
     * @throws CloneNotSupportedException if is not possible to clone a die
     */
    @Test
    public void getLen() throws CloneNotSupportedException {
        ArrayList<Die> d = new ArrayList<Die>(5);
        d.add(0, new Die("Red", 4));
        d.add(1, new Die("Red", 2));
        d.add(2, new Die("Yellow", 1));
        d.add(3, new Die("Blue", 5));
        d.add(4, new Die("Violet", 4));
        Reserve reserve = null;
        reserve = new Reserve(d);
        int l = reserve.getLen();
        Assert.assertNotNull(l);
        Assert.assertTrue(l >= 0 && l == reserve.getDie().size());
    }

    /**
     * DieGetter Tester
     * This method checks if the returned die object of the method 'getDie' in Reserve is an non-empty and not null
     * ArrayList object.
     * @throws CloneNotSupportedException if is not possible to clone a die
     */
    @Test
    public void getDie() throws CloneNotSupportedException {
        ArrayList<Die> d = new ArrayList<Die>(5);
        d.add(0, new Die("Red", 4));
        d.add(1, new Die("Red", 2));
        d.add(2, new Die("Yellow", 1));
        d.add(3, new Die("Blue", 5));
        d.add(4, new Die("Violet", 4));
        Reserve reserve = new Reserve(d);
        ArrayList<Die> a = (ArrayList<Die>) reserve.getDie();
        Assert.assertNotNull(a);
        Assert.assertTrue(a instanceof ArrayList && a.size() >= 0);
    }

    /**
     * DieRemover Tester
     * This method checks if the object passed to method 'removeDie' is a Die object with right number value (between 1
     * and 6) and a right color value ('Red', 'Yellow', 'Green', 'Blue' or 'Purple'). After the call of method 'removeDie',
     * it checks if Reserve doesn't contain anymore the die passed as parameter.
     * @throws CloneNotSupportedException From interface Cloneable
     */
    @Test
    public void removeDie() throws CloneNotSupportedException {
        ArrayList<Die> d = new ArrayList<Die>(5);
        d.add(0, new Die("Red", 4));
        d.add(1, new Die("Red", 2));
        d.add(2, new Die("Yellow", 1));
        d.add(3, new Die("Blue", 5));
        d.add(4, new Die("Violet", 4));
        Reserve reserve = new Reserve(d);
        Die a = d.get(3).clone();
        Assert.assertTrue(a instanceof Die && (a.getDieColor().equals("Blue") ||
                a.getDieColor().equals("Green") || a.getDieColor().equals("Yellow") ||
                a.getDieColor().equals("Violet")) && (a.getDieNumber() >= 1 && a.getDieNumber() <= 6));
        reserve.removeDie(a);
        Assert.assertFalse(reserve.getDie().contains(a));
    }

    /**
     * DieAdder Tester
     * This method checks if the object passed to function 'addDie' in Reserve is a 'Die' object with right number value
     * (between 1 and 6) and a right color value ('Red', 'Yellow', 'Green', 'Blue' or 'Purple'). After the call of the
     * function 'addDie', it checks that Reserve contains the die passed as parameter
     * @throws CloneNotSupportedException if is not possible to clone a die
     */
    @Test
    public void addDie() throws CloneNotSupportedException {
        ArrayList<Die> d = new ArrayList<Die>(5);
        d.add(0, new Die("Red", 4));
        d.add(1, new Die("Red", 2));
        d.add(2, new Die("Yellow", 1));
        d.add(3, new Die("Blue", 5));
        d.add(4, new Die("Violet", 4));
        Reserve reserve = new Reserve(d);
        Die a = new Die("Green", 1);
        Assert.assertTrue(a instanceof Die && (a.getDieColor().equals("Blue") ||
                a.getDieColor().equals("Green") || a.getDieColor().equals("Yellow") ||
                a.getDieColor().equals("Violet")) && (a.getDieNumber() >= 1 && a.getDieNumber() <= 6));
        reserve.addDie(a);
        Assert.assertTrue(reserve.getDie().contains(a));
    }

    /**
     * Reserve Sweeper Tester
     * This method verifies if the Reserve is correctly cleaned by any dice
     * @throws CloneNotSupportedException if is not possible to clone a die
     */
    @Test
    public void toEmptyDice() throws CloneNotSupportedException {
        ArrayList<Die> d = new ArrayList<Die>(5);
        d.add(0, new Die("Red", 4));
        d.add(1, new Die("Red", 2));
        d.add(2, new Die("Yellow", 1));
        d.add(3, new Die("Blue", 5));
        d.add(4, new Die("Violet", 4));
        Reserve reserve = new Reserve(d);
        reserve.toEmptyDice();
        Assert.assertEquals(0,reserve.getLen());
        Assert.assertEquals(0,reserve.getDie().size());
    }
}
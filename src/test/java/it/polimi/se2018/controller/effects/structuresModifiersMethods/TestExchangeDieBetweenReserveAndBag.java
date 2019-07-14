package it.polimi.se2018.controller.effects.structuresModifiersMethods;

import it.polimi.se2018.model.Die;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 *  ExchangeDieBetweenReserveAndBag class tester
 * @author Federico Lichinchi
 */
public class TestExchangeDieBetweenReserveAndBag {

    private ExchangeDieBetweenReserveAndBag exchangeDieBetweenReserveAndBag;
    private Die firstDie;
    private Die secondDie;

    /**
     * SetUp
     * This method prepare the test ambient
     */
    @Before
    public void setUp() {
        exchangeDieBetweenReserveAndBag = new ExchangeDieBetweenReserveAndBag();
        firstDie = new Die("Green", 3);
        secondDie = new Die("Red", 1);
    }

    /**
     * Tear Down
     * This method prepare the test ambient for a new test
     */
    @After
    public void tearDown() {
        exchangeDieBetweenReserveAndBag = null;
        firstDie = null;
        secondDie = null;
    }

    /**
     * This method checks if 'exchange' method exchange number and color between the two dice that have been
     * passed as parameters.
     */
    @Test
    public void exchange() {
        String firstDieColor = firstDie.getDieColor();
        String secondDieColor = secondDie.getDieColor();
        int firstDieNumber = firstDie.getDieNumber();
        int secondDieNumber = secondDie.getDieNumber();
        exchangeDieBetweenReserveAndBag.exchange(firstDie,secondDie);
        Assert.assertEquals(firstDieColor, secondDie.getDieColor());
        Assert.assertEquals(secondDieColor, firstDie.getDieColor());
        Assert.assertTrue( firstDieNumber == secondDie.getDieNumber());
        Assert.assertTrue( secondDieNumber == firstDie.getDieNumber());
    }
}
package it.polimi.se2018.model;

import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.*;

/**
 * TestBoxOfSchemeCards Class
 * This class tests the efficiency of BoxOfSchemeCard
 * @author Luca Massini
 * @author Davide Lorenzi (JDocs)
 */
public class TestBoxOfSchemeCards {
    private BoxOfSchemeCards Box;
    private String[] names = {"Kaleidoscopic Dream","Virtus","Aurorae Magnificus","Via Lux","Sun Catcher","Bellesguard","Firmitas","Symphony of Light","Aurora Sagradis","Industria","Shadow Thief","Batllo","Gravitas","Fractal Drops","Lux Astram","Chromatic Splendor","Firelight","Luz Celestial","Water of Life","Ripples of Light","Lux Mundi","Comitas","Sun's Glory","Fulgor del Cielo"};


    /**
     * Test BoxOfSchemeCard Initialization
     * This method prepare the test ambient
     */
    @Before
    public void setUp(){
        Box = BoxOfSchemeCards.newBox();
    }

    /**
     * Array of SchemeCard Tester
     * This method checks tbe correct function of the array of SchemeCard
     * @return a SchemeCard array that contains all the possible cards in the game requesting them to the
     * class BoxOfSchemeCards
     */
    private  SchemeCard[] arrayOfSchemeCards(){
        SchemeCard[] array = new SchemeCard[24];
           int i;
           for(i = 0; i < 24; i++) {
               array[i] = Box.request(names[i]);
           }
           return array;
    }

    /**
     * Container Tester
     * This method verifies that a string contains a name of the scheme card
     * @param name is the name of a SchemeCard
     * @param names are all the possible names of all the possible SchemeCard name of the game
     * @return true if the SchemeCard whose name (param name) is contented in names (param names)
     */
    private boolean contains(String name,String[] names){
        int i;
        for(i = 0; i<24 ;i++){
            if(names[i].equals(name))
                return true;
        }
        return false;
    }

    /**
     * AllFiller Tester
     * This method ask all the possible SchemeCards to the BoxOfsSchemeCards class. If only one of them isn't
     * contented in the set of all possible schemeCards the test fail, otherwise the test is successful.
     */
    @Test
    public void fillAll(){
        boolean testPassed=true;
        int i;
        SchemeCard[] array = arrayOfSchemeCards();
        for(i=0;i<24;i++){
            if(!contains(array[i].getSchemeCardName(),this.names))
                testPassed=false;
        }
        assertTrue(testPassed);
    }

    /**
     * Singleton Tester
     * This test checks if the singleton pattern of the object BoxOfSchemeCards works properly
     */
    @Test
    public void testSingleton(){
        BoxOfSchemeCards secondObject;
        secondObject = BoxOfSchemeCards.newBox();
        assertSame(Box, secondObject);
    }


    /**
     * SecondInstance Tester
     * this test checks if the reference of a SchemeCard required to BoxOfSchemeCards and
     * another reference of another SchemeCards equal to the other one are exactly equal one to another.
     */
    @Test
    public void testSecondIst() {
        boolean testPassed=true;
        SchemeCard[] firstArray = arrayOfSchemeCards();
        SchemeCard[] secondArray= arrayOfSchemeCards();
        for(int i=0;i<24;i++){
            if(firstArray[i]!=secondArray[i])
                testPassed=false;
        }
        assertTrue(testPassed);
    }

    /**
     * DifferentSchemeCard Tester
     * This test verifies that the SchemeCard array is made by different SchemeCards
     * @param array SchemeCard Array
     * @param length length of the array
     * @return true if is made by different SchemeCard, false otherwise
     */
    private boolean isMadeByDifferentSchemeCards(SchemeCard array[],int length){
        int i,j;
        for(i = 0;i < length; i++){
            for(j  = 0;j < length; j++){
                if(array[i] == null)
                    j = length;
                if(array[j] != null) {
                    if (array[i].equals(array[j]) && i != j)
                        return false;
                }
            }
        }
        return true;
    }

    /**
     * RandomSchemeCard Tester
     * This method tests a new random SchemeCard
     */
    @Test
    public void testRandomSchemeCards(){
          SchemeCard array[] = new SchemeCard[24];
          int i;
          array[0] = Box.request("Virtus");
          for(i = 1;i < 24;i++){
              array[i] = Box.randomSchemeCard();
          }
          assertTrue(isMadeByDifferentSchemeCards(array,24));
    }

}




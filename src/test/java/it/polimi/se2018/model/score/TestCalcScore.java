package it.polimi.se2018.model.score;

import it.polimi.se2018.model.BoxOfSchemeCards;
import it.polimi.se2018.model.Die;
import it.polimi.se2018.model.Grid;
import it.polimi.se2018.model.Player;
import it.polimi.se2018.model.cards.PrivateObjectiveCard;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * TestCalcScore Class
 * Player score computer tester class
 * @author Davide Lorenzi
 */

public class TestCalcScore implements Cloneable {

    protected Grid grid;
    private ColoredDiagonals coloredDiagonals;
    private DeepShades deepShades;
    private DifferentColorColumn differentColorColumn;
    private DifferentColorRow differentColorRow;
    private DifferentColors differentColors;
    private DifferentShadeColumn differentShadeColumn;
    private DifferentShades differentShades;
    private DifferentShadesRow differentShadesRow;
    private LightShades lightShades;
    private MediumShades mediumShades;
    private Player player;
    private PrivateObjectiveCard privateObjectiveCard;
    private CalcScore calcScore;
    private int totalPublicScore;
    private int coloredDiagonalsScore;
    private int deepShadesScore;
    private int differentColorColumnScore;
    private int differentColorRowScore;
    private int differentColorsScore;
    private int differentShadesColumnScore;
    private int differentShadesRowScore;
    private int differentShadesScore;
    private int lightShadesScore;
    private int mediumShadesScore;
    private int freeCells;
    private int privateScore;
    private int score;
    private BoxOfSchemeCards box;

    /**
     * SetUp Default Method
     * This class prepare an instance of any PublicObjectiveCard, create a Player with an associate Grid and
     * PrivateObjectiveCard, so proceed to run the computation of the score
     * @throws Exception if there are problems in creating the Grid or the Player
     */
    @Before
    public void setUp() throws Exception {
        this.coloredDiagonals = ColoredDiagonals.newColoredDiagonals();
        this.deepShades = DeepShades.newDeepShades();
        this.differentColorColumn = DifferentColorColumn.newDifferentColorColumn();
        this.differentColorRow = DifferentColorRow.newDifferentColorRow();
        this.differentColors = DifferentColors.newDifferentColors();
        this.differentShadeColumn = DifferentShadeColumn.newDifferentShadeColumn();
        this.differentShades = DifferentShades.newDifferentShades();
        this.differentShadesRow = DifferentShadesRow.newDifferentShadesRow();
        this.lightShades = LightShades.newLightShades();
        this.mediumShades = MediumShades.newMediumShades();
        box = BoxOfSchemeCards.newBox();
        this.grid = new Grid(box.request("Virtus"));
        this.privateObjectiveCard = new PrivateObjectiveCard("Red Shades", "Sum of the values on all the red Die",1,"Red");
        this.player = new Player("Giggino","192.168.1.54","Blue", grid, privateObjectiveCard);
        player.setPrivateObjectiveCard(privateObjectiveCard);
        this.calcScore = new CalcScore();

        grid.addDie(new Die("Green", 5),0,0);
        grid.addDie(new Die("Blue", 3),1,1);
        grid.addDie(new Die("Red", 4),1,0);
        grid.addDie(new Die("Yellow", 2),0,1);
        grid.addDie(new Die("Purple", 1),1,2);

        grid.addDie(new Die("Red", 1),2,3);
        grid.addDie(new Die("Blue", 2),2,2);
        grid.addDie(new Die("Red", 2),0,2);
        grid.addDie(new Die("Green", 3),3,1);
        grid.addDie(new Die("Purple", 6),2,0);

        grid.addDie(new Die("Yellow", 4),2,1);
        grid.addDie(new Die("Yellow", 3),3,0);
        grid.addDie(new Die("Blue", 6),3,2);
        grid.addDie(new Die("Blue", 6),3,4);
        grid.addDie(new Die("Green", 5),2,4);

        freeCells = calcScore.freeCellsCounter(player);
        privateScore = calcScore.privateObjectiveCounter(player);

        deepShadesScore = deepShades.publicScore(player.getGrid());
        differentColorColumnScore = differentColorColumn.publicScore(player.getGrid());
        differentColorRowScore = differentColorRow.publicScore(player.getGrid());
        differentColorsScore = differentColors.publicScore(player.getGrid());

        differentShadesColumnScore = differentShadeColumn.publicScore(player.getGrid());
        differentShadesScore = differentShades.publicScore(player.getGrid());
        differentShadesRowScore = differentShadesRow.publicScore(player.getGrid());
        lightShadesScore = lightShades.publicScore(player.getGrid());
        mediumShadesScore = mediumShades.publicScore(player.getGrid());
        coloredDiagonalsScore = coloredDiagonals.publicScore(player.getGrid());

        totalPublicScore = coloredDiagonalsScore + deepShadesScore
                + differentColorColumnScore + differentColorRowScore
                + differentColorsScore + differentShadesColumnScore
                + differentShadesScore + lightShadesScore
                + mediumShadesScore + differentShadesRowScore;

        score = totalPublicScore + privateScore - freeCells + player.getNumFavCounter();
    }

    /**
     * TearDown Method
     * This method sets up the class to a clear and new test ambient
     * @throws Exception if is not possible to clear the test ambient
     */
    @After
    public void tearDown() throws Exception {
        totalPublicScore = 0;
        coloredDiagonalsScore = 0;
        deepShadesScore = 0;
        differentColorColumnScore = 0;
        differentColorRowScore = 0;
        differentColorsScore = 0;
        differentShadesColumnScore = 0;
        differentShadesRowScore = 0;
        differentShadesScore = 0;
        lightShadesScore = 0;
        mediumShadesScore = 0;
        freeCells = 0;
        privateScore = 0;
        score = 0;
    }

    /**
     * CalcScore Tester
     * This method verifies the correct total score of the player. Now is cumulative of all the PublicObjectivePlayers
     * Scores. As publicObjectiveCounter will be implemented it will check the real score with only 3
     * PublicObjectiveCards
     */
    @Test
    public void testCalcScore() {
        Assert.assertEquals(61, score);
        Assert.assertNotEquals(55, score);
    }

    /**
     * PrivateScore Tester
     * This test verifies that the computed PrivateScore is correct
     */
    @Test
    public void testPrivateObjectiveCounter() {
        Assert.assertEquals(7, privateScore);
        Assert.assertNotEquals(5, privateScore);
    }

    /**
     * FreeCells Tester
     * This test ensure that the method returns the correct number of free cells in the Player's grid
     */
    @Test
    public void testFreeCellsCounter() {
        Assert.assertEquals(5, freeCells);
        Assert.assertNotEquals(6, freeCells);
    }

    /**
     * PublicObjective Counter Tester
     * This test verifies that all the classes used to compute the public score works properly.
     */
    @Test
    public void testPublicObjectiveCounter() {
        Assert.assertEquals(4, coloredDiagonalsScore);
        Assert.assertEquals(4, deepShadesScore);
        Assert.assertEquals(5, differentColorColumnScore);
        Assert.assertEquals(6, differentColorRowScore);
        Assert.assertEquals(8, differentColorsScore);
        Assert.assertEquals(4, differentShadesColumnScore);
        Assert.assertEquals(5, differentShadesRowScore);
        Assert.assertEquals(10, differentShadesScore);
        Assert.assertEquals(4, lightShadesScore);
        Assert.assertEquals(4, mediumShadesScore);

        Assert.assertNotEquals(5, coloredDiagonalsScore);
        Assert.assertNotEquals(3, deepShadesScore);
        Assert.assertNotEquals(6, differentColorColumnScore);
        Assert.assertNotEquals(5, differentColorRowScore);
        Assert.assertNotEquals(4, differentColorsScore);
        Assert.assertNotEquals(3, differentShadesColumnScore);
        Assert.assertNotEquals(2, differentShadesRowScore);
        Assert.assertNotEquals(7, differentShadesScore);
        Assert.assertNotEquals(2, lightShadesScore);
        Assert.assertNotEquals(6, mediumShadesScore);

        Assert.assertEquals(54, totalPublicScore);
        Assert.assertNotEquals(53, totalPublicScore);

    }
}
package it.polimi.se2018.controller;

import it.polimi.se2018.model.ChoseInputMove;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * MoveParser Test Class
 * This method verifies the correct functioning of the MoveParser
 * @author Davide Lorenzi
 * @author Federico Lichinchi
 */
public class TestMoveParser {

    MoveParser moveParser;

    /**
     * SetUp Method
     * This method prepare the test ambient
     */
    @Before
    public void setUp() {
        this.moveParser = MoveParser.newMoveParser();

    }


    /**
     * AnswerMessage Tester
     * This test verifies that the move parser returns the correct answer from the entrance key. This method
     * verifies all answers that there are into the related JSon file
     */
    @Test
    public void answerMessage() {
        for (int i = 0; i < moveParser.getAnswers().length; i++) {
            Assert.assertEquals(moveParser.getAnswers()[i][0], moveParser.answerMessage(moveParser.getAnswers()[i][1]).getMessage());
        }
    }

    /**
     * Answer Tester
     * This method verifies if the move parser has to answer a client question
     */
    @Test
    public void haveToAnswer() {
        Assert.assertFalse(moveParser.haveToAnswer(moveParser.answerMessage("colour")));
        Assert.assertFalse(moveParser.haveToAnswer(moveParser.requestMessage("ReserveChoosePlace")));
        Assert.assertTrue(moveParser.haveToAnswer(moveParser.requestMessage("ReserveChooseDie")));
    }

    /**
     * IsDigit Tester
     * This method verifies if the passed string are correctly recognised as number strings or not
     */
    @Test
    public void isNumeric() {
        Assert.assertTrue(moveParser.isNumeric("6569559"));
        Assert.assertFalse(moveParser.isNumeric("sdfas32"));
    }

    /**
     * RequestMessage Tester
     * This test verifies that the move parser returns the correct question from the entrance key. This method
     * verifies all questions that there are into the related JSon file
     */
    @Test
    public void requestMessage() {
        for (int i = 0; i < moveParser.getQuestions().length; i++) {
            Assert.assertEquals(moveParser.getQuestions()[i][0], moveParser.requestMessage(moveParser.getQuestions()[i][1]).getMessage());
        }
    }

    /**
     * Contains Tester
     * This method verifies that the search function properly works
     */
    @Test
    public void contains() {
        char[] numbers = {'0','1','2','3','4','5','6','7','8','9'};
        Assert.assertTrue(moveParser.contains('0',numbers));
        Assert.assertFalse(moveParser.contains('s',numbers));
    }

    /**
     * CheckInput Tester
     * This test verifies that CheckInput runs properly catching a number coming from the Client and verifying the question
     */
    @Test
    public void checkInput() {
        Assert.assertFalse(moveParser.checkInput(new ChoseInputMove("hijk", null, null), moveParser.getQuestions()[0][1]));
        Assert.assertTrue(moveParser.checkInput(new ChoseInputMove("S", null, null), moveParser.getQuestions()[0][1]));
        Assert.assertTrue(moveParser.checkInput(new ChoseInputMove("3", null, null), moveParser.getQuestions()[0][1]));
        Assert.assertFalse(moveParser.checkInput(new ChoseInputMove("6", null, null), moveParser.getQuestions()[0][1]));
        Assert.assertTrue(moveParser.checkInput(new ChoseInputMove("5", null, null), moveParser.getQuestions()[2][1]));
        Assert.assertFalse(moveParser.checkInput(new ChoseInputMove("7", null, null), moveParser.getQuestions()[2][1]));
        Assert.assertTrue(moveParser.checkInput(new ChoseInputMove("2", null, null), moveParser.getQuestions()[7][1]));
        Assert.assertFalse(moveParser.checkInput(new ChoseInputMove("23",null, null), moveParser.getQuestions()[7][1]));
        Assert.assertTrue(moveParser.checkInput(new ChoseInputMove("6", null, null), moveParser.getQuestions()[21][1]));
        Assert.assertTrue(moveParser.checkInput(new ChoseInputMove("2", null, null), moveParser.getQuestions()[21][1]));
    }
}
package it.polimi.se2018.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import it.polimi.se2018.model.ChoseInputMove;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;

/**
 * MoveParser
 * This singleton class is used to extract from JSON file all questions that a player can do to the server and all answers that
 * the server can give. In this class there's also a check of the input move.
 * @author Federico Lichinchi
 * @author Davide Lorenzi (JDocs)
 */
public class MoveParser {
    private static MoveParser ist = null;
    private String[][] questions;
    private String[][] answers;

    /**
     * Move Parser Constructor
     * In this method there's the core of the parser from JSON. It reads all questions and answers from the JSON and
     * copy them into a proper multidimensional string.
     */
    private MoveParser() throws FileNotFoundException {
        new Object();
        Gson gson = new Gson();
        Type type1 = new TypeToken<String[][]>() {}.getType();
        questions = gson.fromJson(
                new FileReader("src/main/java/resources/JSONs/Questions.json"), type1);
        answers = gson.fromJson(
                new FileReader("src/main/java/resources/JSONs/Answers.json"), type1);
    }

    /**
     * AnswerMessage Method
     * This method is used to give a defined answer for each string of message incoming after a player move
     * @param key one of the defined questions
     * @return the related answer after a player move
     */
    public ChoseInputMove answerMessage(String key) {
        ChoseInputMove answer = null;
        for (String[] answer1 : answers) {
            if (answer1[1].equals(key)) {
                answer = new ChoseInputMove(answer1[0], "\n", null);
            }
        }
        return answer;

    }

    /**
     * RequestMessage Method
     * This method searches the defined key sentence from the defined questions
     * @param key message incoming
     * @return request to the player for a move
     */
    public ChoseInputMove requestMessage(String key) {
        ChoseInputMove request = null;
        for (String[] question : questions) {
            if (question[1].equals(key)) {
                request = new ChoseInputMove(question[0], "\n", null);
            }
        }
        return request;

    }

    /**
     * CheckInput Method
     * This method is used to check if the incoming move from the player is allowed/exists or not
     * @param move is the move we have to check the existence
     * @param key is the key word used to search the defined sentence
     * @return true if the param number is between the max and the min or if the SimpleName is equals to the chosen
     * question
     */
    public boolean checkInput(ChoseInputMove move, String key) {
        String tmp = move.getMessage();
        if(tmp.equals("S") || tmp.equals("exit"))
            return true;
        for (String[] question : questions) {
            if (question[1].equals(key)) {
                if (question[2] == null) {
                    return true;
                } else if (question[2].indexOf('-') > -1 && isNumeric(tmp)) {
                    int number = Integer.parseInt(tmp);
                    String[] range = question[2].split("-");
                    int min = Integer.parseInt(range[0]);
                    int max = Integer.parseInt(range[1]);
                    return (number >= min && number <= max);
                } else {
                    return false;
                }
            }
        }
        return false;

    }

    /**
     * Contains Check
     * @param c is the parameter to find
     * @param array is the array to scan
     * @return true if the parameter is into the array
     */
    public boolean contains(char c, char[] array) {
        for (char x : array) {
            if (x == c) {
                return true;
            }
        }
        return false;
    }

    /**
     * IsDigit Verifier
     * This method verifies if a string is a string of numbers
     * @param s is the passed string
     * @return true if the passed string is a numeric one, false otherwise
     */
    public boolean isNumeric(String s) {
        char[] numbers = {'0','1','2','3','4','5','6','7','8','9'};
        char[] chars = s.toCharArray();
        for(char c : chars)
            if (!contains(c, numbers))
                return false;
        return true;
    }

    /**
     * Answer Checker
     * This method verifies if a a move has been answered or not
     * @param move move coming from the player
     * @return true if the answer has been given, false otherwise
     */
    public boolean haveToAnswer(ChoseInputMove move) {
        String tmp = move.getMessage();
        for(String[] answer : answers) {
            if(answer[0].equals(tmp))
                return false;
        }
        for(String[] question: questions) {
            if(question[0].equals(tmp) && question[2] != null)
                return true;
        }
        return false;
    }

    /**
     * GetQuestions Method
     * This method return the String of questions
     * @return String of questions
     */
    public String[][] getQuestions() {
        return questions;
    }

    /**
     * GetQuestions Method
     * This method return the String of answers
     * @return String of answers
     */
    public String[][] getAnswers() {
        return answers;

    }
    /**
     * Singleton Caller
     * This method create a new instance of the MoveParser if it has't been already done
     * or not create in the opposite case)
     * @return the actual instance of the Move Parser
     */
    public static MoveParser newMoveParser(){
        if(ist == null) {
            try {
                ist = new MoveParser();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return ist;
    }
}

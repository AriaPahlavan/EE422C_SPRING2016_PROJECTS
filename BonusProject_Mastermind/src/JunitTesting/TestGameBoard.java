package JunitTesting;

import Mastermind.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * BonusProject_Mastermind
 * Created by Aria Pahlavan on Apr 2016.
 */
public class TestGameBoard {
    @Test
    public void simpleGameBoardTest() throws Exception {
        GameBoard gameBoard = new GameBoard();
        GuessPeg[] guessPeg = new GuessPeg[4];
        RoundPegColor[] colors = {RoundPegColor.blue, RoundPegColor.yellow, RoundPegColor.red, RoundPegColor.orange};

        for(int i = 0; i < 4; i += 1) {
            guessPeg[i] = new GuessPeg();
            guessPeg[i].setColor(colors[i]);
        }



        List<Guess> guesses = new ArrayList<>();
        Guess guess = new Guess(guessPeg);



        System.out.println("--------------------Code--------------------");
        gameBoard.getSecretCode().displaySecretCode();

        System.out.println("\n--------------------Guess-------------------");
        guess.displayGuess();

        System.out.println("\n--------------------Result------------------");



        if ( gameBoard.addGuess(guess) )
            System.out.println("The guess was added successfully");


        Result result = gameBoard.getResults().get(0);

        for(int i = 0; i < result.getNumFeedbackPegs(); i+=1) {

            System.out.println(result.getResult()[i].getFlatColor());
        }

        if ( gameBoard.isGuessMatch() )
            System.out.println("Congratulations! You cracked the code ;)");
    }

    @Test
    public void sequenceMatchTest() throws Exception {
        GameBoard gameBoard = new GameBoard();
        GuessPeg[] guessPeg = new GuessPeg[4];
        SecretPeg[] secretPeg = new SecretPeg[4];
        RoundPegColor[] colors = {RoundPegColor.blue, RoundPegColor.yellow, RoundPegColor.red, RoundPegColor.orange};

        for(int i = 0; i < 4; i += 1) {
            guessPeg[i] = new GuessPeg();
            guessPeg[i].setColor(colors[i]);
            secretPeg[i] = new SecretPeg();
            secretPeg[i].setColor(colors[i]);
        }



        List<Guess> guesses = new ArrayList<>();
        Guess guess = new Guess(guessPeg);
        SecretCode secretCode = new SecretCode(secretPeg);

        gameBoard.setSecretCode(secretCode);


        System.out.println("--------------------Code--------------------");
        gameBoard.getSecretCode().displaySecretCode();

        System.out.println("\n--------------------Guess-------------------");
        guess.displayGuess();
        System.out.println("\n--------------------Result------------------");



        assertTrue( "Oops! Having problem processing your guess :(", gameBoard.addGuess(guess) );


        Result result = gameBoard.getResults().get(0);

        for(int i = 0; i < result.getNumFeedbackPegs(); i+=1) {

            assertEquals("Oops! The result is not correct :(", result.getResult()[i].getFlatColor(), FlatPegColor.black);
        }

         assertTrue( "Oops! You didn't win :(", gameBoard.isGuessMatch() );
    }


    @Test
    public void duplicatePegsTest() throws Exception {
        GameBoard gameBoard = new GameBoard();
        GuessPeg[] guessPeg = new GuessPeg[4];
        SecretPeg[] secretPeg = new SecretPeg[4];
        RoundPegColor[] colors = {RoundPegColor.blue, RoundPegColor.blue, RoundPegColor.red, RoundPegColor.orange};
        RoundPegColor[] colors2 = {RoundPegColor.blue, RoundPegColor.blue, RoundPegColor.blue, RoundPegColor.blue};

        for(int i = 0; i < 4; i += 1) {
            guessPeg[i] = new GuessPeg();
            guessPeg[i].setColor(colors[i]);
            secretPeg[i] = new SecretPeg();
            secretPeg[i].setColor(colors2[i]);
        }



        List<Guess> guesses = new ArrayList<>();
        Guess guess = new Guess(guessPeg);
        SecretCode secretCode = new SecretCode(secretPeg);

        gameBoard.setSecretCode(secretCode);


        System.out.println("--------------------Code--------------------");
        gameBoard.getSecretCode().displaySecretCode();

        System.out.println("\n--------------------Guess-------------------");
        guess.displayGuess();
        System.out.println("\n--------------------Result------------------");



        assertTrue( "Oops! Having problem processing your guess :(", gameBoard.addGuess(guess) );


        Result result = gameBoard.getResults().get(0);

        for(int i = 0; i < result.getNumFeedbackPegs(); i+=1) {

            assertEquals("Oops! The result is not correct :(", result.getResult()[i].getFlatColor(), FlatPegColor.black);

            System.out.println(result.getResult()[i].getFlatColor());
        }

//        assertTrue( "Oops! You didn't win :(", gameBoard.isGuessMatch() );
    }

}
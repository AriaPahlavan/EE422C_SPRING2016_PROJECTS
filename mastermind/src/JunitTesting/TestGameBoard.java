package JunitTesting;

import Assignment7.*;
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
//        gameBoard.getSecretCode().displaySecretCode();

        System.out.println("\n--------------------Guess-------------------");
//        guess.displayGuess();

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
//        gameBoard.getSecretCode().displaySecretCode();

        System.out.println("\n--------------------Guess-------------------");
//        guess.displayGuess();
        System.out.println("\n--------------------Result------------------");



        assertTrue( "Oops! Having problem processing your guess :(", gameBoard.addGuess(guess) );


        Result result = gameBoard.getResults().get(0);

        for(int i = 0; i < result.getNumFeedbackPegs(); i+=1) {

            assertEquals("Oops! The result is not correct :(", result.getResult()[i].getFlatColor(), FlatPegColor.black);
        }

         assertTrue( "Oops! You didn't win :(", gameBoard.isGuessMatch() );
        System.out.println("Success!");
    }


    @Test
    public void duplicatePegsTest() throws Exception {
        GameBoard gameBoard = new GameBoard();
        GuessPeg[] guessPeg = new GuessPeg[4];
        SecretPeg[] secretPeg = new SecretPeg[4];
        RoundPegColor[] colors = {RoundPegColor.yellow, RoundPegColor.yellow, RoundPegColor.purple, RoundPegColor.purple};
        RoundPegColor[] colors2 = {RoundPegColor.yellow, RoundPegColor.purple, RoundPegColor.purple, RoundPegColor.yellow};

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
//        gameBoard.getSecretCode().displaySecretCode();

        System.out.println("\n--------------------Guess-------------------");
//        guess.displayGuess();
        System.out.println("\n--------------------Result------------------");



        assertTrue( "Oops! Having problem processing your guess :(", gameBoard.addGuess(guess) );


        Result result = gameBoard.getResults().get(0);


        assertEquals("Oops! The result is not correct :(", result.getResult()[0].getFlatColor(), FlatPegColor.black);
        assertEquals("Oops! The result is not correct :(", result.getResult()[1].getFlatColor(), FlatPegColor.black);
        assertEquals("Oops! The result is not correct :(", result.getResult()[2].getFlatColor(), FlatPegColor.white);
        assertEquals("Oops! The result is not correct :(", result.getResult()[3].getFlatColor(), FlatPegColor.white);


        System.out.println("Success!");
    }


    @Test
    public void multipleGuessTest() throws Exception {
        GameBoard gameBoard = new GameBoard();
        SecretPeg[] secretPeg = new SecretPeg[4];
        RoundPegColor[] colors0 = {RoundPegColor.green, RoundPegColor.blue, RoundPegColor.red, RoundPegColor.yellow};
        RoundPegColor[] colors1 = {RoundPegColor.red, RoundPegColor.green, RoundPegColor.purple, RoundPegColor.orange};
        RoundPegColor[] colors2 = {RoundPegColor.blue, RoundPegColor.blue, RoundPegColor.green, RoundPegColor.blue};
        RoundPegColor[] colors3 = {RoundPegColor.yellow, RoundPegColor.purple, RoundPegColor.purple, RoundPegColor.yellow};

        RoundPegColor[][] guessColorList = {colors0, colors1, colors2, colors3};

        for(int i = 0; i < 4; i += 1) {
            secretPeg[i] = new SecretPeg();
            secretPeg[i].setColor(colors3[i]);
        }


        List<Guess> guesses = new ArrayList<>();

        SecretCode secretCode = new SecretCode(secretPeg);

        gameBoard.setSecretCode(secretCode);



        for(int j = 0; j < 4; j +=1) {

            System.out.println("============================Guess # "+ (j+1)+"========================\n");

            Guess guess = new Guess(guessColorList[j]);

            System.out.println("--------------------Code--------------------");
//            gameBoard.getSecretCode().displaySecretCode();

            System.out.println("\n--------------------Guess-------------------");
            assertTrue("Oops! Having problem processing your guess :(", gameBoard.addGuess(guess));
//            gameBoard.getGuesses().get(j).displayGuess();


            System.out.println("\n--------------------Result------------------\n");

            Result result = gameBoard.getResults().get(j);

            for ( int i = 0; i < result.getNumFeedbackPegs(); i += 1 ) {

                System.out.println(result.getResult()[i].getFlatColor());
            }

            if ( gameBoard.isGuessMatch() )
                break;
        }
        System.out.println("Congratulations!");
    }

    @Test
    public void historyTest() throws Exception {
        GameBoard gameBoard = new GameBoard();
        SecretPeg[] secretPeg = new SecretPeg[4];
        RoundPegColor[] colors0 = {RoundPegColor.green, RoundPegColor.blue, RoundPegColor.red, RoundPegColor.yellow};
        RoundPegColor[] colors1 = {RoundPegColor.red, RoundPegColor.green, RoundPegColor.purple, RoundPegColor.orange};
        RoundPegColor[] colors2 = {RoundPegColor.blue, RoundPegColor.blue, RoundPegColor.green, RoundPegColor.blue};
        RoundPegColor[] colors3 = {RoundPegColor.yellow, RoundPegColor.purple, RoundPegColor.purple, RoundPegColor.yellow};
        RoundPegColor[] colors4 = {RoundPegColor.green, RoundPegColor.purple, RoundPegColor.purple, RoundPegColor.yellow};
        RoundPegColor[] colors5 = {RoundPegColor.red, RoundPegColor.purple, RoundPegColor.purple, RoundPegColor.yellow};
        RoundPegColor[] colors6 = {RoundPegColor.purple, RoundPegColor.purple, RoundPegColor.purple, RoundPegColor.yellow};

        RoundPegColor[][] guessColorList = {colors0, colors1, colors2, colors3, colors4, colors5, colors6};


        for (RoundPegColor[] aGuessColorList : guessColorList) {

            Guess guess = new Guess(aGuessColorList);
            assertTrue("Oops! Having problem processing your guess :(", gameBoard.addGuess(guess));
            if (gameBoard.isGuessMatch()) {
                System.out.println("You win!");
                System.exit(0);
            }

        }

//        gameBoard.displayHistory();

        System.out.println("Congratulations!");
    }

}
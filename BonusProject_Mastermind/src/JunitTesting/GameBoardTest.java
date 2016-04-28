package JunitTesting;

import Mastermind.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * BonusProject_Mastermind
 * Created by Aria Pahlavan on Apr 2016.
 */
public class GameBoardTest {
    @Test
    public void testGameBoardTest() throws Exception {
        GameBoard gameBoard = new GameBoard();
        GuessPeg[] guessPeg = new GuessPeg[4];
//        SecretPeg[] secretPeg = new SecretPeg[4];
        RoundPegColor[] colors = {RoundPegColor.blue, RoundPegColor.yellow, RoundPegColor.red, RoundPegColor.orange};

        for(int i = 0; i < 4; i += 1) {
            guessPeg[i] = new GuessPeg();
            guessPeg[i].setColor(colors[i]);
//            secretPeg[i] = new SecretPeg();
//            secretPeg[i].setColor(colors[i]);
        }



        List<Guess> guesses = new ArrayList<>();
        Guess guess = new Guess(guessPeg);
//        SecretCode secretCode = new SecretCode(secretPeg);

//        gameBoard.setSecretCode(secretCode);


        System.out.println("--------------------Code--------------------");
        gameBoard.getSecretCode().displaySecretCode();

        System.out.println("\n--------------------Guess-------------------");
        System.out.println("blue");
        System.out.println("yellow");
        System.out.println("red");
        System.out.println("orange");
        System.out.println("\n--------------------Result------------------");



        if ( gameBoard.addGuess(guess) )
            System.out.println("The guess was added successfully");


        Result result = gameBoard.getResults().get(0);

        for(int i = 0; i < result.getNumFeedbackPegs(); i+=1) {

            switch (result.getResult()[i].getFlatColor()) {

                case black:
                    System.out.println("black");
                    break;
                case white:
                    System.out.println("white");
                    break;
                case none:
                    break;
            }
        }

        if ( gameBoard.isGuessMatch() )
            System.out.println("Congratulations! You cracked the code ;)");
    }
}
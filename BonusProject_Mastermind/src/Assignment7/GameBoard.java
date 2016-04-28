package Assignment7;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * BonusProject_Mastermind
 * Created by Aria Pahlavan on Apr 2016.
 */
public class GameBoard {
    private SecretCode secretCode;
    private List<Guess> guesses;
    private List<Result> results;
    private boolean guessMatch = false;
    private final int MAX_GUESS = 12;
    protected static Color boardColor = new Color(246, 163, 90);

    private int xBoard;
    private int yBoard;


    public GameBoard() {
        this.secretCode = new SecretCode();
        this.guesses = new ArrayList<>();
        this.results = new ArrayList<>();
        xBoard = 0;
        yBoard = 0;

    }

    public GameBoard(SecretCode secretCode, List<Guess> guesses, List<Result> results) {
        this.secretCode = secretCode;
        this.guesses = guesses;
        this.results = results;
        xBoard = 0;
        yBoard = 0;
    }

    public int getxBoard() {
        return xBoard;
    }

    public void setxBoard(int xBoard) {
        this.xBoard = xBoard;
    }

    public void setxyBoard(int xBoard, int yBoard) {
        this.xBoard = xBoard;
        this.yBoard = yBoard;
    }

    public int getyBoard() {
        return yBoard;
    }

    public void setyBoard(int yBoard) {
        this.yBoard = yBoard;
    }

    public boolean isGuessMatch() {
        return guessMatch;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public SecretCode getSecretCode() {
        return secretCode;
    }

    public List<Guess> getGuesses() {
        return guesses;
    }

    public void setGuesses(List<Guess> guesses) {
        this.guesses = guesses;
    }

    //Just for testing
    public void setSecretCode(SecretCode secretCode) {
        this.secretCode = secretCode;
    }

    /**
     * Adds the players guess into "gameBoard's history"
     *
     * @param guess
     * @return boolean to indicate whether or not the guess was added to
     * the history (if false the max guesses have been made)!
     */
    public boolean addGuess(Guess guess) {
        boolean wasAdded = true;

        // Limit for number of guesses to be made
        if ( this.guesses.size() < MAX_GUESS ) {

            // Check to see if this is a match, else figure out the feedback
            evaluateGuess(guess);
            this.guesses.add(guess);
            wasAdded = true;

            for ( int i = 0; i < 4; i += 1 ) {
                guesses.get(guesses.size() - 1).getGuess()[i].setxyPeg(525 + 100 * i, 585 - 35 * (guesses.size() - 1));
            }

        } else
            wasAdded = false;

        return wasAdded;
    }

    /**
     * Evaluates the guess against the secret code and unless it's a match,
     * it sets the appropriate number of black and white feedback pegs
     *
     * @param guess
     */
    public void evaluateGuess(Guess guess) {
        Result newResult = new Result();
        int numBlackPeg = 0;
        int numWhitePeg = 0;

        // figure out the number of black peg
        for ( int i = 0; i < 4; i += 1 ) {

            //same position for both
            Peg guessPeg = guess.getGuess()[i];
            Peg secretPeg = secretCode.getSecretCode()[i];

            if ( guessPeg.getColor() == secretPeg.getColor() ) {

                // Adds a black peg to result
                newResult.getResult()[newResult.getNumFeedbackPegs()] = new ResultPeg(FlatPegColor.black);
                numBlackPeg += 1;

                //increments number of result pegs
                newResult.setNumFeedbackPegs(newResult.getNumFeedbackPegs() + 1);

                if ( newResult.getNumFeedbackPegs() > 4 ) System.err.println("too many black pegs");

                // Mark guessPeg and secretPeg at checked and matched
                guessPeg.setMatched(true);
                secretPeg.setMatched(true);
            }
        }

        // figure out the number of white peg
        for ( int i = 0; i < 4; i += 1 ) {

            //same position for both
            Peg guessPeg = guess.getGuess()[i];

            // If the current peg in guess is not matched
            if ( !guessPeg.isMatched() ) {

                // Compare it to each pegs of secret code one by one
                for ( Peg secretPeg : secretCode.getSecretCode() ) {

                    //only if it hasn't been already matched
                    if ( !secretPeg.isMatched() )
                        if ( guessPeg.getColor() == secretPeg.getColor() ) {

                            // Adds a white peg to result
                            newResult.getResult()[newResult.getNumFeedbackPegs()] = new ResultPeg(FlatPegColor.white);
                            numWhitePeg += 1;

                            //increments number of result pegs
                            newResult.setNumFeedbackPegs(newResult.getNumFeedbackPegs() + 1);

                            if ( newResult.getNumFeedbackPegs() > 4 ) System.err.println("too many white pegs");


                            // Mark guessPeg and secretPeg at checked and matched
                            guessPeg.setMatched(true);
                            secretPeg.setMatched(true);

                            // break out of the for loop since this peg is already matched
                            break;
                        }
                }
            }
        }

        // After all checks are done check if it's a winner guess
        if ( numBlackPeg == 4 ) {
            if ( numWhitePeg == 0 ) {
                guessMatch = true;

            } else
                System.err.println("There's a problem with verifying win");
        }

        this.results.add(newResult);

        // Unchecking the matched variable of source code for future checking
        for ( SecretPeg secretPeg : secretCode.getSecretCode() )
            secretPeg.setMatched(false);

        // Adding the position of result pegs
        for ( int i = 0; i < 4; i += 1 ){
            newResult.getResult()[i].setxyPeg(925 + 50 * i, 585 - 35 * (guesses.size() - 1));
        }
    }

    /**
     * Displays the game play history
     */
    public void displayHistory(Graphics2D g2) {
        for ( int j = 0; j < guesses.size(); j += 1 ) {

            System.out.println("============================Guess # " + (j + 1) + "========================\n");


            System.out.println("--------------------Code--------------------");
            secretCode.displaySecretCode(g2);

            System.out.println("\n--------------------Guess-------------------");
            guesses.get(j).displayGuess(g2);



            System.out.println("\n--------------------Result------------------\n");

            Result result = results.get(j);
            result.displayResult(g2);

            for ( int i = 0; i < result.getNumFeedbackPegs(); i += 1 ) {

                System.out.println(result.getResult()[i].getFlatColor());

            }


        }
    }

    /**
     * his method paints the board on the applet screen display.
     *
     * @param g2
     */
    public void paintBoard(Graphics2D g2) {
        g2.setColor(boardColor);
        g2.fillRoundRect(xBoard, yBoard, 400, 500, 10, 360);
        displayHistory(g2);
    }

}

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
    private int MAX_GUESS;
    private Board gameBoard;




    public GameBoard() {
        this.secretCode = new SecretCode();
        this.guesses = new ArrayList<>();
        this.results = new ArrayList<>();
        gameBoard = new Board();
        this.MAX_GUESS = Game.MAX_GUESS;
    }

    public void setMAX_GUESS(int MAX_GUESS) {
        this.MAX_GUESS = MAX_GUESS;
    }

    public GameBoard(SecretCode secretCode, List<Guess> guesses, List<Result> results) {
        this.secretCode = secretCode;
        this.guesses = guesses;
        this.results = results;
    }

    public Board getGameBoard() {
        return gameBoard;
    }

    public void setGameBoard(Board gameBoard) {
        this.gameBoard = gameBoard;
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
            wasAdded = true;
            System.out.println("guess was added!");


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
        for ( int i = 0; i < 4; i += 1 ) {
            if ( newResult.getResult()[i] == null )
                break;
            newResult.getResult()[i].setxyPeg(800 + 20 * i, 546  - 35 * (guesses.size() - 1));
        }

        this.guesses.add(guess);
        for ( int i = 0; i < 4; i += 1 ) {
            this.guesses.get(this.guesses.size()-1).getGuess()[i].setxyPeg(535 + 70 * i, 540 - 35 * (guesses.size() - 2));
        }
    }

    /**
     * Displays the game play history
     */
    public void displayHistory(Graphics2D g2) {

//        if ( guessMatch )
            secretCode.displaySecretCode(g2);

        for ( int j = 0; j < guesses.size(); j += 1 ) {
            // Display guesses
            guesses.get(j).displayGuess(g2);


            // Display results
            results.get(j).displayResult(g2);
        }

    }

    /**
     * his method paints the game board and all the pegs and everything on the applet screen display.
     *
     * @param g2
     */
    public void paintGameBoard(Graphics2D g2) {
        gameBoard.paintBoard(g2);
        displayHistory(g2);
    }

}

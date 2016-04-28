package Mastermind;

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

    public GameBoard() {
        this.secretCode = new SecretCode();
        this.guesses = new ArrayList<>();
        this.results = new ArrayList<>();
    }

    public GameBoard(SecretCode secretCode, List<Guess> guesses, List<Result> results) {
        this.secretCode = secretCode;
        this.guesses = guesses;
        this.results = results;
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

    /**
     * Adds the players guess into "gameBoard's history"
     * @param guess
     * @return boolean to indicate whether or not the guess was added to
     *          the history (if false the max guesses have been made)!
     */
    public boolean addGuess(Guess guess) {

        // Limit for number of guesses to be made
        if ( this.guesses.size() <= MAX_GUESS )
            this.guesses.add(guess);
        else
            return false;

        return true;
    }

    /**
     * Evaluates the guess against the secret code and unless it's a match,
     * it sets the appropriate number of black and white feedback pegs
     * @param guess
     * @return
     */
    public boolean evaluateGuess(Guess guess){
        
    }
}

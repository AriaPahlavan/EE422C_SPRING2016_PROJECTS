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
    private final int MAX_GUESS = 12;

    public GameBoard() {
        this.secretCode = new SecretCode();
        this.guesses = new ArrayList<>();
    }

    public GameBoard(SecretCode secretCode, List<Guess> guesses) {
        this.secretCode = secretCode;
        this.guesses = guesses;
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
}

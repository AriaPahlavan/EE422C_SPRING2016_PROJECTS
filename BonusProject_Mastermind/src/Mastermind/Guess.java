package Mastermind;

/**
 * BonusProject_Mastermind
 * Created by Aria Pahlavan on Apr 2016.
 */
public class Guess {
    private GuessPeg[] guess;

    public Guess() {
        this.guess = new GuessPeg[4];
    }

    public Guess(GuessPeg[] guess) {
        this.guess = guess;
    }

    public GuessPeg[] getGuess() {
        return guess;
    }

    public void setGuess(GuessPeg[] guess) {
        this.guess = guess;
    }
}

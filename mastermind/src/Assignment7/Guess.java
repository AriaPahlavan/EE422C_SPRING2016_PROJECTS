package Assignment7;

import java.awt.*;
import java.util.ArrayList;

/**
 * BonusProject_Mastermind
 * Created by Aria Pahlavan on Apr 2016.
 */
public class Guess {
    private GuessPeg[] guess;

    Guess() {
        this.guess = new GuessPeg[4];
    }

    /**
     * For testing purposes only!
     */
    public Guess(ArrayList<RoundPegColor> pegColors) {
        this.guess = new GuessPeg[4];
        makeGuess(pegColors);
    }

    /**
     * For testing purposes only!
     */
    public Guess(RoundPegColor[] pegColors) {
        this.guess = new GuessPeg[4];
        makeGuess(pegColors);
    }

    public Guess(GuessPeg[] guess) {
        this.guess = guess;
    }


    GuessPeg[] getGuess() {
        return guess;
    }

    /**
     * This method receives 4 colors and makes an array of 4 guessPegs
     */
    void makeGuess(ArrayList<RoundPegColor> pegColors) {
        int i = 0;
        for ( RoundPegColor pegColor : pegColors ) {

            //make a peg for each color
            GuessPeg newPeg = new GuessPeg(pegColor);

            //add the new peg to guess
            guess[i] = newPeg;
            i += 1;
        }
    }


    /**
     * This method receives 4 colors and makes an array of 4 guessPegs
     *
     * @param pegColors the array of colors received
     */
    private void makeGuess(RoundPegColor[] pegColors) {
        int i = 0;
        for ( RoundPegColor pegColor : pegColors ) {

            //make a peg for each color
            GuessPeg newPeg = new GuessPeg(pegColor);

            //add the new peg to guess
            guess[i] = newPeg;
            i += 1;
        }
    }

    /**
     * Displays users guess
     */
    void displayGuess(Graphics2D g2) {
        for (GuessPeg peg : guess ) {
            if ( peg == null )
                break;
            peg.paint(g2);
        }
    }

    /**
     * This method resets the guess
     */
    void reset() {
        this.guess = new GuessPeg[4];
    }

    void displayTempGuess(Graphics2D g2) {

        for (GuessPeg peg : guess) {

            if (peg == null)
                break;
            peg.paint(g2);
        }

    }
}

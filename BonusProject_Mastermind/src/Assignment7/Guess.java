package Assignment7;

import java.util.ArrayList;

/**
 * BonusProject_Mastermind
 * Created by Aria Pahlavan on Apr 2016.
 */
public class Guess {
    private GuessPeg[] guess;

    public Guess() {
        this.guess = new GuessPeg[4];
    }

    /**
     * For testing purposes only!
     * @param pegColors
     */
    public Guess(ArrayList<RoundPegColor> pegColors) {
        this.guess = new GuessPeg[4];
        makeGuess(pegColors);
    }

    /**
     * For testing purposes only!
     * @param pegColors
     */
    public Guess(RoundPegColor[] pegColors) {
        this.guess = new GuessPeg[4];
        makeGuess(pegColors);
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

    /**
     * This method receives 4 colors and makes an array of 4 guessPegs
     * @param pegColors
     */
    public void makeGuess(ArrayList<RoundPegColor> pegColors){

        int i = 0;
        for ( RoundPegColor pegColor : pegColors ){

            //make a peg for each color
            GuessPeg newPeg = new GuessPeg(pegColor);

            //add the new peg to guess
            guess[i] = newPeg;
            i+=1;
        }
    }


    /**
     * This method receives 4 colors and makes an array of 4 guessPegs
     * @param pegColors
     */
    public void makeGuess(RoundPegColor[] pegColors){

        int i = 0;
        for ( RoundPegColor pegColor : pegColors ){

            //make a peg for each color
            GuessPeg newPeg = new GuessPeg(pegColor);

            //add the new peg to guess
            guess[i] = newPeg;
            i+=1;
        }
    }

    /**
     * Displays users guess
     */
    public void displayGuess(){
        for(int i = 0; i < 4; i+=1){

            System.out.println((guess[i].getColor()));
        }
    }
}

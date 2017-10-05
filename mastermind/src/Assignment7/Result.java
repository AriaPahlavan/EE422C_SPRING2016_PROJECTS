package Assignment7;

import java.awt.*;

/**
 * BonusProject_Mastermind
 * Created by Aria Pahlavan on Apr 2016.
 */
public class Result {
    private ResultPeg[] result;
    private int numFeedbackPegs;

    Result() {
        this.result = new ResultPeg[4];
        this.numFeedbackPegs = 0;
    }

    public int getNumFeedbackPegs() {
        return numFeedbackPegs;
    }

    void setNumFeedbackPegs(int numFeedbackPegs) {
        this.numFeedbackPegs = numFeedbackPegs;
    }

    public ResultPeg[] getResult() {
        return result;
    }

    /**
     * Displays users guess
     */
    void displayResult(Graphics2D g2){
        for(int i = 0; i < 4; i+=1){
            if ( result[i] == null )
                break;
            result[i].paint(g2);
        }
    }
}

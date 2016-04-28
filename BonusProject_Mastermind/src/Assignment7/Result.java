package Assignment7;

import java.awt.*;

/**
 * BonusProject_Mastermind
 * Created by Aria Pahlavan on Apr 2016.
 */
public class Result {
    private ResultPeg[] result;
    private int numFeedbackPegs;

    public Result() {
        this.result = new ResultPeg[4];
        this.numFeedbackPegs = 0;
    }

    public Result(ResultPeg[] result) {
        this.result = result;
        this.numFeedbackPegs = 0;
    }

    public int getNumFeedbackPegs() {
        return numFeedbackPegs;
    }

    public void setNumFeedbackPegs(int numFeedbackPegs) {
        this.numFeedbackPegs = numFeedbackPegs;
    }

    public ResultPeg[] getResult() {
        return result;
    }

    public void setResult(ResultPeg[] result) {
        this.result = result;
    }

    /**
     * Displays users guess
     */
    public void displayResult(Graphics2D g2){
        for(int i = 0; i < 4; i+=1){

            System.out.println((result[i].getColor()));
            result[i].paintPeg(g2);
        }
    }
}

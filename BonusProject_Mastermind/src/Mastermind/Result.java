package Mastermind;

/**
 * BonusProject_Mastermind
 * Created by Aria Pahlavan on Apr 2016.
 */
public class Result {
    private ResultPeg[] result;

    public Result() {
        this.result = new ResultPeg[4];
    }

    public Result(ResultPeg[] result) {
        this.result = result;
    }

    public ResultPeg[] getResult() {
        return result;
    }

    public void setResult(ResultPeg[] result) {
        this.result = result;
    }
}

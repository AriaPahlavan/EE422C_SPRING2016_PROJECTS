package Mastermind;

/**
 * BonusProject_Mastermind
 * Created by Aria Pahlavan on Apr 2016.
 */
public class ResultPeg extends Peg{
    private FlatPegColor flatColor;

    public ResultPeg() {
        super();
        this.flatColor = FlatPegColor.none;
    }

    public ResultPeg(FlatPegColor flatColor) {
        super();
        this.flatColor = flatColor;
    }

    public ResultPeg(RoundPegColor color, int position, FlatPegColor flatColor) {
        super(color, position);
        this.flatColor = flatColor;
    }

    public FlatPegColor getFlatColor() {
        return flatColor;
    }

    public void setFlatColor(FlatPegColor flatColor) {
        this.flatColor = flatColor;
    }
}

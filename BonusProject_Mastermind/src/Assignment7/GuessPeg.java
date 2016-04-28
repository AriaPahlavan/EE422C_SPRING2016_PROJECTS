package Assignment7;

/**
 * BonusProject_Mastermind
 * Created by Aria Pahlavan on Apr 2016.
 */
public class GuessPeg extends Peg{
    public GuessPeg() {
        super();
    }

    public GuessPeg(RoundPegColor color) {
        super(color);
    }

    public GuessPeg(RoundPegColor color, int position, boolean isMatched) {
        super(color, position, isMatched);
    }
}

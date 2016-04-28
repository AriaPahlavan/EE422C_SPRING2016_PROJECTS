package Assignment7;





/**
 * BonusProject_Mastermind
 * Created by Aria Pahlavan on Apr 2016.
 */
public class Peg {
    private RoundPegColor color;
    private boolean isMatched;
    private int position;

    public Peg() {
        this.color = RoundPegColor.none;
        this.position = 0;  // 1    2   3   4
        this.isMatched = false;
    }

    public Peg(RoundPegColor color, int position, boolean isMatched) {
        this.color = color;
        this.position = position;
        this.isMatched = isMatched;
    }

    public Peg(RoundPegColor color) {
        this();
        this.color = color;
    }

    public boolean isMatched() {
        return isMatched;
    }

    public void setMatched(boolean matched) {
        this.isMatched = matched;
    }

    public RoundPegColor getColor() {
        return color;
    }

    public void setColor(RoundPegColor color) {
        this.color = color;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}

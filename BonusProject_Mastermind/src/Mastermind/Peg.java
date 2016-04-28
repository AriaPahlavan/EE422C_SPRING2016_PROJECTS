package Mastermind;





/**
 * BonusProject_Mastermind
 * Created by Aria Pahlavan on Apr 2016.
 */
public class Peg {
    private RoundPegColor color;
    private boolean wasChecked;
    private int position;

    public Peg() {
        this.color = RoundPegColor.none;
        this.position = 0;  // 1    2   3   4
        this.wasChecked = false;
    }

    public Peg(RoundPegColor color, int position, boolean wasChecked) {
        this.color = color;
        this.position = position;
        this.wasChecked = wasChecked;
    }

    public boolean isWasChecked() {
        return wasChecked;
    }

    public void setWasChecked(boolean wasChecked) {
        this.wasChecked = wasChecked;
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

package Mastermind;


enum RoundPegColor {
    blue, green, orange, purple, red, yellow, none
}

enum FlatPegColor {
    black, white, none
}
/**
 * BonusProject_Mastermind
 * Created by Aria Pahlavan on Apr 2016.
 */
public class Peg {
    private RoundPegColor color;
    private int position;

    public Peg() {
        this.color = RoundPegColor.none;
        this.position = 0;  // 1    2   3   4
    }

    public Peg(RoundPegColor color, int position) {
        this.color = color;
        this.position = position;
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

package Assignment7;


import java.awt.*;

import static Assignment7.GameColors.*;

/**
 * BonusProject_Mastermind
 * Created by Aria Pahlavan on Apr 2016.
 */
public class Peg {
    private RoundPegColor color;
    private boolean isMatched;
    static final int PEG_RADIOUS = 20;
    private static final int CODE_PEG_RADIOUS = 30;
    private static final int RESULT_PEG_RADIOUS = 10;
    private int yPeg;
    private int xPeg;


    Peg() {
        this.color = RoundPegColor.none;
        this.isMatched = false;
    }

    Peg(RoundPegColor color, int position, boolean isMatched) {
        this.color = color;
        this.isMatched = isMatched;
        yPeg = 0;
        xPeg = 0;
    }

    Peg(RoundPegColor color) {
        this();
        this.color = color;
        yPeg = 0;
        xPeg = 0;
    }

    void setxyPeg(int xPeg, int yPeg) {
        this.xPeg = xPeg;
        this.yPeg = yPeg;
    }

    boolean isMatched() {
        return isMatched;
    }

    void setMatched(boolean matched) {
        this.isMatched = matched;
    }

    public RoundPegColor getColor() {
        return color;
    }

    public void setColor(RoundPegColor color) {
        this.color = color;
    }

    /**
     * his method paints the board on the applet screen display.
     */
    public void paint(Graphics2D g2) {
        g2.setColor(pickColor());
        g2.fillOval(xPeg, yPeg, PEG_RADIOUS, PEG_RADIOUS);
    }

    /**
     * his method paints the board on the applet screen display.
     */
    void paintCodePeg(Graphics2D g2) {
        g2.setColor(pickColor());
        g2.fillOval(xPeg, yPeg, CODE_PEG_RADIOUS, CODE_PEG_RADIOUS);
    }

    /**
     * his method paints the board on the applet screen display.
     */
    void paintResultPeg(Graphics2D g2) {
        g2.fillOval(xPeg, yPeg, RESULT_PEG_RADIOUS - 2, RESULT_PEG_RADIOUS - 2);
    }

    /**
     * Picks and returns the correct color for pegs to be drawn
     */
    public Color pickColor() {
        Color newColor = null;
        switch (color) {
            case blue:
                newColor = Color.blue;
                break;
            case green:
                newColor = Color.green;
                break;
            case orange:
                newColor = peg_orange;
                break;
            case purple:
                newColor = peg_purple;
                break;
            case red:
                newColor = Color.red;
                break;
            case yellow:
                newColor = Color.yellow;
                break;
            case none:
                break;
        }

        return newColor;
    }


}

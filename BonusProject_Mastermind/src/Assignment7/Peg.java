package Assignment7;


import java.awt.*;

/**
 * BonusProject_Mastermind
 * Created by Aria Pahlavan on Apr 2016.
 */
public class Peg {
    private RoundPegColor color;
    private boolean isMatched;
    private int position;
    private final int PEG_RADIOUS = 10;
    private int yPeg;
    private int xPeg;
    protected static Color PURPLE = new Color(76,0,153);

    public Peg() {
        this.color = RoundPegColor.none;
        this.position = 0;  // 1    2   3   4
        this.isMatched = false;
    }

    public Peg(RoundPegColor color, int position, boolean isMatched) {
        this.color = color;
        this.position = position;
        this.isMatched = isMatched;
        yPeg = 0;
        xPeg = 0;
    }

    public Peg(RoundPegColor color) {
        this();
        this.color = color;
        yPeg = 0;
        xPeg = 0;
    }

    public int getyPeg() {
        return yPeg;
    }

    public void setyPeg(int yPeg) {
        this.yPeg = yPeg;
    }

    public void setxyPeg(int xPeg, int yPeg) {
        this.xPeg = xPeg;
        this.yPeg = yPeg;
    }

    public int getxPeg() {
        return xPeg;
    }

    public void setxPeg(int xPeg) {
        this.xPeg = xPeg;
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

    /**
     * his method paints the board on the applet screen display.
     * @param g2
     */
    public void paintBoard(Graphics2D g2){
        g2.setColor(pickColor());
        g2.fillOval(xPeg, yPeg, PEG_RADIOUS, PEG_RADIOUS);
    }

    /**
     * Picks and returns the correct color for pegs to be drawn
     * @return
     */
    public Color pickColor(){
        Color newColor = null;
        switch (color) {
            case blue:
                newColor = Color.blue;
                break;
            case green:
                newColor = Color.green;
                break;
            case orange:
                newColor = Color.orange;
                break;
            case purple:
                newColor = PURPLE;
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

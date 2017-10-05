package Assignment7;

import java.awt.*;

/**
 * BonusProject_Mastermind
 * Created by Aria Pahlavan on Apr 2016.
 */
public class ResultPeg extends Peg{
    private FlatPegColor flatColor;

    ResultPeg(FlatPegColor flatColor) {
        super();
        this.flatColor = flatColor;
    }

    public FlatPegColor getFlatColor() {
        return flatColor;
    }

    @Override
    public Color pickColor() {
        Color newColor = null;
        switch (flatColor) {

            case black:
                newColor = Color.black;
                break;
            case white:
                newColor = Color.white;
                break;
            case none:
                break;
        }

        return newColor;
    }

    @Override
    public void paint(Graphics2D g2) {
        g2.setColor(pickColor());
        paintResultPeg(g2);
    }
}

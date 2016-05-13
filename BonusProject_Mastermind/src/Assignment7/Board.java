package Assignment7;

import java.awt.*;

/**
 * BonusProject_Mastermind
 * Created by Aria Pahlavan on May 2016.
 */
public class Board {

    private int xBoard;
    private int yBoard;
    protected static Color boardColor = new Color(246, 163, 90);
    protected static Color boardColor2 = new Color(176, 103, 60);


    public Board() {
        xBoard = 0;
        yBoard = 0;
    }

    public int getxBoard() {
        return xBoard;
    }

    public void setxBoard(int xBoard) {
        this.xBoard = xBoard;
    }

    public void setxyBoard(int xBoard, int yBoard) {
        this.xBoard = xBoard;
        this.yBoard = yBoard;
    }

    public int getyBoard() {
        return yBoard;
    }

    public void setyBoard(int yBoard) {
        this.yBoard = yBoard;
    }


    /**
     * his method paints the board on the applet screen display.
     *
     * @param g2
     */
    public void paintBoard(Graphics2D g2) {
        //Coloring board bg
        g2.setColor(boardColor);
        g2.fillRoundRect(xBoard, yBoard, 400, 600, 80, 90);

        g2.setColor(boardColor2);
        g2.fillRoundRect(xBoard, yBoard, 405, 605, 80, 90);

        g2.setColor(boardColor);
        g2.fillRoundRect(xBoard, yBoard, 400, 600, 80, 90);

        //drawing dividing line and peg holes on the board
        for ( int i = 0; i < 14; i++ ) {
            //lines
            g2.setColor(new Color(160, 100, 70));
            g2.drawLine(525, 531 - 35 * (i - 2), 525 + 250, 531 - 35 * (i - 2));

            g2.setColor(new Color(120, 50, 20));
            g2.drawLine(525, 532 - 35 * (i - 2), 525 + 250, 532 - 35 * (i - 2));

            g2.setColor(new Color(160, 100, 70));
            g2.drawLine(525, 533 - 35 * (i - 2), 525 + 250, 533 - 35 * (i - 2));

            if ( i < 13 ) {

                //peg holes
                for ( int j = 0; j < 4; j++ ) {
                    g2.setColor(Color.gray);
                    g2.fillOval(535 + 70 * j, 540 - 35 * (i - 1), Peg.PEG_RADIOUS, Peg.PEG_RADIOUS);

                    g2.setColor(new Color(25, 30, 20));
                    g2.fillOval(540 + 70 * j, 545 - 35 * (i - 1), Peg.PEG_RADIOUS - 10, Peg.PEG_RADIOUS - 10);
                }

                //feedback peg boundary line
                g2.setColor(new Color(120, 50, 20));
                g2.fillRoundRect(790, 540 - 35 * (i - 1), 90, 20, 20, 90);

                g2.setColor(boardColor);
                g2.fillRoundRect(792, 542 - 35 * (i - 1), 86, 16, 20, 90);
            }
        }
    }
}

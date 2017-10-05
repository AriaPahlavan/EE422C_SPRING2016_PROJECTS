package Assignment7;

import java.awt.*;

import static Assignment7.GameColors.*;

/**
 * BonusProject_Mastermind
 * Created by Aria Pahlavan on May 2016.
 */
public class Board {

    private int xBoard;
    private int yBoard;



    public Board() {
        xBoard = 500;
        yBoard = 40;
    }

    public int getxBoard() {
        return xBoard;
    }

    public int getyBoard() {
        return yBoard;
    }

    /**
     * his method paints the board on the applet screen display.
     *
     * @param g2 Graphical display to paint on
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
        forEachRowPaint(g2, 0);
    }

    private void forEachRowPaint(Graphics2D g2, int i) {
        if ( i == 14 )
            return;
//        for ( int i = 0; i < 14; i++ ) {
            //lines
            g2.setColor(GameColors.lightCream);
            g2.drawLine(525, 531 - 35 * (i - 2), 525 + 250, 531 - 35 * (i - 2));

            g2.setColor(GameColors.darkCream);
            g2.drawLine(525, 532 - 35 * (i - 2), 525 + 250, 532 - 35 * (i - 2));

            g2.setColor(GameColors.lightCream);
            g2.drawLine(525, 533 - 35 * (i - 2), 525 + 250, 533 - 35 * (i - 2));

            if ( i < 13 ) {
                //peg holes
                forEachColumnPaint(g2, i, 0);

                //feedback peg boundary line
                g2.setColor(GameColors.darkCream);
                g2.fillRoundRect(790, 540 - 35 * (i - 1), 90, 20, 20, 90);

                g2.setColor(boardColor);
                g2.fillRoundRect(792, 542 - 35 * (i - 1), 86, 16, 20, 90);
            }
//        }
        forEachRowPaint(g2, i+1);
    }

    private void forEachColumnPaint(Graphics2D g2, int i, int j) {
        if ( j == 4)
            return;

        g2.setColor(Color.gray);
        g2.fillOval(535 + 70 * j, 540 - 35 * (i - 1), Peg.PEG_RADIOUS, Peg.PEG_RADIOUS);
        g2.setColor(GameColors.superDarkGreen);
        g2.fillOval(540 + 70 * j, 545 - 35 * (i - 1), Peg.PEG_RADIOUS - 10, Peg.PEG_RADIOUS - 10);

        forEachColumnPaint(g2, i, j+1);
    }
}

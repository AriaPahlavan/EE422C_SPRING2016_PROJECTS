package Assignment5;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Project5_Cars
 * Created by Aria Pahlavan on Apr 2016.
 */
public class Prompt {


    protected final Font START_FONT = new Font("DejaVu Sans, Bold", Font.BOLD + Font.ITALIC, 40);
    protected final Font START_SMALL_FONT = new Font("DejaVu Sans, Bold", Font.BOLD + Font.ITALIC, 20);

    protected final Font WINNER_FONT = new Font("Purisa", Font.BOLD, 40);
    protected final Font PROMPT_FONT = new Font("Purisa", Font.BOLD, 25);
    protected final Font SMALL_FONT = new Font("Purisa", Font.BOLD, 18);
    private ArrayList<Integer> winnersList = new ArrayList<>();
    private  int PROMPT_HEIGHT = 0;
    private  int PROMPT_WIDTH = 0;
    private boolean done = false;



    /**
     *
     * @param display
     * @param g2
     */
    public void startGame(A5Driver display, Graphics2D g2) {
        g2.setFont(START_FONT);
        g2.setColor(Color.cyan);


        //draw Prompt window
        g2.fillRoundRect(PROMPT_WIDTH, PROMPT_HEIGHT, 2 * PROMPT_WIDTH, 5 * PROMPT_HEIGHT, 20, 45);
        g2.setColor(new Color(14, 61, 58));
        g2.fillRoundRect(PROMPT_WIDTH + 10, PROMPT_HEIGHT + 10, 2 * PROMPT_WIDTH - 20, 5 * PROMPT_HEIGHT - 20, 20, 45);


        if ( PROMPT_WIDTH != 300 && PROMPT_HEIGHT != 100) {
            PROMPT_WIDTH  += 6;
            PROMPT_HEIGHT += 2;
        }
        else
            done = true;




        //Write game title
        g2.setColor(new Color(226, 45, 0));

        g2.drawString("Need For Speed: UT", (11 * PROMPT_WIDTH) / 10 + 25, (21 * PROMPT_HEIGHT) / 10);


        //Ask to press Enter or Space to start
        g2.setFont(START_SMALL_FONT);
        g2.setColor(Color.BLACK);

        g2.drawString("Press Enter or Space key to race...", (11 * PROMPT_WIDTH) / 10 + 60, (21 * PROMPT_HEIGHT) / 10 + 175);
    }


        /**
         * @param display
         * @param g2
         */
    public void announceWinner(A5Driver display, Graphics2D g2) {
        g2.setColor(Color.cyan);
        g2.fillRoundRect(PROMPT_WIDTH, PROMPT_HEIGHT, 2 * PROMPT_WIDTH, 5 * PROMPT_HEIGHT, 20, 45);
        g2.setColor(new A5Driver().BurntOrange);
        g2.fillRoundRect(PROMPT_WIDTH + 10, PROMPT_HEIGHT + 10, 2 * PROMPT_WIDTH - 20, 5 * PROMPT_HEIGHT - 20, 20, 45);


        //draw the car number
        g2.setColor(new Color(4, 41, 19));
        g2.setFont(WINNER_FONT);

        //Winner announcement
        g2.drawString("Car " + display.getWinner().getCarNum() + " Wins!!", (11 * PROMPT_WIDTH) / 10 + 125, (21 * PROMPT_HEIGHT) / 10);

        g2.setFont(PROMPT_FONT);
        //Performance time
        g2.drawString("Race Time:", (11 * PROMPT_WIDTH) / 10, (21 * PROMPT_HEIGHT) / 10 + 100);
        g2.drawString(new Long(display.getTimer().getElapsedTime()).toString(), PROMPT_WIDTH * 2, (21 * PROMPT_HEIGHT) / 10 + 100);
        g2.drawString(" milliseconds", (65 * PROMPT_WIDTH) / 30, (21 * PROMPT_HEIGHT) / 10 + 100);

        g2.setFont(SMALL_FONT);
        //Performance time
        getWinnersList(display);
        ArrayList<Integer> cars = new ArrayList<>(winnersList);
        for ( int i = 0; i < 5; i += 1)
            switch (i+1){
                case 1:
                    g2.drawString(i+1 + "st place: \t\tcar #" + cars.get(i), (11 * PROMPT_WIDTH) / 10, (21 * PROMPT_HEIGHT) / 10 + 150 +  i*40);
                    break;
                case 2:
                    g2.drawString(i+1 + "nd place: \t\tcar #" + cars.get(i), (11 * PROMPT_WIDTH) / 10, (21 * PROMPT_HEIGHT) / 10 + 150 +  i*40);
                    break;
                default:
                    g2.drawString(i+1 + "rd place: \t\tcar #" + cars.get(i), (11 * PROMPT_WIDTH) / 10, (21 * PROMPT_HEIGHT) / 10 + 150 +  i*40);


            }

    }


    public void getWinnersList(A5Driver display) {
        CarDrawer[] temp = display.getMyCar();
        ArrayList<CarXcoordinates> xCoordinates = new ArrayList<>();

        CarXcoordinates t;
        for ( int i = 0; i < temp.length; i += 1 ) {
            t = new CarXcoordinates();
            t.setCar(temp[i]);
            xCoordinates.add(t);
        }
        xCoordinates.trimToSize();


        Collections.sort(xCoordinates);
        for ( CarXcoordinates car : xCoordinates ) {
            winnersList.add(car.getCarNumber());
        }

    }


    /**
     * @param g
     */
    public void paint(A5Driver display, Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        announceWinner(display, g2);

    } // end of paint

}

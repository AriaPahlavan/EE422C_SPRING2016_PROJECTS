package Assignment5;

import java.awt.*;
import java.util.ArrayList;

/**
 * Project5_Cars
 * Created by Aria Pahlavan on Apr 2016.
 */
public class Prompt {

    protected final Font WINNER_FONT = new Font("Purisa", Font.BOLD, 40);
    protected final Font PROMPT_FONT = new Font("Purisa", Font.BOLD, 25);
    protected final Font SMALL_FONT = new Font("Purisa", Font.BOLD, 10);
    private CarDrawer winnersList[] = new CarDrawer[5];
    private final int PROMPT_HEIGHT = 100;
    private final int PROMPT_WIDTH = 300;


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
        g2.setColor(new Color(4,41,19));

        g2.setFont(WINNER_FONT);
        //Winner announcement
        g2.drawString("Car " + display.getWinner().getCarNum() + " Wins!!", (11 * PROMPT_WIDTH) / 10 + 125, (21 * PROMPT_HEIGHT) / 10);

        g2.setFont(PROMPT_FONT);
        //Performance time
        g2.drawString("Time elapsed:", (11 * PROMPT_WIDTH) / 10, (21 * PROMPT_HEIGHT) / 10 + 100);
        g2.drawString(new Long(display.getTimer().getElapsedTime()).toString(), PROMPT_WIDTH * 2, (21 * PROMPT_HEIGHT) / 10+ 100);
        g2.drawString(" milliseconds", (65*PROMPT_WIDTH)/30, (21 * PROMPT_HEIGHT) / 10+ 100);

        g2.setFont(SMALL_FONT);
        //Performance time
        CarDrawer[] winners = getWinnersList(display);
        g2.drawString("Time elapsed:", (11 * PROMPT_WIDTH) / 10, (21 * PROMPT_HEIGHT) / 10 + 100);

    }


    public CarDrawer[] getWinnersList(A5Driver display){
        CarDrawer[] temp = display.getMyCar();
        CarDrawer winner = display.getWinner();
        ArrayList<Integer> xCoordinates = new ArrayList<>();

        for (int i = 0; i < temp.length; i+=1){
            xCoordinates.add(temp[i].getxCar());
        }

        xCoordinates.trimToSize();


        return null;
    }

    private void swapCars(CarDrawer[] list, int i, int j){

    }

    /**
     * @param g
     */
    public void paint(A5Driver display, Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        announceWinner(display, g2);

    } // end of paint

}

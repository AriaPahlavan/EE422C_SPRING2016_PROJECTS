package Assignment7;

import java.awt.*;
import java.util.ArrayList;

/**
 * Project5_Cars
 * Created by Aria Pahlavan on Apr 2016.
 */
public class Prompt {
    //Member data
    protected final Font START_FONT = new Font("DejaVu Sans, Bold", Font.BOLD + Font.ITALIC, 40);
    protected final Font START_SMALL_FONT = new Font("DejaVu Sans, Bold", Font.BOLD + Font.ITALIC, 20);
    protected final Font WINNER_FONT = new Font("Purisa", Font.BOLD, 40);
    protected final Font PROMPT_FONT = new Font("Purisa", Font.BOLD, 25);
    protected final Font SMALL_FONT = new Font("Purisa", Font.BOLD, 18);
    private ArrayList<Integer> winnersList = new ArrayList<>();
    private int PROMPT_HEIGHT = 100;
    private int PROMPT_WIDTH = 300;


    private boolean done = false;

    //Getters and Setters
    public void setPROMPT_HEIGHT(int PROMPT_HEIGHT) {
        this.PROMPT_HEIGHT = PROMPT_HEIGHT;
    }

    public void setPROMPT_WIDTH(int PROMPT_WIDTH) {
        this.PROMPT_WIDTH = PROMPT_WIDTH;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    /**
     * This method prompts the user to the isntructions on how to start the race.
     *
     * @param g2
     */
    public void startGame(Graphics2D g2) {

        //draw Prompt window
        g2.setFont(START_FONT);
        g2.setColor(new Color(14, 61, 58));
        g2.fillRoundRect(PROMPT_WIDTH , PROMPT_HEIGHT, 2 * PROMPT_WIDTH + 125, 5 * PROMPT_HEIGHT, 100, 90);
        g2.setColor(new Color(0, 203, 160));
        g2.fillRoundRect(PROMPT_WIDTH , PROMPT_HEIGHT , 2 * PROMPT_WIDTH - 5 + 125, 5 * PROMPT_HEIGHT - 12, 80, 90);


        //Write game title
        g2.setColor(new Color(226, 45, 0));

        g2.drawString("HELLO MASTERMIND!!", (11 * PROMPT_WIDTH) / 10 + 125, (21 * PROMPT_HEIGHT) / 10);


        //Ask to press Enter or Space to start
        g2.setFont(START_SMALL_FONT);
        g2.setColor(Color.BLACK);

        g2.drawString("Press Enter or Space key to play...", (11 * PROMPT_WIDTH) / 10 + 110, (21 * PROMPT_HEIGHT) / 10 + 90);
        g2.drawString("Press Backspace or Delete to undo last peg.", (11 * PROMPT_WIDTH) / 10 + 110, (21 * PROMPT_HEIGHT) / 10 + 130);
        g2.drawString("Press Enter to place your new guess pegs", (11 * PROMPT_WIDTH) / 10 + 110, (21 * PROMPT_HEIGHT) / 10 + 160);
        g2.drawString("in corresponding holes to be checked.", (11 * PROMPT_WIDTH) / 10 + 110, (21 * PROMPT_HEIGHT) / 10 + 190);
        g2.drawString("Press Q if you want to give up trying.", (11 * PROMPT_WIDTH) / 10 + 110, (21 * PROMPT_HEIGHT) / 10 + 230);
        g2.drawString("", (11 * PROMPT_WIDTH) / 10 + 110, (21 * PROMPT_HEIGHT) / 10 + 270);
        g2.drawString("", (11 * PROMPT_WIDTH) / 10 + 110, (21 * PROMPT_HEIGHT) / 10 + 310);
        g2.drawString("", (11 * PROMPT_WIDTH) / 10 + 110, (21 * PROMPT_HEIGHT) / 10 + 350);

//        R, Y, O, P, G, B correspond to pegs of color
//        red, yellow, purple, green, blue, respectively
    }


    public void endGame(Graphics2D g2, String message, GameBoard myGameBoard) {
        g2.setFont(START_FONT);
        g2.setColor(Color.cyan);


        //draw Prompt window
        g2.fillRoundRect(PROMPT_WIDTH + 100, PROMPT_HEIGHT + 100, 2 * PROMPT_WIDTH, 5 * PROMPT_HEIGHT - 100, 20, 45);
        g2.setColor(new Color(14, 61, 58));
        g2.fillRoundRect(PROMPT_WIDTH + 10 + 100, PROMPT_HEIGHT + 110, 2 * PROMPT_WIDTH - 20, 5 * PROMPT_HEIGHT - 120, 20, 45);


        //Write game title
        g2.setColor(new Color(226, 45, 0));

        g2.drawString(message, (11 * PROMPT_WIDTH) / 10 + 125, (21 * PROMPT_HEIGHT) / 210 + 300);


        //Ask to press Enter or Space to start
        g2.setFont(START_SMALL_FONT);
        g2.setColor(Color.BLACK);

        if ( myGameBoard.isGuessMatch() )
            g2.drawString("Number of guesses: " +myGameBoard.getGuesses().size(), (11 * PROMPT_WIDTH) / 10 + 125, (21 * PROMPT_HEIGHT) / 10 + 200);

        g2.drawString("Press R to play again.", (11 * PROMPT_WIDTH) / 10 + 125, (21 * PROMPT_HEIGHT) / 10 + 260);


    }


//    /**
//     * This method prompts the user with the stats regarding to the previous race.
//     * @param display
//     * @param g2
//     */
//    public void announceWinner(A5Driver display, Graphics2D g2) {
//        g2.setColor(Color.cyan);
//        g2.fillRoundRect(PROMPT_WIDTH, PROMPT_HEIGHT, 2 * PROMPT_WIDTH, 5 * PROMPT_HEIGHT, 20, 45);
//        g2.setColor(new A5Driver().BurntOrange);
//        g2.fillRoundRect(PROMPT_WIDTH + 10, PROMPT_HEIGHT + 10, 2 * PROMPT_WIDTH - 20, 5 * PROMPT_HEIGHT - 20, 20, 45);
//
//        //Beginning animation
//        if ( PROMPT_WIDTH != 300 && PROMPT_HEIGHT != 100 ) {
//            PROMPT_WIDTH += 6;
//            PROMPT_HEIGHT += 2;
//        } else
//            done = true;
//
//        //End of animation, start prompting
//        if ( done ) {
//            //draw the car number
//            g2.setColor(new Color(4, 41, 19));
//            g2.setFont(WINNER_FONT);
//
//            //Winner announcement
//            g2.drawString("Car " + display.getWinner().getCarNum() + " Wins!!", (11 * PROMPT_WIDTH) / 10 + 125, (21 * PROMPT_HEIGHT) / 10);
//
//            g2.setFont(PROMPT_FONT);
//            //Performance time
//            g2.drawString("Race Time:", (11 * PROMPT_WIDTH) / 10, (21 * PROMPT_HEIGHT) / 10 + 100);
//            g2.drawString(new Long(display.getTimer().getElapsedTime()).toString(), PROMPT_WIDTH * 2, (21 * PROMPT_HEIGHT) / 10 + 100);
//            g2.drawString(" milliseconds", (65 * PROMPT_WIDTH) / 30, (21 * PROMPT_HEIGHT) / 10 + 100);
//
//            g2.setFont(SMALL_FONT);
//            //Performance time
//            getWinnersList(display);
//            ArrayList<Integer> cars = new ArrayList<>(winnersList);
//            for ( int i = 0; i < 5; i += 1 )
//                switch (i + 1) {
//                    case 1:
//                        g2.drawString(i + 1 + "st place: \t\tcar #" + cars.get(i), (11 * PROMPT_WIDTH) / 10, (21 * PROMPT_HEIGHT) / 10 + 150 + i * 40);
//                        break;
//                    case 2:
//                        g2.drawString(i + 1 + "nd place: \t\tcar #" + cars.get(i), (11 * PROMPT_WIDTH) / 10, (21 * PROMPT_HEIGHT) / 10 + 150 + i * 40);
//                        break;
//                    default:
//                        g2.drawString(i + 1 + "rd place: \t\tcar #" + cars.get(i), (11 * PROMPT_WIDTH) / 10, (21 * PROMPT_HEIGHT) / 10 + 150 + i * 40);
//                }
//        }
//    }
//
//
//    /**
//     * This method rearranges the racers from winner to the last place
//     * @param display
//     */
//    public void getWinnersList(A5Driver display) {
//        CarDrawer[] temp = display.getMyCar();
//        ArrayList<CarXcoordinates> xCoordinates = new ArrayList<>();
//
//        CarXcoordinates t;
//        for ( int i = 0; i < temp.length; i += 1 ) {
//            t = new CarXcoordinates();
//            t.setCar(temp[i]);
//            xCoordinates.add(t);
//        }
//        xCoordinates.trimToSize();
//
//
//        Collections.sort(xCoordinates);
//        for ( CarXcoordinates car : xCoordinates ) {
//            winnersList.add(car.getCarNumber());
//        }
//
//    }
//
//
//    /**
//     * This method paints the popups
//     * @param g
//     */
//    public void paint(A5Driver display, Graphics g) {
//        Graphics2D g2 = (Graphics2D) g;
//        announceWinner(display, g2);
//
//    } // end of paint

    /**
     * This method resets the prompt member variables
     */
    public void reset() {
        winnersList = new ArrayList<>();
        PROMPT_HEIGHT = 0;
        PROMPT_WIDTH = 0;
        done = false;
    }

}

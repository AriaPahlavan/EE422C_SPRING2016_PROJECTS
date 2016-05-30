package Assignment7;

import java.awt.*;
import java.util.ArrayList;

enum PopupStatus {
    INIT,
    RUN,
    DONE,
    RESET
}

/**
 * Project5_Cars
 * Created by Aria Pahlavan on Apr 2016.
 */
public class Prompt {
    //Member data
    protected final Font START_FONT = new Font("DejaVu Sans, Bold", Font.BOLD + Font.ITALIC, 40);
    protected final Font START_SMALL_FONT = new Font("DejaVu Sans, Bold", Font.BOLD + Font.ITALIC, 20);
    protected final Font START_BIG_FONT = new Font("DejaVu Sans, Bold", Font.BOLD + Font.ITALIC, 45);
    protected final Font START_BIGGER_FONT = new Font("DejaVu Sans, Bold", Font.BOLD + Font.ITALIC, 50);
    protected final Font WINNER_FONT = new Font("Purisa", Font.BOLD, 40);
    protected final Font PROMPT_FONT = new Font("Purisa", Font.BOLD, 25);
    protected final Font SMALL_FONT = new Font("Purisa", Font.BOLD, 18);
    private ArrayList<Integer> winnersList = new ArrayList<>();
    private double PROMPT_HEIGHT = 0.421272023;
    private double PROMPT_WIDTH = 1.26381607;
    private int X_AXIS = 300;
    private int X_instruction = 800;
    private int Y_instruction = 220;
    private boolean done = false;
    private boolean inprogress = true;
    private PopupStatus status = PopupStatus.INIT;


    //Getters and Setters
    public void setPROMPT_HEIGHT(double PROMPT_HEIGHT) {
        this.PROMPT_HEIGHT = PROMPT_HEIGHT;
    }

    public void setPROMPT_WIDTH(double PROMPT_WIDTH) {
        this.PROMPT_WIDTH = PROMPT_WIDTH;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public boolean isInprogress() {
        return inprogress;
    }

    public PopupStatus getStatus() {
        return status;
    }

    public void setStatus(PopupStatus status) {
        this.status = status;
    }

    public void setInprogress(boolean inprogress) {
        this.inprogress = inprogress;
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
        g2.fillRoundRect(X_AXIS, (int) PROMPT_HEIGHT, 2 * (int) PROMPT_WIDTH + 125, 5 * (int) PROMPT_HEIGHT, 100, 90);
        g2.setColor(new Color(0, 203, 160));
        g2.fillRoundRect(X_AXIS, (int) PROMPT_HEIGHT, 2 * (int) PROMPT_WIDTH - 5 + 125, 5 * (int) PROMPT_HEIGHT - 12, 80, 90);

        //Beginning animation
        if ( status == PopupStatus.INIT ) {
            PROMPT_WIDTH *= 1.2;
            PROMPT_HEIGHT *= 1.2;

            if ( (int) PROMPT_WIDTH == 300 || (int) PROMPT_HEIGHT == 100 ) {

                status = PopupStatus.RUN;
                PROMPT_WIDTH = 300;
                PROMPT_HEIGHT = 100;
            }
        }


        if ( status == PopupStatus.RUN ) {

            //Write game title
            g2.setColor(new Color(226, 45, 0));
            g2.drawString("HELLO MASTERMIND!!", (11 * (int) PROMPT_WIDTH) / 10 + 125, (21 * (int) PROMPT_HEIGHT) / 10 - 25);


            //Ask to press Enter or Space to start
            g2.setFont(START_SMALL_FONT);
            g2.setColor(Color.BLACK);

            g2.drawString("Press Enter or Space key to play...", (11 * (int) PROMPT_WIDTH) / 10 + 110, (21 * (int) PROMPT_HEIGHT) / 10 + 50);
            g2.drawString("Press Backspace or Delete to undo last peg.", (11 * (int) PROMPT_WIDTH) / 10 + 110, (21 * (int) PROMPT_HEIGHT) / 10 + 80);
            g2.drawString("Press Enter to place your new guess pegs in ", (11 * (int) PROMPT_WIDTH) / 10 + 110, (21 * (int) PROMPT_HEIGHT) / 10 + 110);
            g2.drawString("corresponding holes to be checked.", (11 * (int) PROMPT_WIDTH) / 10 + 160, (21 * (int) PROMPT_HEIGHT) / 10 + 140);
            g2.drawString("Press Q if you want to give up trying.", (11 * (int) PROMPT_WIDTH) / 10 + 110, (21 * (int) PROMPT_HEIGHT) / 10 + 170);

            Color[] colors = { Color.red, Peg.PURPLE, Color.blue, Color.YELLOW, Peg.ORANGE, Color.green };
            char[] colorInitials = { 'R', 'P', 'B', 'Y', 'O', 'G' };

            for ( int i = 0; i < 6; i += 2 ) {
                g2.setColor(Color.black);
                g2.drawString(colorInitials[i] + " <->", (11 * (int) PROMPT_WIDTH) / 10 + 110, (21 * (int) PROMPT_HEIGHT) / 10 + 230 + 40 * (i / 2));
                g2.setColor(colors[i]);
                g2.fillOval((11 * (int) PROMPT_WIDTH) / 10 + 190, (21 * (int) PROMPT_HEIGHT) / 10 + 215 + 40 * (i / 2), 20, 20);


                g2.setColor(Color.black);
                g2.drawString(colorInitials[i + 1] + " <->", (11 * (int) PROMPT_WIDTH) / 10 + 410, (21 * (int) PROMPT_HEIGHT) / 10 + 230 + 40 * (i / 2));
                g2.setColor(colors[i + 1]);
                g2.fillOval((11 * (int) PROMPT_WIDTH) / 10 + 490, (21 * (int) PROMPT_HEIGHT) / 10 + 215 + 40 * (i / 2), 20, 20);
            }
        }

        //closing animation
        if ( status == PopupStatus.DONE ) {
            PROMPT_WIDTH /= 1.7;
            PROMPT_HEIGHT /= 1.7;

            System.out.println("1) Width: " + (int) PROMPT_WIDTH);
            System.out.println("2) Height: " + (int) PROMPT_HEIGHT);
            if ( (int) PROMPT_WIDTH == 0 ) {

                status = PopupStatus.RESET;
            }

        }

    }


    /**
     * This method prompts the user the instructions for playing the game properly.
     *
     * @param g2
     */
    public void instructions(Graphics2D g2) {
        int PROMPT_WIDTH = 300;
        int PROMPT_HEIGHT = 100;
        //Draw bg box
        g2.setColor(new Color(117, 151, 101));
        g2.fillRoundRect(X_instruction + 155, Y_instruction + 30, PROMPT_WIDTH - 20, PROMPT_HEIGHT + 180, 80, 90);

        //Ask to press Enter or Space to start
        g2.setFont(START_SMALL_FONT);
        g2.setColor(Color.BLACK);

        g2.drawString("\"Backspace\" to undo", (11 * X_instruction) / 10 + 110, (21 * PROMPT_HEIGHT) / 10 + 80);
        g2.drawString("\"Enter\" to match ", (11 * X_instruction) / 10 + 110, (21 * PROMPT_HEIGHT) / 10 + 110);
        g2.drawString("\"Q\" to give up", (11 * X_instruction) / 10 + 110, (21 * PROMPT_HEIGHT) / 10 + 140);

        Color[] colors = { Color.red, Peg.PURPLE, Color.blue, Color.YELLOW, Peg.ORANGE, Color.green };
        char[] colorInitials = { 'R', 'P', 'B', 'Y', 'O', 'G' };

        for ( int i = 0; i < 6; i += 2 ) {
            g2.setColor(Color.black);
            g2.drawString(colorInitials[i] + " > ", (11 * X_instruction) / 10 + 110, (21 * PROMPT_HEIGHT) / 10 + 210 + 40 * (i / 2));
            g2.setColor(colors[i]);
            g2.fillOval((11 * X_instruction) / 10 + 155, (21 * PROMPT_HEIGHT) / 10 + 195 + 40 * (i / 2), 20, 20);


            g2.setColor(Color.black);
            g2.drawString(colorInitials[i + 1] + " > ", (11 * X_instruction) / 10 + 240, (21 * PROMPT_HEIGHT) / 10 + 210 + 40 * (i / 2));
            g2.setColor(colors[i + 1]);
            g2.fillOval((11 * X_instruction) / 10 + 285, (21 * PROMPT_HEIGHT) / 10 + 195 + 40 * (i / 2), 20, 20);
        }
    }


    /**
     * Prompts the result of the game!
     *
     * @param g2
     * @param message
     * @param myGameBoard
     */
    public void endGame(Graphics2D g2, String message, GameBoard myGameBoard) {


        g2.setFont(START_BIG_FONT);
        g2.setColor(new Color(0, 102, 28));


        //draw Prompt window
        g2.fillRoundRect((int) PROMPT_WIDTH - 220, (int) PROMPT_HEIGHT + 100, 2 * (int) PROMPT_WIDTH - 200, 5 * (int) PROMPT_HEIGHT - 100 - 50, 80, 90);
        g2.setColor(new Color(104, 198, 97));
        g2.fillRoundRect((int) PROMPT_WIDTH - 220, (int) PROMPT_HEIGHT + 100, 2 * (int) PROMPT_WIDTH - 5 - 200, 5 * (int) PROMPT_HEIGHT - 112 - 50, 80, 90);


        //Beginning animation
        if ( status == PopupStatus.INIT ) {
            PROMPT_WIDTH *= 1.141;
            PROMPT_HEIGHT *= 1.113;



            if ( (int) PROMPT_WIDTH == 290 || (int) PROMPT_HEIGHT == 99 ) {

                status = PopupStatus.RUN;
                PROMPT_WIDTH = 300;
                PROMPT_HEIGHT = 100;
            }
        }


        if ( status == PopupStatus.RUN ) {

            //Write game title
            g2.setColor(new Color(226, 45, 0));

            if ( myGameBoard.isGuessMatch() ) {
                g2.drawString("Congrats!", (11 * (int) PROMPT_WIDTH) / 10 - 170, (21 * (int) PROMPT_HEIGHT) / 210 + 280);
                g2.drawString("You won :)", (11 * (int) PROMPT_WIDTH) / 10 - 170, (21 * (int) PROMPT_HEIGHT) / 210 + 330);

            } else {
                g2.setFont(START_BIGGER_FONT);
                g2.drawString(message, (11 * (int) PROMPT_WIDTH) / 10 - 190, (21 * (int) PROMPT_HEIGHT) / 210 + 330);
            }

            //Ask to press Enter or Space to start
            g2.setFont(PROMPT_FONT);
            g2.setColor(Color.BLACK);

            if ( myGameBoard.isGuessMatch() )
                g2.drawString("Number of guesses: " + myGameBoard.getGuesses().size(), (11 * (int) PROMPT_WIDTH) / 10 - 220, (21 * (int) PROMPT_HEIGHT) / 10 + 200);

            g2.drawString("Press R to play again.", (11 * (int) PROMPT_WIDTH) / 10 - 220, (21 * (int) PROMPT_HEIGHT) / 10 + 250);

        }

        //closing animation
        if ( status == PopupStatus.DONE ) {
            PROMPT_WIDTH /= 1.141;
            PROMPT_HEIGHT /= 1.113;

            if ( (int) PROMPT_HEIGHT == 0 ) {
                status = PopupStatus.RESET;
            }

        }
    }


    /**
     * This method resets the prompt member variables
     */
    public void reset() {
        PROMPT_HEIGHT = 1;
        PROMPT_WIDTH = 1;
        done = false;
    }

}

package Assignment7;

import com.google.common.collect.ImmutableMap;

import java.awt.*;
import java.util.Map;

import static Assignment7.GameColors.peg_orange;
import static Assignment7.GameColors.peg_purple;
import static Assignment7.GameFonts.*;
import static Assignment7.PopupStatus.*;

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
class Prompt {
    private double promptHeight = 0.421272023;
    private double promptWidth = 1.26381607;
    private final static int X_AXIS = 300;
    private final static int X_INSTRUCTION = 800;
    private final static int Y_INSTRUCTION = 220;
    private PopupStatus status = INIT;
    final static String START = "start";
    final static String END = "end";
    private final static Color[] colors = { Color.red, peg_purple, Color.blue, Color.YELLOW, peg_orange, Color.green };
    private final static char[] colorInitials = { 'R', 'P', 'B', 'Y', 'O', 'G' };
    private final static Map<Integer, String> welcomeMsg = ImmutableMap.of(
            1, "Press Enter or Space to play...",
            2, "Press Backspace or Delete to undo last peg",
            3, "Press Enter to check your next guess ",
            4, "Press S to change the settings",
            5, "Press Q if you want to give up trying"
    );

    private void setPromptHeight(double promptHeight) {
        this.promptHeight = promptHeight;
    }

    private void setPromptWidth(double promptWidth) {
        this.promptWidth = promptWidth;
    }

    PopupStatus getStatus() {
        return status;
    }

    void setStatus(PopupStatus status) {
        this.status = status;
    }

    /**
     * This method prompts the user to the isntructions on how to start the race.
     *
     * @param g2
     * @param state
     */
    void startGamePopup(Graphics2D g2, String state) {
        drawPromptWindow(g2, state);
        beginningAnimation(state);

        if ( status == RUN ) { printPromptMessage(g2); }
        if ( status == DONE ) { closetingAnimation(state); }
    }

    /**
     * Prompts the result of the game!
     */
    void endGamePopup(Graphics2D g2, GameBoard myGameBoard, String state) {
        drawPromptWindow(g2, state);
        beginningAnimation(state);

        if ( status == RUN ) { printEndMsg(g2, myGameBoard); }
        if ( status == DONE ) { closetingAnimation(state); }
    }

    /**
     * This method prompts the user the instructions for playing the game properly.
     */
    void instructions(Graphics2D g2) {
        int PROMPT_WIDTH = 300;
        int PROMPT_HEIGHT = 100;
        //Draw bg box
        g2.setColor(GameColors.yellowGreen);
        g2.fillRoundRect(X_INSTRUCTION + 155, Y_INSTRUCTION + 30, PROMPT_WIDTH - 20, PROMPT_HEIGHT + 180, 80, 90);

        //Ask to press Enter or Space to start
        g2.setFont(START_SMALL_FONT);
        g2.setColor(Color.BLACK);

        g2.drawString("\"Backspace\" to undo", (11 * X_INSTRUCTION) / 10 + 110, (21 * PROMPT_HEIGHT) / 10 + 80);
        g2.drawString("\"Enter\" to match ", (11 * X_INSTRUCTION) / 10 + 110, (21 * PROMPT_HEIGHT) / 10 + 110);
        g2.drawString("\"Q\" to give up", (11 * X_INSTRUCTION) / 10 + 110, (21 * PROMPT_HEIGHT) / 10 + 140);

        Color[] colors = { Color.red, peg_purple, Color.blue, Color.YELLOW, peg_orange, Color.green };
        char[] colorInitials = { 'R', 'P', 'B', 'Y', 'O', 'G' };

        for ( int i = 0; i < 6; i += 2 ) {
            g2.setColor(Color.black);
            g2.drawString(colorInitials[i] + " > ", (11 * X_INSTRUCTION) / 10 + 110, (21 * PROMPT_HEIGHT) / 10 + 210 + 40 * (i / 2));
            g2.setColor(colors[i]);
            g2.fillOval((11 * X_INSTRUCTION) / 10 + 155, (21 * PROMPT_HEIGHT) / 10 + 195 + 40 * (i / 2), 20, 20);


            g2.setColor(Color.black);
            g2.drawString(colorInitials[i + 1] + " > ", (11 * X_INSTRUCTION) / 10 + 240, (21 * PROMPT_HEIGHT) / 10 + 210 + 40 * (i / 2));
            g2.setColor(colors[i + 1]);
            g2.fillOval((11 * X_INSTRUCTION) / 10 + 285, (21 * PROMPT_HEIGHT) / 10 + 195 + 40 * (i / 2), 20, 20);
        }
    }

    /**
     * This method resets the prompt member variables
     */
    void reset() {
        promptHeight = 1;
        promptWidth = 1;
    }

    private void closetingAnimation(String state) {
        switch (state){
            case START:
                promptWidth /= 1.7;
                promptHeight /= 1.7;
                break;
            case END:
                promptWidth /= 1.141;
                promptHeight /= 1.113;
                break;
        }

        if ( (int) promptHeight == 0 ) { status = RESET; }
    }

    private void printEndMsg(Graphics2D g2, GameBoard myGameBoard) {
        g2.setColor(GameColors.orange);

        if ( myGameBoard.isGuessMatch() ) {
            g2.drawString("Congrats!", calcStartGameX() - 280, (21 * (int) promptHeight) / 210 + 280);
            g2.drawString("You won :)", calcStartGameX() - 280, (21 * (int) promptHeight) / 210 + 330);
        } else {
            g2.setFont(START_BIGGER_FONT);
            g2.drawString("You lost :(", calcStartGameX() - 300, (21 * (int) promptHeight) / 210 + 330);
        }

        //Ask to press Enter or Space to start
        g2.setFont(PROMPT_FONT);
        g2.setColor(Color.BLACK);

        if ( myGameBoard.isGuessMatch() )
            g2.drawString("Number of guesses: " + myGameBoard.getGuesses().size(), calcStartGameX() - 330, calcStartGameY(6) + 20);

        g2.drawString("Press R to play again.", calcStartGameX() - 330, calcStartGameY(8) + 10);
    }

    private void printPromptMessage(Graphics2D g2) {
        printMsgStart(g2);
    }

    private void drawPromptWindow(Graphics2D g2, String state) {
        switch (state) {
            case "start":
                g2.setFont(START_FONT);
                g2.setColor(GameColors.indigo);
                g2.fillRoundRect(X_AXIS, (int) promptHeight, 2 * (int) promptWidth + 125, 5 * (int) promptHeight, 100, 90);
                g2.setColor(GameColors.cyan);
                g2.fillRoundRect(X_AXIS, (int) promptHeight, 2 * (int) promptWidth - 5 + 125, 5 * (int) promptHeight - 12, 80, 90);
                break;
            case "end":
                g2.setFont(START_BIG_FONT);
                g2.setColor(GameColors.darkGreen);
                g2.fillRoundRect((int) promptWidth - 220, (int) promptHeight + 100, 2 * (int) promptWidth - 200, 5 * (int) promptHeight - 100 - 50, 80, 90);
                g2.setColor(GameColors.lightGreen);
                g2.fillRoundRect((int) promptWidth - 220, (int) promptHeight + 100, 2 * (int) promptWidth - 5 - 200, 5 * (int) promptHeight - 112 - 50, 80, 90);
                break;
        }

    }

    private void printWelcome(Graphics2D g2) {
        printWelcomeRec(g2, 5);
    }

    private void printWelcomeRec(Graphics2D g2, int num) {
        if ( num == 0 ) {
            return;
        }

        g2.drawString(welcomeMsg.get(num), calcStartGameX(), calcStartGameY(num));
        printWelcomeRec(g2, num - 1);
    }

    private int calcStartGameY(int count) {
        return (21 * (int) promptHeight) / 10 + (30 * count);
    }

    private int calcStartGameX() {
        return (11 * (int) promptWidth) / 10 + 110;
    }

    private void beginningAnimation(String state) {
        if ( status == INIT ) {
            updatePromptDim(state);

            if ( checkPromptDim(state) ) {
                status = RUN;
                promptWidth = 300;
                promptHeight = 100;
            }
        }
    }

    private void updatePromptDim(String state) {
        if ( state.equals(START) ) {
            promptWidth *= 1.2;
            promptHeight *= 1.2;
        } else if ( state.equals(END) ) {
            promptWidth *= 1.141;
            promptHeight *= 1.113;
        }
    }

    private boolean checkPromptDim(String state) {
        boolean result = false;
        switch (state) {
            case START:
                result = (int) promptWidth == 300 || (int) promptHeight == 100;
                break;
            case END:
                result = (int) promptWidth == 290 || (int) promptHeight == 99;
                break;
        }
        return result;
    }

    private void printMsgStart(Graphics2D g2) {
        g2.setColor(GameColors.orange);
        g2.drawString("HELLO MASTERMIND!!", calcStartGameX() - 30, calcStartGameY(-1));

        g2.setFont(START_SMALL_FONT);
        g2.setColor(Color.BLACK);

        printWelcome(g2);
        printColorCodes(g2);
    }

    private void printColorCodes(Graphics2D g2) {
        printColorCodeRec(g2, 5);
    }

    private void printColorCodeRec(Graphics2D g2, int i) {
        if ( i < 0 ) {
            return;
        }

        printColors(g2, i, 0);
        printColors(g2, i - 1, 300);

        printColorCodeRec(g2, i - 2);
    }

    private void printColors(Graphics2D g2, int i, int x) {
        g2.setColor(Color.black);
        g2.drawString(colorInitials[i] + " <->", calcStartGameX() + x, calcColorCodeY(i, 4));
        g2.setColor(colors[i]);
        g2.fillOval(calcStartGameX() + x + 80, calcColorCodeY(i, 1), 20, 20);
    }

    private int calcColorCodeY(int i, int four) {
        return calcStartGameY(7) + 5 * four + 40 * (i / 2);
    }

    void resetPromptDim() {
        setPromptHeight(0.421272023);
        setPromptWidth(1.26381607);
    }
}

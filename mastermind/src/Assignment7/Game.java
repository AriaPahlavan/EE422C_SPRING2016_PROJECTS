package Assignment7;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import static Assignment7.GameColors.*;
import static Assignment7.GameStatus.*;
import static Assignment7.Prompt.END;
import static Assignment7.Prompt.START;

enum GameStatus {
    NOT_STARTED,
    LOST,
    WON,
    IN_PROGRESS
}

/**
 * Bonus Project - Mastermind Game
 * Created by Aria Pahlavan on Apr 2016.
 */
public class Game extends Applet implements Runnable, KeyListener, ActionListener {
    private final static boolean isDebugMode = false;
    private final int ABS_MAX_GUESS = 13;
    static int MAX_GUESS = 13;
    private boolean isPromptDisabled = false;
    private boolean keyPressed = true;
    private boolean isOver = false;
    private Graphics doubleG;
    private Image image;
    private GameBoard myGameBoard;
    private Prompt prompt;
    private Guess tempGuess;
    private ArrayList<RoundPegColor> tempPegs = null;
    private GameStatus status = GameStatus.NOT_STARTED;
    private TextField maxGuessTxt;
    private Label name;
    private Button ok_button;
    private Button cancel_button;

    private void configureTempGuess() {
        tempGuess.makeGuess(tempPegs);
        for ( int i = 0; i < 4; i += 1 ) {
            GuessPeg currPeg = tempGuess.getGuess()[i];
            int guessNum = myGameBoard.getGuesses().size() - 1;

            if ( currPeg == null )
                break;

            currPeg.setxyPeg(565 + 50 * i, 525 - 35 * (guessNum));
        }
    }

    private void disablePopup() {
        isPromptDisabled = true;

        if ( status == NOT_STARTED )
            status = IN_PROGRESS;
    }

    private boolean isPopupDone(KeyEvent e) {
        return (status == NOT_STARTED && ( e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_ENTER ))
                || ( (status == LOST || status == WON) && e.getKeyCode() == KeyEvent.VK_R );
    }

    private void keyPressedDuringGame(KeyEvent e) {
        if ( isGuessRegisterTime(e) ) { registerGuess(); }                          // register guess
        if ( isUndo(e) ) { tempPegs.remove(tempPegs.size() - 1); }                  // undo previous peg
        if ( e.getKeyCode() == KeyEvent.VK_Q ) { userLost(); }                   // take the loss
        if ( isUpdateGuessTime() ) { addColoredPegs(e); }                           // update guesses
        if ( e.getKeyCode() == KeyEvent.VK_ESCAPE ) { this.requestFocus(); }        // request focus
    }

    private void registerGuess() {
        configureTempGuess();
        tempPegs.clear();
        myGameBoard.addGuess(tempGuess);
        tempGuess = new Guess();
    }

    private boolean isGuessRegisterTime(KeyEvent e) {
        return e.getKeyCode() == KeyEvent.VK_ENTER && tempPegs.size() == 4;
    }

    private void addColoredPegs(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_B:
                tempPegs.add(RoundPegColor.blue);
                break;
            case KeyEvent.VK_R:
                tempPegs.add(RoundPegColor.red);
                break;
            case KeyEvent.VK_G:
                tempPegs.add(RoundPegColor.green);
                break;
            case KeyEvent.VK_P:
                tempPegs.add(RoundPegColor.purple);
                break;
            case KeyEvent.VK_O:
                tempPegs.add(RoundPegColor.orange);
                break;
            case KeyEvent.VK_Y:
                tempPegs.add(RoundPegColor.yellow);
                break;
        }
    }

    private boolean isUpdateGuessTime() {
        return tempPegs.size() < 4
                && status == IN_PROGRESS;
    }

    private boolean isUndo(KeyEvent e) {
        return (e.getKeyCode() == KeyEvent.VK_BACK_SPACE || e.getKeyCode() == KeyEvent.VK_DELETE)
                && !tempPegs.isEmpty();
    }

    private void reset() {
        tempGuess = new Guess();
        tempPegs = new ArrayList<>();
        myGameBoard = new GameBoard();

        if ( isPromptDisabled ) {
            status = IN_PROGRESS;
        } else
            status = NOT_STARTED;

        keyPressed = true;
        resetPrompt();
        isOver = false;
    }

    private void resetPrompt() {
        prompt.resetPromptDim();
        prompt.setStatus(PopupStatus.INIT);
    }

    private void updateMaxGuess(int newMaxGuess) {
        MAX_GUESS = newMaxGuess;
        myGameBoard.setMAX_GUESS(MAX_GUESS);
        prompt.setStatus(PopupStatus.DONE);
        disableSettings();
        maxGuessTxt.setForeground(Color.black);

        if ( status == IN_PROGRESS ) { reset(); }
    }

    private void enableSettings() {
        maxGuessTxt.setFocusable(true);
        ok_button.setFocusable(true);
        cancel_button.setFocusable(true);
        name.setVisible(true);
        maxGuessTxt.setVisible(true);
        ok_button.setVisible(true);
        cancel_button.setVisible(true);
        this.setFocusable(false);
    }

    private void disableSettings() {
        name.setFocusable(false);
        maxGuessTxt.setFocusable(false);
        ok_button.setFocusable(false);
        cancel_button.setFocusable(false);
        name.setVisible(false);
        maxGuessTxt.setVisible(false);
        ok_button.setVisible(false);
        cancel_button.setVisible(false);
        this.setFocusable(true);
    }

    private void paintBoardWithElements(Graphics2D g2) {
        myGameBoard.paintGameBoard(g2);
        tempGuess.displayTempGuess(g2);
        tempGuess.reset();

        uncoverAnswerDebug(g2);
    }

    private void uncoverAnswerDebug(Graphics2D g2) {
        if ( !isDebugMode ) {
            g2.setColor(new Color(205, 133, 63));
            g2.fillRoundRect(522, 80, 350, 60, 10, 360);
        }
    }

    private void startGame(Graphics2D g2) {
        prompt.startGamePopup(g2, START);
        if ( prompt.getStatus() == PopupStatus.RESET ) { resetPromptAndStartGame(); }
    }

    private void endGame(Graphics2D g2, GameBoard myGameBoard) {
        prompt.endGamePopup(g2, myGameBoard, END);

        //reset the prompt parameters and start the game
        if ( prompt.getStatus() == PopupStatus.RESET ) {
            prompt.setStatus(PopupStatus.INIT);
            reset();
        }
    }

    private void resetPromptAndStartGame() {
        prompt.setStatus(PopupStatus.INIT);
        status = IN_PROGRESS;
        keyPressed = true;
    }

    @Override
    public void init() {
        //initializing the background images and threads
        this.setSize(1400, 700);
        addKeyListener(this);
        this.setFocusable(true);
        this.setBackground(lightBlack);

        //initializing txt, btn and lbl
        maxGuessTxt = new TextField(5);
        name = new Label("Number of guesses allowed: ");
        ok_button = new Button("Update");
        cancel_button = new Button("Cancel");
        maxGuessTxt.setText(Integer.toString(MAX_GUESS));

        add(name);
        add(maxGuessTxt);
        add(ok_button);
        add(cancel_button);

        maxGuessTxt.addActionListener(this);
        ok_button.addActionListener(this);
        cancel_button.addActionListener(this);
    }

    @Override
    public void run() {
        while ( true ) {

            //repaint only if the user is interacting with game
            if ( keyPressed || status != IN_PROGRESS ) {

                //if use won
                if ( myGameBoard.isGuessMatch() ) {
                    status = WON;
                    if ( !isOver ) {
                        prompt.reset();
                        isOver = true;
                    }
                }
                //if the user hasn't guessed right and reached max # of guesses
                else if ( myGameBoard.getGuesses().size() == MAX_GUESS ) {
                    userLost();
                }

                //When the Enter or Space key is pressed, start the game
                if ( status == IN_PROGRESS ) {

                    //Game has started and correct guess was not entered!
                    configureTempGuess();
                }

                repaint();

                keyPressed = false;
            }
            try {
                Thread.sleep(17);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void userLost() {
        status = LOST;
        if ( !isOver ) {
            prompt.reset();
            isOver = true;
        }
    }

    @Override
    public void start() {
        //setting up the game!
        tempGuess = new Guess();
        tempPegs = new ArrayList<>();
        prompt = new Prompt();
        myGameBoard = new GameBoard();
        prompt = new Prompt();
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void update(Graphics g) {
        //improve graphics and decrease the flickering
        if ( image == null ) {
            image = createImage(this.getWidth(), this.getHeight());
            doubleG = image.getGraphics();
        }

        doubleG.setColor(getBackground());
        doubleG.fillRect(0, 0, this.getWidth(), this.getHeight());

        doubleG.setColor(getForeground());
        paint(doubleG);

        g.drawImage(image, 0, 0, this);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        // While closing a popup
        if ( prompt.getStatus() != PopupStatus.DONE )
            if ( (status == WON || status == LOST) ) { myGameBoard.paintGameBoard(g2); }
            else if ( status == IN_PROGRESS ) { paintBoardWithElements(g2); }

        switch (status){
            case NOT_STARTED:                                       // start game
                if ( !isPromptDisabled) { startGame(g2); }
                break;
            case IN_PROGRESS:                                       // prompt instructions
                prompt.instructions(g2);
                break;
            case WON:                                               // prompt win
                endGame(g2, myGameBoard);
                break;
            case LOST:                                              // prompt loss
                endGame(g2, myGameBoard);
                break;
            default:
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if ( isPopupDone(e) ) { prompt.setStatus(PopupStatus.DONE); }           // popup is done
        if ( status == IN_PROGRESS ) { keyPressedDuringGame(e); }               // key pressed and game in progress
        if ( e.getKeyCode() == KeyEvent.VK_D ) { disablePopup(); }              // disable popups
        if ( e.getKeyCode() == KeyEvent.VK_E ) { isPromptDisabled = false; }    // enable popups
        if ( e.getKeyCode() == KeyEvent.VK_S ) { enableSettings(); }            // enable settings
        if ( e.getKeyCode() == KeyEvent.VK_ESCAPE ) { disableSettings(); }      // disable settings

        keyPressed = true;

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ( e.getActionCommand().equals("Cancel") ) {
            disableSettings();
            this.requestFocus();
        }
        else
            try {
                int temp = Integer.parseInt(maxGuessTxt.getText());

                if ( temp <= ABS_MAX_GUESS && temp > 0 ) { updateMaxGuess(temp); }
                else { maxGuessTxt.setForeground(Color.red); }
            }
            catch (Exception ignored) { /* do nothing */ }
            finally { maxGuessTxt.setForeground(Color.red); }
    }
}

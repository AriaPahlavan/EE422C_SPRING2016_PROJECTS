package Assignment7;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

/**
 * BonusProject_Mastermind
 * Created by Aria Pahlavan on Apr 2016.
 */
public class Game extends Applet implements Runnable, KeyListener {
    private Graphics doubleG;
    private Image image;
    private GameBoard myGameBoard;
    private boolean isStarted;
    private Guess tempGuess;
    private ArrayList<RoundPegColor> tempPegs;
    private boolean waiting = true;
    private int numTempGuess = 0;
    private final int MAX_GUESS = 12;
    private boolean isOver = false;


    @Override
    /**
     * This method initializes the background images and threads
     */
    public void init() {
        //initializing the background images and threads
        this.setSize(1400, 700);
        addKeyListener(this);

    }

    @Override
    /**
     * This method starts and initializes the cars, thread, and popups
     */
    public void start() {
        //setting up the game!
        tempGuess = new Guess();
        tempPegs = new ArrayList<>();

        myGameBoard = new GameBoard();
        myGameBoard.setxyBoard(500, 50);
        Thread thread = new Thread(this);
        thread.start();
//        winnerPrompt = new Prompt();
    }

    @Override
    /**
     * This method updates the is called over and over to do stuff life repainting the screen!
     */
    public void run() {
        while ( true ) {

            if ( myGameBoard.getGuesses().size() == MAX_GUESS )
                isOver = true;


            //while the guess is incorrect
            if ( !myGameBoard.isGuessMatch() ) {
                //When the Enter or Space key is pressed, start the game
                if ( isStarted ) {

                    //TODO what to do when they hit Enter
                    if ( waiting ) {
                        configureTempGuess();
                    }

                    repaint();
                }
            }
            //when the code is guessed
            else {
                //TODO what to do when they guessed the code
                repaint();
            }

            try {
                Thread.sleep(16);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void configureTempGuess() {
        tempGuess.makeGuess(tempPegs);
        for ( int i = 0; i < 4; i += 1 ) {
            GuessPeg currPeg = tempGuess.getGuess()[i];
            int guessNum = myGameBoard.getGuesses().size() - 1;

            if ( currPeg == null )
                break;

            currPeg.setxyPeg(540 + 50 * i, 575 - 35 * (guessNum));
        }
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

        if ( myGameBoard.isGuessMatch() || isOver ) {
            // Paint board and it's elements
            myGameBoard.paintBoard(g2);
        } else {
            // Paint board and it's elements
            myGameBoard.paintBoard(g2);
            tempGuess.displayTempGuess(g2);
            tempGuess.reset();
//            g2.setColor(new Color(205,133,63));
//            g2.fillRoundRect(522, 80, 350, 60, 10, 360);


        }

//        //Displaying the time
//        String elapsedTime = new Integer((int) (timer.getElapsedTime() * 0.001)).toString();
//        g2.setFont(TIMER_FONT);
//        g2.setColor(Color.lightGray);
//        g2.drawString("Time: ", getWidth() - 550, 50);
//        g2.drawString(elapsedTime, getWidth() - 350, 50);
//        g2.drawString(" seconds", getWidth() - 335, 50);
//        g2.setColor(new Color(255, 0, 43));
//        g2.drawString("Time: ", getWidth() - 550 - 1, 50 + 2);
//        g2.drawString(elapsedTime, getWidth() - 350 - 1, 50 + 2);
//        g2.drawString(" seconds", getWidth() - 335 - 1, 50 + 2);

//        //popup before starting the race
//        if ( !isPromptDisabled )
//            if ( !isStarted ) {
//                winnerPrompt.startGame(g2);
//            } else {
//                if ( !isFinished() ) {
//
//                    winnerPrompt.setPROMPT_HEIGHT(0);
//                    winnerPrompt.setPROMPT_WIDTH(0);
//                    winnerPrompt.setDone(false);
//                }
//            }
//
//        //popup for game stats
//        if ( isFinished() )
//            winnerPrompt.paint(this, g);
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        //Notifies that user wants to start the race
        if ( e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_ENTER) {
            isStarted = true;
        }

        // Register guess
        if ( e.getKeyCode() == KeyEvent.VK_ENTER && tempPegs.size() == 4 ) {
            if ( !isOver &&  !myGameBoard.isGuessMatch() ) {
                configureTempGuess();
                tempPegs.clear();
                myGameBoard.addGuess(tempGuess);
                tempGuess = new Guess();
            }
            else {
                reset();
            }
        }

        //Notifies that user wants to start the race
        if ( e.getKeyCode() == KeyEvent.VK_BACK_SPACE || e.getKeyCode() == KeyEvent.VK_DELETE ) {
            if ( tempPegs.size() > 0 ) {
                tempPegs.remove(tempPegs.size() - 1);
            }
        }

        //update guess
        if ( tempPegs.size() < 4 && waiting && !isOver && isStarted && !(e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_ENTER) ) {
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
//            numTempGuess+=1;
        }

//
//        //Disabling popup!
//        if ( e.getKeyCode() == KeyEvent.VK_N )
//            isPromptDisabled = true;
//
//
//        //Enabling popup!
//        if ( e.getKeyCode() == KeyEvent.VK_E )
//            isPromptDisabled = false;
//
//        //S for Sport!! ;)
//
//        if ( e.getKeyCode() == KeyEvent.VK_S )
//            isSport = true;

    }

    private void reset() {
        tempGuess = new Guess();
        tempPegs = new ArrayList<>();

        myGameBoard = new GameBoard();
        myGameBoard.setxyBoard(500, 50);

        isStarted = true;
        waiting = true;
        numTempGuess = 0;
        isOver = false;
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

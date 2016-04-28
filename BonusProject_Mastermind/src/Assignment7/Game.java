package Assignment7;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * BonusProject_Mastermind
 * Created by Aria Pahlavan on Apr 2016.
 */
public class Game extends Applet implements Runnable, KeyListener {
    private Graphics doubleG;
    private Image image;
    private GameBoard myGameBoard;
    private boolean isStarted;


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
        myGameBoard = new GameBoard();
        myGameBoard.setxyBoard(500, 100);
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
            //while the guess is incorrect
            if ( !myGameBoard.isGuessMatch() ) {
                //When the Enter or Space key is pressed, start the game
                if ( isStarted ) {

                    //TODO what to do when they hit Enter
                }
            }
            //when the code is guessed
            else {
                //TODO what to do when they guessed the code
            }

            repaint();
            try {
                Thread.sleep(16);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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

        // Paint board and it's elements
        myGameBoard.paintBoard(g2);


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
        if ( e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_ENTER ) {
            isStarted = true;
        }

//        //Resets the race
//        if ( e.getKeyCode() == KeyEvent.VK_R && isFinished() )
//            reset();
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

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

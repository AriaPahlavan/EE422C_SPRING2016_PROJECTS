package Assignment7;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

enum GameStatus {
    NOT_STRTD,
    LOST,
    WON,
    IN_PRGRSS
}

/**
 * BonusProject_Mastermind
 * Created by Aria Pahlavan on Apr 2016.
 */
public class Game extends Applet implements Runnable, KeyListener {
    private Graphics doubleG;
    private Image image;
    private GameBoard myGameBoard;
    private Guess tempGuess;
    private ArrayList<RoundPegColor> tempPegs;
    private final int MAX_GUESS = 2;
    private boolean isPromptDisabled = false;
    private Prompt prompt;
    private boolean keyPressed = true;
    private GameStatus status = GameStatus.NOT_STRTD;
    private boolean debugMode = true;
    private boolean promptInstructions = false;
    private boolean isPromptUsed = false;

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
        prompt = new Prompt();

        myGameBoard = new GameBoard();
        myGameBoard.getGameBoard().setxyBoard(500, 40);
        Thread thread = new Thread(this);
        thread.start();
        prompt = new Prompt();
    }

    @Override
    /**
     * This method updates the is called over and over to do stuff life repainting the screen!
     */
    public void run() {
        while ( true ) {

            //repaint only if the user is interacting with game
            if ( keyPressed || status != GameStatus.IN_PRGRSS || promptInstructions ) {

                //if use won
                if ( myGameBoard.isGuessMatch() ) {
                    status = GameStatus.WON;
                }
                //if the user hasn't guessed right and reached max # of guesses
                else if ( myGameBoard.getGuesses().size() == MAX_GUESS ) {
                    status = GameStatus.LOST;
                }

                //When the Enter or Space key is pressed, start the game
                if ( status == GameStatus.IN_PRGRSS ) {

                    //Game has started and correct guess was not entered!
                    configureTempGuess();
                }

                repaint();

                keyPressed = false;
            }
            try {
                Thread.sleep(30);
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

            currPeg.setxyPeg(565 + 50 * i, 525 - 35 * (guessNum));
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

        if ( status == GameStatus.WON || status == GameStatus.LOST ) {
            // Paint board and it's elements
            myGameBoard.paintGameBoard(g2);
        } else if ( status != GameStatus.NOT_STRTD || isPromptDisabled ) {
            // Paint board and it's elements and guess
            myGameBoard.paintGameBoard(g2);
            tempGuess.displayTempGuess(g2);
            tempGuess.reset();

            //code cover
            if ( !debugMode ) {
                g2.setColor(new Color(205, 133, 63));
                g2.fillRoundRect(522, 80, 350, 60, 10, 360);
            }
        }

        //popup before starting the race
        if ( !isPromptDisabled )
            if ( status == GameStatus.NOT_STRTD ) {
                prompt.startGame(g2);
            }

        if ( isPromptUsed ) {
            prompt.setPROMPT_HEIGHT(0);
            prompt.setPROMPT_WIDTH(0);
            prompt.setDone(false);
            isPromptUsed = false;
        }


        if ( status == GameStatus.IN_PRGRSS )
            if ( promptInstructions ) {
                prompt.setPROMPT_HEIGHT(100);
                prompt.setPROMPT_WIDTH(300);
                this.prompt.instructions(g2);
            }

        //popup for game stats
        if ( status == GameStatus.WON ) {
            prompt.endGame(g2, "Congrats! You won :)", myGameBoard);
        } else if ( status == GameStatus.LOST ) {
            prompt.endGame(g2, "You lost :(", myGameBoard);
        }
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {


        if ( status == GameStatus.IN_PRGRSS ) {

            // Register guess
            if ( e.getKeyCode() == KeyEvent.VK_ENTER && tempPegs.size() == 4 ) {
                configureTempGuess();
                tempPegs.clear();
                myGameBoard.addGuess(tempGuess);
                tempGuess = new Guess();
            }

            //undo prev guess peg entered by user
            if ( e.getKeyCode() == KeyEvent.VK_BACK_SPACE || e.getKeyCode() == KeyEvent.VK_DELETE ) {
                if ( tempPegs.size() > 0 ) {
                    tempPegs.remove(tempPegs.size() - 1);
                }
            }

            //update guess
            if ( tempPegs.size() < 4 && status == GameStatus.IN_PRGRSS && !(e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_ENTER) ) {
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

            //To give up
            if ( e.getKeyCode() == KeyEvent.VK_Q ) {
                status = GameStatus.LOST;

            }

            if ( e.getKeyCode() == KeyEvent.VK_H ) {
                promptInstructions = !promptInstructions;
                if ( promptInstructions == false )
                    isPromptUsed = true;
            }
        }

        //Notifies that user wants to start the race
        if ( status == GameStatus.NOT_STRTD )
            if ( e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_ENTER ) {
                status = GameStatus.IN_PRGRSS;
                isPromptUsed = true;
            }

        //reset after game's over
        if ( status == GameStatus.LOST || status == GameStatus.WON )
            if ( e.getKeyCode() == KeyEvent.VK_R )
                reset();


        //Disabling popup!
        if ( e.getKeyCode() == KeyEvent.VK_D ) {
            isPromptDisabled = true;

            if ( status == GameStatus.NOT_STRTD )
                status = GameStatus.IN_PRGRSS;
        }

        //Enabling popup!
        if ( e.getKeyCode() == KeyEvent.VK_E )
            isPromptDisabled = false;


        keyPressed = true;

    }

    private void reset() {
        tempGuess = new Guess();
        tempPegs = new ArrayList<>();

        myGameBoard = new GameBoard();
        myGameBoard.getGameBoard().setxyBoard(500, 50);

        if ( isPromptDisabled ) {
            status = GameStatus.IN_PRGRSS;
        } else
            status = GameStatus.NOT_STRTD;

        keyPressed = true;
        isPromptUsed = true;
        promptInstructions = false;
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

package Assignment7;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
public class Game extends Applet implements Runnable, KeyListener, ActionListener {
    private Graphics doubleG;
    private Image image;
    private GameBoard myGameBoard;
    private Guess tempGuess;
    private ArrayList<RoundPegColor> tempPegs;
    protected static int MAX_GUESS = 13;
    private boolean isPromptDisabled = false;
    private Prompt prompt;
    private boolean keyPressed = true;
    private GameStatus status = GameStatus.NOT_STRTD;
    private boolean debugMode = false;
    private boolean isOver = false;
    private TextField maxGuess;
    private Label name;
    private Button ok;


    @Override
    /**
     * This method initializes the background images and threads
     */
    public void init() {
        //initializing the background images and threads
        this.setSize(1400, 700);
        addKeyListener(this);

        //initializing txt, btn and lbl
        maxGuess = new TextField(5);
        name = new Label("Enter maximum number of guesses: ");
        ok = new Button("Update");

        maxGuess.setText(new Integer(MAX_GUESS).toString());
        add(name);
        add(maxGuess);
        add(ok);


        maxGuess.addActionListener(this);
        ok.addActionListener(this);


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
            if ( keyPressed || status != GameStatus.IN_PRGRSS ) {

                //if use won
                if ( myGameBoard.isGuessMatch() ) {
                    status = GameStatus.WON;
                    if ( !isOver ) {
                        prompt.reset();
                        isOver = true;
                    }
                }
                //if the user hasn't guessed right and reached max # of guesses
                else if ( myGameBoard.getGuesses().size() == MAX_GUESS ) {
                    status = GameStatus.LOST;
                    if ( !isOver ) {
                        prompt.reset();
                        isOver = true;
                    }
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
                Thread.sleep(17);
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

        //In the middle of closing a popup?
        if ( prompt.getStatus() != PopupStatus.DONE ) {

            //Game over?
            if ( (status == GameStatus.WON || status == GameStatus.LOST) ) {
                // Paint board and it's elements
                myGameBoard.paintGameBoard(g2);
            } else if ( status == GameStatus.IN_PRGRSS ) {
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
        }


        //popup before starting the race
        if ( !isPromptDisabled ) {
            if ( status == GameStatus.NOT_STRTD ) {


                //initialize, run and close the prompt
                prompt.startGame(g2);


                //reset the prompt parameters and start the game
                if ( prompt.getStatus() == PopupStatus.RESET ) {
                    prompt.setStatus(PopupStatus.INIT);
                    status = GameStatus.IN_PRGRSS;
                    keyPressed = true;
                }
            }
        }


        if ( status == GameStatus.IN_PRGRSS ) {
            this.prompt.instructions(g2);
        }


        //popup for game stats
        if ( status == GameStatus.WON ) {
            prompt.endGame(g2, "Congrats! You won :)", myGameBoard);

            //reset the prompt parameters and start the game
            if ( prompt.getStatus() == PopupStatus.RESET ) {
                prompt.setStatus(PopupStatus.INIT);
//                status = GameStatus.IN_PRGRSS;
//                keyPressed = true;
                reset();
            }

        } else if ( status == GameStatus.LOST ) {
            prompt.endGame(g2, "You lost :(", myGameBoard);

            //reset the prompt parameters and start the game
            if ( prompt.getStatus() == PopupStatus.RESET ) {
                prompt.setStatus(PopupStatus.INIT);
//                status = GameStatus.IN_PRGRSS;
//                keyPressed = true;
                reset();

            }
        }


    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {


        //When game in progress
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
        }


        //Notifies that user wants to start the race
        if ( status == GameStatus.NOT_STRTD )
            if ( e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_ENTER ) {
                prompt.setStatus(PopupStatus.DONE);
            }

        //reset after game's over
        if ( status == GameStatus.LOST || status == GameStatus.WON )
            if ( e.getKeyCode() == KeyEvent.VK_R ) {
                prompt.setStatus(PopupStatus.DONE);
            }


        //Disabling popup!
        if ( e.getKeyCode() == KeyEvent.VK_D ) {
            isPromptDisabled = true;

            if ( status == GameStatus.NOT_STRTD )
                status = GameStatus.IN_PRGRSS;
        }

        //Enabling popup!
        if ( e.getKeyCode() == KeyEvent.VK_E ) {
            isPromptDisabled = false;
        }

        if ( e.getKeyCode() == KeyEvent.VK_S) {
            //Display the settings
            enableSettings();
        }

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
        resetPrompt();
        isOver = false;
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public void resetPrompt() {
        prompt.setPROMPT_HEIGHT(0.421272023);
        prompt.setPROMPT_WIDTH(1.26381607);
        prompt.setDone(false);
        prompt.setStatus(PopupStatus.INIT);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            int temp = Integer.parseInt(maxGuess.getText());
            if ( temp <= 13 && temp > 0 ) {
                MAX_GUESS = temp;
                myGameBoard.setMAX_GUESS(MAX_GUESS);
                prompt.setStatus(PopupStatus.DONE);

                //Get rid of the settings
                disableSettings();
                maxGuess.setForeground(Color.black);

            } else {
                maxGuess.setForeground(Color.red);
            }

        } catch (Exception e1) {
            maxGuess.setForeground(Color.red);
        }
        finally {
            if ( status == GameStatus.IN_PRGRSS )
                reset();
        }
    }


    private void enableSettings(){
        maxGuess.setFocusable(true);
        name.setVisible(true);
        maxGuess.setVisible(true);
        ok.setVisible(true);
        this.setFocusable(false);
    }

    private void disableSettings(){
        name.setFocusable(false);
        maxGuess.setFocusable(false);
        ok.setFocusable(false);
        name.setVisible(false);
        maxGuess.setVisible(false);
        ok.setVisible(false);
        this.setFocusable(true);
    }
}

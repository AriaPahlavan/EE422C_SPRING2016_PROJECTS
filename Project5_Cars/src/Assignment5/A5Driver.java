package Assignment5;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;

/**
 * Project5_Cars
 * Created by Aria Pahlavan on Apr 2016.
 */
public class A5Driver extends Applet implements Runnable, KeyListener {

    protected final Color BurntOrange = new Color(204, 102, 0);
    private CarDrawer myCar[] = new CarDrawer[5];
    private Graphics doubleG;
    private Image image;
    private boolean isStarted = false;
    private boolean finished = false;
    private StopWatch timer = new StopWatch();
    protected final Font TIMER_FONT = new Font("Purisa", Font.BOLD, 30);
    private double xRaceTrack = 0;
    private double dxRaceTrack = 0;
    private Image raceTrack;
    private Prompt winnerPrompt;
    private CarDrawer winner = null;
    private boolean isPromptDisabled = false;
    private boolean isSport = false;

    public CarDrawer[] getMyCar() {
        return myCar;
    }

    public void setWinner(CarDrawer winner) {
        this.winner = winner;
    }

    public CarDrawer getWinner() {
        return winner;
    }

    public StopWatch getTimer() {
        return timer;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    @Override
    /**
     * This method initializes the background images and threads
     */
    public void init() {
        //initializing the background images and threads
        URL url;
        this.setSize(1400, 700);
        addKeyListener(this);
        try {
            url = A5Driver.class.getResource("/images/background.png");
            raceTrack = this.getImage(url);
        } catch (Exception e) {
        }
    }

    @Override
    /**
     * This method starts and initializes the cars, thread, and popups
     */
    public void start() {
        //setting up the game!
        myCar = new CarDrawer[5];

        for ( int i = 0; i < myCar.length; i += 1 )
            //x = 100;  y = 50, 170, 290, 410, 530
            myCar[i] = new CarDrawer(20, ((i) * 120) + 125, new Integer(i + 1).toString());
        Thread thread = new Thread(this);
        thread.start();
        winnerPrompt = new Prompt();
    }

    @Override
    /**
     * This method updates the is called over and over to do stuff life repainting the screen!
     */
    public void run() {
        while ( true ) {
            //while the race is happening
            if ( !finished ) {
                //When the Enter or Space key is pressed, start the race
                if ( isStarted ) {
                    timer.start();

                    for ( int i = 0; i < myCar.length; i += 1 )
                        myCar[i].updateCarPosition(this);
                    xRaceTrack -= dxRaceTrack;
                }
            }
            //when the race is done
            else {
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
//        g.setColor(new Color(15,77,147));
//        g.fillRect(0,0, getWidth(),getHeight());
        Graphics2D g2 = (Graphics2D) g;

        g2.drawImage(raceTrack, (int) xRaceTrack, 0, this);

        //Sport car or no??
        if ( isSport ) {
            //Displaying the cars
            for ( int i = 0; i < myCar.length; i += 1 ){
                myCar[i].paintSport(g2);
                if ( myCar[i].getDxCar() != 0 && !finished)
                    myCar[i].paintNitro(g2);
            }

        }
        else {
            //Displaying the cars
            for ( int i = 0; i < myCar.length; i += 1 )
                myCar[i].paint(g2);
        }

        //Displaying the time
        String elapsedTime = new Integer((int) (timer.getElapsedTime() * 0.001)).toString();
        g2.setFont(TIMER_FONT);
        g2.setColor(Color.lightGray);
        g2.drawString("Time: ", getWidth() - 550, 50);
        g2.drawString(elapsedTime, getWidth() - 350, 50);
        g2.drawString(" seconds", getWidth() - 335, 50);
        g2.setColor(new Color(255, 0, 43));
        g2.drawString("Time: ", getWidth() - 550 - 1, 50 + 2);
        g2.drawString(elapsedTime, getWidth() - 350 - 1, 50 + 2);
        g2.drawString(" seconds", getWidth() - 335 - 1, 50 + 2);

        //popup before starting the race
        if ( !isPromptDisabled )
            if ( !isStarted ) {
                winnerPrompt.startGame(g2);
            } else {
                if ( !isFinished() ) {

                    winnerPrompt.setPROMPT_HEIGHT(0);
                    winnerPrompt.setPROMPT_WIDTH(0);
                    winnerPrompt.setDone(false);
                }
            }

        //popup for game stats
        if ( isFinished() )
            winnerPrompt.paint(this, g);
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

        //Resets the race
        if ( e.getKeyCode() == KeyEvent.VK_R && isFinished() )
            reset();

        //Disabling popup!
        if ( e.getKeyCode() == KeyEvent.VK_N )
            isPromptDisabled = true;


        //Enabling popup!
        if ( e.getKeyCode() == KeyEvent.VK_E )
            isPromptDisabled = false;

        //S for Sport!! ;)

        if ( e.getKeyCode() == KeyEvent.VK_S )
            isSport = !isSport;


    }


    @Override
    public void keyReleased(KeyEvent e) {
    }

    /**
     * This method resets all classes to initial state
     */
    public void reset() {
        timer.reset();
        winner = null;
        finished = false;
        isStarted = false;
        winnerPrompt.reset();
        myCar = new CarDrawer[5];
        for ( int i = 0; i < myCar.length; i += 1 )
            myCar[i] = new CarDrawer(20, ((i) * 120) + 125, new Integer(i + 1).toString());
        winnerPrompt = new Prompt();

    }


}

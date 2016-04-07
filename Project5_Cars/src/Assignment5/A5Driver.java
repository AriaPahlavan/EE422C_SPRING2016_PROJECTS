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
    protected final Font TIMER_FONT = new Font("Purisa", Font.BOLD, 15);
    private double xRaceTrack = 0;
    private double dxRaceTrack = 0;
    private Image raceTrack;
    private Prompt winnerPrompt;
    private CarDrawer winner = null;

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
    public void init() {
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
    public void start() {

        for ( int i = 0; i < myCar.length; i += 1 )
            //x = 100;  y = 50, 170, 290, 410, 530
            myCar[i] = new CarDrawer(20, ((i) * 120) + 125, new Integer(i + 1).toString());
        Thread thread = new Thread(this);
        thread.start();
        winnerPrompt = new Prompt();
    }

    @Override
    public void run() {
        while ( true ) {
//            winnerPrompt.announceWinner(this);

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

        //Displaying the cars
        for ( int i = 0; i < myCar.length; i += 1 )
            myCar[i].paint(g2);

        //Displaying the time
        String elapsedTime = Long.toString(timer.getElapsedTime());
        g2.setFont(TIMER_FONT);
        g2.setColor(Color.lightGray);
        g2.drawString(elapsedTime, getWidth() - 250, 25);
        g2.drawString(" milliseconds", getWidth() - 200, 25);
        g2.setColor(new Color(2, 104, 70));
        g2.drawString(elapsedTime, getWidth() - 250 - 2, 25 + 2);
        g2.drawString(" milliseconds", getWidth() - 200 - 2, 25 + 2);

        if ( isFinished() )
            winnerPrompt.paint(this, g);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if ( e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_ENTER ) {
            isStarted = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}

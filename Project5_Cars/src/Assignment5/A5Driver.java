package Assignment5;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Project5_Cars
 * Created by Aria Pahlavan on Apr 2016.
 */
public class A5Driver extends Applet implements Runnable, KeyListener{

    private CarDrawer myCar[] = new CarDrawer[5];
    private Graphics doubleG;
    private Image image;
    private boolean start = false;

    @Override
    public void init() {
        this.setSize(1500, 900);
        addKeyListener(this);
    }

    @Override
    public void start() {
        for ( int i = 0; i < myCar.length; i += 1 )
            myCar[i] = new CarDrawer(100, ((i)*120) + 25, new Integer(i+1).toString());
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        while ( true ) {

            if ( start ) {
                for ( int i = 0; i < myCar.length; i += 1 )
                    myCar[i].updateCarPosition(this);

                repaint();
            }
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
        for ( int i = 0; i < myCar.length; i += 1 )
            myCar[i].paint(g);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if ( e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_ENTER){
            start = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

package Assignment5;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Project5_Cars
 * Created by Aria Pahlavan on Apr 2016.
 */
public class A5Driver extends Applet implements Runnable, KeyListener {

    private CarDrawer myCar;
    private Graphics doubleG;
    private Image image;

    @Override
    public void init() {
        this.setSize(600, 600);
        addKeyListener(this);
    }

    @Override
    public void start() {
        myCar = new CarDrawer();
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        while ( true ) {

            myCar.setxCar(myCar.getxCar() + 1);
            repaint();
            try {
                Thread.sleep(3);
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
        myCar.paint(g);
    }


    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyChar()){
            case KeyEvent.VK_A:
            case KeyEvent.VK_LEFT:
                myCar.moveLeft();
                break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:
                myCar.moveRight();
                break;

        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

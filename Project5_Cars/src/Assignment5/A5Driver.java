package Assignment5;

import java.applet.Applet;
import java.awt.*;

/**
 * Project5_Cars
 * Created by Aria Pahlavan on Apr 2016.
 */
public class A5Driver extends Applet {

    CarDrawer myCar;

    @Override
    public void init() {
        this.setSize(600, 600);
    }

    @Override
    public void start() {
        myCar = new CarDrawer();
    }

    @Override
    public void paint(Graphics g) {
        myCar.paint(g);
    }
}

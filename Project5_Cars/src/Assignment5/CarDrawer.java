package Assignment5;

/**
 * Project5_Cars
 * Created by Aria Pahlavan on Apr 2016.
 */

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

public class CarDrawer {

    //Member data
    private int xCar, yCar;
    private double dxCar;
    private final int SPEED_LIMIT = 200;
    protected Color BurntOrange = new Color(204, 102, 0);
    private final double dt = 0.25;
    private final double ACCELERATION = 50;
    private final double FRICTION = 0.9;
    private final int WIDTH_CAR = 60;
    private final int HEIGHT_CAR = 35;

    public CarDrawer() {
        this.xCar = 100;
        this.yCar = 100;
        this.dxCar = 0;
    }

    public CarDrawer(int xCar, int yCar, double dxCar) {
        this.xCar = xCar;
        this.yCar = yCar;
        this.dxCar = dxCar;
    }

    public double getDxCar() {
        return dxCar;
    }

    public void setDxCar(double dxCar) {
        this.dxCar = dxCar;
    }

    public int getxCar() {
        return xCar;
    }

    public void setxCar(int xCar) {
        this.xCar = xCar;
    }

    public int getyCar() {
        return yCar;
    }

    public void setyCar(int yCar) {
        this.yCar = yCar;
    }


    public void updateCarPosition(A5Driver display) {


        if ( xCar + dxCar > display.getWidth() - WIDTH_CAR - 1 ) {
            //The car has hit the right wall.
            //TODO Game Over: this car is the winner.
        }
        if ( xCar + dxCar <= WIDTH_CAR ) {
            //the car is behind the left wall, i.e. out of the screen
            xCar = 0 + WIDTH_CAR;
//            dxCar= -dxCar;
        }

        if ( Math.abs(dxCar) < 0.05 ) dxCar = 0;


        //displacement formula: i.e. d = v0*t + 1/2*a*t^2
        xCar += dxCar * dt + .5 * ACCELERATION * dt * dt;

        if ( dxCar != 0 ) {
            //Friction formula
            dxCar *= FRICTION;
            System.out.println("3)>>\t" + dxCar);
        }


    }

    /**
     * This method paints the car on the applet screen display.
     *
     * @param g
     */
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
// create the car body
        Rectangle body = new Rectangle(xCar, yCar + 10, 60, 10);
// create the car tires
        Ellipse2D.Double frontTire = new Ellipse2D.Double(xCar + 10, yCar + 20, 10, 10);
        g.fillOval(xCar + 10, yCar + 20, 10, 10);
        Ellipse2D.Double rearTire = new Ellipse2D.Double(xCar + 40, yCar + 20, 10, 10);
        g.fillOval(xCar + 40, yCar + 20, 10, 10);

// create the 4 points connecting the windshields and roof
        Point2D.Double r1 = new Point2D.Double(xCar + 10, yCar + 10);
        Point2D.Double r2 = new Point2D.Double(xCar + 20, yCar);
        Point2D.Double r3 = new Point2D.Double(xCar + 40, yCar);
        Point2D.Double r4 = new Point2D.Double(xCar + 50, yCar + 10);
// create the windshields and roof of the car
        Line2D.Double frontWindshield = new Line2D.Double(r1, r2);
        Line2D.Double roofTop = new Line2D.Double(r2, r3);
        Line2D.Double rearWindshield = new Line2D.Double(r3, r4);


// draw all of the car parts on the screen
        g2.draw(frontWindshield);
        g2.draw(rearWindshield);
        g2.draw(frontTire);
        g2.draw(rearTire);
        g2.setColor(BurntOrange);
        g2.fill(body);
        g2.draw(roofTop);
// draw the label under the car
        g2.setColor(Color.red);
        g2.drawString("UT JavaMobile 1.0", 100, 150);
    } // end of paint

    /**
     * This method changes dx so the car would start moving left
     */
    public void moveLeft() {
        if ( Math.abs(dxCar - (ACCELERATION * dt)) < SPEED_LIMIT )

            //velocity formula: i.e. v = v0 + a*t
            dxCar -= (ACCELERATION * dt);

    }// end of move left


    /**
     * This method changes dx so the car would start moving right
     */
    public void moveRight() {
        if ( dxCar + (ACCELERATION * dt) < SPEED_LIMIT ) {
            //velocity formula: i.e. v = v0 + a*t
            dxCar += ACCELERATION * dt;
            System.out.println("1)\t" + dxCar);
        }
    }//end of move right

    public void decelerate() {
//        if ( dxCar > 0 ) dxCar -= 2;
//        if ( dxCar < 0 ) dxCar += 2;
    }
} // end of CarDrawer
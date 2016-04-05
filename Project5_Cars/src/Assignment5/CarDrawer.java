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
    private final int SPEED_LIMIT = 100;
    protected Color BurntOrange = new Color(204, 102, 0);
    private final double dt = 0.2;
    private final double ACCELERATION = 140;
    private final double BACKUP_ACCELERATION = 50;
    private final double FRICTION = 0.6;
    private final int WIDTH_CAR = 60;
    private final int HEIGHT_CAR = 32;
    private final int BACKUP_SPEED_LIMIT = 20;
    private String carNum;

    public CarDrawer() {
        this.xCar = 100;
        this.yCar = 100;
        this.dxCar = 0;
    }

    public CarDrawer(int xCar, int yCar, String carNum) {
        this.xCar = xCar;
        this.yCar = yCar;
        this.carNum = carNum;
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

        if ( dxCar != 0 ) {
            //Friction formula
            dxCar *= FRICTION;
            System.out.println("3)>>\t" + dxCar);
        }

        if ( dxCar != 0 ) {
            if ( dxCar > 0 )
                //displacement formula: i.e. d = v0*t + 1/2*a*t^2
                xCar += dxCar * dt + .5 * ACCELERATION * dt * dt;
            else
                //displacement formula: i.e. d = v0*t + 1/2*a*t^2
                xCar += dxCar * dt - (.5 * BACKUP_ACCELERATION * dt * dt);
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
//        RoundRectangle2D body = new RoundRectangle2D.Double(xCar, yCar + 10, 60, 15, 20, 360);
        Rectangle body = new Rectangle(xCar,  (yCar + (5*HEIGHT_CAR)/16), WIDTH_CAR,  ((5*HEIGHT_CAR)/16));
// create the car tires
        Ellipse2D.Double frontTire = new Ellipse2D.Double(xCar + WIDTH_CAR / 6, yCar + (5*HEIGHT_CAR)/8,  WIDTH_CAR/5, (3 * HEIGHT_CAR)/8);
        Ellipse2D.Double frontRim = new Ellipse2D.Double(xCar + (13 * WIDTH_CAR) / 60, yCar + (23 * HEIGHT_CAR) / 32, WIDTH_CAR/10, (3 * HEIGHT_CAR)/16);

        Ellipse2D.Double rearTire = new Ellipse2D.Double(xCar + (2 * WIDTH_CAR) / 3, yCar + (5*HEIGHT_CAR)/8, 0.2 * WIDTH_CAR, (3 * HEIGHT_CAR)/8);
        Ellipse2D.Double rearRim = new Ellipse2D.Double(xCar + (43 * WIDTH_CAR) / 60, yCar + (23 * HEIGHT_CAR) / 32, 0.1 * WIDTH_CAR, (3 * HEIGHT_CAR)/16);

// create the 4 points connecting the windshields and roof
        Point2D.Double r1 = new Point2D.Double(xCar + WIDTH_CAR / 6, yCar + (5*HEIGHT_CAR)/16);
        Point2D.Double r2 = new Point2D.Double(xCar + WIDTH_CAR / 3, yCar);
        Point2D.Double r3 = new Point2D.Double(xCar + (2 * WIDTH_CAR) / 3, yCar);
        Point2D.Double r4 = new Point2D.Double(xCar + (5 * WIDTH_CAR) / 6, yCar + (5*HEIGHT_CAR)/16);
// create the windshields and roof of the car
        Line2D.Double frontWindshield = new Line2D.Double(r1, r2);
        Line2D.Double roofTop = new Line2D.Double(r2, r3);
        Line2D.Double rearWindshield = new Line2D.Double(r3, r4);


// draw all of the car parts on the screen
        if ( dxCar >= 0 ) {
            g2.setColor(Color.orange);
            g2.fillArc(xCar + (55 * WIDTH_CAR) / 60, yCar + (13*HEIGHT_CAR)/32, WIDTH_CAR/6, (5*HEIGHT_CAR)/32, 270, 180);
        } else {
            g2.setColor(Color.red);
            g2.fillArc(xCar - (5*WIDTH_CAR)/60, yCar + (13*HEIGHT_CAR)/32, WIDTH_CAR/6, (5*HEIGHT_CAR)/32, 90, 180);
        }
        g2.setColor(Color.black);
        g2.draw(frontWindshield);
        g2.draw(rearWindshield);
        g2.fill(frontTire);
        g2.fill(rearTire);
        g2.setColor(BurntOrange);
        g2.fill(body);
        g2.draw(roofTop);

        g2.setColor(Color.gray);
        g2.fill(frontRim);
        g2.fill(rearRim);
// draw the label under the car
        g2.setColor(Color.red);
        g2.drawString("UT JavaMobile 1.0", xCar, yCar + (25*HEIGHT_CAR)/16);

        //draw the car number
        g2.setColor(Color.black);
        g2.drawString(carNum, xCar + WIDTH_CAR/2, yCar + (5*HEIGHT_CAR)/8);

    } // end of paint

    /**
     * This method changes dx so the car would start moving left
     */
    public void moveLeft() {
        if ( Math.abs(dxCar - (BACKUP_ACCELERATION * dt)) < BACKUP_SPEED_LIMIT )

            //velocity formula: i.e. v = v0 + a*t
            dxCar -= (BACKUP_ACCELERATION * dt);

    }// end of move left


    /**
     * This method changes dx so the car would start moving right
     */
    public void moveRight() {
        if ( dxCar + (ACCELERATION * dt) < SPEED_LIMIT ) {
            //velocity formula: i.e. v = v0 + a*t
            dxCar += ACCELERATION * dt;
        }
    }//end of move right

} // end of CarDrawer
package Assignment5;

import java.awt.*;

/**
 * Project5_Cars
 * Created by Aria Pahlavan on Apr 2016.
 */
public class WinnerAnnouncer {


    /**
     *
     * @param display
     */
    public void announceWinner(A5Driver display) {
    }

    /**
     *
     * @param g
     */
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
// create the car body
////        RoundRectangle2D body = new RoundRectangle2D.Double(xCar, yCar + 10, 60, 15, 20, 360);
//        Rectangle body = new Rectangle(xCar, (int) (yCar + (5 * HEIGHT_CAR) / 16), (int) WIDTH_CAR, (int) ((5 * HEIGHT_CAR) / 16));
//// create the car tires
//        Ellipse2D.Double frontTire = new Ellipse2D.Double(xCar + (40 * WIDTH_CAR) / 60, yCar + (19 * HEIGHT_CAR) / 32, TIRE_RADIUS, TIRE_RADIUS);
//        Ellipse2D.Double frontRim = new Ellipse2D.Double(xCar + (43.5 * WIDTH_CAR) / 60, yCar + (22 * HEIGHT_CAR) / 32, RIM_RADIUS, RIM_RADIUS);
//
//
//        Ellipse2D.Double rearTire = new Ellipse2D.Double(xCar + (10 * WIDTH_CAR) / 60, yCar + (19 * HEIGHT_CAR) / 32, TIRE_RADIUS, TIRE_RADIUS);
//        Ellipse2D.Double rearRim = new Ellipse2D.Double(xCar + (13.5 * WIDTH_CAR) / 60, yCar + (22 * HEIGHT_CAR) / 32, RIM_RADIUS, RIM_RADIUS);
//
//// create the 4 points connecting the windshields and roof
//        Point2D.Double r1 = new Point2D.Double(xCar + WIDTH_CAR / 6, yCar + (5 * HEIGHT_CAR) / 16);
//        Point2D.Double r2 = new Point2D.Double(xCar + WIDTH_CAR / 3, yCar);
//        Point2D.Double r3 = new Point2D.Double(xCar + (2 * WIDTH_CAR) / 3, yCar);
//        Point2D.Double r4 = new Point2D.Double(xCar + (5 * WIDTH_CAR) / 6, yCar + (5 * HEIGHT_CAR) / 16);
//// create the windshields and roof of the car
//        Line2D.Double frontWindshield = new Line2D.Double(r1, r2);
//        Line2D.Double roofTop = new Line2D.Double(r2, r3);
//        Line2D.Double rearWindshield = new Line2D.Double(r3, r4);
//
//
//// draw all of the car parts on the screen
//        if ( dxCar >= 0 ) {
//            g2.setColor(Color.orange);
//            g2.fillArc(xCar + (int) ((55 * WIDTH_CAR) / 60), yCar + (int) ((13 * HEIGHT_CAR) / 32), (int) (WIDTH_CAR / 6), (int) ((5 * HEIGHT_CAR) / 32), 270, 180);
//        } else {
//            g2.setColor(Color.red);
//            g2.fillArc(xCar - (int) ((5 * WIDTH_CAR) / 60), yCar + (int) ((13 * HEIGHT_CAR) / 32), (int) (WIDTH_CAR / 6), (int) ((5 * HEIGHT_CAR) / 32), 90, 180);
//        }
//        g2.setColor(Color.black);
//        g2.draw(frontWindshield);
//        g2.draw(rearWindshield);
//        g2.fill(frontTire);
//        g2.fill(rearTire);
//        g2.setColor(BurntOrange);
//        g2.fill(body);
//        g2.draw(roofTop);
//
//        g2.setColor(Color.gray);
//        g2.fill(frontRim);
//        g2.fill(rearRim);
//// draw the label under the car
//        g2.setColor(Color.red);
//        g2.drawString("UT JavaMobile 1.0", xCar, (float) (yCar + (25 * HEIGHT_CAR) / 16));
//
//        //draw the car number
//        g2.setColor(Color.black);
//        g2.drawString(carNum, xCar + (int) (WIDTH_CAR / 2), yCar + (int) (5 * HEIGHT_CAR) / 8);

    } // end of paint

}

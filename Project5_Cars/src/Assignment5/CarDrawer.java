package Assignment5;

/**
 * Project5_Cars
 * Created by Aria Pahlavan on Apr 2016.
 */

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.Random;

public class CarDrawer {

    //Member data
    private int xCar, yCar;
    private double dxCar;
    private final int SPEED_LIMIT = 20;
    private double dt = 0.2;
    private double ACCELERATION = 80;
    private final double BACKUP_ACCELERATION = 50;
    private double FRICTION = 0.6;
    private final double WIDTH_CAR = 90;
    private final double HEIGHT_CAR = 62;
    private final double TIRE_RADIUS = ((31 - 19) * HEIGHT_CAR) / 32;
    private final double RIM_RADIUS = ((31 - 19 - 6) * HEIGHT_CAR) / 32;
    private final int BACKUP_SPEED_LIMIT = 20;
    private String carNum;
    private final Font CAR_FONT = new Font("Century SchoolBook L Bold", Font.ITALIC, (int) (WIDTH_CAR) / 6);

    //Constructors
    public CarDrawer() {
        this.xCar = 100;
        this.yCar = 100;
        this.dxCar = 0;
        Random random = new Random();
        for ( long rand2 = System.currentTimeMillis() % 31; rand2 > 0; rand2 -= 1 ) {
            random.nextInt();
        }

        //random value btwn 65 - 85
        this.ACCELERATION = random.nextInt(21) + 50;

        //random value btwn 0.15 - 0.25
        this.dt = (random.nextFloat() + 1.3) / 10;

        //random value btwn 0 - 0.2 + 0.45 - 0.65 = 0.45 - 0.85
        this.FRICTION = (random.nextInt(3) + (random.nextInt(3) + 4.5)) / 10;

    }

    public CarDrawer(int xCar, int yCar, String carNum) {
        this();
        this.xCar = xCar;
        this.yCar = yCar;
        this.carNum = carNum;
    }

    //Getters and Setters
    public String getCarNum() {
        return carNum;
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


    /**
     * This method updates the position of the car using the
     * acceleration, friction, and dt values (using actual mechanic physics formulas).
     *
     * @param display
     */
    public void updateCarPosition(A5Driver display) {
        moveRight();

        //this car is the winner car!!
        if ( xCar + dxCar > display.getWidth() - WIDTH_CAR - 56 ) {
            //The car has hit the right wall.
            xCar = (int) (display.getWidth() - WIDTH_CAR - 56);
            display.setFinished(true);
            display.getTimer().stop();
            display.setWinner(this);
        } else {
            if ( xCar + dxCar <= 0 ) {
                //the car is behind the left wall, i.e. out of the screen
                //Never gonna happen in this game!! :(
            }

            if ( Math.abs(dxCar) < 0.05 ) dxCar = 0;

            if ( dxCar != 0 ) {
                //Friction formula
                dxCar *= FRICTION;
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
    }

    /**
     * This method paints the car on the applet screen display.
     *
     * @param g2
     */
    public void paint(Graphics2D g2) {
// create the car body
//        RoundRectangle2D body = new RoundRectangle2D.Double(xCar, yCar + 10, 60, 15, 20, 360);
        Rectangle body = new Rectangle(xCar, (int) (yCar + (5 * HEIGHT_CAR) / 16), (int) WIDTH_CAR, (int) ((5 * HEIGHT_CAR) / 16));
// create the car tires
        Ellipse2D.Double frontTire = new Ellipse2D.Double(xCar + (40 * WIDTH_CAR) / 60, yCar + (19 * HEIGHT_CAR) / 32, TIRE_RADIUS, TIRE_RADIUS);


        Ellipse2D.Double rearTire = new Ellipse2D.Double(xCar + (10 * WIDTH_CAR) / 60, yCar + (19 * HEIGHT_CAR) / 32, TIRE_RADIUS, TIRE_RADIUS);

// create the 4 points connecting the windshields and roof
        Point2D.Double r1 = new Point2D.Double(xCar + WIDTH_CAR / 6, yCar + (5 * HEIGHT_CAR) / 16);
        Point2D.Double r2 = new Point2D.Double(xCar + WIDTH_CAR / 3, yCar);
        Point2D.Double r3 = new Point2D.Double(xCar + (2 * WIDTH_CAR) / 3, yCar);
        Point2D.Double r4 = new Point2D.Double(xCar + (5 * WIDTH_CAR) / 6, yCar + (5 * HEIGHT_CAR) / 16);
// create the windshields and roof of the car
        Line2D.Double frontWindshield = new Line2D.Double(r1, r2);
        Line2D.Double roofTop = new Line2D.Double(r2, r3);
        Line2D.Double rearWindshield = new Line2D.Double(r3, r4);


// draw all of the car parts on the screen

        g2.setColor(Color.black);
        g2.draw(frontWindshield);
        g2.draw(rearWindshield);
        g2.fill(frontTire);
        g2.fill(rearTire);
        g2.setColor(new A5Driver().BurntOrange);
        g2.fill(body);
        g2.draw(roofTop);

//// draw the label under the car
//        g2.setColor(Color.red);
//        g2.drawString("UT JavaMobile 1.0", xCar, (float) (yCar + (25 * HEIGHT_CAR) / 16));

        //draw the car number
        g2.setColor(Color.black);
        g2.setFont(CAR_FONT);
        g2.drawString(carNum, xCar + (int) (WIDTH_CAR / 2), yCar + (int) (18 * HEIGHT_CAR) / 32);

    } // end of paint

    /**
     * This method changes dx so the car would start moving left
     */
    public void moveLeft() {
        //Never gonna be used in this game :(
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


    public void paintSport(Graphics2D g2) {
// create the car body
//        RoundRectangle2D body = new RoundRectangle2D.Double(xCar, yCar + 10, 60, 15, 20, 360);
// create the car tires
        Ellipse2D.Double frontTire = new Ellipse2D.Double(xCar + (40 * WIDTH_CAR) / 60, yCar + (19 * HEIGHT_CAR) / 32, TIRE_RADIUS, TIRE_RADIUS);
        Ellipse2D.Double frontRim = new Ellipse2D.Double(xCar + (43.5 * WIDTH_CAR) / 60, yCar + (22 * HEIGHT_CAR) / 32, RIM_RADIUS, RIM_RADIUS);


        Ellipse2D.Double rearTire = new Ellipse2D.Double(xCar + (10 * WIDTH_CAR) / 60 - 5, yCar + (19 * HEIGHT_CAR) / 32, TIRE_RADIUS, TIRE_RADIUS);
        Ellipse2D.Double rearRim = new Ellipse2D.Double(xCar + (13.5 * WIDTH_CAR) / 60 - 5, yCar + (22 * HEIGHT_CAR) / 32, RIM_RADIUS, RIM_RADIUS);

// create the 4 points connecting the windshields and roof
        Point2D.Double r1 = new Point2D.Double(xCar + WIDTH_CAR / 6, yCar + (5 * HEIGHT_CAR) / 16);
        Point2D.Double r1_2F = new Point2D.Double(xCar + WIDTH_CAR / 6 + 1, yCar + (5 * HEIGHT_CAR) / 16);
        Point2D.Double r1_3F = new Point2D.Double(xCar + WIDTH_CAR / 6 - 1, yCar + (5 * HEIGHT_CAR) / 16);
        Point2D.Double r2 = new Point2D.Double(xCar + WIDTH_CAR / 3, yCar);
        Point2D.Double r2_2F = new Point2D.Double(xCar + WIDTH_CAR / 3 + 1, yCar);
        Point2D.Double r2_3F = new Point2D.Double(xCar + WIDTH_CAR / 3 - 1, yCar);
        Point2D.Double r3 = new Point2D.Double(xCar + (2 * WIDTH_CAR) / 3, yCar);
        Point2D.Double r2_2R = new Point2D.Double(xCar + WIDTH_CAR / 3, yCar + 1);
        Point2D.Double r3_2R = new Point2D.Double(xCar + (2 * WIDTH_CAR) / 3, yCar + 1);
        Point2D.Double r2_3R = new Point2D.Double(xCar + WIDTH_CAR / 3, yCar - 1);
        Point2D.Double r3_3R = new Point2D.Double(xCar + (2 * WIDTH_CAR) / 3, yCar - 1);
        Point2D.Double r3_2F = new Point2D.Double(xCar + (2 * WIDTH_CAR) / 3+1, yCar);
        Point2D.Double r3_3F = new Point2D.Double(xCar + (2 * WIDTH_CAR) / 3-1, yCar);
        Point2D.Double r4 = new Point2D.Double(xCar + (5 * WIDTH_CAR) / 6, yCar + (5 * HEIGHT_CAR) / 16);
        Point2D.Double r4_2F = new Point2D.Double(xCar + (5 * WIDTH_CAR) / 6+1, yCar + (5 * HEIGHT_CAR) / 16);
        Point2D.Double r4_3F = new Point2D.Double(xCar + (5 * WIDTH_CAR) / 6-1, yCar + (5 * HEIGHT_CAR) / 16);


// create the windshields and roof of the car
        Line2D.Double frontWindshield = new Line2D.Double(r1, r2);
        Line2D.Double frontWindshield2 = new Line2D.Double(r1_2F, r2_2F);
        Line2D.Double frontWindshield3 = new Line2D.Double(r1_3F, r2_3F);
        Line2D.Double rearWindshield = new Line2D.Double(r3, r4);
        Line2D.Double rearWindshield2 = new Line2D.Double(r3_2F, r4_2F);
        Line2D.Double rearWindshield3 = new Line2D.Double(r3_3F, r4_3F);
        Line2D.Double roofTop = new Line2D.Double(r2, r3);
        Line2D.Double roofTop2 = new Line2D.Double(r2_2R, r3_2R);
        Line2D.Double roofTop3 = new Line2D.Double(r2_3R, r3_3R);


// draw all of the car parts on the screen
        if ( dxCar >= 0 ) {
            g2.setColor(new Color(143, 226, 231));
            g2.fillArc(xCar + (int) ((55 * WIDTH_CAR) / 60), yCar + (int) ((13 * HEIGHT_CAR) / 32), (int) (WIDTH_CAR / 6), (int) ((5 * HEIGHT_CAR) / 32), 270, 180);
        } else {
            g2.setColor(Color.red);
            g2.fillArc(xCar - (int) ((5 * WIDTH_CAR) / 60), yCar + (int) ((13 * HEIGHT_CAR) / 32), (int) (WIDTH_CAR / 6), (int) ((5 * HEIGHT_CAR) / 32), 90, 180);
        }
        g2.setColor(Color.black);
        g2.draw(frontWindshield);
        g2.draw(frontWindshield2);
        g2.draw(frontWindshield3);
        g2.draw(rearWindshield);
        g2.draw(rearWindshield2);
        g2.draw(rearWindshield3);
        g2.fill(frontTire);
        g2.fill(rearTire);
        g2.setColor(new A5Driver().BurntOrange);
        g2.fillRoundRect(xCar, (int) (yCar + (5 * HEIGHT_CAR) / 16), (int) WIDTH_CAR, (int) ((5 * HEIGHT_CAR) / 16), 10, 360);

        g2.draw(roofTop);
        g2.draw(roofTop2);
        g2.draw(roofTop3);

        //Sport feature!
        g2.setColor(Color.gray);
        g2.fill(frontRim);
        g2.fill(rearRim);


        //draw the car number
        g2.setColor(Color.black);
        g2.setFont(CAR_FONT);
        g2.drawString(carNum, xCar + (int) (WIDTH_CAR / 2), yCar + (int) (18 * HEIGHT_CAR) / 32);


    } // end of paint


    /**
     * Adds nitro effect!!
     * @param g2
     */
    public void paintNitro(Graphics2D g2) {
        Point2D.Double p1 = new Point2D.Double(xCar, (int) (yCar + (5 * HEIGHT_CAR) / 16) + 10);
        Point2D.Double p2 = new Point2D.Double(xCar, (int) (yCar + (5 * HEIGHT_CAR) / 16) + 15);
        Point2D.Double p3 = new Point2D.Double(xCar, (int) (yCar + (5 * HEIGHT_CAR) / 16) + 14);
        Point2D.Double p4 = new Point2D.Double(xCar, (int) (yCar + (5 * HEIGHT_CAR) / 16) + 13);
        Point2D.Double p5 = new Point2D.Double(xCar, (int) (yCar + (5 * HEIGHT_CAR) / 16) + 11);
        Point2D.Double p6 = new Point2D.Double(xCar, (int) (yCar + (5 * HEIGHT_CAR) / 16) + 12);
        Point2D.Double p7 = new Point2D.Double(xCar, (int) (yCar + (5 * HEIGHT_CAR) / 16) + 13);
        Point2D.Double p8 = new Point2D.Double(xCar, (int) (yCar + (5 * HEIGHT_CAR) / 16) + 11);
        Point2D.Double p9 = new Point2D.Double(xCar, (int) (yCar + (5 * HEIGHT_CAR) / 16) + 11);
        Point2D.Double p10 = new Point2D.Double(xCar, (int) (yCar + (5 * HEIGHT_CAR) / 16) + 12);
        Point2D.Double p11= new Point2D.Double(xCar, (int) (yCar + (5 * HEIGHT_CAR) / 16) + 13);
        Point2D.Double p12 = new Point2D.Double(xCar, (int) (yCar + (5 * HEIGHT_CAR) / 16) + 11);

        Point2D.Double n1 = new Point2D.Double(xCar - 20, (int) (yCar + (5 * HEIGHT_CAR) / 16) + 10);
        Point2D.Double n2 = new Point2D.Double(xCar - 16, (int) (yCar + (5 * HEIGHT_CAR) / 16) + 15);
        Point2D.Double n3 = new Point2D.Double(xCar - 10, (int) (yCar + (5 * HEIGHT_CAR) / 16) + 14);
        Point2D.Double n4 = new Point2D.Double(xCar - 18, (int) (yCar + (5 * HEIGHT_CAR) / 16) + 13);
        Point2D.Double n5 = new Point2D.Double(xCar - 8, (int) (yCar + (5 * HEIGHT_CAR) / 16) + 11);
        Point2D.Double n6 = new Point2D.Double(xCar - 15, (int) (yCar + (5 * HEIGHT_CAR) / 16) + 12);
        Point2D.Double n7 = new Point2D.Double(xCar - 11, (int) (yCar + (5 * HEIGHT_CAR) / 16) + 13);
        Point2D.Double n8 = new Point2D.Double(xCar - 16, (int) (yCar + (5 * HEIGHT_CAR) / 16) + 11);
        Point2D.Double n9 = new Point2D.Double(xCar - 20, (int) (yCar + (5 * HEIGHT_CAR) / 16) + 11);
        Point2D.Double n10 = new Point2D.Double(xCar - 15, (int) (yCar + (5 * HEIGHT_CAR) / 16) + 12);
        Point2D.Double n11 = new Point2D.Double(xCar - 18, (int) (yCar + (5 * HEIGHT_CAR) / 16) + 13);
        Point2D.Double n12 = new Point2D.Double(xCar - 17, (int) (yCar + (5 * HEIGHT_CAR) / 16) + 11);

        Line2D.Double nitroLine1 = new Line2D.Double(p1 , n2);
        Line2D.Double nitroLine2 = new Line2D.Double(p2 , n3);
        Line2D.Double nitroLine3 = new Line2D.Double(p3 , n5);
        Line2D.Double nitroLine4 = new Line2D.Double(p4 , n6);
        Line2D.Double nitroLine5 = new Line2D.Double(p5 , n8);
        Line2D.Double nitroLine6 = new Line2D.Double(p6 , n1);
        Line2D.Double nitroLine7 = new Line2D.Double(p7 , n4);
        Line2D.Double nitroLine8 = new Line2D.Double(p8 , n7);
        Line2D.Double nitroLine9 = new Line2D.Double(p10 , n9);
        Line2D.Double nitroLine10 = new Line2D.Double(p12 , n12);
        Line2D.Double nitroLine11 = new Line2D.Double(p11 , n11);
        Line2D.Double nitroLine12 = new Line2D.Double(p9 , n10);



        g2.setColor(new Color(163, 0, 44));
        g2.draw(nitroLine9);
        g2.setColor(new Color(196, 109, 30));
        g2.draw(nitroLine10);
        g2.setColor(new Color(166, 64, 0));
        g2.draw(nitroLine11);
        g2.setColor(new Color(223, 0, 7));
        g2.draw(nitroLine12);
        g2.setColor(new Color(0, 169, 193));
        g2.draw(nitroLine1);
        g2.setColor(new Color(129, 172, 198));
        g2.draw(nitroLine2);
        g2.setColor(new Color(0, 75, 134));
        g2.draw(nitroLine3);
        g2.setColor(new Color(0, 52, 134));
        g2.draw(nitroLine4);
        g2.setColor(new Color(90, 227, 231));
        g2.draw(nitroLine5);
        g2.setColor(new Color(0,0, 134));
        g2.draw(nitroLine6);
        g2.setColor(new Color(0, 59, 134));
        g2.draw(nitroLine7);
        g2.setColor(new Color(223, 0, 17));
        g2.draw(nitroLine12);
        g2.setColor(new Color(0, 89, 134));
        g2.draw(nitroLine8);
        g2.draw(nitroLine9);
        g2.setColor(new Color(182, 151, 0));



    }


    } // end of CarDrawer
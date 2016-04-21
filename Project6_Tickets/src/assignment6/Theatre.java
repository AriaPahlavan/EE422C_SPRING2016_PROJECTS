package assignment6;

import java.util.ArrayList;

/**
 * Project6_Tickets
 * Created by Aria Pahlavan on Apr 2016.
 */
public class Theatre {

    private TheaterHouse middle;
    private TheaterHouse houseRight;
    private TheaterHouse houseLeft;
    protected static int numberAvailableSeats;


    /**
     * Constructs a new theater with middle, right, and left houses of varying rows and widths
     * according to their theater house object constructors
     *
     * @see TheaterHouse.java for details
     */
    public Theatre() {
        this.middle = new TheaterHouse(HousePos.MIDDLE);
        this.houseRight = new TheaterHouse(HousePos.HOUSE_RIGHT);
        this.houseLeft = new TheaterHouse(HousePos.HOUSE_LEFT);
        this.numberAvailableSeats = this.middle.getAvaialbleSeats() + this.houseRight.getAvaialbleSeats() + this.houseLeft.getAvaialbleSeats();
    }

    /**
     * Gets the middle theater house of this theater
     *
     * @return the middle theater house
     */
    public TheaterHouse getMiddle() {
        return middle;
    }

    /**
     * Gets the right house of this theater
     *
     * @return the right house of this theater
     */
    public TheaterHouse getHouseRight() {
        return houseRight;
    }

    /**
     * gets the left house of this theater
     *
     * @return the left house of this theater
     */
    public TheaterHouse getHouseLeft() {
        return houseLeft;
    }

    /**
     * Gets the number of available seats in the theater remaining
     *
     * @return the number of available seats in the theater
     */
    public int getNumberAvailableSeats() {
        return numberAvailableSeats;
    }

    /**
     * This method will find the best seat available in all three house of theatre
     *
     * @return Seat to be used to generate customer ticket. If a null return, then
     * the show is sold out.
     */
    public Seat bestAvailableSeat() {
        Seat middle = bestAvailableMiddleSeat();
        Seat houseLeft = bestAvailableHouseLeftSeat();
        Seat houseRight = bestAvailableHouseRightSeat();

        return compareSeats(middle, houseLeft, houseRight);
    }


    /**
     * This method compares the best available seats from the three sections of theatre
     * and returns the best out of three
     *
     * @param bestMiddleSeat
     * @param bestLeftSeat
     * @param bestRightSeat
     * @return Seat
     */
    private Seat compareSeats(Seat bestMiddleSeat, Seat bestLeftSeat, Seat bestRightSeat) {
        Seat bestSeat;
        if ( bestRightSeat == null && bestRightSeat == null && bestRightSeat == null ) {
            return null;
        }
        /** There is a middle seat available*/
        else if ( bestMiddleSeat != null ) {
            //there is middle seat available..
            if ( bestLeftSeat != null ) {
                //there's also a left seat available
                bestSeat = bestMiddleSeat.compareTo(bestLeftSeat) <= 0 ? bestMiddleSeat : bestLeftSeat;
                if ( bestRightSeat != null ) {
                    //there's also a house right seat available
                    return bestSeat.compareTo(bestRightSeat) <= 0 ? bestSeat : bestRightSeat;
                } else {
                    //no house right seat... just return best of middle and house left
                    return bestSeat;
                }
            } else {
                //No house left seat
                if ( bestRightSeat != null ) {
                    //there's only a middle seat and a house right seat
                    return bestMiddleSeat.compareTo(bestRightSeat) <= 0 ? bestMiddleSeat : bestRightSeat;
                } else {
                    //Only a middle seat available
                    return bestMiddleSeat;
                }
            }

        }
        /** NO middle seat available*/
        else {
            //no middle seat
            if ( bestLeftSeat == null ) {
                //neither a house left seat
                return bestRightSeat;
            } else {
                //a house left seat available
                if ( bestRightSeat != null ) {
                    //also a house right seat available
                    return bestLeftSeat.compareTo(bestRightSeat) <= 0 ? bestLeftSeat : bestRightSeat;
                } else {
                    //only a house left seat available
                    return bestLeftSeat;
                }
            }

        }
    }

    /**
     * This method finds the best available seat in the middle
     */
    private Seat bestAvailableMiddleSeat() {
        ArrayList<Seat> seats = this.middle.getHouseSeatsList();

        //return the first best seat
        for ( Seat seat : seats )
            if ( seat.isAvailable() )
                return seat;

        //No seats available in this house
        return null;
    }

    /**
     * This method finds the best available seat in the House Left
     */
    private Seat bestAvailableHouseLeftSeat() {
        ArrayList<Seat> seats = this.houseLeft.getHouseSeatsList();

        //return the first best seat
        for ( Seat seat : seats )
            if ( seat.isAvailable() )
                return seat;

        //No seats available in this house
        return null;
    }

    /**
     * This method finds the best available seat in the House Left
     */
    private Seat bestAvailableHouseRightSeat() {
        ArrayList<Seat> seats = this.houseRight.getHouseSeatsList();

        //return the first best seat
        for ( Seat seat : seats )
            if ( seat.isAvailable() )
                return seat;

        //No seats available in this house
        return null;
    }

    /**
     * Attempts to mark the seat as taken,
     * However if it is already taken then report unsuccessful
     *
     * @param seat
     * @return
     */
    public boolean markAvailableSeatTaken(Seat seat) {

        if ( seat == null ) {
            System.err.println("Error - Invalid Seat No.");
        }

        boolean wasRemoved;
        if ( !(wasRemoved = middle.markSeatAsTaken(seat)) )
            if ( !(wasRemoved = houseLeft.markSeatAsTaken(seat)) )
                if ( !(wasRemoved = houseRight.markSeatAsTaken(seat)) ) {
                    if ( wasRemoved ) {
                        numberAvailableSeats -= 1;
                    }
                    return wasRemoved;
                }

        if ( wasRemoved ) {
            numberAvailableSeats -= 1;
        }
        return wasRemoved;

    }

    /**
     * A ticket for that seat is printed to the screen – leave it on the screen long enough to be
     * read easily by the client. The output format is up to you, but should contain the essential
     * information found on a theater ticket.
     *
     * @param seat
     */
    public void printTicketSeat(Seat seat) {
        System.out.println("<Customer name>: Ticket Purchase Successful - Seat No. " + seat.getSeatNo());
    }
}

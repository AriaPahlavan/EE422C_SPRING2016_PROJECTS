package assignment6;

import java.util.ArrayList;

/**
 * The position of the houseSeatsList
 * Created by Aria Pahlavan on Apr 2016.
 */
enum HousePos {
    MIDDLE,
    HOUSE_RIGHT,
    HOUSE_LEFT,
    NONE
}

/**
 * Project6_Tickets
 * Created by Aria Pahlavan on Apr 2016.
 */
public class TheaterHouse {
    private ArrayList<Seat> houseSeatsList;
    private int numberOfRows;
    private HousePos housePosition;
    private int avaialbleSeats;
    public final String[] ROW_LETTER = { "A", "B", "C", "D", "E", "F", "G", "H", "J", "K", "L", "M", "N", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "AA" };
    public final int[] SEAT_NUMBER = { 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128 };
    //the number of seat in each row of the houseSeatsList middle
    public final int MIDDLE_SEAT_QTY = 14;
    //the number of seat in each row of the houseSeatsList left
    public final int HOUSE_LEFT_SEAT_QTY = 7;
    //the number of seat in each row of the houseSeatsList right
    public final int HOUSE_RIGHT_SEAT_QTY = 7;

    /**
     * Creates a default theater house with empty arraylist, the number of rows, and the house position
     */
    public TheaterHouse() {
        this.houseSeatsList = new ArrayList<>();
        this.numberOfRows = 0;
        this.housePosition = HousePos.NONE;
    }
    
    /**
     * Creates a new list of house seats for this Theater house based on which house this is 
     * supposed to represent, Middle, left, right, or none
     * @param housePosition the house position for this theater
     */
    public TheaterHouse(HousePos housePosition) {

        this.housePosition = housePosition;

        switch (this.housePosition) {
        	// Seats 108 to 121 fall in this category of A to V
            case MIDDLE:
                this.numberOfRows = 22;
                houseSeatsList = new ArrayList<>();
                for ( int rowNum = 0; rowNum < this.numberOfRows; rowNum += 1 ) {
                    for ( int seatNum = 108; seatNum < MIDDLE_SEAT_QTY + 108; seatNum += 1 ) {
                        Seat seat = new Seat(seatNum, ROW_LETTER[rowNum]);
                        houseSeatsList.add(seat);
                    }
                }
                avaialbleSeats = numberOfRows * MIDDLE_SEAT_QTY;
                break;
            case HOUSE_LEFT:
            	// Seats 101 to 107 fall in this category of rows A to AA
                this.numberOfRows = 25;
                houseSeatsList = new ArrayList<>();
                for ( int rowNum = 0; rowNum < this.numberOfRows; rowNum += 1 ) {
                    for ( int seatNum = 101; seatNum < HOUSE_LEFT_SEAT_QTY + 101; seatNum += 1 ) {
                        Seat seat = new Seat(seatNum, ROW_LETTER[rowNum]);
                        houseSeatsList.add(seat);
                    }
                }
                avaialbleSeats = numberOfRows * HOUSE_LEFT_SEAT_QTY;
                break;
            case HOUSE_RIGHT:
            	// Seats 122 to 128 fall in this category of rows A to AA
                this.numberOfRows = 25;
                houseSeatsList = new ArrayList<>();
                for ( int rowNum = 0; rowNum < this.numberOfRows; rowNum += 1 ) {
                    for ( int seatNum = 122; seatNum < HOUSE_RIGHT_SEAT_QTY + 122; seatNum += 1 ) {
                        Seat seat = new Seat(seatNum, ROW_LETTER[rowNum]);
                        houseSeatsList.add(seat);
                    }
                }
                avaialbleSeats = numberOfRows * HOUSE_RIGHT_SEAT_QTY;
                break;
            default:
                System.err.println("No such houseSeatsList exist at our theatre!");
        }
    }

    /**
     * Gets the available seats for this theater house
     * @return the number of seats in this theater house
     */
    public int getAvaialbleSeats() {
        return avaialbleSeats;
    }

    /**
     * Gets the list of seats for this theater house
     * @return the list of seats for this theater house
     */
    public ArrayList<Seat> getHouseSeatsList() {
        return houseSeatsList;
    }
    
    /**
     * Sets the list to a new list of theater seats
     * @param houseSeatsList the new list of seats for this house
     */
    public void setHouseSeatsList(ArrayList<Seat> houseSeatsList) {
        this.houseSeatsList = houseSeatsList;
    }

    /**
     * Gets the number of rows for the theater house
     * @return number of rows for this theater house
     */
    public int getNumberOfRows() {
        return numberOfRows;
    }

    /**
     * sets the number of rows for this theater house
     * @param numberOfRows the new number of rows for the theater house
     */
    public void setNumberOfRows(int numberOfRows) {
        this.numberOfRows = numberOfRows;
    }

    /**
     * Gets the house position (which side of the theater) this house is
     * @return the house position of this theater house
     */
    public HousePos getHousePosition() {
        return housePosition;
    }

    /**
     * Sets the house position for this theater house
     * @param housePosition the new house position for the theater house
     */
    public void setHousePosition(HousePos housePosition) {
        this.housePosition = housePosition;
    }

    /**
     * this method removes the seat from availability list
     * @param seat
     * @return boolean true if the seat was successfully removed from availability list
     */
    public boolean markSeatAsTaken(Seat seat){
        //remove the seat from availability list if it exists
        seat.setAvailable(false);
        boolean wasRemoved = houseSeatsList.contains(seat);

        if ( wasRemoved )
            avaialbleSeats -= 1;

        return wasRemoved;

    }
}

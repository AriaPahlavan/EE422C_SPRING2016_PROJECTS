package assignment6;

import java.util.ArrayList;

/**
 * The position of the house
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
    private ArrayList<Seat> house;
    private int numberOfRows;
    private HousePos housePosition;
    private final String[] ROW_LETTER = { "A", "B", "C", "D", "E", "F", "G", "H", "J", "K", "L", "M", "N", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "AA" };
    private final int[] SEAT_NUMBER = { 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 124, 125, 126, 127, 128 };
    //the number of seat in each row of the house middle
    private final int MIDDLE_SEAT_QTY = 14;
    //the number of seat in each row of the house left
    private final int HOUSE_LEFT_SEAT_QTY = 7;
    //the number of seat in each row of the house right
    private final int HOUSE_RIGHT_SEAT_QTY = 7;


    public TheaterHouse() {
        this.house = new ArrayList<>();
        this.numberOfRows = 0;
        this.housePosition = HousePos.NONE;
    }

    public TheaterHouse(HousePos housePosition) {

        this.housePosition = housePosition;

        switch (this.housePosition) {
            case MIDDLE:
                this.numberOfRows = 22;
                house = new ArrayList<>();
                for ( int rowNum = 0; rowNum < this.numberOfRows; rowNum += 1 ) {
                    for ( int seatNum = 108; seatNum < MIDDLE_SEAT_QTY + 108; seatNum += 1 ) {
                        Seat seat = new Seat(seatNum, ROW_LETTER[rowNum]);
                        house.add(seat);
                    }
                }
                break;
            case HOUSE_LEFT:
                this.numberOfRows = 25;
                house = new ArrayList<>();
                for ( int rowNum = 0; rowNum < this.numberOfRows; rowNum += 1 ) {
                    for ( int seatNum = 101; seatNum < HOUSE_LEFT_SEAT_QTY + 101; seatNum += 1 ) {
                        Seat seat = new Seat(seatNum, ROW_LETTER[rowNum]);
                        house.add(seat);
                    }
                }
                break;
            case HOUSE_RIGHT:
                this.numberOfRows = 25;
                house = new ArrayList<>();
                for ( int rowNum = 0; rowNum < this.numberOfRows; rowNum += 1 ) {
                    for ( int seatNum = 122; seatNum < HOUSE_RIGHT_SEAT_QTY + 122; seatNum += 1 ) {
                        Seat seat = new Seat(seatNum, ROW_LETTER[rowNum]);
                        house.add(seat);
                    }
                }
                break;
            default:
                System.err.println("No such house exist at our theatre!");
        }
    }

    public ArrayList<Seat> getHouse() {
        return house;
    }

    public void setHouse(ArrayList<Seat> house) {
        this.house = house;
    }

    public int getNumberOfRows() {
        return numberOfRows;
    }

    public void setNumberOfRows(int numberOfRows) {
        this.numberOfRows = numberOfRows;
    }

    public HousePos getHousePosition() {
        return housePosition;
    }

    public void setHousePosition(HousePos housePosition) {
        this.housePosition = housePosition;
    }
}

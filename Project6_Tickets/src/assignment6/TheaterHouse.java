package assignment6;

import java.util.ArrayList;

/**
 * The position of the house
 * Created by Aria Pahlavan on Apr 2016.
 */
enum HousePos{
    HOUSE_FRONT,
    HOUSE_RIGHT,
    HOUSE_LEFT,
    NONE
}

/**
 * Project6_Tickets
 * Created by Aria Pahlavan on Apr 2016.
 */
public class TheaterHouse {
    private ArrayList<Seats> house;
    private int numberOfRows;
    private HousePos housePosition;

    public TheaterHouse() {
        this.house = new ArrayList<>();
        this.numberOfRows = numberOfRows;
        this.housePosition = HousePos.NONE;
    }

    public TheaterHouse(int numberOfRows, HousePos housePosition) {
        this.numberOfRows = numberOfRows;
        this.housePosition = housePosition;

        switch (this.housePosition){
            case HOUSE_FRONT:
                break;
            case HOUSE_LEFT:
                break;
            case HOUSE_RIGHT:
                break;
            default:
                System.err.println("No such house exist at our theatre!");
        }
    }
}

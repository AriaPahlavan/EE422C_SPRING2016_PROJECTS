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
        this.numberOfRows = 0;
        this.housePosition = HousePos.NONE;
    }

    public TheaterHouse(HousePos housePosition) {

        this.housePosition = housePosition;

        switch (this.housePosition){
            case HOUSE_FRONT:
                //TODO Setup the house front
                this.numberOfRows = numberOfRows;
                break;
            case HOUSE_LEFT:
                //TODO Setup the house left
                this.numberOfRows = numberOfRows;
                break;
            case HOUSE_RIGHT:
                //TODO Setup the house right
                this.numberOfRows = numberOfRows;
                break;
            default:
                System.err.println("No such house exist at our theatre!");
        }
    }
}

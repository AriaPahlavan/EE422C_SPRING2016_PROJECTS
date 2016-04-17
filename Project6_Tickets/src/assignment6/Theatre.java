package assignment6;

/**
 * Project6_Tickets
 * Created by Aria Pahlavan on Apr 2016.
 */
public class Theatre {
    private TheaterHouse houseFront;
    private TheaterHouse houseRight;
    private TheaterHouse houseLeft;
    private int numberAvailableSeats;


    public Theatre() {
        this.houseFront = new TheaterHouse(HousePos.HOUSE_FRONT);
        this.houseRight = new TheaterHouse(HousePos.HOUSE_RIGHT);
        this.houseLeft = new TheaterHouse(HousePos.HOUSE_LEFT);
        
    }

    public String bestAvailableSeat(){
        //TODO find the best available seat
        return null;
    }

    public
}

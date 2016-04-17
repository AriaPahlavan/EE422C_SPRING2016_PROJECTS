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

    /**
     * This method will find the best seat available in all three house of theatre
     * @return Seat to be used to generate customer ticket
     */
    public Seat bestAvailableSeat(){
        //TODO find the best available seat
        return null;
    }

    /**
     * Attempts to mark the seat as taken,
     * However if it is already taken then report unsuccessful
     * @param seat
     * @return
     */
    public boolean markAvailableSeatTaken(Seat seat){
        //TODO implement the method to mark seat as taken and return false if it is already taken
        return true;
    }

    /**
     * A ticket for that seat is printed to the screen – leave it on the screen long enough to be
     * read easily by the client. The output format is up to you, but should contain the essential
     * information found on a theater ticket.
     * @param seat
     */
    public void printTicketSeat(Seat seat){
        //TODO print on A ticket for that seat is printed to the screen
    }
}

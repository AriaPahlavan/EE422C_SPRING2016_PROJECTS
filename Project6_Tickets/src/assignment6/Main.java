package assignment6;

/**
 * Project6_Tickets
 * Created by Aria Pahlavan on Apr 2016.
 */
public class Main {

    public static void main(String[] args) {
        TheaterHouse theaterHouse = new Theatre().getHouseRight();

        for ( Seat seat : theaterHouse.getHouse() ) {
            System.out.println(seat.getSeatNo());
        }
    }
}

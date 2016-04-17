package assignment6;

/**
 * Project6_Tickets
 * Created by Aria Pahlavan on Apr 2016.
 */
public class Seat {
    private int seatNumber;
    private String seatRow;
    private String seat;
    private boolean isTaken = false;

    public Seat() {
        this.seatNumber = 0;
        this.seatRow = "";
        this.seat = "";
    }

    public Seat(int seatNumber, String seatRow, String seat) {
        this.seatNumber = seatNumber;
        this.seatRow = seatRow;
        this.seat = seat;
    }

}

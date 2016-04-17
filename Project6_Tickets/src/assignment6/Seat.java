package assignment6;

/**
 * Project6_Tickets
 * Created by Aria Pahlavan on Apr 2016.
 */
public class Seat {
    private int seatNumber;
    private String rowLetter;
    private String seatNo;
    private boolean isTaken = false;

    public Seat() {
        this.seatNumber = 0;
        this.rowLetter = "";
        this.seatNo = "";
    }

    public Seat(int seatNumber, String rowLetter) {
        this.seatNumber = seatNumber;
        this.rowLetter = rowLetter;
        this.seatNo = rowLetter + new Integer(seatNumber).toString();
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getRowLetter() {
        return rowLetter;
    }

    public void setRowLetter(String rowLetter) {
        this.rowLetter = rowLetter;
    }

    public String getSeatNo() {
        return seatNo;
    }

    public void setSeatNo(String seatNo) {
        this.seatNo = seatNo;
    }

    public boolean isTaken() {
        return isTaken;
    }

    public void setTaken(boolean taken) {
        isTaken = taken;
    }
}

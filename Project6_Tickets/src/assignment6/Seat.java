package assignment6;

/**
 * Project6_Tickets
 * Created by Aria Pahlavan on Apr 2016.
 */
public class Seat implements Comparable<Seat> {
    private int seatNumber;
    private String rowLetter;
    private String seatNo;
    private boolean isAvailable = true;
    
    /**
     * Creates a default seat with row letter, chair number, and string to represent the two
     */
    public Seat() {
        this.seatNumber = 0;
        this.rowLetter = "";
        this.seatNo = "";
    }

    /**
     * Creates a seat with the desired row letter and chair number
     * @param seatNumber the desired chair number
     * @param rowLetter the row number
     */
    public Seat(int seatNumber, String rowLetter) {
        this.seatNumber = seatNumber;
        this.rowLetter = rowLetter;
        this.seatNo = rowLetter + new Integer(seatNumber).toString();
    }
    
    /**
     * Gets the integer seat number of the chair
     * @return the integer seat number of the chair
     */
    public int getSeatNumber() {
        return seatNumber;
    }

    /**
     * Sets the seat number for the chair
     * @param seatNumber seat number for the chair
     */
    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }
    
    /**
     * Gets the row letter of the seat
     * @return row letter of the seat
     */
    public String getRowLetter() {
        return rowLetter;
    }

    /**
     * Seats the row letter for the seat
     * @param rowLetter the new row letter for the seat
     */
    public void setRowLetter(String rowLetter) {
        this.rowLetter = rowLetter;
    }

    /**
     * Gets the string representation of the seat
     * @return the string representation of the seat's row and chair number
     */
    public String getSeatNo() {
        return seatNo;
    }

    /**
     * Sets the seat's string representation
     * @param seatNo the seat's string representation
     */
    public void setSeatNo(String seatNo) {
        this.seatNo = seatNo;
    }

    /**
     * Checks if the seat is available
     * @return true if available, false if not
     */
    public boolean isAvailable() {
        return isAvailable;
    }

    /**
     * Sets the availability of the chair
     * @param available the new availability of the chair
     */
    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    /**
     * compares this seat to another seat and returns 1 if this seat comes after the passed seat, 
     * or -1 if it comes before the passed seat, or 0 if they are the same seat
     * @param o the other seat to compare to
     * @returns 1 if the seat is further back than the other, -1 if not, 0 if they are equal
     */
    public int compareTo(Seat o) {
        if ( this.rowLetter.equals("AA") || o.rowLetter.equals("AA") ){
            if ( this.rowLetter.equals("AA") && o.rowLetter.equals("AA")  ) {
                return this.rowLetter.compareTo(o.rowLetter);
            }
            else if ( this.rowLetter.equals("AA") ){
                return 1;
            }
            else {
                return -1;
            }
        }
        return this.rowLetter.compareTo(o.rowLetter);
    }
}

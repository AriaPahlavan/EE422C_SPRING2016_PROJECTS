package Assignment4;

/**
 * This class will return the an appropriate error message in case of occurrence of invalid inputs
 *
 * @author Aria Pahlavan, Jett Anderson
 */
public class NoSuchLadderException extends Exception {
    public NoSuchLadderException (String message) {
        super(message);
    }

    public void printError(){
        System.err.println("Error - " + getMessage());
    }

}

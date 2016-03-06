package Assignment4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Assignment4
 * Created by Aria Pahlavan on Mar 2016.
 */
public class FileReader {

    public ArrayList<String> readDictionaryFile(String filename) {
        //Opening file and scanning the contents
        File file = new File(filename);
        Scanner scan = null;
        ArrayList<String> list = new ArrayList<String>();
        try {
            scan = new Scanner(file);

            //if a line does not start with an '*', the first 5 letters
            //are copied into the list.
            while ( scan.hasNextLine() ) {
                String line = scan.nextLine();
                if ( line.charAt(0) != '*' ) {
                    String word = line.substring(0, 5);
                    list.add(word);
                }
            }
        } catch (FileNotFoundException fnfe) {
            //File error handling
            fnfe.printStackTrace();
            System.err.println("Error - File " + file + " not found.");
        } finally {
            //In case an error occurs, close the scanner safely.
            if ( scan != null ) {
                scan.close();
            }
        }

        return list;
    }

    public ArrayList<String> readTestFile(String filename) {
        //Opening file and scanning the contents
        File file = new File(filename);
        Scanner scan = null;
        ArrayList<String> list = new ArrayList<String>();
        try {
            scan = new Scanner(file);

            while ( scan.hasNext() ) {
                StringBuffer word = new StringBuffer(scan.next());
                word.append(" ");
                word.append(scan.next());

                if ( word.toString().matches("[a-z]{5}( +)?(\t+)?[a-z]{5}") && !scan.hasNext("[^\\n]"))
                    list.add(word.toString());
                else System.err.println("Error - Invalid Input found: " + word.toString());
            }
        } catch (FileNotFoundException fnfe) {
            //File error handling
            fnfe.printStackTrace();
            System.err.println("Error - File " + file + " not found.");
        } finally {
            //In case an error occurs, close the scanner safely.
            if ( scan != null ) {
                scan.close();
            }
        }

        return list;
    }

}

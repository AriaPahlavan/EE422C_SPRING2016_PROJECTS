/*
    ADD YOUR HEADER HERE
 */

package Assignment4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

// do not change class name or interface it implements
public class WordLadderSolver implements Assignment4Interface {
    // declares class members here.
    private Dictionary dictionary;
    private final int START_INDEX = 0;

    // add a constructor for this object. HINT: it would be a good idea to set up the dictionary there

    /**
     * Custom constructor - Opens the file containing the dictionary words, scan the words
     * then initializes the dictionary fields.
     * @param filename
     * @throws FileNotFoundException if an error occurs while attempting to
     * open the dictionary file.
     */
    public WordLadderSolver (String filename) {
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

        //initializing dictionary
        list.trimToSize();
        dictionary = new Dictionary(list);

    }

    // do not change signature of the method implemented from the interface
    @Override
    public ArrayList<String> computeLadder (String startWord, String endWord) throws NoSuchLadderException {

        return null;
    }

    @Override
    public boolean validateResult (String startWord, String endWord, ArrayList<String> wordLadder) {
        //stripping the first and last word for testing
        int size = wordLadder.size();
        String start = wordLadder.get(START_INDEX);
        String end = wordLadder.get(size-1);

        //Checking the first and last word in the ladder
        if ( !start.equals(startWord) || !end.equals(endWord) )
            return false;

        //checking if all intermediate words are exactly one distance apart
        for (int i = 0; i<(size-1); i++){
            int numCharDiff = numDifferentChar(wordLadder.get(i), wordLadder.get(i+1));
            if ( numCharDiff != 1 ){
                return false;
            }
        }

        return true;
    }

    /**
     * This method compares to strings character by character and counts the number
     * of character differences between the two input parameters.
     * @param s1
     * @param s2
     * @return the number of character difference between the two string parameters.
     */
    private int numDifferentChar (String s1, String s2) {
        //counter of the differing letters
        int counter = 0;
        char[] firstWord = s1.toCharArray();
        char[] secondWord = s2.toCharArray();

        // the size of the shortest string
        // (more specifically character array).
        int size = Math.min(s1.length(), s2.length());

        // checking the letters one by one and
        // counting the unlike chars
        for (int i=0; i<size; i+=1 ){
            if ( firstWord[i] != secondWord[i] )
                counter += 1;
        }

        return counter;
    }

    // add additional methods here

}

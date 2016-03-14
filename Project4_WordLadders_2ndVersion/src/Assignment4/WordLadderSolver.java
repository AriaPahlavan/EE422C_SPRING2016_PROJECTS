/*
    ADD YOUR HEADER HERE
 */

package Assignment4;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;

// do not change class name or interface it implements
public class WordLadderSolver implements Assignment4Interface {
    private Dictionary dictionary;
    private ArrayList<String> solutionList;
    private ArrayList<String> tempList;
    private final int START_INDEX = 0;

    // add a constructor for this object. HINT: it would be a good idea to set up the dictionary there


    /**
     * Default constructor - Used for Junit testing
     */
    public WordLadderSolver() {
        //initializing solutionList
        solutionList = new ArrayList<>();


    }

    /**
     * Custom constructor - Opens the file containing the dictionary words, scan the words
     * then initializes the dictionary fields.
     *
     * @param list
     * @throws FileNotFoundException
     *         if an error occurs while attempting to
     *         open the dictionary file.
     */
    public WordLadderSolver(ArrayList<String> list) {


        //initializing dictionary
        list.trimToSize();
        dictionary = new Dictionary(list);

        //initializing solutionList
        solutionList = new ArrayList<>();

    }

    //Setters and Getters
    public void setDictionary(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    public ArrayList<String> getSolutionList() {
        return solutionList;
    }

    //Member methods
    @Override
    public ArrayList<String> computeLadder(String startWord, String endWord) throws NoSuchLadderException {
        tempList = new ArrayList<>();
        Vertex startVertex = dictionary.search(startWord);
        Vertex endVertex = dictionary.search(endWord);

        if ( startVertex != null && endVertex != null ) {

            solutionList.add(startWord);
            if ( makeLadder(startVertex, endVertex, -1) ) {
                completeSolution();
                return solutionList;
            } else {
                return null;
            }
        }

        return null;
    }

    /**
     * updates the solution list using the temporary list
     */
    private void completeSolution() {

        int lastIndex = tempList.size() - 1;

        for ( int i = lastIndex; i >= 0; i -= 1 ) {
            solutionList.add(tempList.get(i));
        }

        tempList.clear();
    }


    /**
     *
     */
    private boolean makeLadder(Vertex start, Vertex end, int position) {

        //Base case:
        if ( start == null || end == null )
            return false;
        int difference = numDifferentChar(start.getPhrase(), end.getPhrase());
        if ( difference == 1 || difference == 0 ) {
            tempList.add(end.getPhrase());
            return true;
        }

        //Recursion body
        start.setStatus(true);


        /**
         * Sorting edges by distance from end word
         */
        ArrayList<Vertex> edgeVertex = start.getEdges();
        int size = edgeVertex.size();
        ArrayList<String> edges = new ArrayList<>();
        for ( int i = 0; i < size; i += 1 ) {
            edges.add(numDifferentChar(edgeVertex.get(i).getPhrase(), end.getPhrase()) + edgeVertex.get(i).getPhrase());
            //
//            System.out.println(edges.get(i));
            //
        }

        Collections.sort(edges);


        for ( String s : edges ) {
            int length = s.length();
            Vertex vertex = dictionary.search(s.subSequence((length - 5), length).toString());
            //
//            System.out.println(vertex.getPhrase());
            //

            int updatedPosition = findCharIndex(start.getPhrase(), vertex.getPhrase());

            if ( updatedPosition < 0 )
                System.err.println("There is an error in find char index: " + start.getPhrase() + vertex.getPhrase());

            //for all edges of start, if not visited the, do a recurse call
            if ( position != updatedPosition &&!vertex.wasChecked() ) {
                if ( makeLadder(vertex, end, updatedPosition) ) {
                    tempList.add(vertex.getPhrase());
                    return true;
                }
            }
        }

        //No ladder found
        return false;
    }

    @Override
    public boolean validateResult(String startWord, String endWord, ArrayList<String> wordLadder) {

        if ( wordLadder == null )
            return false;

        //stripping the first and last word for testing
        int size = wordLadder.size();
        String start = wordLadder.get(START_INDEX);
        String end = wordLadder.get(size - 1);

        //Checking the first and last word in the ladder
        if ( !start.equals(startWord) || !end.equals(endWord) )
            return false;

        //checking if all intermediate words are exactly one distance apart
        for ( int i = 0; i < (size - 1); i++ ) {
            int numCharDiff = numDifferentChar(wordLadder.get(i), wordLadder.get(i + 1));
            if ( !(numCharDiff == 1 || numCharDiff == 0) ) {
                return false;
            }
        }

        return true;
    }

    /**
     * This method compares to strings character by character and counts the number
     * of character differences between the two input parameters.
     *
     * @param s1
     * @param s2
     * @return the number of character difference between the two string parameters.
     */
    int numDifferentChar(String s1, String s2) {
        //counter of the differing letters
        int counter = 0;
        char[] firstWord = s1.toCharArray();
        char[] secondWord = s2.toCharArray();

        // the size of the shortest string
        // (more specifically character array).
        int size = Math.min(s1.length(), s2.length());

        // checking the letters one by one and
        // counting the unlike chars
        for ( int i = 0; i < size; i += 1 ) {
            if ( firstWord[i] != secondWord[i] )
                counter += 1;
        }

        return counter;
    }

    /**
     * This method compares to strings character by character and looks for the
     * a difference in characters and if caught.
     *
     * @param s1
     * @param s2
     * @return the position where a character difference occurred.
     */
    int findCharIndex(String s1, String s2) {

        //Position of the different letter
        char[] first = s1.toCharArray();
        char[] second = s2.toCharArray();

        // the size of the shortest string
        // (more specifically character array).
        int size = Math.min(s1.length(), s2.length());

        // checking the letters one by one and
        // looking for a difference in chars
        for ( int i = 0; i < size; i += 1 ) {
            if ( first[i] != second[i] )
                return i;
        }

        //NOT REACHED
        return -1;
    }


    /**
     * Resets the solutions list and temporary list.
     */
    void reset() {
        solutionList.clear();

        for ( Vertex vertex : dictionary.getGraph() ) {
            vertex.setStatus(false);
        }
    }

    /**
     * 1 Breadth-First-Search(Graph, root):
     * 2
     * 3     for each node n in Graph:
     * 4         n.distance = INFINITY
     * 5         n.parent = NIL
     * 6
     * 7     create empty queue Q
     * 8
     * 9     root.distance = 0
     * 10     Q.enqueue(root)
     * 11
     * 12     while Q is not empty:
     * 13
     * 14         current = Q.dequeue()
     * 15
     * 16         for each node n that is adjacent to current:
     * 17             if n.distance == INFINITY:
     * 18                 n.distance = current.distance + 1
     * 19                 n.parent = current
     * 20                 Q.enqueue(n)
     */
    public void BreadthFirstSearch() {

    }

}

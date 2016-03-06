package Assignment4;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Aria Pahlavan, Jett Anderson on Mar 2016
 */
public class Dictionary {
    private ArrayList<String> dictionary;
    private ArrayList<Vertex> graph;


    /**
     * Custom constructor - Initialized the dictionary and solutionList field.
     *
     * @param list
     */
    public Dictionary(ArrayList<String> list) {
        //initializing dictionary
        dictionary = list;
        Collections.sort(dictionary);


        // Creating graph:
        //      each word (vertex) is connected to all words
        //      which is only one distance apart
        graph = new ArrayList<Vertex>(list.size());
        createGraph();


    }

    //Setters and Getters
    public ArrayList<String> getDictionary() {
        return dictionary;
    }

    public ArrayList<Vertex> getGraph() {
        return graph;
    }

    public void setDictionary(ArrayList<String> newDictionary) {
        dictionary = newDictionary;
    }

    /**
     * This method will create a graph containing every word in
     * dictionary as its Vertices and each vertex wil be connected
     * to all vertices that are only one distance apart.
     */
    private void createGraph() {
        String first = new String();
        String second = new String();

        //convert all words to vertices and fill up the graph list
        for ( String word : dictionary ) {

            Vertex newPhrase = new Vertex(word);
            graph.add(newPhrase);
        }

        int size = graph.size();

        //now connect the vertices with correct edges
        for ( int i = 0; i < size; i += 1 ) {

            Vertex vertex = graph.get(i);
            first = vertex.getPhrase();

            for ( int j = 0; j < size; j += 1 ) {

                if ( j != i ) {

                    Vertex edgeVertex = graph.get(j);
                    second = edgeVertex.getPhrase();

                    int distance = numDifferentChar(first, second);

                    if ( distance == 1 )
                        vertex.setEdges(edgeVertex);
                }
            }
        }
    }


    /**
     * Checks for whether or not the input word exists in dictionary
     * but looking searching through the list of dictionary words.
     *
     * @param word
     * @return the search result of input word in dictionary
     */
    public Vertex search(String word) {

        if ( dictionary.contains(word) ) {
            for ( Vertex vertex : graph )
                if ( vertex.getPhrase().equals(word) )
                    return vertex;
        } else {
            //if the word does not exist in our graph
            return null;
        }

        //NOT REACHED
        System.err.println("The exist in the dictionary but is not a vertex of graph");
        return null;
    }


    /**
     *
     */
    public void BreadthFirstSearch() {

    }


    /**
     * This method compares to strings character by character and counts the number
     * of character differences between the two input parameters.
     *
     * @param s1
     * @param s2
     * @return the number of character difference between the two string parameters.
     */
    private int numDifferentChar(String s1, String s2) {
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

}

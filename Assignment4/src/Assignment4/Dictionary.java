package Assignment4;

import java.util.ArrayList;
/**
 *
 * @author Aria Pahlavan, Jett Anderson on Mar 2016
 */
public class Dictionary {
	private ArrayList<String> dictionary;
	private ArrayList<Vertex> graph;
	private ArrayList<String> solutionList;


	/**
	 * Custom constructor - Initialized the dictionary and solutionList field.
	 * @param list
     */
    public Dictionary(ArrayList<String> list) {
	    //initializing dictionary
	    dictionary = list;

	    //initializing solutionList
	    solutionList = new ArrayList<>();

	     // Creating graph:
	     //      each word (vertex) is connected to all words
	     //      which is only one distance apart
	    graph = new ArrayList<Vertex>(list.size());
	    createGraph();


	}

	/**
	 * This method will create a graph containing every word in
	 * dictionary as its Vertices and each vertex wil be connected
	 * to all vertices that are only one distance apart.
	 */
    private void createGraph () {
		for (String word : dictionary){

			
		}
	}


	/**
	 * Checks for whether or not the input word exists in dictionary
	 * but looking searching through the list of dictionary words.
	 * @param word
	 * @return the search result of input word in dictionary
     */
    public boolean search(String word){
		return dictionary.contains(word);
	}

	//Setters and Getters
	public ArrayList<String> getDictionary(){
		return dictionary;
	}
	
	public void setDictionary(ArrayList<String> newDictionary){
		dictionary = newDictionary;
	}

	public BreadthFirstSearch(){

	}
}

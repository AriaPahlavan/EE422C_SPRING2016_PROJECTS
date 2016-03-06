package Assignment4;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;

import static junit.framework.TestCase.assertEquals;

/**
 * Assignment4
 * Created by Aria Pahlavan on Mar 2016.
 */
public class graphTest {
    ArrayList<String> myDictionary = new ArrayList<>();
    final String[] edges = { "alone", "clane" };
    Dictionary dictionary;


    @Test
    public void testGraph() {
        init();
        dictionary = new Dictionary(myDictionary);
        ArrayList<Vertex> graph = dictionary.getGraph();

        Collections.sort(myDictionary);

        //Testing vertices list
        for ( int i = 0; i < myDictionary.size(); i++ ) {
            assertEquals(graph.get(i).getPhrase(), myDictionary.get(i));
        }

        //Testing edges
        ArrayList<Vertex> vertex = graph.get(0).getEdges();
        for ( int i = 0; i < edges.length; i++ ) {
            assertEquals(vertex.get(i).getPhrase(), edges[i]);
        }

    }


    @Test
    public void testMAkeLadder() throws NoSuchLadderException {
        testGraph();

//        for (Vertex vertex : dictionary.getGraph()) {
//            System.out.println(vertex.getPhrase());
//        }
//        System.out.println("*********");

        WordLadderSolver wordLadderSolver = new WordLadderSolver();

        wordLadderSolver.setDictionary(dictionary);

        wordLadderSolver.computeLadder("stone", "money");

        for (String ladderStep : wordLadderSolver.getSolutionList() )
            System.out.println(ladderStep);


    }

    private void init() {
        myDictionary.add("stone");
        myDictionary.add("seems");
        myDictionary.add("scone");
        myDictionary.add("morey");
        myDictionary.add("alone");
        myDictionary.add("honey");
        myDictionary.add("atoms");
        myDictionary.add("bogey");
        myDictionary.add("beady");
        myDictionary.add("money");
        myDictionary.add("conns");
        myDictionary.add("sunny");
        myDictionary.add("toner");
        myDictionary.add("shone");
        myDictionary.add("beads");
        myDictionary.add("creep");
        myDictionary.add("atone");
        myDictionary.add("smart");
        myDictionary.add("tones");
        myDictionary.add("mosey");
        myDictionary.add("angel");
        myDictionary.add("clons");
        myDictionary.add("coins");
        myDictionary.add("bones");
        myDictionary.add("cones");
        myDictionary.add("devil");
        myDictionary.add("coons");
        myDictionary.add("alane");
        myDictionary.add("clane");
        myDictionary.add("funny");
        myDictionary.add("zebra");
        myDictionary.add("flans");
        myDictionary.add("moons");
        myDictionary.add("atlas");
        myDictionary.add("child");
        myDictionary.add("dooms");
        myDictionary.add("clans");
        myDictionary.add("coney");
        myDictionary.add("buddy");
        myDictionary.add("heart");
        myDictionary.add("clone");
    }

}
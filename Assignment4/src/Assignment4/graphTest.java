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
    ArrayList<String> vocabs = new ArrayList<>();
    final String[] edges = { "car", "fam", "jam" };


    @Test
    public void testGraph() {
        init();
        Dictionary dictionary = new Dictionary(vocabs);
        ArrayList<Vertex> graph = dictionary.getGraph();

        Collections.sort(vocabs);

        //Testing vertices list
        for ( int i = 0; i < vocabs.size(); i++ ) {
            assertEquals(graph.get(i).getPhrase(), vocabs.get(i));
        }

        //Testing edges
        ArrayList<Vertex> vertex = graph.get(0).getEdges();
        for ( int i = 0; i < edges.length; i++ ) {
            assertEquals(vertex.get(i).getPhrase(), edges[i]);
        }


    }

    private void init() {
        vocabs.add("sum");
        vocabs.add("jam");
        vocabs.add("sun");
        vocabs.add("car");
        vocabs.add("num");
        vocabs.add("jet");
        vocabs.add("cam");
        vocabs.add("far");
        vocabs.add("fam");
        vocabs.add("jim");
    }

}
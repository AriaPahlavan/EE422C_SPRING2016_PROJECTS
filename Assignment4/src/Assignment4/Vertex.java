package Assignment4;

import java.util.ArrayList;

/**
 * Assignment4
 * Created by Aria Pahlavan on Mar 2016.
 */
public class Vertex {
    private ArrayList<Vertex> edges;
    private Vertex parent;
    private boolean wasChecked;
    private String phrase;

    /**
     * Default constructor
     */
    public Vertex () {
        this.edges = new ArrayList<>();
        this.parent = null;
        this.wasChecked = false;
        this.phrase = "";
    }


}

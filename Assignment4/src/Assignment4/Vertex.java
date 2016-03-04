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

    /**
     * Custom constructor
     * @param phrase
     */
    public Vertex (String phrase) {
        this();
        this.phrase = phrase;
    }

    //Getters and Setters

    public String getPhrase() {
        return phrase;
    }

    public ArrayList<Vertex> getEdges() {
        return edges;
    }

    public Vertex getParent() {
        return parent;
    }

    public boolean isWasChecked() {
        return wasChecked;
    }

    public void setEdges(ArrayList<Vertex> edges) {
        this.edges = edges;
    }

    public void setEdges(Vertex edge) {
        this.edges.add(edge);
    }

    public void setParent(Vertex parent) {
        this.parent = parent;
    }

    public void setWasChecked(boolean wasChecked) {
        this.wasChecked = wasChecked;
    }

    public void setPhrase(String phrase) {
        this.phrase = phrase;
    }
}

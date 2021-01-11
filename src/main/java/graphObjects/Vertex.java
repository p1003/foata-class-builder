package graphObjects;

import languageObjects.Letter;

import java.util.ArrayList;
import java.util.List;

public class Vertex {
    private final List<Vertex> neighbours;
    private final Letter letter;
    private int FoataClass;
    private final int idx;

    public Vertex (Letter letter, int idx) {
        this.neighbours = new ArrayList<>();
        this.letter = letter;
        this.FoataClass = 0;
        this.idx = idx;
    }

    public boolean findNeighbour (Vertex target) {
        if(this.equals(target))
            return true;
        for(Vertex v : neighbours) {
            if(v.findNeighbour(target))
                return true;
        }
        return false;
    }

    public void calculateFoataClass (int depth) {
        if(depth <= FoataClass)
            return;
        FoataClass = depth;
        for(Vertex v : neighbours) {
            v.calculateFoataClass(depth+1);
        }
    }

    public void addVertex (Vertex vertex) {
        if(!neighbours.contains(vertex))
            neighbours.add(vertex);
    }
    public void removeVertex (Vertex vertex) {
        neighbours.remove(vertex);
    }

    public Letter getLetter() {
        return letter;
    }
    public int getFoataClass() {
        return FoataClass;
    }
    public List<Vertex> getNeighbours() {
        return neighbours;
    }
    public int getIdx() {
        return idx;
    }
}

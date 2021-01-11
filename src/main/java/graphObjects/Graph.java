package graphObjects;

import languageObjects.Letter;
import languageObjects.Translator;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Graph {
    private final List<Vertex> vertexes;

    public Graph (Translator translator) {
        this.vertexes = new ArrayList<>();
        int i = 1;
        for(Letter l : translator.getWord()) {
            vertexes.add(new Vertex(l,i));
            i++;
        }

        i = 1;
        for(Vertex v1 : vertexes) {
            for(int j = i; j < vertexes.size(); j++) {
                Vertex v2 = vertexes.get(j);
                if(translator.areDependent(v1.getLetter().getSign(),v2.getLetter().getSign()))
                    v1.addVertex(v2);
            }
            i++;
        }
        reduceGraph();
    }

    private void reduceGraph () {
        for(Vertex currVertex : vertexes) {
            List<Vertex> currNeighbours = new ArrayList<>(currVertex.getNeighbours());
            for(Vertex target : currNeighbours) {
                currVertex.removeVertex(target);
                if(!currVertex.findNeighbour(target))
                    currVertex.addVertex(target);
            }
        }
        for(Vertex v : vertexes) {
            v.calculateFoataClass(1);
        }
    }

    public List<Vertex> getVertexes() {
        return vertexes;
    }

    public void writeGraphToDotFile (String filename) {
        try {
            FileWriter graphWriter = new FileWriter(filename);
            graphWriter.write("digraph g{\n");
            for(Vertex v : vertexes) {
                for(Vertex n : v.getNeighbours()) {
                    graphWriter.write(v.getIdx()+" -> "+n.getIdx()+"\n");
                }
            }
            for(Vertex v : vertexes) {
                graphWriter.write(v.getIdx()+"[label="+v.getLetter().getSign()+"]\n");
            }
            graphWriter.write("}");
            graphWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}

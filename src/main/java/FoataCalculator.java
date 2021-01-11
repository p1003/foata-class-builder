import graphObjects.Graph;
import graphObjects.Vertex;
import languageObjects.Letter;
import languageObjects.Translator;

import java.util.ArrayList;
import java.util.List;

public class FoataCalculator {
    public static void calculateFoata (Graph graph) {
        List<Vertex> vertexList = graph.getVertexes();

        List<List<Vertex>> FoataClasses = new ArrayList<>();
        int N = 0;
        for(Vertex v : vertexList) {
            int fc = v.getFoataClass();
            if(fc > N)
                N = fc;
        }
        for(int i=0; i<N; i++)
            FoataClasses.add(new ArrayList<Vertex>());

        for(Vertex v : vertexList)
            FoataClasses.get(v.getFoataClass()-1).add(v);

        StringBuilder result = new StringBuilder("Graph FNF = ");
        for(List<Vertex> list : FoataClasses) {
            result.append("[");
            boolean first = true;
            for(Vertex v : list) {
                if(first)
                    first = false;
                else
                    result.append(",");
                result.append(v.getLetter().getSign());
            }
            result.append("]");
        }
        System.out.println(result);
    }

    public static void calculateFoata (Translator translator) {
        List<Letter> word = translator.getWord();
        int N = word.size();
        int[] FoataValues = new int[N];
        for(int i = 0; i < N; i++) {
            FoataValues[i] = 1;
        }
        int maxClass = 0;
        for(int i=0; i<N; i++) {
            Letter l = word.get(i);
            for(int j=i+1; j<N; j++) {
                if(FoataValues[j] <= FoataValues[i]) {
                    if(translator.areDependent(l.getSign(),word.get(j).getSign())) {
                        int newVal = FoataValues[i] + 1;
                        FoataValues[j] = newVal;
                        if(newVal > maxClass)
                            maxClass = newVal;
                    }
                }
            }
        }
        List<List<Letter>> FoataClasses = new ArrayList<>();

        for(int i=0; i<maxClass; i++)
            FoataClasses.add(new ArrayList<Letter>());

        for(int i=0; i<N; i++)
            FoataClasses.get(FoataValues[i]-1).add(word.get(i));

        StringBuilder result = new StringBuilder("Trace FNF = ");
        for(List<Letter> list : FoataClasses) {
            result.append("[");
            boolean first = true;
            for(Letter v : list) {
                if(first)
                    first = false;
                else
                    result.append(",");
                result.append(v.getSign());
            }
            result.append("]");
        }
        System.out.println(result);
    }
}

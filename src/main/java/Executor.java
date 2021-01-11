import graphObjects.Graph;
import languageObjects.Translator;

public class Executor {
    public Executor() {
    }

    public void run (String inputFile, String outputFile) {

        Translator translator = new Translator();

        // przeczytanie pliku wejściowego i zapisanie alfabetu
        translator.translate(FileReader.readFile("src/text_files/"+inputFile+".txt"));

        //wyznaczenie relacji zależności i niezależności
        translator.calculateDependencies();

        // wypisanie alfabetu, D i I
        System.out.println(translator.AtoString());
        System.out.println(translator.DtoString());
        System.out.println(translator.ItoString());

        //wyznaczenie klas Foaty na podstawie słowa
        FoataCalculator.calculateFoata(translator);

        // stworzenie grafu skierowanego produkcji
        Graph graph = new Graph(translator);

        //wyznaczenie klas Foaty na podstawie grafu
        FoataCalculator.calculateFoata(graph);

        //zapisanie grafu do pliku .dot
        graph.writeGraphToDotFile("src/dot_files/"+outputFile+".dot");
    }
}

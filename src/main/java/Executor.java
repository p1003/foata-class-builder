import graphObjects.Graph;
import languageObjects.Translator;

public class Executor {
    public Executor() {
    }

    public void run (String inputFile, String outputFile) {
        FileReader fileReader = new FileReader("src/text_files/"+inputFile+".txt");
        Translator translator = new Translator();
        // przeczytanie pliku wejściowego i zapisanie alfabetu
        translator.translate(fileReader.readFile());
        //wyznaczenie relacji zależności i niezależności
        translator.calculateDependencies();

        // wypisanie alfabetu, D i I
        System.out.println(translator.AtoString());
        System.out.println(translator.DtoString());
        System.out.println(translator.ItoString());

        // stworzenie grafu skierowanego produkcji
        Graph graph = new Graph(translator);
        FoataCalculator foataCalculator = new FoataCalculator();
        //wyznaczenie klas Foaty na podstawie słowa
        foataCalculator.calculateFoata(translator);
        //wyznaczenie klas Foaty na podstawie grafu
        foataCalculator.calculateFoata(graph);
        //zapisanie grafu do pliku .dot
        graph.writeGraphToDotFile("src/dot_files/"+outputFile+".dot");
    }
}

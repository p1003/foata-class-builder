import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileReader {
    private final String fileName;

    public FileReader (String fileName) {
        this.fileName = fileName;
    }

    public List<String> readFile () {
        List<String> result = new ArrayList<>();

        try {
            File file = new File(this.fileName);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                result.add(line.trim());
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return result;
    }
}

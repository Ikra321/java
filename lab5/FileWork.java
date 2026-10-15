import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FileWork {
    private String inputPath;
    private String outputPath;

    public FileWork(String inputPath, String outputPath) {
        this.inputPath = inputPath;
        this.outputPath = outputPath;
    }

    public List<String> readLines() throws IOException {
        return Files.readAllLines(Paths.get(inputPath));
    }

    public void writeText(String text) throws IOException {
        Files.writeString(Paths.get(outputPath), text);
    }
}
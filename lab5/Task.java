import java.util.List;
import java.util.Scanner;

public class Task {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите ширину строки: ");
        int width = scanner.nextInt();

        FileWork fileManager = new FileWork("input.txt", "output.txt");

        try {
            List<String> lines = fileManager.readLines();
            StringBuilder result = new StringBuilder();

            for (String line : lines) {
                if (line.trim().isEmpty()) {
                    result.append("\n");
                    continue;
                }

                String indent = "";
                for (int i = 0; i < line.length(); i++) {
                    char c = line.charAt(i);
                    if (c == ' ' || c == '\t') indent += c;
                    else break;
                }

                String[] words = line.trim().split("\\s+");
                if (words.length <= 1) {
                    result.append(line).append("\n");
                    continue;
                }

                int wordsLen = 0;
                for (String w : words) {
                    wordsLen += w.length();
                }

                int targetWidth = width - indent.length();
                int totalSpaces = targetWidth - wordsLen;
                
                if (totalSpaces <= 0) {
                    result.append(line).append("\n");
                    continue;
                }

                int gaps = words.length - 1;
                int spacePerGap = totalSpaces / gaps;
                int extraSpaces = totalSpaces % gaps;

                result.append(indent);
                for (int i = 0; i < words.length; i++) {
                    result.append(words[i]);
                    if (i < gaps) {
                        int spacesToAdd = spacePerGap + (i < extraSpaces ? 1 : 0);
                        for (int s = 0; s < spacesToAdd; s++) {
                            result.append(" ");
                        }
                    }
                }
                result.append("\n");
            }

            fileManager.writeText(result.toString().trim());
            System.out.println("Успешно.");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
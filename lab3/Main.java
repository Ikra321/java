import java.io.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Введите строку:");

        try {
            InputStreamReader isr = new InputStreamReader(System.in, "cp866");
            BufferedReader br = new BufferedReader(isr);
            
            String line;
            while ((line = br.readLine()) != null) {
                if (line.isEmpty()) {
                    break; 
                }
            
                Task.printResult(line);
            }
            
        } catch (IOException e) {
            System.out.println("Ошибка ввода.");
        }
    }
}
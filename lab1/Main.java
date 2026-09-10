import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        Taylor findSum = new Taylor();

        double x = readX(scanner);
        int k = readK(scanner);

        double standardValue = Math.log1p(x);
        double calculatedValue = findSum.calculateSum(x, k);

        System.out.printf("Ряд Тейлора: %.3f%n", calculatedValue);
        System.out.printf("Math.log1p:   %.3f%n", standardValue);
    }

    static double readX(Scanner scanner) {
        while(true){
            System.out.print("Введите значение x в промежутке (-1; 1]:");
            if(scanner.hasNextDouble()){
                double x = scanner.nextDouble();
                if (x > -1 && x <= 1){
                    return x;
                }
                System.out.println("Неверное значение x. Введите число из заданного промежутка.");
                }
                else{
                    System.out.println("Неверное значение x. Введите число.");
                    scanner.next();
                }
            }  
        }

    static int readK(Scanner scanner) {
        while(true){
            System.out.print("Введите натуральное положительное значение k:");
            if(scanner.hasNextInt()){
                int k = scanner.nextInt();
                if (k >= 1){
                    return k;
                }
                System.out.println("Неверное значение k.");
                }
                else{
                    System.out.println("Неверное значение k. Введите число.");
                    scanner.next();
                }
            }  
        }
    }

class Taylor {
public double  calculateSum(double x, int k){
    double sum = 0.0;
    double epsilon = Math.pow(10, -k);
    double term = x;
    for(int number = 1; Math.abs(term) >= epsilon; ++number) {
        sum += term;
        term = -term * x * number / (number + 1);
    }
    return sum;
}
}

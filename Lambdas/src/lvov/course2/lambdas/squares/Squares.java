package lvov.course2.lambdas.squares;

import java.util.Scanner;
import java.util.stream.*;

public class Squares {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите число:");
        double number = scanner.nextInt();

        System.out.println("Введите количество корней:");
        int numbersCount = scanner.nextInt();

        var squares = Stream.iterate(number, Math::sqrt).limit(numbersCount + 1).skip(1).toList();
        System.out.println("Результат вычислений: ");
        System.out.println(squares);
    }
}

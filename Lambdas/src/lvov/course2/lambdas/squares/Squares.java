package lvov.course2.lambdas.squares;

import java.util.Scanner;
import java.util.stream.*;

public class Squares {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите количество корней:");
        int numbersCount = scanner.nextInt();

        Stream<Double> squares = Stream.iterate(0, x -> x + 1)
                .map(Math::sqrt)
                .limit(numbersCount);

        System.out.println("Результат вычислений:");
        squares.forEach(System.out::println);
    }
}

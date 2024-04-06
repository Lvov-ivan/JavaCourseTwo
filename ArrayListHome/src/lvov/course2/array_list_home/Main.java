package lvov.course2.array_list_home;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static ArrayList<Integer> removeDuplicates(ArrayList<Integer> numbers) {
        ArrayList<Integer> listWithoutRepetitions = new ArrayList<>();

        for (int i = 0; i < numbers.size(); i++) {
            if (numbers.indexOf(numbers.get(i)) >= i) {
                listWithoutRepetitions.add(numbers.get(i));
            }
        }

        return listWithoutRepetitions;
    }

    public static void removeEvenNumbers(ArrayList<Integer> numbers) {
        for (int i = 0; i < numbers.size(); i++) {
            if (numbers.get(i) % 2 == 0) {
                numbers.remove(i);
                i--;
            }
        }
    }

    public static ArrayList<Integer> parseListIntegers(String line) {
        String[] stringsArray = line.replaceAll(" ", "").split(",");
        ArrayList<Integer> numbers = new ArrayList<>();

        for (String item : stringsArray) {
            numbers.add(Integer.parseInt(item));
        }

        return numbers;
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(new FileInputStream("ArrayListHome\\src\\lvov\\course2\\array_list_home\\Numbers.txt"))
        ) {
            StringBuilder stringBuilder = new StringBuilder();

            while (scanner.hasNextLine()) {
                stringBuilder.append(scanner.nextLine());
            }

            ArrayList<Integer> numbersList1 = parseListIntegers(stringBuilder.toString());
            System.out.println("Изначальный список " + numbersList1);
            removeEvenNumbers(numbersList1);
            System.out.println("Список без чётных чисел " + numbersList1);

            ArrayList<Integer> numbersList2 = parseListIntegers(stringBuilder.toString());
            numbersList2 = removeDuplicates(numbersList2);
            System.out.println("Список без повторений " + numbersList2);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Файл не найден " + e);
        }
    }
}
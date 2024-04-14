package lvov.course2.array_list_home;

import java.io.*;
import java.util.ArrayList;

public class Main {
    public static <T> ArrayList<T> getListWithoutDuplicates(ArrayList<T> listWithDuplicates) {
        ArrayList<T> listWithoutDuplicates = new ArrayList<>(listWithDuplicates.size());

        for (T listWithRepetition : listWithDuplicates) {
            if (!listWithoutDuplicates.contains(listWithRepetition)) {
                listWithoutDuplicates.add(listWithRepetition);
            }
        }

        return listWithoutDuplicates;
    }

    public static void removeEvenNumbers(ArrayList<Integer> numbers) {
        for (int i = 0; i < numbers.size(); i++) {
            if (numbers.get(i) % 2 == 0) {
                numbers.remove(i);
                i--;
            }
        }
    }

    public static ArrayList<Integer> parseListIntegers(ArrayList<String> stringsList) {
        ArrayList<Integer> numbers = new ArrayList<>();

        for (String string : stringsList) {
            String[] stringsArray = string.replaceAll(" ", "").split(",");

            for (String number : stringsArray) {
                numbers.add(Integer.parseInt(number));
            }
        }

        return numbers;
    }

    public static ArrayList<String> getListStringsFromFile(String filePath) throws IOException {
        ArrayList<String> stringsList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))
        ) {
            String line;

            while ((line = reader.readLine()) != null) {
                stringsList.add(line);
            }

        } catch (IOException e) {
            throw new IOException("Файл не найден!");
        }

        return stringsList;
    }

    public static void main(String[] args) throws IOException {
        ArrayList<String> stringsList = getListStringsFromFile("ArrayListHome\\src\\lvov\\course2\\array_list_home" +
                "\\Numbers.txt");

        ArrayList<Integer> numbersList1 = parseListIntegers(stringsList);
        System.out.println("Изначальный список: " + numbersList1);
        removeEvenNumbers(numbersList1);
        System.out.println("Список без чётных чисел: " + numbersList1);

        ArrayList<Integer> listWithoutDuplicates = getListWithoutDuplicates(parseListIntegers(stringsList));
        System.out.println("Список без повторений: " + listWithoutDuplicates);
    }
}
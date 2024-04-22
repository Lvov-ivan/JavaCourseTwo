package lvov.course2.array_list_home;

import java.io.*;
import java.util.ArrayList;

public class Main {
    public static <T> ArrayList<T> getListWithoutDuplicates(ArrayList<T> list) {
        ArrayList<T> listWithoutDuplicates = new ArrayList<>(list.size());

        for (T listItem : list) {
            if (!listWithoutDuplicates.contains(listItem)) {
                listWithoutDuplicates.add(listItem);
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

    public static ArrayList<Integer> parseIntegersList(ArrayList<String> stringsList) {
        ArrayList<Integer> numbersList = new ArrayList<>();

        for (String string : stringsList) {
            String[] stringsArray = string.replaceAll(" ", "").split(",");

            for (String arrayItem : stringsArray) {
                numbersList.add(Integer.parseInt(arrayItem));
            }
        }

        return numbersList;
    }

    public static ArrayList<String> getFileLines(String filePath) throws IOException {
        ArrayList<String> lines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        }

        return lines;
    }

    public static void main(String[] args) throws IOException {
        ArrayList<String> fileLines = getFileLines("ArrayListHome/src/lvov/course2/array_list_home/Numbers.txt");

        ArrayList<Integer> numbersList = parseIntegersList(fileLines);
        System.out.println("Изначальный список: " + numbersList);
        removeEvenNumbers(numbersList);
        System.out.println("Список без чётных чисел: " + numbersList);

        ArrayList<Integer> listWithoutDuplicates = getListWithoutDuplicates(parseIntegersList(fileLines));
        System.out.println("Список без повторений: " + listWithoutDuplicates);
    }
}
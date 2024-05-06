package lvov.course2.array_list_home;

import java.io.*;
import java.util.ArrayList;

public class Main {
    public static <T> ArrayList<T> getListWithoutDuplicates(ArrayList<T> list) {
        ArrayList<T> listWithoutDuplicates = new ArrayList<>(list.size());

        for (T item : list) {
            if (!listWithoutDuplicates.contains(item)) {
                listWithoutDuplicates.add(item);
            }
        }

        return listWithoutDuplicates;
    }

    public static void removeEvenNumbers(ArrayList<Integer> numbers) {
        for (int i = numbers.size() - 1; i >= 0; i--) {
            if (numbers.get(i) % 2 == 0) {
                numbers.remove(i);
            }
        }
    }

    public static ArrayList<Integer> convertToIntegersList(ArrayList<String> stringsList) {
        ArrayList<Integer> numbersList = new ArrayList<>();

        for (String string : stringsList) {
            String[] stringsArray = string.replaceAll(" ", "").split(",");

            for (String item : stringsArray) {
                numbersList.add(Integer.parseInt(item));
            }
        }

        return numbersList;
    }

    public static ArrayList<String> getFileLines(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            ArrayList<String> lines = new ArrayList<>();
            String line;

            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }

            return lines;
        }
    }

    public static void main(String[] args) {
        ArrayList<String> fileLines = new ArrayList<>();

        try {
            fileLines = getFileLines("ArrayListHome/src/lvov/course2/array_list_home/Numbers.txt");
        } catch (FileNotFoundException e) {
            System.out.println("Указанный файл не найден");
        } catch (IOException e) {
            System.out.println("Ошибка доступа к указанному файлу");
        }

        System.out.println("Изначальный список:");
        System.out.println(fileLines);

        ArrayList<Integer> numbersList = convertToIntegersList(fileLines);
        removeEvenNumbers(numbersList);
        System.out.println("Список без чётных чисел: " + numbersList);

        ArrayList<Integer> listWithoutDuplicates = getListWithoutDuplicates(convertToIntegersList(fileLines));
        System.out.println("Список без повторений: " + listWithoutDuplicates);
    }
}
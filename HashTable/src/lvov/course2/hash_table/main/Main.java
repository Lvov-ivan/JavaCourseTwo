package lvov.course2.hash_table.main;

import lvov.course2.hash_table.HashTable;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        HashTable<String> list1 = new HashTable<>();
        list1.add("Саша");
        list1.add("Вася");
        list1.add("Катя");
        list1.add("Вася");
        list1.add("Саша");
        list1.add("Петя");
        list1.add("Маша");

        System.out.println("Список1 до удаления: " + list1);
        list1.remove("Вася");
        System.out.println("Список1 после удаления: " + list1);

        if (list1.contains("Петя")) {
            System.out.println("Список содержит элемент: \"Петя\"");
        } else {
            System.out.println("Список не содержит элемент: \"Петя\"");
        }

        HashTable<String> list2 = new HashTable<>();
        list2.add("Вася");
        list2.add("Катя");

        if (list1.containsAll(list2)) {
            System.out.println("Список1 содержит все элементы списка2");
        } else {
            System.out.println("Список1 не содержит все элементы списка2");
        }

        list1.addAll(list2);
        System.out.println("Список1 после добавления в него списка2: " + list1);

        list1.removeAll(list2);
        System.out.println("Список1 после удаления из него всех элементов списка2: " + list1);

        ArrayList<String> list3 = new ArrayList<>();
        list3.add("Петя");
        list3.add("Саша");

        list1.retainAll(list3);
        System.out.println("Список1 в котором остались только элементы списка3" + list1);
    }
}
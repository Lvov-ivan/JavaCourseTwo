package lvov.course2.hash_table.main;

import lvov.course2.hash_table.HashTable;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        HashTable<String> hashTable1 = new HashTable<>();
        hashTable1.add("Саша");
        hashTable1.add("Вася");
        hashTable1.add("Катя");
        hashTable1.add("Вася");
        hashTable1.add("Саша");
        hashTable1.add("Петя");
        hashTable1.add("Маша");

        System.out.println("Хэш-таблица1 до удаления: " + hashTable1);
        hashTable1.remove("Вася");
        System.out.println("Хэш-таблица1 после удаления: " + hashTable1);

        if (hashTable1.contains("Петя")) {
            System.out.println("Хэш-таблица содержит элемент: \"Петя\"");
        } else {
            System.out.println("Хэш-таблица не содержит элемент: \"Петя\"");
        }

        HashTable<String> hashTable2 = new HashTable<>();
        hashTable2.add("Вася");
        hashTable2.add("Катя");

        if (hashTable1.containsAll(hashTable2)) {
            System.out.println("Хэш-таблица1 содержит все элементы хэш-таблицы2");
        } else {
            System.out.println("Хэш-таблица1 не содержит все элементы хэш-таблицы2");
        }

        hashTable1.addAll(hashTable2);
        System.out.println("Хэш-таблица1 после добавления в неё хэш-таблицы2: " + hashTable1);

        hashTable1.removeAll(hashTable2);
        System.out.println("Хэш-таблица1 после удаления из неё всех элементов хэш-таблицы2: " + hashTable1);

        ArrayList<String> list3 = new ArrayList<>();
        list3.add("Петя");
        list3.add("Саша");

        hashTable1.retainAll(list3);
        System.out.println("Хэш-таблица1 в которой остались только элементы списка3" + hashTable1);
    }
}
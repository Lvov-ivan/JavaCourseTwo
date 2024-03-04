package lvov.course2.list.main;

import lvov.course2.list.SinglyLinkedList;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        SinglyLinkedList<String> pets = new SinglyLinkedList<String>();
        pets.addLast("Лиса");
        pets.addLast("Попугай");
        pets.addLast("Рыбки");
        pets.addLast("Собака");
        pets.addLast("Кошка");
        pets.addLast("Ящерица");
        pets.addLast("Корова");

        System.out.println(pets);

        System.out.println("Длина списка: " + pets.getCount());
        System.out.println("Значение первого элемента: " + pets.getFirst());
        System.out.println("Получение элемента по индексу: " + pets.getDataByIndex(3));
        System.out.println("Изменение элемента по индексу, старое значение: " + pets.setDataByIndex(2, "Хорёк"));
        System.out.println("Удалённый элемент по индексу: " + pets.removeNodeByIndex(1));

        System.out.println();
        System.out.println(pets);
        pets.insertNodeByIndex(1, "Лошадь");
        System.out.println("Вставка элемента по индексу " + pets);
        System.out.println("Удаление элемента по значению: " + pets.removeNodeByData("Лошадь"));

        System.out.println();
        System.out.println(pets);
        System.out.println("Удаление первого элемента: " + pets.removeFirstNode());

        System.out.println();
        SinglyLinkedList<String> copyList = pets.copy();
        System.out.println("Список до разворота: " + pets);
        pets.revert();
        System.out.println("Список после разворота: " + pets);
        System.out.println("Скопированный список: " + copyList);
    }
}
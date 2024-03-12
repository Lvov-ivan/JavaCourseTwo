package lvov.course2.list.main;

import lvov.course2.list.SinglyLinkedList;

public class Main {
    public static void main(String[] args) {
        SinglyLinkedList<String> pets = new SinglyLinkedList<>();
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
        System.out.println("Получение элемента по индексу: " + pets.getByIndex(3));
        System.out.println("Изменение элемента по индексу, старое значение: " + pets.setByIndex(2, "Хорёк"));
        System.out.println("Удалённый элемент по индексу: " + pets.removeByIndex(1));

        System.out.println();
        System.out.println(pets);
        pets.addByIndex(1, "Лошадь");
        System.out.println("Вставка элемента по индексу " + pets);
        System.out.println("Удаление элемента по значению: " + pets.removeByData("Лошадь"));

        System.out.println();
        System.out.println(pets);
        System.out.println("Удаление первого элемента: " + pets.removeFirst());

        System.out.println();
        SinglyLinkedList<String> copyList = pets.copy();
        System.out.println("Список до разворота: " + pets);
        pets.revert();
        System.out.println("Список после разворота: " + pets);
        System.out.println("Скопированный список: " + copyList);
    }
}
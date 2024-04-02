package lvov.course2.array_list.main;

import lvov.course2.array_list.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<String> list1 = new ArrayList<>(1);
        list1.add("Мышка");
        list1.add("Кулер");
        list1.add("Оперативная память");
        list1.add("Монитор");

        System.out.println("Список до удаления: " + list1);
        System.out.println("Удалённый элемент: " + list1.remove(1));
        System.out.println("Список после удаления: " + list1);

        list1.add(2, "Клавиатура");
        System.out.println("Список после вставки по индексу 2: " + list1);

        list1.remove("Клавиатура");
        System.out.println("Список после удаления элемента \"Клавиатура\" : " + list1);

        if (list1.contains("Монитор")) {
            System.out.println("Список содержит элемент: \"Монитор\"");
        } else {
            System.out.println("Список не содержит элемент: \"Монитор\"");
        }

        ArrayList<String> list2 = new ArrayList<>();
        list2.add("Мышка");
        list2.add("Монитор");

        if (list1.containsAll(list2)) {
            System.out.println("Список1 содержит все элементы списка2");
        } else {
            System.out.println("Список1 не содержит все элементы списка2");
        }

        ArrayList<String> list3 = new ArrayList<>();
        list3.add("Коврик");
        list3.add("Наушники");

        list1.addAll(2, list3);
        System.out.println("Список1 после добавления в него списка3 начиная с индекса 2: " + list1);

        list1.removeAll(list2);
        System.out.println("Список1 после удаления из него всех элементов списка2 " + list1);

        list1.trimToSize();
        System.out.println("Список1 после применения метода \"trimToSize\" " + list1);

        list1.add("Процессор");
        System.out.println("Список1 до применения метода \"retainAll\" " + list1);
        list1.retainAll(list3);
        System.out.println("Список1 в котором остались только элементы списка3 " + list1);
    }
}
package lvov.course2.binaryTree.main;

import lvov.course2.binaryTree.BinaryTree;

import java.util.TreeMap;
import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
        BinaryTree<Integer> binaryTree = new BinaryTree<>();
        binaryTree.add(8);
        binaryTree.add(10);
        binaryTree.add(3);
        binaryTree.add(1);
        binaryTree.add(6);
        binaryTree.add(14);
        binaryTree.add(4);
        binaryTree.add(7);
        binaryTree.add(13);
        binaryTree.add(30);
        binaryTree.add(25);
        binaryTree.add(35);
        binaryTree.add(23);
        binaryTree.add(24);

        Consumer<Integer> print = value -> System.out.printf("%s; ", value);

        System.out.println("Дерево до удаления:");
        binaryTree.traversalInWidth(print);
        System.out.println();

        System.out.println(binaryTree.remove(14));
        System.out.println("Дерево после удаления:");
        binaryTree.traversalInWidth(print);
        System.out.println();

        if (binaryTree.contains(1)) {
            System.out.println("Значение 1 есть в дереве");
        } else {
            System.out.println("Значение нет в дереве");
        }

        System.out.println("Обход дерева в ширину:");
        binaryTree.traversalInWidth(print);
        System.out.println();

        System.out.println("Обход дерева в глубину без рекурсии:");
        binaryTree.traversalInDepth(print);
        System.out.println();

        System.out.println("Обход дерева в глубину с рекурсией:");
        binaryTree.traversalInDepthWithRecursion(print);
    }
}
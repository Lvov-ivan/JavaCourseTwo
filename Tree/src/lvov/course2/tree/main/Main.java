package lvov.course2.tree.main;

import lvov.course2.tree.BinaryTree;

import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
        BinaryTree<Integer> binaryTree = new BinaryTree<>();
        binaryTree.insert(8);
        binaryTree.insert(10);
        binaryTree.insert(3);
        binaryTree.insert(1);
        binaryTree.insert(6);
        binaryTree.insert(14);
        binaryTree.insert(4);
        binaryTree.insert(7);
        binaryTree.insert(13);
        binaryTree.insert(30);
        binaryTree.insert(25);
        binaryTree.insert(35);
        binaryTree.insert(23);
        binaryTree.insert(24);

        Consumer<Integer> print = value -> System.out.printf("%s; ", value);

        System.out.println("Дерево до удаления:");
        binaryTree.traversalForWidth(print);
        System.out.println();

        System.out.println(binaryTree.remove(14));
        System.out.println("Дерево после удаления:");
        binaryTree.traversalForWidth(print);
        System.out.println();

        if (binaryTree.contains(1)) {
            System.out.println("Значение 1 есть в дереве");
        } else {
            System.out.println("Значение нет в дереве");
        }

        System.out.println("Обход дерева в ширину:");
        binaryTree.traversalForWidth(print);
        System.out.println();

        System.out.println("Обход дерева в глубину без рекурсии:");
        binaryTree.traversalForDepth(print);
        System.out.println();

        System.out.println("Обход дерева в глубину с рекурсией:");
        binaryTree.traversalForDepthWithRecursion(print);
    }
}
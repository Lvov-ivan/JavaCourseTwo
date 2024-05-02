package lvov.course2.tree.main;

import lvov.course2.tree.Tree;
import lvov.course2.tree.TreeNode;

public class Main {
    public static void main(String[] args) {
        Tree<Integer> binaryTree = new Tree<>();
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

        System.out.println("Дерево до удаления:");
        binaryTree.widthFirstCrawl();
        binaryTree.remove(14);
        System.out.println("Дерево после удаления:");
        binaryTree.widthFirstCrawl();

        TreeNode<Integer> node = binaryTree.search(1);
        if (node != null) {
            System.out.println("Значение найденной ноды: " + node);
        } else {
            System.out.println("Нода не найдена");
        }
    }
}
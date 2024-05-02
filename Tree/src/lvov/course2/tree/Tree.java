package lvov.course2.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Tree<T extends Comparable<T>> {
    private TreeNode<T> rootNode;
    private int size = 0;

    public Tree() {
        rootNode = null;
    }

    public void insert(T data) {
        TreeNode<T> newNode = new TreeNode<>(data);

        if (rootNode == null) {
            rootNode = newNode;
            size++;

            return;
        }

        TreeNode<T> currentNode = rootNode;

        while (currentNode != null) {
            if (currentNode.getData().compareTo(data) > 0) {
                if (currentNode.getLeft() == null) {
                    currentNode.setLeft(newNode);
                    size++;

                    break;
                } else {
                    currentNode = currentNode.getLeft();
                }
            } else {
                if (currentNode.getRight() == null) {
                    currentNode.setRight(newNode);
                    size++;

                    break;
                } else {
                    currentNode = currentNode.getRight();
                }
            }
        }
    }

    public TreeNode<T> search(T data) {
        TreeNode<T> currentNode = rootNode;

        while (currentNode != null) {
            if (currentNode.getData().compareTo(data) == 0) {
                return currentNode;
            }

            if (currentNode.getData().compareTo(data) > 0) {
                currentNode = currentNode.getLeft();
            } else {
                currentNode = currentNode.getRight();
            }
        }

        return null;
    }

    public boolean remove(T data) {
        TreeNode<T> parentNode = rootNode;
        TreeNode<T> currentNode = rootNode;

        boolean isLeft = false;

        while (true) {
            if (currentNode == null) {
                return false;
            }

            if (currentNode.getData().compareTo(data) == 0) {
                if (currentNode.getLeft() == null && currentNode.getRight() == null) {
                    parentNode.setLeft(null);
                    size--;

                    return true;
                }

                if (currentNode.getLeft() != null && currentNode.getRight() == null) {
                    if (isLeft) {
                        parentNode.setLeft(currentNode.getLeft());
                    } else {
                        parentNode.setRight(currentNode.getLeft());
                    }

                    size--;

                    return true;
                }

                if (currentNode.getLeft() == null && currentNode.getRight() != null) {
                    if (isLeft) {
                        parentNode.setLeft(currentNode.getRight());
                    } else {
                        parentNode.setRight(currentNode.getRight());
                    }

                    size--;

                    return true;
                }

                TreeNode<T> minLeftNode = currentNode.getRight();
                TreeNode<T> minLeftNodeParent = minLeftNode;

                while (minLeftNode.getLeft() != null) {
                    minLeftNodeParent = minLeftNode;
                    minLeftNode = minLeftNode.getLeft();
                }

                if (minLeftNode.getRight() != null) {
                    minLeftNodeParent.setLeft(null);
                    minLeftNodeParent.setRight(minLeftNode.getRight());
                }

                minLeftNode.setLeft(currentNode.getLeft());
                minLeftNode.setRight(currentNode.getRight());

                if (isLeft) {
                    parentNode.setLeft(minLeftNode);
                } else {
                    parentNode.setRight(minLeftNode);
                }

                size--;

                return true;
            }

            if (currentNode.getLeft() != null && currentNode.getLeft().getData().compareTo(data) > 0) {
                parentNode = currentNode;
                currentNode = currentNode.getLeft();
                isLeft = true;
            } else {
                parentNode = currentNode;
                currentNode = currentNode.getRight();
                isLeft = false;
            }
        }
    }

    public int size() {
        return size;
    }

    public void widthFirstCrawl() {
        Queue<TreeNode<T>> queue = new LinkedList<>();

        queue.add(rootNode);

        while (!queue.isEmpty()) {
            TreeNode<T> currentNode = queue.remove();
            System.out.println(currentNode.getData());

            if (currentNode.getLeft() != null) {
                queue.add(currentNode.getLeft());
            }

            if (currentNode.getRight() != null) {
                queue.add(currentNode.getRight());
            }
        }
    }

    public void depthFirstTraversalWithoutRecursion() {
        ArrayList<TreeNode<T>> stack = new ArrayList<>();

        stack.add(rootNode);

        while (!stack.isEmpty()) {
            TreeNode<T> currentNode = stack.remove(stack.size() - 1);
            System.out.println(currentNode.getData());

            if (currentNode.getRight() != null) {
                stack.add(currentNode.getRight());
            }

            if (currentNode.getLeft() != null) {
                stack.add(currentNode.getLeft());
            }
        }
    }

    public void depthFirstTraversalWithRecursion() {
        visit(rootNode);
    }

    private void visit(TreeNode<T> node) {
        System.out.println(node.getData());

        for (TreeNode<T> child : node.getChildren()) {
            visit(child);
        }
    }
}
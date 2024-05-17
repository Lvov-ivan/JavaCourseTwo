package lvov.course2.tree;

import java.util.*;
import java.util.function.Consumer;

public class BinaryTree<E> {
    private TreeNode<E> root;
    private int size;

    private final Comparator<? super E> comparator;

    public BinaryTree() {
        root = null;
        comparator = null;
    }

    public BinaryTree(Comparator<? super E> comparator) {
        this.comparator = comparator;
    }

    @SuppressWarnings("unchecked")
    final int compare(E o1, E o2) {
        if (o1 == null) {
            return -1;
        }

        if (o2 == null) {
            return 1;
        }

        return comparator == null ? ((Comparable<? super E>) o1).compareTo(o2)
                : comparator.compare(o1, o2);
    }

    public int size() {
        return size;
    }

    public void insert(E data) {
        TreeNode<E> newNode = new TreeNode<>(data);

        if (root == null) {
            root = newNode;
            size++;

            return;
        }

        TreeNode<E> currentNode = root;

        while (true) {
            int comparisonResult = compare(data, currentNode.getData());

            if (comparisonResult < 0) {
                if (currentNode.getLeft() == null) {
                    currentNode.setLeft(newNode);
                    size++;

                    return;
                }

                currentNode = currentNode.getLeft();
            }

            if (comparisonResult >= 0) {
                if (currentNode.getRight() == null) {
                    currentNode.setRight(newNode);
                    size++;

                    return;
                }

                currentNode = currentNode.getRight();
            }
        }
    }

    public boolean contains(E data) {
        TreeNode<E> currentNode = root;

        while (currentNode != null) {
            int comparisonResult = compare(data, currentNode.getData());

            if (comparisonResult == 0) {
                return true;
            }

            if (comparisonResult < 0) {
                currentNode = currentNode.getLeft();
            } else {
                currentNode = currentNode.getRight();
            }
        }

        return false;
    }

    public boolean remove(E data) {
        TreeNode<E> parentNode = null;
        TreeNode<E> currentNode = root;

        boolean isLeft = false;
        int comparisonResult = compare(data, root.getData());

        while (comparisonResult != 0) {
            parentNode = currentNode;

            if (comparisonResult < 0) {
                isLeft = true;
                currentNode = currentNode.getLeft();
            } else {
                isLeft = false;
                currentNode = currentNode.getRight();
            }

            if (currentNode == null) {
                return false;
            }

            comparisonResult = compare(data, currentNode.getData());
        }

        if (currentNode.getLeft() == null && currentNode.getRight() == null) {
            if (currentNode == root) {
                root = null;
            } else if (isLeft) {
                parentNode.setLeft(null);
            } else {
                parentNode.setRight(null);
            }

            size--;

            return true;
        }

        if (currentNode.getRight() == null) {
            if (currentNode == root) {
                root = currentNode.getLeft();
            } else if (isLeft) {
                parentNode.setLeft(currentNode.getLeft());
            } else {
                parentNode.setRight(currentNode.getLeft());
            }

            size--;

            return true;
        }

        if (currentNode.getLeft() == null) {
            if (currentNode == root) {
                root = currentNode.getRight();
            } else if (isLeft) {
                parentNode.setLeft(currentNode.getRight());
            } else {
                parentNode.setRight(currentNode.getRight());
            }

            size--;

            return true;
        }

        TreeNode<E> minLeftNode = currentNode.getRight();
        TreeNode<E> minLeftNodeParent = currentNode;

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

    public void traversalForWidth(Consumer<E> consumer) {
        if (root == null) {
            return;
        }

        Queue<TreeNode<E>> queue = new LinkedList<>();

        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode<E> currentNode = queue.remove();
            consumer.accept(currentNode.getData());

            if (currentNode.getLeft() != null) {
                queue.add(currentNode.getLeft());
            }

            if (currentNode.getRight() != null) {
                queue.add(currentNode.getRight());
            }
        }
    }

    public void traversalForDepth(Consumer<E> consumer) {
        if (root == null) {
            return;
        }

        Deque<TreeNode<E>> stack = new LinkedList<>();

        stack.add(root);

        while (!stack.isEmpty()) {
            TreeNode<E> currentNode = stack.removeLast();
            consumer.accept(currentNode.getData());

            if (currentNode.getRight() != null) {
                stack.add(currentNode.getRight());
            }

            if (currentNode.getLeft() != null) {
                stack.add(currentNode.getLeft());
            }
        }
    }

    public void traversalForDepthWithRecursion(Consumer<E> consumer) {
        visit(root, consumer);
    }

    private void visit(TreeNode<E> node, Consumer<E> consumer) {
        if (node == null) {
            return;
        }

        consumer.accept(node.getData());
        visit(node.getLeft(), consumer);
        visit(node.getRight(), consumer);
    }
}
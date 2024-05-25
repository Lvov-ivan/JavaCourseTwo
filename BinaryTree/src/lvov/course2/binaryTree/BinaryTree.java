package lvov.course2.binaryTree;

import java.util.*;
import java.util.function.Consumer;

public class BinaryTree<E> {
    private BinaryTreeNode<E> root;
    private int size;

    private final Comparator<? super E> comparator;

    public BinaryTree() {
        comparator = null;
    }

    public BinaryTree(Comparator<? super E> comparator) {
        this.comparator = comparator;
    }

    private int compare(E o1, E o2) {
        if (o1 == null && o2 == null) {
            return 1;
        }

        if (o1 == null) {
            return -1;
        }

        if (o2 == null) {
            return 1;
        }

        //noinspection unchecked
        return comparator != null
                ? comparator.compare(o1, o2)
                : ((Comparable<? super E>) o1).compareTo(o2);
    }

    public int size() {
        return size;
    }

    public void add(E data) {
        BinaryTreeNode<E> newNode = new BinaryTreeNode<>(data);

        if (root == null) {
            root = newNode;
            size++;

            return;
        }

        BinaryTreeNode<E> currentNode = root;

        while (true) {
            int comparisonResult = compare(data, currentNode.getData());

            if (comparisonResult < 0) {
                if (currentNode.getLeft() == null) {
                    currentNode.setLeft(newNode);
                    size++;

                    return;
                }

                currentNode = currentNode.getLeft();
            } else {
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
        BinaryTreeNode<E> currentNode = root;

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
        if (size == 0) {
            return false;
        }

        BinaryTreeNode<E> parentNode = findParent(data);

        if (parentNode == null) {
            root = null;
            size = 0;

            return true;
        }

        if (parentNode.getLeft() != null) {
            if (compare(parentNode.getLeft().getData(), data) == 0) {
                deleteChild(parentNode, parentNode.getLeft());

                return true;
            }
        }

        if (parentNode.getRight() != null) {
            if (compare(parentNode.getRight().getData(), data) == 0) {
                deleteChild(parentNode, parentNode.getRight());

                return true;
            }
        }

        return false;
    }

    private BinaryTreeNode<E> findParent(E data) {
        BinaryTreeNode<E> currentParent = null;
        BinaryTreeNode<E> currentNode = root;

        while (true) {
            int compareResult = compare(data, currentNode.getData());

            if (compareResult == 0) {
                return currentParent;
            } else if (compareResult < 0) {
                if (currentNode.getLeft() != null) {
                    currentParent = currentNode;
                    currentNode = currentNode.getLeft();
                } else {
                    return currentNode;
                }
            } else {
                if (currentNode.getRight() != null) {
                    currentParent = currentNode;
                    currentNode = currentNode.getRight();
                } else {
                    return currentNode;
                }
            }
        }
    }

    private void deleteChild(BinaryTreeNode<E> parent, BinaryTreeNode<E> child) {
        if (child.getLeft() == null && child.getRight() == null) {
            parent.setLeft(null);
            size--;

            return;
        }

        if (child.getLeft() == null) {
            parent.setLeft(child.getRight());
            size--;

            return;
        }

        if (child.getRight() == null) {
            parent.setLeft(child.getLeft());
            size--;

            return;
        }

        BinaryTreeNode<E> minNodeParent = child;
        BinaryTreeNode<E> minNode = minNodeParent.getRight();

        while (minNode.getLeft() != null) {
            minNodeParent = minNode;
            minNode = minNode.getLeft();
        }

        minNodeParent.setLeft(minNode.getRight());
        minNode.setLeft(child.getLeft());
        minNode.setRight(child.getRight());

        parent.setRight(minNode);

        size--;
    }

    public void traversalInWidth(Consumer<E> consumer) {
        if (root == null) {
            return;
        }

        Queue<BinaryTreeNode<E>> queue = new LinkedList<>();

        queue.add(root);

        while (!queue.isEmpty()) {
            BinaryTreeNode<E> currentNode = queue.remove();
            consumer.accept(currentNode.getData());

            if (currentNode.getLeft() != null) {
                queue.add(currentNode.getLeft());
            }

            if (currentNode.getRight() != null) {
                queue.add(currentNode.getRight());
            }
        }
    }

    public void traversalInDepth(Consumer<E> consumer) {
        if (root == null) {
            return;
        }

        Deque<BinaryTreeNode<E>> stack = new LinkedList<>();

        stack.add(root);

        while (!stack.isEmpty()) {
            BinaryTreeNode<E> currentNode = stack.removeLast();
            consumer.accept(currentNode.getData());

            if (currentNode.getRight() != null) {
                stack.add(currentNode.getRight());
            }

            if (currentNode.getLeft() != null) {
                stack.add(currentNode.getLeft());
            }
        }
    }

    public void traversalInDepthWithRecursion(Consumer<E> consumer) {
        visitNode(root, consumer);
    }

    private void visitNode(BinaryTreeNode<E> node, Consumer<E> consumer) {
        if (node == null) {
            return;
        }

        consumer.accept(node.getData());
        visitNode(node.getLeft(), consumer);
        visitNode(node.getRight(), consumer);
    }
}
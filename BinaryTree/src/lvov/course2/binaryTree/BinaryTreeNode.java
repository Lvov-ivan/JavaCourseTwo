package lvov.course2.binaryTree;

class BinaryTreeNode<E> {
    private BinaryTreeNode<E> left;
    private BinaryTreeNode<E> right;
    private E data;

    public BinaryTreeNode(E data) {
        this.data = data;
    }

    public BinaryTreeNode<E> getLeft() {
        return left;
    }

    public void setLeft(BinaryTreeNode<E> left) {
        this.left = left;
    }

    public BinaryTreeNode<E> getRight() {
        return right;
    }

    public void setRight(BinaryTreeNode<E> right) {
        this.right = right;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }
}
package lvov.course2.tree;

import java.util.ArrayList;

public class TreeNode<T extends Comparable<T>> {
    private TreeNode<T> left;
    private TreeNode<T> right;
    private T data;

    public TreeNode(T data) {
        this.data = data;
    }

    public TreeNode<T> getLeft() {
        return left;
    }

    public void setLeft(TreeNode<T> left) {
        this.left = left;
    }

    public TreeNode<T> getRight() {
        return right;
    }

    public void setRight(TreeNode<T> right) {
        this.right = right;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ArrayList<TreeNode<T>> getChildren() {
        ArrayList<TreeNode<T>> children = new ArrayList<>();
        if (left != null) {
            children.add(left);
        }

        if (right != null) {
            children.add(right);
        }

        return children;
    }

    @Override
    public String toString() {
        return data.toString();
    }
}
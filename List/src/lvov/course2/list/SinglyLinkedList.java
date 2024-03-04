package lvov.course2.list;

public class SinglyLinkedList<T> {
    private ListItem<T> head;
    private int count;

    public int getCount() {
        return count;
    }

    public T getFirst() {
        return head.getData();
    }

    @Override
    public String toString() {
        if (count == 0) {
            return "[]";
        }

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append('[');

        for (ListItem<T> currentNode = head; currentNode != null; currentNode = currentNode.getNext()) {
            stringBuilder
                    .append(currentNode.getData())
                    .append(", ");
        }

        stringBuilder
                .delete(stringBuilder.length() - 2, stringBuilder.length())
                .append(']');

        return stringBuilder.toString();
    }

    public void addFirst(T data) {
        head = new ListItem<>(data, head);

        count++;
    }

    public void addLast(T data) {
        if (count == 0) {
            addFirst(data);
        } else {
            insertNodeByIndex(count, data);
        }
    }

    private ListItem<T> iterateToNode(int index) {
        int i = 0;
        ListItem<T> currentNode;

        for (currentNode = head; currentNode != null; currentNode = currentNode.getNext()) {
            if (i == index) {
                break;
            }

            i++;
        }

        return currentNode;
    }

    public T getDataByIndex(int index) {
        checkIndex(index);

        return iterateToNode(index).getData();
    }

    public T setDataByIndex(int index, T data) {
        checkIndex(index);

        ListItem<T> currentNode = iterateToNode(index);

        T oldData = currentNode.getData();
        currentNode.setData(data);

        return oldData;
    }

    public T removeNodeByIndex(int index) {
        checkIndex(index);

        if (index == 0) {
            return removeFirstNode();
        }

        ListItem<T> previousNode = iterateToNode(index - 1);
        ListItem<T> currentNode = iterateToNode(index);
        previousNode.setNext(currentNode.getNext());
        count--;

        return currentNode.getData();
    }

    public void insertFirstNode(T data) {
        addFirst(data);
    }

    public void insertNodeByIndex(int index, T data) {
        checkIndex(index);

        if (index == 0) {
            addFirst(data);
            return;
        }

        ListItem<T> previousNode = iterateToNode(index - 1);
        ListItem<T> currentNode = previousNode.getNext();
        previousNode.setNext(new ListItem<>(data, currentNode));

        count++;
    }

    public boolean removeNodeByData(T data) {
        for (ListItem<T> currentNode = head, previousNode = null; currentNode != null; previousNode = currentNode, currentNode = currentNode.getNext()) {
            if (head.getData().equals(data)) {
                removeFirstNode();
                return true;
            }

            if (currentNode.getData() == null && data != null) {
                continue;
            }

            if (currentNode.getData() == null && data == null || currentNode.getData().equals(data)) {
                assert previousNode != null;
                previousNode.setNext(currentNode.getNext());
                count--;

                return true;
            }
        }

        return false;
    }

    public T removeFirstNode() {
        T deletedNodeData = head.getData();
        head = head.getNext();
        count--;

        return deletedNodeData;
    }

    public void revert() {
        ListItem<T> currentNode = head;
        ListItem<T> previousNode = null;

        while (currentNode.getNext() != null) {
            ListItem<T> nextNode = currentNode.getNext();
            currentNode.setNext(previousNode);
            previousNode = currentNode;
            currentNode = nextNode;
        }

        currentNode.setNext(previousNode);
        head = currentNode;
    }

    public SinglyLinkedList<T> copy() {
        SinglyLinkedList<T> newList = new SinglyLinkedList<>();
        if (count == 0) {
            return newList;
        }

        ListItem<T> lastNodeNewList = new ListItem<>(head.getData());
        newList.head = lastNodeNewList;

        newList.count++;

        for (ListItem<T> currentNode = head.getNext(); currentNode != null; currentNode = currentNode.getNext()) {
            ListItem<T> newNode = new ListItem<>(currentNode.getData());
            lastNodeNewList.setNext(newNode);
            lastNodeNewList = newNode;

            newList.count++;
        }

        return newList;
    }

    private void checkIndex(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Индекс не может быть отрицательным. Передаваемый индекс = " + index);
        }

        if (index > count) {
            throw new IndexOutOfBoundsException("Индекс не может быть больше размера списка. Допустимые границы от 0 до " + count +
                    " Передаваемый индекс = " + index);
        }
    }
}
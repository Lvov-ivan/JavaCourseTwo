package lvov.course2.list;

import java.util.NoSuchElementException;
import java.util.Objects;

public class SinglyLinkedList<E> {
    private ListItem<E> head;
    private int count;

    public int getCount() {
        return count;
    }

    public E getFirst() {
        if (count == 0) {
            throw new NoSuchElementException("Список пустой");
        }

        return head.getData();
    }

    @Override
    public String toString() {
        if (count == 0) {
            return "[]";
        }

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append('[');

        for (ListItem<E> node = head; node != null; node = node.getNext()) {
            stringBuilder
                    .append(node.getData())
                    .append(", ");
        }

        stringBuilder
                .delete(stringBuilder.length() - 2, stringBuilder.length())
                .append(']');

        return stringBuilder.toString();
    }

    public void addFirst(E data) {
        head = new ListItem<>(data, head);
        count++;
    }

    public void addLast(E data) {
        addByIndex(count, data);
    }

    private ListItem<E> getNode(int index) {
        ListItem<E> node = head;

        for (int i = 0; i < index; i++) {
            node = node.getNext();
        }

        return node;
    }

    public E getByIndex(int index) {
        checkIndex(index);

        return getNode(index).getData();
    }

    public E setByIndex(int index, E data) {
        checkIndex(index);

        ListItem<E> node = getNode(index);

        E oldData = node.getData();
        node.setData(data);

        return oldData;
    }

    public E removeByIndex(int index) {
        checkIndex(index);

        if (index == 0) {
            return removeFirst();
        }

        ListItem<E> previousNode = getNode(index - 1);
        ListItem<E> currentNode = previousNode.getNext();
        previousNode.setNext(currentNode.getNext());
        count--;

        return currentNode.getData();
    }

    public void addByIndex(int index, E data) {
        if (index < 0 || index > count) {
            throw new IndexOutOfBoundsException("Индекс не может быть отрицательным или больше размера списка. " +
                    "Допустимые границы от 0 до " + count + ". Передаваемый индекс = " + index);
        }

        if (index == 0) {
            addFirst(data);
            return;
        }

        ListItem<E> previousNode = getNode(index - 1);
        previousNode.setNext(new ListItem<>(data, previousNode.getNext()));

        count++;
    }

    public boolean removeByData(E data) {
        if (count == 0) {
            return false;
        }

        if (Objects.equals(head.getData(), data)) {
            removeFirst();
            return true;
        }

        for (ListItem<E> currentNode = head.getNext(), previousNode = head; currentNode != null; previousNode = currentNode, currentNode = currentNode.getNext()) {
            if (Objects.equals(currentNode.getData(), data)) {
                previousNode.setNext(currentNode.getNext());
                count--;

                return true;
            }
        }

        return false;
    }

    public E removeFirst() {
        if (count == 0) {
            throw new NoSuchElementException("Список пустой");
        }

        E removedData = head.getData();
        head = head.getNext();
        count--;

        return removedData;
    }

    public void revert() {
        if (count <= 1) {
            return;
        }

        ListItem<E> currentNode = head;
        ListItem<E> previousNode = null;

        while (currentNode != null) {
            ListItem<E> nextNode = currentNode.getNext();
            currentNode.setNext(previousNode);
            previousNode = currentNode;
            currentNode = nextNode;
        }

        head = previousNode;
    }

    public SinglyLinkedList<E> copy() {
        SinglyLinkedList<E> copiedList = new SinglyLinkedList<>();

        if (count == 0) {
            return copiedList;
        }

        ListItem<E> copiedListNode = new ListItem<>(head.getData());
        copiedList.count = count;
        copiedList.head = copiedListNode;

        for (ListItem<E> currentNode = head.getNext(); currentNode != null; currentNode = currentNode.getNext()) {
            copiedListNode.setNext(new ListItem<>(currentNode.getData()));
            copiedListNode = copiedListNode.getNext();
        }

        return copiedList;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= count) {
            throw new IndexOutOfBoundsException("Индекс не может быть отрицательным, больше или равным размеру списка. " +
                    "Допустимые границы от 0 до " + (count - 1) + ". Передаваемый индекс = " + index);
        }
    }
}
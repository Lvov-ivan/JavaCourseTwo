package lvov.course2.array_list;

import java.util.*;
import java.util.List;

public class ArrayList<E> implements List<E> {
    private int modCount = 0;
    private int size;
    private E[] items;

    public ArrayList() {
        int defaultCapacity = 16;
        //noinspection unchecked
        items = (E[]) new Object[defaultCapacity];
    }

    public ArrayList(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Размер списка не может быть меньше или равен 0");
        }

        //noinspection unchecked
        items = (E[]) new Object[capacity];
    }

    private void increaseCapacity() {
        items = Arrays.copyOf(items, items.length * 2);
    }

    private void provideCapacity(int collectionSize) {
        items = Arrays.copyOf(items, size + collectionSize);
    }

    public void trimToSize() {
        items = Arrays.copyOf(items, size);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object object) {
        return indexOf(object) >= 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new ArrayIterator();
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(items, size);
    }

    @Override
    public <T> T[] toArray(T[] array) {
        if (array.length < size) {
            //noinspection unchecked
            return (T[]) Arrays.copyOf(items, size, array.getClass());
        }

        System.arraycopy(items, 0, array, 0, size);

        if (array.length > size) {
            array[size] = null;
        }

        return array;
    }

    @Override
    public boolean add(E item) {
        add(size, item);

        return true;
    }

    @Override
    public boolean remove(Object object) {
        int index = indexOf(object);

        if (index >= 0) {
            remove(index);

            return true;
        }

        return false;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        for (Object item : collection) {
            if (!contains(item)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> collection) {
        if (collection == null) {
            throw new NullPointerException("Переданная коллекция = null");
        }

        if (collection.isEmpty()) {
            return false;
        }

        addAll(size, collection);

        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> collection) {
        checkIndexForAdding(index);

        if (collection == null) {
            throw new NullPointerException("Переданная коллекция = null");
        }

        if (collection.isEmpty()) {
            return false;
        }

        if (size + collection.size() >= items.length) {
            provideCapacity(collection.size());
        }

        System.arraycopy(items, index, items, index + collection.size(), size - index);
        size = size + collection.size();

        int insertionIndex = index;

        for (E item : collection) {
            items[insertionIndex] = item;
            insertionIndex++;
        }

        modCount++;

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        if (collection.isEmpty() || size == 0) {
            return false;
        }

        boolean isModified = false;

        for (int i = 0; i < items.length; i++) {
            if (collection.contains(items[i])) {
                remove(i);
                i--;
                isModified = true;
            }
        }

        return isModified;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        boolean isModified = false;

        for (int i = size - 1; i >= 0; i--) {
            if (!collection.contains(items[i])) {
                remove(i);
                isModified = true;
            }
        }

        return isModified;
    }

    @Override
    public void clear() {
        Arrays.fill(items, null);
        size = 0;
    }

    @Override
    public E get(int index) {
        checkIndex(index);

        return items[index];
    }

    @Override
    public E set(int index, E item) {
        checkIndex(index);

        E deletedItem = items[index];
        items[index] = item;

        return deletedItem;
    }

    @Override
    public void add(int index, E item) {
        checkIndexForAdding(index);

        if (size >= items.length) {
            increaseCapacity();
        }

        System.arraycopy(items, index, items, index + 1, size - index);
        items[index] = item;
        size++;
        modCount++;
    }

    @Override
    public E remove(int index) {
        checkIndex(index);

        E deletedItem = items[index];
        System.arraycopy(items, index + 1, items, index, size - 1 - index);
        items[size - 1] = null;
        size--;
        modCount++;

        return deletedItem;
    }

    @Override
    public int indexOf(Object item) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(items[i], item)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object item) {
        for (int i = size - 1; i >= 0; i--) {
            if (Objects.equals(items[i], item)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public ListIterator<E> listIterator() {
        //noinspection DataFlowIssue
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        //noinspection DataFlowIssue
        return null;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        //noinspection DataFlowIssue
        return null;
    }

    private class ArrayIterator implements Iterator<E> {
        private int index = -1;
        private final int initialModCount = modCount;

        public boolean hasNext() {
            return index + 1 < size;
        }

        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException("В коллекции больше нет элементов");
            }

            if (modCount != initialModCount) {
                throw new ConcurrentModificationException("Коллекция была изменена");
            }

            index++;
            return items[index];
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Переданный индекс не может быть отрицательным, равным или больше размера " +
                    "списка. Переданный индекс = " + index + ". Допустимые границы от 0 до " + (size - 1));
        }
    }

    private void checkIndexForAdding(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Переданный индекс не может быть отрицательным или больше размера " +
                    "списка. Переданный индекс = " + index + " Размер списка = " + size);
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append('[');

        for (int i = 0; i < size; i++) {
            stringBuilder
                    .append(items[i])
                    .append(", ");
        }

        if (size > 0) {
            stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        }

        stringBuilder.append(']');

        return stringBuilder.toString();
    }
}
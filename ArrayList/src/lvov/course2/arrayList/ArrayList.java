package lvov.course2.arrayList;

import java.util.*;
import java.util.List;

public class ArrayList<T> implements List<T> {
    private int modCount = 1;
    private int size;
    private int capacity;
    private T[] items;

    public ArrayList() {
        capacity = 16;
        //noinspection unchecked
        items = (T[]) new Object[capacity];
    }

    public ArrayList(int capacity) {
        this.capacity = capacity;
        //noinspection unchecked
        items = (T[]) new Object[capacity];
    }

    private void increaseCapacity() {
        capacity = items.length * 2;
        items = Arrays.copyOf(items, capacity);
    }

    public void trimToSize() {
        capacity = size;
        //noinspection unchecked
        T[] newArray = (T[]) new Object[capacity];
        System.arraycopy(items, 0, newArray, 0, size);
        items = newArray;
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
        if (size == 0) {
            return false;
        }

        for (T item : items) {
            if (Objects.equals(item, object)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayIterator<>();
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(items, size);
    }

    @Override
    public <T1> T1[] toArray(T1[] array) {
        if (array.length != size()) {
            array = Arrays.copyOf(array, array.length + (size() - array.length));
        }

        for (int i = 0; i < size(); i++) {
            //noinspection unchecked
            array[i] = (T1) get(i);
        }

        return array;
    }

    @Override
    public boolean add(T item) {
        if (size >= capacity) {
            increaseCapacity();
        }

        items[size] = item;
        size++;
        modCount++;

        return true;
    }

    @Override
    public boolean remove(Object item) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(items[i], item)) {
                remove(i);
                modCount++;

                return true;
            }
        }

        return false;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        for (Object collectionItem : collection) {
            if (!contains(collectionItem)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> collection) {
        if (collection == null || collection.isEmpty()) {
            return false;
        }

        for (T collectionItem : collection) {
            add(collectionItem);
        }

        modCount++;

        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> collection) {
        checkIndexWhenAdding(index);

        if (collection == null || collection.isEmpty() || (index < 0)) {
            return false;
        }

        if (size + collection.size() >= capacity) {
            increaseCapacity();
        }

        System.arraycopy(items, index, items, index + collection.size(), collection.size());
        size = size + collection.size();

        for (T collectionItem : collection) {
            items[index++] = collectionItem;
        }

        modCount++;

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        boolean modified = false;

        for (Object collectionItem : collection) {
            if (remove(collectionItem)) {
                modified = true;
                modCount++;
            }
        }

        return modified;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        boolean modified = false;

        for (int i = 0; i < size; i++) {
            if (collection.contains(items[i])) {
                i++;
            } else {
                remove(i);
                i--;
                modCount++;

                modified = true;
            }
        }

        return modified;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            items[i] = null;
            modCount++;
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index);

        return items[index];
    }

    @Override
    public T set(int index, T item) {
        checkIndex(index);

        modCount++;
        return items[index] = item;
    }

    @Override
    public void add(int index, T item) {
        checkIndexWhenAdding(index);

        System.arraycopy(items, index, items, index + 1, size - index);
        items[index] = item;
        size++;
        modCount++;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);

        T oldData = items[index];
        System.arraycopy(items, index + 1, items, index, size - 1 - index);
        items[size - 1] = null;
        size--;
        modCount++;

        return oldData;
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
        int lastIndexOf = -1;

        for (int i = 0; i < size; i++) {
            if (Objects.equals(items[i], item)) {
                lastIndexOf = i;
            }
        }

        return lastIndexOf;
    }

    @Override
    public ListIterator<T> listIterator() {
        //noinspection DataFlowIssue
        return null;
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        //noinspection DataFlowIssue
        return null;
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        //noinspection DataFlowIssue
        return null;
    }

    private class ArrayIterator<T1> implements Iterator<T1> {
        private int index = -1;
        private final int modCountValue = modCount;

        public boolean hasNext() {
            return index + 1 < size;
        }

        public T1 next() {
            if (index > size) {
                throw new NoSuchElementException("В коллекции больше нет элементов");
            }

            if (modCount != modCountValue) {
                throw new ConcurrentModificationException("Коллекция была изменена");
            }

            index++;
            //noinspection unchecked
            return (T1) items[index];
        }
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Переданный индекс не может быть отрицательным или больше размера " +
                    "списка. Переданный индекс = " + index + " Размер списка = " + (size - 1));
        }
    }

    private void checkIndexWhenAdding(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Переданный индекс не может быть отрицательным или больше размера " +
                    "списка. Переданный индекс = " + index + " Размер списка = " + size);
        }
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        for (T item : items) {
            stringBuilder
                    .append(item)
                    .append(", ");
        }

        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());

        return stringBuilder.toString();
    }
}
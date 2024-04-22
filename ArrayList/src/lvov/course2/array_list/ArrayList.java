package lvov.course2.array_list;

import java.util.*;
import java.util.List;

public class ArrayList<E> implements List<E> {
    private int modCount;
    private int size;
    private E[] items;
    private final int DEFAULT_CAPACITY = 16;

    public ArrayList() {

        //noinspection unchecked
        items = (E[]) new Object[DEFAULT_CAPACITY];
    }

    public ArrayList(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Вместимость списка не может быть меньше 0. Переданная вместимость = "
                    + capacity);
        }

        //noinspection unchecked
        items = (E[]) new Object[capacity];
    }

    private void increaseCapacity() {
        if (items.length == 0) {
            //noinspection unchecked
            items = (E[]) new Object[DEFAULT_CAPACITY];
        } else {
            items = Arrays.copyOf(items, items.length * 2);
        }
    }

    public void provideCapacity(int requiredCapacity) {
        if (size + requiredCapacity > items.length) {
            int newLength = (requiredCapacity + size) * 2;
            items = Arrays.copyOf(items, newLength);
        }
    }

    public void trimToSize() {
        if (size < items.length) {
            items = Arrays.copyOf(items, size);
        }
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

        //noinspection SuspiciousSystemArraycopy
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
        return addAll(size, collection);
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

        provideCapacity(collection.size());

        System.arraycopy(items, index, items, index + collection.size(), size - index);
        size += collection.size();

        int i = index;

        for (E item : collection) {
            items[i] = item;
            i++;
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

        for (int i = size - 1; i >= 0; i--) {
            if (collection.contains(items[i])) {
                remove(i);
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
        if (size > 0) {
            Arrays.fill(items, 0, size, null);
            size = 0;
            modCount++;
        }
    }

    @Override
    public E get(int index) {
        checkIndex(index);

        return items[index];
    }

    @Override
    public E set(int index, E item) {
        checkIndex(index);

        E oldItem = items[index];
        items[index] = item;

        return oldItem;
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

        E removedItem = items[index];
        System.arraycopy(items, index + 1, items, index, size - 1 - index);
        items[size - 1] = null;
        size--;
        modCount++;

        return removedItem;
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

        @Override
        public boolean hasNext() {
            return index + 1 < size;
        }

        @Override
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
            throw new IndexOutOfBoundsException("Индекс вышел за границы допустимого диапазона. Допустимые границы" +
                    " от 0 до " + (size - 1) + ". Переданный индекс = " + index);
        }
    }

    private void checkIndexForAdding(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Индекс вышел за границы допустимого диапазона. Допустимые границы" +
                    " от 0 до " + size + ". Переданный индекс = " + index);
        }
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append('[');

        for (int i = 0; i < size; i++) {
            stringBuilder
                    .append(items[i])
                    .append(", ");
        }

        stringBuilder
                .delete(stringBuilder.length() - 2, stringBuilder.length())
                .append(']');

        return stringBuilder.toString();
    }
}
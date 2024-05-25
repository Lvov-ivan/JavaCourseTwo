package lvov.course2.hash_table;

import java.util.*;

public class HashTable<E> implements Collection<E> {
    private static final int DEFAULT_CAPACITY = 100;

    private final ArrayList<E>[] lists;
    private int size;
    private int modCount;

    public HashTable() {
        //noinspection unchecked
        lists = (ArrayList<E>[]) new ArrayList[DEFAULT_CAPACITY];
    }

    public HashTable(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Вместимость хэш-таблицы не может быть меньше или равна 0. " +
                    "Переданная вместимость = " + capacity);
        }

        //noinspection unchecked
        lists = (ArrayList<E>[]) new ArrayList[capacity];
    }

    private int getIndex(Object object) {
        if (object == null) {
            return 0;
        }

        return Math.abs(object.hashCode() % lists.length);
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
        int index = getIndex(object);

        return lists[index] != null && lists[index].contains(object);
    }

    @Override
    public Iterator<E> iterator() {
        return new HashTableIterator();
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        int i = 0;

        for (ArrayList<E> list : lists) {
            if (list != null) {
                for (Object object : list) {
                    array[i] = object;
                    i++;
                }
            }
        }

        return array;
    }

    @Override
    public <T> T[] toArray(T[] array) {
        if (array.length < size) {
            //noinspection unchecked
            return (T[]) Arrays.copyOf(toArray(), size, array.getClass());
        }

        //noinspection SuspiciousSystemArraycopy
        System.arraycopy(toArray(), 0, array, 0, size);

        if (array.length > size) {
            array[size] = null;
        }

        return array;
    }

    @Override
    public boolean add(E item) {
        int index = getIndex(item);

        if (lists[index] == null) {
            lists[index] = new ArrayList<>();
        }

        lists[index].add(item);
        size++;
        modCount++;

        return true;
    }

    @Override
    public boolean remove(Object object) {
        int index = getIndex(object);

        if (lists[index] != null && lists[index].remove(object)) {
            size--;
            modCount++;
            return true;
        }

        return false;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        if (collection == null) {
            throw new NullPointerException("Переданная коллекция = null");
        }

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

        for (E item : collection) {
            add(item);
        }

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        if (collection == null) {
            throw new NullPointerException("Переданная коллекция = null");
        }

        if (collection.isEmpty() || size == 0) {
            return false;
        }

        boolean isModified = false;

        for (ArrayList<E> list : lists) {
            if (list != null) {
                int initialListSize = list.size();

                if (list.removeAll(collection)) {
                    isModified = true;
                    size -= initialListSize - list.size();
                }
            }
        }

        if (isModified) {
            modCount++;
        }

        return isModified;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        if (collection == null) {
            throw new NullPointerException("Переданная коллекция = null");
        }

        if (size == 0) {
            return false;
        }

        boolean isModified = false;

        for (ArrayList<E> list : lists) {
            if (list != null) {
                int initialListSize = list.size();

                if (list.retainAll(collection)) {
                    isModified = true;
                    size -= initialListSize - list.size();
                }
            }
        }

        if (isModified) {
            modCount++;
        }

        return isModified;
    }

    @Override
    public void clear() {
        if (size == 0) {
            return;
        }

        for (ArrayList<E> list : lists) {
            if (list != null) {
                list.clear();
            }
        }

        size = 0;
        modCount++;
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append('[');

        for (ArrayList<E> list : lists) {
            if (list != null && !list.isEmpty()) {
                stringBuilder
                        .append(list)
                        .append(", ");
            }
        }

        stringBuilder
                .delete(stringBuilder.length() - 2, stringBuilder.length())
                .append(']');

        return stringBuilder.toString();
    }

    private class HashTableIterator implements Iterator<E> {
        private int passedItemsCount;
        private int listIndex = -1;
        private Iterator<E> listIterator;
        private final int initialModCount = modCount;

        @Override
        public boolean hasNext() {
            return passedItemsCount < size;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException("В коллекции больше нет элементов");
            }

            if (modCount != initialModCount) {
                throw new ConcurrentModificationException("Коллекция была изменена");
            }

            if (listIterator == null || !listIterator.hasNext()) {
                listIndex++;
                listIterator = null;
            }

            while (lists[listIndex] == null || lists[listIndex].isEmpty()) {
                listIndex++;
            }

            if (listIterator == null) {
                listIterator = lists[listIndex].iterator();
            }

            passedItemsCount++;

            return listIterator.next();
        }
    }
}
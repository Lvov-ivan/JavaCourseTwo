package lvov.course2.hash_table;

import java.util.*;

public class HashTable<E> implements Collection<E> {
    private final ArrayList<E>[] table;
    private int size;
    private int modCount;
    final private int DEFAULT_CAPACITY = 100;

    public HashTable() {
        //noinspection unchecked
        table = (ArrayList<E>[]) new ArrayList[DEFAULT_CAPACITY];
    }

    public HashTable(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Размер хэш-таблицы не может быть меньше или равен 0. " +
                    "Переданная вместимость = " + capacity);
        }

        //noinspection unchecked
        table = (ArrayList<E>[]) new ArrayList[capacity];
    }

    private int getIndex(Object object) {
        if (object == null) {
            return 0;
        }

        return Math.abs(object.hashCode() % table.length);
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

        if ((object == null && table[0] == null) || table[index] == null) {
            return false;
        }

        return table[index].contains(object);
    }

    @Override
    public Iterator<E> iterator() {
        return new TableIterator();
    }

    @Override
    public Object[] toArray() {
        Object[] objectsArray = new Object[size];
        int insertIndex = 0;

        for (ArrayList<E> list : table) {
            if (list != null) {
                for (Object object : list) {
                    objectsArray[insertIndex] = object;
                    insertIndex++;
                }
            }
        }

        return objectsArray;
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

        if (table[index] == null) {
            table[index] = new ArrayList<>();
        }

        table[index].add(item);
        size++;
        modCount++;

        return true;
    }

    @Override
    public boolean remove(Object object) {
        int index = getIndex(object);

        if (table[index].remove(object)) {
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
            int index = getIndex(item);

            if (table[index] == null || !contains(item)) {
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

        for (ArrayList<E> list : table) {
            if (list != null) {
                for (int i = 0; i < list.size(); i++) {
                    if (collection.contains(list.get(i))) {
                        list.remove(i);
                        i--;
                        isModified = true;
                    }
                }
            }
        }

        return isModified;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        if (collection == null) {
            throw new NullPointerException("Переданная коллекция = null");
        }

        if (collection.isEmpty() || size == 0) {
            return false;
        }

        boolean isModified = false;

        for (ArrayList<E> list : table) {
            if (list != null) {
                for (int i = 0; i < list.size(); i++) {
                    if (!collection.contains(list.get(i))) {
                        list.remove(i);
                        i--;
                        isModified = true;
                    }
                }
            }
        }

        return isModified;
    }

    @Override
    public void clear() {
        if (size > 0) {
            for (ArrayList<E> list : table) {
                if (list != null) {
                    list.clear();
                }
            }

            size = 0;
            modCount++;
        }
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append('[');

        for (ArrayList<E> list : table) {
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

    private class TableIterator implements Iterator<E> {
        private int passedItemsCount = 0;
        private int arrayIndex;
        private final int initialModCount = modCount;
        private Iterator<E> listIterator;

        @Override
        public boolean hasNext() {
            return passedItemsCount < size;
        }

        private boolean isThereElementInHashTable() {
            arrayIndex++;

            while (arrayIndex < table.length && table[arrayIndex] == null) {
                arrayIndex++;
            }

            return arrayIndex < table.length && table[arrayIndex] != null;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException("В коллекции больше нет элементов");
            }

            if (modCount != initialModCount) {
                throw new ConcurrentModificationException("Коллекция была изменена");
            }

            if ((listIterator == null || !listIterator.hasNext()) && isThereElementInHashTable()) {
                listIterator = table[arrayIndex].iterator();
            }

            passedItemsCount++;
            return listIterator.next();
        }
    }
}
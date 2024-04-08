package lvov.course2.hash_table;

import java.util.*;

public class HashTable<E> implements Collection<E> {
    private final ArrayList[] items;
    private int size;
    private int modCount = 1;

    public HashTable() {
        items = new ArrayList[100];
    }

    public HashTable(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Размер списка не может быть меньше или равен 0");
        }

        items = new ArrayList[size];
    }

    private int getIndex(Object item) {
        return item.hashCode() % items.length;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return items.length == 0;
    }

    @Override
    public boolean contains(Object item) {
        if (item == null) {
            throw new NullPointerException("Переданный объект = null");
        }

        int index = getIndex(item);

        if (items[index] != null) {
            return items[index].contains(item);
        }

        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new TableIterator();
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
        if (item == null) {
            throw new NullPointerException("Переданный объект = null");
        }

        int index = getIndex(item);

        if (items[index] == null) {
            items[index] = new ArrayList<>();

            if (items[index].add(item)) {
                size++;
                modCount++;
                return true;
            }
        }

        if (items[index] != null) {
            if (items[index].add(item)) {
                size++;
                modCount++;
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean remove(Object item) {
        int index = getIndex(item);

        if (items[index].remove(item)) {
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

            if (items[index] == null || !items[index].contains(item)) {
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
            if (!add(item)) {
                return false;
            }
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

        boolean modified = false;

        for (Object collectionItem : collection) {
            if (remove(collectionItem)) {
                modCount++;
                modified = true;
            } else {
                modified = false;
                break;
            }
        }

        return modified;
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        if (collection == null) {
            throw new NullPointerException("Переданная коллекция = null");
        }

        if (collection.isEmpty() || size == 0) {
            return false;
        }

        boolean modified = false;

        for (Iterator<E> i = iterator(); i.hasNext(); ) {
            if (!collection.contains(i.next())) {
                i.remove();
                modified = true;
            }
        }

        return modified;
    }

    @Override
    public void clear() {
        Arrays.fill(items, null);
        size = 0;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append('[');

        for (var item : items) {
            if (item != null && !item.isEmpty()) {
                stringBuilder
                        .append(item)
                        .append(", ");
            }
        }

        if (size > 0) {
            stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length());
        }

        stringBuilder.append(']');

        return stringBuilder.toString();
    }

    private class TableIterator implements Iterator<E> {
        private int index = -1;
        private int arrayIndex = 0;
        private final int initialModCount = modCount;
        Iterator subIterator = null;

        @Override
        public boolean hasNext() {
            if (index == size) {
                return false;
            }

            if (subIterator == null || !subIterator.hasNext()) {
                if (isThereElementInTable()) {
                    subIterator = items[arrayIndex].iterator();
                } else {
                    return false;
                }
            }

            return subIterator.hasNext();
        }

        private boolean isThereElementInTable() {
            arrayIndex++;

            while (arrayIndex < items.length && items[arrayIndex] == null) {
                arrayIndex++;
            }

            return arrayIndex < items.length && items[arrayIndex] != null;
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
            //noinspection unchecked
            return (E) subIterator.next();
        }

        @Override
        public void remove() {
            subIterator.remove();
        }
    }
}

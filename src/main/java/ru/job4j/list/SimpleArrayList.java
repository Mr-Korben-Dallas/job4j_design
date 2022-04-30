package ru.job4j.list;

import java.util.*;

public class SimpleArrayList<T> implements List<T> {
    private T[] container;

    private int size;

    private int modCount;

    public SimpleArrayList() {
        this.container = (T[]) new Object[10];
    }

    public SimpleArrayList(int capacity) {
        this.container = (T[]) new Object[capacity];
    }

    @Override
    public void add(T value) {
        if (size == container.length) {
            container = grow();
        }
        container[size] = value;
        size = size + 1;
        modCount++;
    }

    private T[] grow() {
        return Arrays.copyOf(container, (int) container.length * 2);
    }

    @Override
    public T set(int index, T newValue) {
        Objects.checkIndex(index, size);
        var temp = container[index];
        container[index] = newValue;
        modCount++;
        return temp;
    }

    @Override
    public T remove(int index) {
        Objects.checkIndex(index, size);
        var temp = container[index];
        System.arraycopy(
                container,
                index + 1,
                container,
                index,
                container.length - index - 1
        );
        size--;
        modCount++;
        return temp;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return container[index];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            final int expectedModCount = modCount;
            int point;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return point < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return container[point++];
            }
        };
    }
}
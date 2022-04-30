package ru.job4j.collection.list.linkedlist;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements List<E> {
    SimpleLinkedList.Node<E> first;
    SimpleLinkedList.Node<E> last;
    int modCount = 0;
    int size = 0;

    @Override
    public void add(E value) {
        linkLast(value);
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> f = first;
        for (int i = 0; i < index; i++) {
            f = f.next;
        }
        return f.item;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int expectedModCount = modCount;
            Node<E> f = first;

            @Override
            public boolean hasNext() {
                return f != null;
            }

            @Override
            public E next() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Node<E> temp = f;
                f = f.next;
                return temp.item;
            }
        };
    }

    private void linkLast(E e) {
        final SimpleLinkedList.Node<E> l = last;
        final SimpleLinkedList.Node<E> newNode = new SimpleLinkedList.Node<>(l, e, null);
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
        size++;
        modCount++;
    }

    private static class Node<E> {
        E item;
        SimpleLinkedList.Node<E> next;
        SimpleLinkedList.Node<E> prev;

        Node(SimpleLinkedList.Node<E> prev, E element, SimpleLinkedList.Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }
}

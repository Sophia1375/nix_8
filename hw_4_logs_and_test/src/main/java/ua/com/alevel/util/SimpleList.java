package ua.com.alevel.util;

import java.util.*;

public class SimpleList<E> implements List<E> {

    public static final int INIT_CAP = 8;
    private Object[] arr;
    private int size;

    public SimpleList() {
        arr = new Object[INIT_CAP];
        size = 0;
    }


    public SimpleList(E[] arr) {
        if (arr == null) {
            this.arr = new Object[0];
            size = 0;
        } else {
            this.arr = arr;
            size = arr.length;
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
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator<E> iterator() {

        return new Iterator<E>() {
            private int curr = 0;

            @Override
            public boolean hasNext() {
                return curr < size;
            }

            @Override
            public E next() {
                return (E) arr[curr++];
            }
        };
    }

    @Override
    public Object[] toArray() {
        return arr;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return (T[]) Arrays.copyOf(arr, size, a.getClass());
    }

    @Override
    public boolean add(E e) {
        if (size >= arr.length) {
            grow();
        }
        arr[size] = e;
        size++;
        return true;
    }

    private void grow() {
        if (arr.length < INIT_CAP) {
            arr = Arrays.copyOf(arr, INIT_CAP);
        } else {
            arr = Arrays.copyOf(arr, arr.length * 2);
        }
    }

    @Override
    public boolean remove(Object o) {
        boolean isDeleted = false;
        for (int i = 0; i < size; i++) {
            if (!isDeleted) {
                if (o.equals(arr[i])) {
                    arr[i] = null;
                    isDeleted = true;
                }
            } else {
                arr[i - 1] = arr[i];
                arr[i] = null;
            }
        }
        if (isDeleted) size--;
        return isDeleted;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override
    public E get(int index) {
        if (index > size - 1) {
            throw new RuntimeException("index more than size");
        }
        return (E) arr[index];
    }

    @Override
    public E set(int index, E element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(int index, E element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public E remove(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int indexOf(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int lastIndexOf(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator<E> listIterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }
}

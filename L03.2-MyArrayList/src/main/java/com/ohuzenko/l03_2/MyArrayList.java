package com.ohuzenko.l03_2;

import java.util.*;

public class MyArrayList<T> implements List<T> {


    final private static int DEFAULT_CAPACITY = 10_000;
    final private static int EMPTY_ARRAY_INDEX = -1;
    private T[] array;
    private int size;
    private int capacity;


    public MyArrayList() throws IllegalArgumentException {
        this(DEFAULT_CAPACITY);

    }


    public MyArrayList(final int cap) throws IllegalArgumentException {
        array = initArray(cap);
    }

    private T[] initArray(int cap) {
        if (cap < 1) {
            throw new IllegalArgumentException();
        }

        capacity = cap;
        size = EMPTY_ARRAY_INDEX;
        return (T[]) new Object[cap];
    }

    public int size() throws NullPointerException {
        if (array == null) throw new NullPointerException();
        return size + 1;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean contains(Object o) {

        return indexOf(o) >= 0;

    }

    public Iterator<T> iterator() {
        return Arrays.stream((T[]) array).iterator();
    }

    public Object[] toArray() {
        return Arrays.copyOf(array, size());
    }

    public <T> T[] toArray(T[] a) {

        if (a.length < size) {
            return (T[]) Arrays.copyOf(array, size, a.getClass());
        }
        System.arraycopy(array, 0, a, 0, size);
        if (a.length > size)
            a[size] = null;
        return a;
    }

    public boolean add(T t) {

        if (size + 1 < capacity - 1) {
            array[++size] = t;

        } else {

            T[] tmp = initArray(capacity * 2);
            System.arraycopy(array, 0, tmp, 0, size + 1);
            capacity *= 2;
            array = tmp;
            array[++size] = t;
        }
        return true;
    }


    public boolean remove(Object o) {

        int index = indexOf(o);
        if (index < 0) {
            return false;
        }

        if (index < size) {
            System.arraycopy(array, index + 1, array, index, size() - index - 1);
        }
        size--;

        return true;
    }


    public boolean containsAll(Collection<?> c) {
        return c.stream().allMatch(this::contains);
    }

    public boolean addAll(Collection<? extends T> c) {

        int srcLen = c.toArray().length;
        if (srcLen + size >= capacity) {
            initArray(capacity * 2);
        }

        System.arraycopy((T[]) c.toArray(), 0, array, size + 1, srcLen);
        size += srcLen;
        return true;
    }

    public boolean addAll(int index, Collection<? extends T> c) {
        int ind = index;

        for (T element : c) {
            add(ind, element);
            ind++;
        }

        size += c.toArray().length;
        return true;
    }

    public boolean removeAll(Collection<?> c) {
        c.forEach(this::remove);
        return true;
    }

    public boolean retainAll(Collection<?> c) {

        for (int i = 0; i < size(); ) {

            if (!c.contains(array[i])) {
                remove(array[i]);
            } else {
                i++;
            }
        }
        return true;
    }

    public void clear() {
        size = EMPTY_ARRAY_INDEX;
    }

    public T get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException();
        }
        return (T) array[index];
    }

    public T set(int index, T element) {

        checkIndexLeftBound(index);
        if (index < size()) {
            array[index] = element;
            return array[index];
        }
        return null;
    }

    public void add(int index, T element) {
        checkIndexLeftBound(index);

        if (size() >= capacity) {
            initArray(capacity * 2);
        }

        if (index <= size) {
            System.arraycopy(array, index, array, index + 1, size() - index - 1);
            array[index] = element;
            size++;
        }

    }

    public T remove(int index) {
        checkIndexLeftBound(index);
        T removed = null;
        if (index < size) {
            removed = array[index];
            System.arraycopy(array, index + 1, array, index, size() - index - 1);
        }
        size--;

        return removed;
    }

    private void checkIndexLeftBound(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("index value cannot be negative");
        }
    }

    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < size(); i++)
                if (array[i] == null)
                    return i;
        } else {
            for (int i = 0; i < size(); i++)
                if (o.equals(array[i]))
                    return i;
        }
        return -1;

    }

    public int lastIndexOf(Object o) {
        if (o == null) {
            for (int i = size()-1; i >= 0; i--)
                if (array[i] == null)
                    return i;
        } else {
            for (int i = size()-1; i >= 0; i--)
                if (o.equals(array[i]))
                    return i;
        }

        return -1;
    }

    public ListIterator<T> listIterator() {
        return Arrays.asList((T[])array).listIterator();
    }

    public ListIterator<T> listIterator(int index) {
        return Arrays.asList((T[])array).listIterator(index);
    }

    public List<T> subList(int fromIndex, int toIndex) {
        return  Arrays.asList((T[])array).subList(fromIndex, toIndex);
    }

    //do not want to spoil toString function, so this one is only to demonstrate the list state
    public void printMe(String eventDescription) {
        System.out.println(eventDescription + "inner array state: ");
        for(int i = 0; i < size(); i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println("\n-----------------------");

    }
}

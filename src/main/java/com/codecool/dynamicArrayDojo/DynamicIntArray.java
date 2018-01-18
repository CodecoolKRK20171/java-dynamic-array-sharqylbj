package com.codecool.dynamicArrayDojo;

import java.util.Arrays;

// put your code here!
public class DynamicIntArray<E> {

    private int size;

    private Object[] data;

    public DynamicIntArray(int initialCapacity) {
        if (initialCapacity < 0){
            throw new IllegalArgumentException("Array cannot be of size: " + initialCapacity);
        }

        this.data = new Object[initialCapacity];
    }

    public DynamicIntArray() {
        this(10);
    }

    private void ensureCapacity(int minCapacity) {
        if (minCapacity - data.length > 0) {
            grow(minCapacity);
        }
    }

    private void grow(int minCapacity) {
        int oldCapacity = data.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if (newCapacity - minCapacity < 0) {
            newCapacity = minCapacity;
        }
        data = Arrays.copyOf(data, newCapacity);
    }

    private void rangeCheck(int index) {
        if (index >= size) {
            throw new ArrayIndexOutOfBoundsException("Index out of bounds: " + index);
        }
    }

    public void add(E e) {
        ensureCapacity(size + 1);
        data[size++] = e;
    }

    public void insert(int index, E element) {
        if (index > size) {
            add(element);
        } else {
            ensureCapacity(size + 1);
            System.arraycopy(data, index, data, index + 1, size - index);
            size++;
            data[index] = element;
        }
    }

    public void remove(int index) {
        rangeCheck(index);
        int elementsMoved = size - index - 1;
        if (elementsMoved > 0) {
            System.arraycopy(data, index + 1, data, index, elementsMoved);
        }
        size--;
    }

    public String toString() {
        String dataString = "";
        for(int i = 0; i < size; i++){
            dataString = dataString + " " + data[i];
        }
        return dataString;
    }
}
